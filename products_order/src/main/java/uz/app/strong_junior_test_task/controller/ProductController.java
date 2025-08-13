package uz.app.strong_junior_test_task.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.app.strong_junior_test_task.dto.ProductCreateRequest;
import uz.app.strong_junior_test_task.dto.ProductResponse;
import uz.app.strong_junior_test_task.dto.ProductUpdateRequest;
import uz.app.strong_junior_test_task.service.ProductService;

import java.util.List;

@Tag(name = "Product API", description = "Mahsulotlar bilan ishlash endpointlari")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // USER va ADMIN ko‘ra oladi
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAll();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Faqat ADMIN qo‘sha oladi
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductResponse createProduct(@RequestBody @Valid ProductCreateRequest request) {
        return productService.createProduct(request);
    }

    // Faqat ADMIN o‘zgartira oladi
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,
                                         @RequestBody @Valid ProductUpdateRequest request) {
        return productService.updateProduct(id, request);
    }

    // Faqat ADMIN o‘chira oladi
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // USER va ADMIN qidirishi mumkin
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/search")
    public List<ProductResponse> searchProducts(
            @RequestParam String name,
            @RequestParam String category
    ) {
        return productService.searchProducts(name, category);
    }
}
