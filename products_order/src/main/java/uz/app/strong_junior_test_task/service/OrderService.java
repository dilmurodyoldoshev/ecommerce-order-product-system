package uz.app.strong_junior_test_task.service;

import uz.app.strong_junior_test_task.dto.CreateOrderRequest;
import uz.app.strong_junior_test_task.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getAllOrders();

    List<OrderResponse> getOrdersByCustomerEmail(String email);

    OrderResponse changeOrderStatus(Long id, String statusStr);

    void cancelOrder(Long id);
}
