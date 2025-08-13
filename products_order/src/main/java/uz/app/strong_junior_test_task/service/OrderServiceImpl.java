package uz.app.strong_junior_test_task.service;

import uz.app.strong_junior_test_task.dto.*;
import uz.app.strong_junior_test_task.entity.*;
import uz.app.strong_junior_test_task.enums.OrderStatus;
import uz.app.strong_junior_test_task.exception.*;
import uz.app.strong_junior_test_task.repository.OrderRepository;
import uz.app.strong_junior_test_task.repository.ProductRepository;
import uz.app.strong_junior_test_task.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        // Duplicate mahsulotlarni tekshirish
        Set<Long> uniqueProductIds = new HashSet<>();
        for (OrderItemRequest item : request.getItems()) {
            if (!uniqueProductIds.add(item.getProductId())) {
                throw new IllegalArgumentException("Bir buyurtmada bir xil mahsulot ikki marta bo‘lmasligi kerak.");
            }
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Mahsulot topilmadi: " + itemRequest.getProductId()));

            validateProductAvailability(product, itemRequest.getQuantity());

            updateProductStock(product, -itemRequest.getQuantity());

            double itemTotal = product.getPrice() * itemRequest.getQuantity();

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .unitPrice(product.getPrice())
                    .totalPrice(itemTotal)
                    .build();

            orderItems.add(orderItem);
            totalAmount += itemTotal;
        }

        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .totalAmount(totalAmount)
                .orderItems(new ArrayList<>()) // vaqtinchalik
                .build();

        orderItems.forEach(item -> item.setOrder(order));
        order.setOrderItems(orderItems);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return List.of();
    }

    @Override
    public List<OrderResponse> getOrdersByCustomerEmail(String email) {
        return List.of();
    }

    @Override
    public OrderResponse changeOrderStatus(Long id, String statusStr) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        OrderStatus currentStatus = order.getStatus();
        OrderStatus newStatus;
        try {
            newStatus = OrderStatus.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidOrderStatusException("Noto‘g‘ri status nomi: " + statusStr);
        }

        if (!currentStatus.allowedNextStatuses().contains(newStatus)) {
            throw new InvalidOrderStatusException("Statusni " + currentStatus + " dan " + newStatus + " ga o‘tkazib bo‘lmaydi");
        }

        if (newStatus == OrderStatus.CONFIRMED && currentStatus != OrderStatus.CONFIRMED) {
            order.getOrderItems().forEach(item -> updateProductStock(item.getProduct(), -item.getQuantity()));
        }

        if (newStatus == OrderStatus.CANCELLED && currentStatus != OrderStatus.CANCELLED) {
            order.getOrderItems().forEach(item -> updateProductStock(item.getProduct(), item.getQuantity()));
        }

        order.setStatus(newStatus);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public void cancelOrder(Long id) {
        changeOrderStatus(id, "CANCELLED");
    }

    private void validateProductAvailability(Product product, int quantity) {
        if (!Boolean.TRUE.equals(product.getIsActive()) || product.getStock() <= 0) {
            throw new IllegalStateException("Mahsulot mavjud emas yoki aktiv emas: " + product.getName());
        }
        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Yetarli mahsulot yo‘q: " + product.getName());
        }
    }

    private void updateProductStock(Product product, int change) {
        int updatedStock = product.getStock() + change;
        product.setStock(updatedStock);
        product.setIsActive(updatedStock > 0);
        productRepository.save(product);
    }
}
