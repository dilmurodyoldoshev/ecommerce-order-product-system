# E-commerce Order Management System

This is a Spring Boot-based RESTful application designed for managing products and customer orders in a simple e-commerce system. The project includes **authentication and authorization** features.

## ✅ Features

- 📦 Product management (view, add, update, delete products)
- 🛒 Order creation with stock validation
- 🔄 Order status update (PENDING ➡ CONFIRMED ➡ DELIVERED / CANCELLED)
- 📉 Product stock management (increase/decrease)
- ✅ Email format validation
- 🚫 Duplicate product check in orders
- 🔒 Authentication and Authorization (Spring Security)
  - `/auth/register` - user registration
  - `/auth/login` - user login
  - Only **admin** can add/update/delete products
  - Buyers can only view products and create orders
- 📊 Swagger UI for API testing

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- PostgreSQL
- Swagger/OpenAPI (springdoc)
- Docker (optional)
- Lombok

## 📦 API Endpoints

### Product API

| Method | Path                         | Description                        |
|--------|------------------------------|------------------------------------|
| GET    | `/api/products`              | Get all products                   |
| GET    | `/api/products/{id}`         | Get product by ID                  |
| GET    | `/api/products/search`       | Search products by keyword         |
| POST   | `/api/products`              | Add new product (**Admin only**)   |
| PUT    | `/api/products/{id}`         | Update product (**Admin only**)    |
| DELETE | `/api/products/{id}`         | Delete product (**Admin only**)    |

### Order API

| Method | Path                             | Description                        |
|--------|---------------------------------|------------------------------------|
| GET    | `/api/orders`                    | Get all orders                     |
| GET    | `/api/orders/{id}`               | Get order by ID                    |
| GET    | `/api/orders/customer/{email}`   | Get orders for a specific customer |
| POST   | `/api/orders`                    | Create new order                   |
| PUT    | `/api/orders/{id}/status`        | Update order status                |
| DELETE | `/api/orders/{id}`               | Delete order                       |

### Auth API

| Method | Path              | Description            |
|--------|-----------------|------------------------|
| POST   | `/auth/register` | User registration      |
| POST   | `/auth/login`    | User login (JWT token) |

## 📚 Swagger

Access API documentation at:

[http://localhost:8080/ecommerce/swagger-ui/index.html](http://localhost:8080/ecommerce/swagger-ui/index.html)
