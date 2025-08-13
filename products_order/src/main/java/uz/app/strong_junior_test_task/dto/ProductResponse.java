package uz.app.strong_junior_test_task.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private String category;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
