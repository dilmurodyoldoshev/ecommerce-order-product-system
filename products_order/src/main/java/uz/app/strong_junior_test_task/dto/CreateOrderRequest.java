package uz.app.strong_junior_test_task.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    // Bu maydonlar endi token orqali olinadi, foydalanuvchi kiritmaydi
    private String customerName;
    private String customerEmail;

    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemRequest> items;
}
