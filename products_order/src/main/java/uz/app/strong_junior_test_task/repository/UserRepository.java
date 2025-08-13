package uz.app.strong_junior_test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.strong_junior_test_task.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
