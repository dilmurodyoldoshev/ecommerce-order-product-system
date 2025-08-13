package uz.app.strong_junior_test_task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Keyinchalik boshqa exceptionlar qo‘shamiz
}
