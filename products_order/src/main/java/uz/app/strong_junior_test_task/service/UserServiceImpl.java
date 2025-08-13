package uz.app.strong_junior_test_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.app.strong_junior_test_task.entity.User;
import uz.app.strong_junior_test_task.dto.AuthRequest;
import uz.app.strong_junior_test_task.dto.AuthResponse;
import uz.app.strong_junior_test_task.repository.UserRepository;
import uz.app.strong_junior_test_task.filter.JwtProvider;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return new AuthResponse(null, "Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new AuthResponse(null, "User registered successfully");
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> new AuthResponse(jwtProvider.generateToken(user.getEmail()), "Login successful"))
                .orElse(new AuthResponse(null, "Invalid email or password"));
    }
}
