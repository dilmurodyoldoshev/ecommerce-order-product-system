package uz.app.strong_junior_test_task.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotNull
    @DecimalMin("0.0")
    private Double price;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    private Boolean isActive = true;
}
