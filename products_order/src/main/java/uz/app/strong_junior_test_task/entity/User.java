package uz.app.strong_junior_test_task.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.app.strong_junior_test_task.enums.Role;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}