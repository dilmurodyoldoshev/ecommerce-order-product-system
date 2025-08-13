package uz.app.strong_junior_test_task.controller;

import uz.app.strong_junior_test_task.dto.CreateOrderRequest;
import uz.app.strong_junior_test_task.dto.OrderResponse;
import uz.app.strong_junior_test_task.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Order API", description = "Buyurtmalarni boshqarish uchun endpointlar")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // USER buyurtma yaratishi mumkin
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    // ADMIN barcha buyurtmalarni ko‘ra oladi
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ADMIN ham, USER ham o‘z buyurtmasini ID bo‘yicha ko‘ra oladi (Service ichida tekshiruv bo‘lishi kerak)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // ADMIN ma’lum mijoz buyurtmalarini ko‘rishi mumkin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customer/{email}")
    public List<OrderResponse> getOrdersByCustomer(@PathVariable String email) {
        return orderService.getOrdersByCustomerEmail(email);
    }

    // ADMIN buyurtma statusini o‘zgartirishi mumkin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public OrderResponse changeStatus(@PathVariable Long id,
                                      @RequestParam String status) {
        return orderService.changeOrderStatus(id, status);
    }

    // USER faqat o‘z buyurtmasini bekor qilishi mumkin, ADMIN ham bekor qila oladi
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }
}
