package uz.app.strong_junior_test_task.service;

import uz.app.strong_junior_test_task.dto.ProductCreateRequest;
import uz.app.strong_junior_test_task.dto.ProductResponse;
import uz.app.strong_junior_test_task.dto.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();
    ProductResponse getProductById(Long id);
    ProductResponse createProduct(ProductCreateRequest request);
    ProductResponse updateProduct(Long id, ProductUpdateRequest request);
    void deleteProduct(Long id);
    List<ProductResponse> searchProducts(String name, String category);
}
