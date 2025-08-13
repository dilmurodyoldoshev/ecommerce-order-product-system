package uz.app.strong_junior_test_task.repository;

import uz.app.strong_junior_test_task.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(String name, String category);
    List<Product> findAllByIsActiveTrue();

}
