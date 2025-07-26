# Bookstore API

A simple RESTful API built with Spring Boot for managing Books and Authors, using an in-memory H2 database.

---

## Features

- **CRUD operations for Authors**
  - Create, read, update, and delete authors
  - View all authors or fetch by ID

- **CRUD operations for Books**
  - Create, read, update, and delete books
  - Pagination and sorting support for listing books
  - Associate books with authors
  - Update book's author separately

- **In-memory H2 database**
  - Auto schema generation and updates
  - H2 console for database inspection

- **Error handling**
  - Proper HTTP status codes for resource not found
  - Basic runtime exceptions for invalid IDs

- **Logging**
  - SQL queries output to console for debugging

---

## Tools & Technologies

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (in-memory)
- Maven or Gradle (build tool)
- Postman (API testing)
- IDE (IntelliJ IDEA, Eclipse, VSCode, etc.)

---

## Getting Started

### Prerequisites

- Java JDK 17 or higher installed
- Maven or Gradle installed
- Postman installed (optional, for testing)
- Git installed (optional)

### Clone the repository

```bash
git clone <repository-url>
cd bookstore
