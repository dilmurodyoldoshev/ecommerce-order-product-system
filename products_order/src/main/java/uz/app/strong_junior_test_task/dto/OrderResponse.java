package uz.app.strong_junior_test_task.dto;

import uz.app.strong_junior_test_task.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String customerName;
    private String customerEmail;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Double totalAmount;
    private List<OrderItemResponse> items;
}
