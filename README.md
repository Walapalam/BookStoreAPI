# Bookstore API - Coursework for Client-Server Architecture Module

This project is a **Bookstore API** designed for the coursework of the **Client-Server Architecture** module. The system allows for the management of books, authors, customers, carts, and orders, with all the necessary functionality required for an online bookstore system. It utilizes a **Singleton Design Pattern** for service layer classes and custom exceptions to handle business logic and errors effectively.

## Project Overview

The project is designed to simulate a simple online bookstore. It consists of several main components:

- **Book Management**: Allows users to add, update, delete, and fetch books.
- **Author Management**: Allows users to manage authors and their books.
- **Customer Management**: Handles customer-related data, such as creating a customer profile, updating details, and retrieving customer information.
- **Cart Management**: Allows customers to manage their shopping carts by adding/removing books.
- **Order Management**: Provides functionality for creating and tracking orders based on the customer's cart.

### Technologies Used
- **Java**: Core programming language for backend implementation.
- **Singleton Design Pattern**: Used for ensuring a single instance of service classes, which manage business logic and interact with the repository.
- **Custom Exceptions**: Used to handle various error cases (e.g., `BookNotFoundException`, `OutOfStockException`, etc.).
- **Repository Pattern**: Used to manage and store data related to books, authors, customers, carts, and orders.

---

## Design and Structural Choices

### 1. **Singleton Design Pattern**

The **Singleton Design Pattern** is used for service classes (e.g., `BookService`, `AuthorService`, `CustomerService`, etc.) to ensure that there is only one instance of each service in the system. This pattern ensures that the service layer manages business logic effectively, provides centralized access, and reduces redundancy. By using the Singleton pattern, we can ensure that service layer resources are efficiently shared across the application.

- **BookService**: A singleton class that handles operations related to books (add, get, update, delete).
- **AuthorService**: A singleton class managing operations related to authors.
- **CustomerService**: A singleton class that manages customer data and related actions.
- **CartService**: A singleton class managing shopping carts for customers.
- **OrderService**: A singleton class for managing orders.

#### Why Singleton?
- It avoids the overhead of creating new instances of service classes multiple times during the application runtime.
- It provides a consistent access point to the services, ensuring a smooth flow of data through different layers.

### 2. **Repository Pattern**

The **Repository Pattern** is used to separate the logic that retrieves data from the underlying database or storage system. This pattern abstracts the data access layer and allows for easier management of data persistence and retrieval.

- **BookRepository**: Handles all interactions with the data storage for books.
- **AuthorRepository**: Manages author data.
- **CustomerRepository**: Handles the management of customer data.
- **CartRepository**: Manages shopping cart data.
- **OrderRepository**: Responsible for managing order data.

#### Why Repository?
- It centralizes all data access logic, making it easier to maintain and test.
- By abstracting data access, it makes the system more flexible, as we can change the underlying data storage mechanism without affecting the business logic.

### 3. **Custom Exceptions**

We defined several **custom exceptions** to handle different error scenarios, providing meaningful feedback to the users of the API. These exceptions are thrown in the service layer when specific error conditions are encountered.

- **BookNotFoundException**: Thrown when a book is not found in the repository.
- **AuthorNotFoundException**: Thrown when an author is not found.
- **CustomerNotFoundException**: Thrown when a customer is not found.
- **InvalidInputException**: Thrown when invalid data is provided for operations.
- **OutOfStockException**: Thrown when there are insufficient stock levels to fulfill an order.
- **CartNotFoundException**: Thrown when a customer's cart is not found.

#### Why Custom Exceptions?
- Custom exceptions provide more meaningful and specific error messages, improving error handling and debugging.
- By separating different error conditions into custom exceptions, it ensures that each exception is handled appropriately in the application.

### 4. **Service Layer**

The **Service Layer** acts as the business logic layer. The service classes encapsulate the core functionality of the bookstore application. Each service class is responsible for validating the inputs, calling the corresponding repository methods, and handling any exceptions. The service layer performs the necessary business logic (e.g., checking stock availability, validating input, etc.).

- **BookService**: Manages book-related operations.
- **AuthorService**: Handles author-related functionality.
- **CustomerService**: Manages customer operations.
- **CartService**: Manages shopping cart operations.
- **OrderService**: Handles order processing and management.

#### Why Service Layer?
- It provides a centralized place for business logic and validations, ensuring that the code is maintainable and scalable.
- The service layer is decoupled from the repository layer, allowing for easier unit testing and reusability.

### 5. **Exception Handling in Resource Layer**

Exceptions are caught in the **resource layer** (API endpoint handlers). The service layer throws custom exceptions when an error occurs, and these exceptions are caught in the resource layer (e.g., REST controllers). This allows for appropriate HTTP status codes and error messages to be returned to the client.

- **404 Not Found**: For exceptions like `BookNotFoundException`, `CartNotFoundException`, and `AuthorNotFoundException`.
- **400 Bad Request**: For `InvalidInputException` when the client sends invalid data.
- **422 Unprocessable Entity**: For `OutOfStockException` when the requested quantity is unavailable.

### 6. **Data Validation**

Data validation is implemented in the service layer, where input data is checked for null values and other constraints (e.g., book ISBN, customer email). This ensures that the system only processes valid data and prevents errors down the line.

---

## Project Structure
```src/ 
└── com/ 
  └── raqeeb/ 
    └── bookstore/ 
      └── bookstoreapi/ 
        ├── model/  
        │ ├── Book.java 
        │ ├── Author.java 
        │ ├── Customer.java 
        │ ├── Cart.java 
        │ └── Order.java 
        ├── repository/ 
        │ ├── BookRepository.java 
        │ ├── AuthorRepository.java 
        │ ├── CustomerRepository.java 
        │ ├── CartRepository.java 
        │ └── OrderRepository.java 
        ├── service/ 
        │ ├── BookService.java 
        │ ├── AuthorService.java 
        │ ├── CustomerService.java 
        │ ├── CartService.java 
        │ └── OrderService.java 
        ├── exception/ 
        │ ├── BookNotFoundException.java 
        │ ├── AuthorNotFoundException.java 
        │ ├── CustomerNotFoundException.java 
        │ ├── InvalidInputException.java 
        │ ├── OutOfStockException.java 
        │ └── CartNotFoundException.java 
        └── resources/ 
        └── application.properties
```

---

## Future Improvements

- **Database Integration**: Currently, the data is stored in-memory using HashMaps. This can be replaced with a persistent database (e.g., MySQL, MongoDB) in future versions.
- **API Security**: Implementing authentication and authorization to secure the API endpoints.
- **Testing**: Implement unit tests for service and repository layers using JUnit.

---

## Conclusion

This **Bookstore API** follows a well-structured design and architecture to implement core bookstore features. The **Singleton Design Pattern** ensures single service instances for resource management, while **Custom Exceptions** ensure meaningful error reporting. The **Repository Pattern** separates data storage logic, and the **Service Layer** encapsulates all business logic. 

This project demonstrates fundamental principles of **Client-Server Architecture** and is scalable, maintainable, and extendable for further development.

