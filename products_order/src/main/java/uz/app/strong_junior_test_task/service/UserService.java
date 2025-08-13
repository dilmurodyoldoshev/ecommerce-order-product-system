package uz.app.strong_junior_test_task.service;

import uz.app.strong_junior_test_task.dto.AuthRequest;
import uz.app.strong_junior_test_task.dto.AuthResponse;
import uz.app.strong_junior_test_task.entity.User;

public interface UserService {
    AuthResponse register(User user);
    AuthResponse login(AuthRequest request);
}
