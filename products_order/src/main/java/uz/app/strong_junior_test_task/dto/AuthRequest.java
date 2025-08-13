package uz.app.strong_junior_test_task.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
