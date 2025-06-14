# 🛒 Shopito Backend System

A complete backend system for e-commerce management including features such as product handling, order placement, user authentication, cart management, and an admin control panel.

## 📌 Features

- JWT Authentication
- Role-Based Access Control (Admin / User)
- User Management
- Product Management (CRUD)
- Shopping Cart
- Order Placement
- Admin Dashboard for Orders & Products
- Product Reviews and Ratings
- Category Management
- File Upload for Product Images
- Swagger API Documentation
- Global Exception Handling
- Input Validation

## 🔐 Authentication & Authorization

### AuthController
- `POST /register`: Register a new user
- `POST /login`: Login and receive JWT token

## 👤 User Management

### UserController
- `GET /users/profile`: Get logged-in user's profile
- `PUT /users/profile`: Update profile information

## 📦 Product Management (Admin)

### ProductController
- `POST /admin/products`: Add new product
- `PUT /admin/products/{id}`: Update a product
- `DELETE /admin/products/{id}`: Delete a product
- `GET /products`: List all products
- `GET /products/{id}`: Get product details
- `GET /products/category/{name}`: Get products by category

## 🛒 Cart Management

### CartController
- `POST /cart/add`: Add item to cart
- `PUT /cart/update`: Update quantity in cart
- `DELETE /cart/remove/{id}`: Remove item from cart
- `GET /cart`: View cart

## 📦 Order Management

### OrderController
- `POST /orders`: Place an order
- `GET /orders`: Get all orders for logged-in user
- `GET /orders/{id}`: Get specific order details

## 🛠️ Admin Order Management

### AdminOrderController
- `GET /admin/orders`: View all orders

## 🌟 Reviews & Ratings

### ReviewController
- `POST /products/{id}/review`: Add a review to a product
- `GET /products/{id}/reviews`: View all reviews for a product

## 🗂️ Category Management (Admin)

### CategoryController
- `POST /admin/categories`: Add a new category
- `GET /categories`: Get all categories
- `DELETE /admin/categories/{id}`: Delete a category

## 💳 Payment Integration (Test Mode)

> Payment integration is implemented using a **test mode gateway** (such as Stripe test environment or a mock payment service).  
> This allows users to simulate checkout and order confirmation without real transactions.

### PaymentController
- `POST /payment/checkout` – Initiates a simulated payment and completes the order process

✅ The endpoint is fully implemented and functional in a **sandbox/testing environment**, and can be upgraded to real payment gateways like **Stripe**, **PayPal**, or **Fawry** with minor changes.


### PaymentController
- `POST /payment/checkout`: Initiate test payment

## 📸 Screenshots

You can include screenshots of your API Swagger UI, database schema.

Example:

![Swagger UI](https://github.com/mohamed12hamdy/Shopito/blob/master/swagger-images/Screenshot%20(59).png?raw=true)
![Swagger UI](https://github.com/mohamed12hamdy/Shopito/blob/master/swagger-images/Screenshot%20(60).png?raw=true)
![Swagger UI](https://github.com/mohamed12hamdy/Shopito/blob/master/swagger-images/Screenshot%20(61).png?raw=true)
![Swagger UI](https://github.com/mohamed12hamdy/Shopito/blob/master/swagger-images/Screenshot%20(62).png?raw=true)

## 🗃️ Database Schema

The following Schema shows the structure of the Shopito database, including relationships between users, products, orders, cart, and reviews.

![Database Schema](https://github.com/mohamed12hamdy/Shopito/blob/master/swagger-images/Screenshot%20(68).png?raw=true)

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/mohamed12hamdy/shopito.git
   cd ecommerce
   ```

2. **Configure the application**
   - Set up your database (PostgreSQL)
   - Configure `application.properties` with your database and email credentials.

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   ```
   http://localhost:8081/api
   ```
   
## 🛠 Technologies Used

- **Java 17** – Modern, robust backend programming language
- **Spring Boot 3** – Core framework for building the backend
- **Spring Security** – Role-based authentication and authorization
- **JWT (JSON Web Token)** – Token-based authentication system
- **Spring Data JPA** – ORM for interacting with relational databases
- **Hibernate** – JPA implementation for database operations
- **PostgreSQL** – Primary relational database (can be swapped with MySQL or H2)
- **Maven** – Build and dependency management tool
- **Lombok** – Reduces boilerplate code using annotations
- **ModelMapper** – Converts between entities and DTOs
- **Swagger / SpringDoc OpenAPI** – Interactive API documentation and testing
- **Validation API** – Input validation using `javax.validation` annotations
- **Global Exception Handling** – Clean and centralized error responses via `@ControllerAdvice`
- **Postman** – Manual API testing and collection sharing
- **DbSchema** – Visual ERD design for database schema

> 💡 All features follow clean code, layered architecture (Controller, Service, Repository), and RESTful best practices.

  
🚀 Developed by **Mohamed Hamdy**
