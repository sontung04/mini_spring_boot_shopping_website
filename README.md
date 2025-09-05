# 🛒 Spring Boot REST API – E-commerce Backend

## 📌 Introduction
This is a **Backend REST API** application built with **Java Spring Boot** providing the following main features:
- **Authentication & Authorization** (JWT): Register, login, user roles (`USER` and `ADMIN`).
- **Product Management (Product CRUD)**
- **Order Management (Order CRUD)**

The application follows a **layered architecture** (Controller → Service → Repository → Entity) and complies with **RESTful API** standards.

---

## ⚙️ Technologies Used
- **Java 24**  
- **Spring Boot 3.x**  
  - Spring Web  
  - Spring Data JPA  
  - Spring Security (JWT)  
- **MySQL** (database)  
- **Maven** (dependency management)  
- **Postman** (API testing)  

---

## 🚀 Installation & Run

### 1. System Requirements
- JDK 24+  
- Maven 3.5+  
- MySQL 8+  

### 2. Database Configuration
Create a MySQL database named `ecommerce_db`.  
Update the file `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
jwt.secret=your_secret_key
jwt.expiration=3600000
```

### 3. Run Application
```bash
mvn spring-boot:run
```

Application will be available at: `http://localhost:8080`

---

## 📡 Main API Endpoints

### 🔑 Authentication
- `POST /api/auth/register` → Register a new user  
- `POST /api/auth/login` → Login and receive JWT Token  

### 📦 Product
- `POST /products` → Create new product  
- `GET /products` → Get all products  
- `GET /products/{id}` → Get product details  
- `GET /products/title/{name}` → Find product by name
- `PUT /products/{id}` → Update product  
- `DELETE /products/{id}` → Delete product  

### 📝 Order
- `POST /orders` → Create new order  
- `GET /orders` → Get all orders
- `GET /orders/{id}` → Get order details  
- `GET /orders/user/{id}` → Find orders by user ID
- `DELETE /orders/{id}` → Cancel order  

---

## ✅ Future Improvements
- Add **pagination & search** functionality for products  
- Integrate **Swagger/OpenAPI** for API documentation  
- Write **unit tests & integration tests** with JUnit/Mockito  
- Add **React** for front-end
- Deploy the application to **Docker / Heroku / AWS**  
