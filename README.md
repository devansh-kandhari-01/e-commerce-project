# E-Commerce Backend (Spring Boot)

This project is a backend application I built to understand how real-world e-commerce systems manage products.
It focuses on building clean REST APIs using **Spring Boot**, handling data with **JPA + MySQL**, and managing things like validation, exceptions, and image uploads properly.

The idea behind this project was not just to make APIs work, but to structure the code in a clean and maintainable way — similar to how production-level backend systems are designed.

---

## What this project does

* Create, update, delete, and fetch products
* Search products using keywords
* Upload and retrieve product images
* Validate incoming data before saving
* Handle errors in a clean and consistent way
* Test APIs easily using Swagger UI

---

## Tech Stack

* Java 17
* Spring Boot 3
* Spring Data JPA (Hibernate)
* MySQL
* Lombok
* Swagger (OpenAPI)
* Maven

---

## Project Structure

```
com.companyname.ecommerce

├── controller
│   └── ProductController
│
├── service
│   └── ProductService
│
├── repository
│   └── ProductRepository
│
├── model
│   └── Product
│
├── exception
│   ├── GlobalExceptionHandler
│   ├── ProductNotFoundException
│   └── ProductImgNotFoundException
│
└── ECommerceApplication
```

The project follows a **layered architecture**, where:

* Controller → handles HTTP requests
* Service → contains business logic
* Repository → interacts with database
* Model → represents database entity
* Exception → handles all errors centrally

---

## How to run this project

### 1. Clone the repository

```bash
git clone https://github.com/devansh-kandhari-01/e-commerce-project.git
cd e-commerce-project
```

---

### 2. Set environment variables

Instead of hardcoding credentials, I used environment variables:

```bash
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
```

---

### 3. Create database

```sql
CREATE DATABASE E_Commerce_Product_DB;
```

---

### 4. Run the application

```bash
mvn spring-boot:run
```

---

## API Endpoints

| Method | Endpoint                    | Description                               |
| ------ |-----------------------------| ----------------------------------------- |
| GET    | `/products`                 | Get all products                          |
| GET    | `/products/{productId}`     | Get product by ID                         |
| GET    | `/products/search?keyword=` | Search products                           |
| GET    | `/products/{productId}/img` | Get product image                         |
| POST   | `/products`                 | Add new product                           |
| PUT    | `/products/{productId}`     | Update product (partial update supported) |
| PUT    | `/products/{productId}/img` | Update product image                      |
| DELETE | `/products/{productId}`     | Delete product                            |

---

## Image Handling

This project supports storing product images directly in the database.

* Image is stored as `byte[]`
* Metadata like type and name is also stored
* If image is missing → custom exception is thrown

This helped me understand how file handling works in backend systems.

---

## Validation

Before saving data, the following validations are applied:

* `productName`, `productDesc`, `productBrand` → must not be blank (`@NotBlank`)
* `productPrice` → must not be null and must be greater than 0 (`@NotNull`, `@Min(1)`)
* `productQuantity` → must not be null and cannot be negative (`@NotNull`, `@Min(0)`)
* `productReleaseDate` → cannot be null (`@NotNull`)
* `productAvailability` → must be provided (`@NotNull`)

---

## Exception Handling

All exceptions are handled centrally using `@RestControllerAdvice`.

Some examples:

* Product not found → returns `404 NOT FOUND`
* Product image not found → custom error message
* Validation errors → returns list of messages
* File upload issues → handled using `IOException`
* Unknown errors → fallback handler

---

## Interesting Implementation Detail

While updating a product, I didn’t overwrite everything blindly.

Instead, I check each field:

* Only update fields that are not null
* This allows **partial updates**

This is closer to how real APIs behave.

---

## Swagger UI

You can test all APIs from your browser:

```
http://localhost:8080/swagger-ui.html
```

No need for Postman — everything is interactive.

---

## Environment Variables

Database credentials are not hardcoded:

```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

This makes the project safer and closer to production practices.

---

## About Me

**Devansh Kandhari**
GitHub: https://github.com/devansh-kandhari-01
