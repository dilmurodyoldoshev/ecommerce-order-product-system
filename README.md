# E-commerce Order Management System

This is a Spring Boot-based RESTful application designed for managing products and customer orders in a simple e-commerce system.

## ✅ Features

- 📦 Product management (view product list)
- 🛒 Order creation with stock validation
- 🔄 Order status update (PENDING ➡ CONFIRMED ➡ DELIVERED / CANCELLED)
- 📉 Product stock management (decrease/increase)
- ✅ Email format validation
- 🚫 Duplicate product check in orders
- 📊 Swagger UI for API testing

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger/OpenAPI (springdoc)
- Docker (optional)
- Lombok

## 📦 API Endpoints (examples)

| Method | Path             | Description                  |
|--------|------------------|------------------------------|
| GET    | `/api/products`  | Get all products             |
| POST   | `/api/orders`    | Create new order             |
| PUT    | `/api/orders/{id}/status?status=CONFIRMED` | Update order status |

## 📚 Swagger

Access API documentation at:

http://localhost:8080/swagger-ui/index.html
