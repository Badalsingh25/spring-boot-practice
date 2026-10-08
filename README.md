# ☕ Spring Boot Practice

<div align="center">

### 🚀 Java • Spring • Spring Boot • REST APIs • JPA • Spring Security

A structured collection of my **Java & Spring Boot learning journey**, containing hands-on practice projects, experiments, backend concepts, REST APIs, JPA/Hibernate implementations, dependency injection, and Spring Security applications.

<br/>

[![Java](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring](https://img.shields.io/badge/Spring-Core-green?style=for-the-badge&logo=spring)](https://spring.io/)
[![JPA](https://img.shields.io/badge/JPA-Hibernate-yellow?style=for-the-badge)](https://hibernate.org/)
[![REST API](https://img.shields.io/badge/REST-API-blue?style=for-the-badge)](https://restfulapi.net/)
[![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven)](https://maven.apache.org/)
[![GitHub](https://img.shields.io/badge/GitHub-Version%20Control-black?style=for-the-badge&logo=github)](https://github.com/)

</div>

---

## 📌 About This Repository

This repository contains my **Spring Framework and Spring Boot practice work** developed while learning backend development with Java.

Instead of keeping individual experiments scattered across different repositories, this project brings together my learning implementations in one place.

The repository covers concepts ranging from **Spring Core fundamentals and Dependency Injection** to **REST APIs, JPA/Hibernate, database relationships, and Spring Security**.

> 🎯 **Goal:** Build a strong foundation in Java backend development and progressively move toward production-level Spring Boot applications.

---

## 🧭 Learning Roadmap

```text
Java
  │
  ▼
Spring Core
  │
  ├── IoC
  ├── Dependency Injection
  ├── Beans
  ├── Component Scanning
  └── Configuration
  │
  ▼
Spring Boot
  │
  ├── Auto Configuration
  ├── Starters
  ├── Application Properties
  └── Project Structure
  │
  ▼
REST APIs
  │
  ├── Controllers
  ├── DTOs
  ├── Services
  ├── Repositories
  └── Exception Handling
  │
  ▼
JPA / Hibernate
  │
  ├── Entities
  ├── Relationships
  ├── JPQL
  ├── Queries
  └── Transactions
  │
  ▼
Spring Security
  │
  ├── Authentication
  ├── Authorization
  ├── JWT
  └── OAuth2
  │
  ▼
🚀 Production-Ready Backend Development
```

---

# 📂 Projects & Modules

## 1. 🔧 Spring Core & Dependency Injection

### `SpringBootApp1-by-tShapedSkills-DI-IOC`

Practice project focused on understanding the fundamentals of Spring's **IoC container and Dependency Injection**.

### Concepts

- Inversion of Control
- Dependency Injection
- Spring Beans
- Configuration classes
- Component-based architecture
- Interface-based dependency injection
- Service implementations

---

## 2. ⚙️ Understanding Spring Boot Internals

### `InternalWorkingofSpringBoot`

A practice project focused on understanding how Spring Boot applications are structured and how different components interact.

### Concepts

- Spring Boot application startup
- Configuration
- Service layer
- Dependency Injection
- Interface-based implementations
- Payment service abstraction
- Maven project structure

---

## 3. 🤖 Chatbot Backend

### `chatbot`

A Spring Boot based chatbot backend project.

### Concepts

- REST APIs
- Controllers
- Services
- DTOs
- WebClient
- External AI API integration
- Request/response handling

---

## 4. 🌐 REST API Practice

### `LearningSpringboot-RestApi-Revision`

A dedicated project for practicing Spring Boot REST API development.

### Concepts

- REST controllers
- HTTP methods
- Request mapping
- DTOs
- Service layer
- Repository layer
- Entity mapping
- CORS configuration
- Model mapping

---

## 5. 🌐 REST API Implementation

### `restapi`

Another REST API implementation focused on building a clean backend architecture.

### Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Concepts

- RESTful API design
- DTO pattern
- Controller-Service-Repository architecture
- JPA repositories
- Entity mapping
- Mapper configuration

---

# 🗄️ 6. JPA & Hibernate

## `jpa`

A hands-on project for learning **Spring Data JPA and Hibernate**.

### Concepts

- Entity mapping
- Primary keys
- Relationships
- One-to-One
- One-to-Many
- Many-to-One
- Repository interfaces
- JPQL
- Derived queries
- Transactions
- Database operations

### Domain

The project uses a **Hospital Management System** domain to practice real-world entity relationships.

```text
Patient
   │
   ├── Appointment
   │
   └── Insurance

Doctor
   │
   ├── Appointment
   │
   └── Department
```

---

# 🏥 7. JPA Revision

## `jpa-Revision`

An extended/revision implementation of the Hospital Management System used to strengthen JPA and Hibernate concepts.

### Includes

- Patient management
- Doctor management
- Department relationships
- Appointment management
- Insurance relationships
- DTOs
- Repository queries
- Service layer
- Database initialization
- Unit testing

---

# 🔐 8. Spring Security & Hospital Management System

## `spring-boot-data-jpa-springsecurity-hospital-management-system`

The most advanced project in this repository.

This project combines **Spring Boot + Spring Data JPA + Spring Security** to build a backend for a Hospital Management System.

### Features

- User registration
- User login
- Authentication
- Authorization
- JWT authentication
- OAuth2 integration
- Role-based permissions
- Doctor management
- Patient management
- Appointment management
- Department management
- Insurance management
- Refresh token handling
- Global exception handling
- DTO-based API responses

### Security Architecture

```text
                ┌──────────────────┐
                │      Client      │
                └────────┬─────────┘
                         │
                         ▼
                ┌──────────────────┐
                │ Spring Security  │
                └────────┬─────────┘
                         │
              ┌──────────┴──────────┐
              │                     │
              ▼                     ▼
        Authentication        Authorization
              │                     │
              ▼                     ▼
             JWT              Roles / Permissions
              │                     │
              └──────────┬──────────┘
                         ▼
                ┌──────────────────┐
                │   REST APIs      │
                └────────┬─────────┘
                         ▼
                ┌──────────────────┐
                │ Service Layer    │
                └────────┬─────────┘
                         ▼
                ┌──────────────────┐
                │ Repository/JPA   │
                └────────┬─────────┘
                         ▼
                    Database
```

---

# 🧪 Testing

Testing is included in several projects to practice validating backend functionality.

The repository contains tests for areas such as:

- Application context
- Patient operations
- Doctor operations
- Insurance operations
- Appointment relationships
- Repository operations

Testing technologies include:

- JUnit
- Spring Boot Test

---

# 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| ☕ Java | Backend programming |
| 🌱 Spring Framework | Application framework |
| 🚀 Spring Boot | Backend application development |
| 🔐 Spring Security | Authentication & authorization |
| 🗄️ Spring Data JPA | Database persistence |
| 🧬 Hibernate | ORM |
| 🌐 REST | API development |
| 📦 Maven | Dependency & build management |
| 🧪 JUnit | Testing |
| 🐬 MySQL | Relational database |
| 🔑 JWT | Token-based authentication |
| 🔗 OAuth2 | Social authentication |

---

# 🏗️ Common Backend Architecture

Most REST/JPA projects follow a layered architecture:

```text
                    Client
                      │
                      ▼
               ┌─────────────┐
               │ Controller  │
               └──────┬──────┘
                      │
                      ▼
               ┌─────────────┐
               │   Service   │
               └──────┬──────┘
                      │
                      ▼
               ┌─────────────┐
               │ Repository  │
               └──────┬──────┘
                      │
                      ▼
               ┌─────────────┐
               │  Database   │
               └─────────────┘
```

This separation helps keep the application **modular, maintainable, and easier to test**.

---

# 📁 Repository Structure

```text
spring-boot-practice/
│
├── .gitignore
│
├── AmitApplication/
│
├── InternalWorkingofSpringBoot/
│
├── LearningSpringboot-RestApi-Revision/
│
├── SpringBootApp1-by-tShapedSkills-DI-IOC/
│
├── chatbot/
│
├── demo/
│
├── first/
│
├── jpa/
│
├── jpa-Revision/
│
├── restapi/
│
├── spring-boot-data-jpa-springsecurity-hospital-management-system/
│
├── hospitaldb.mwb
└── hospitaldb.mwb.bak
```

---

# 🚀 Getting Started

## Prerequisites

Make sure you have installed:

- Java 17+
- Maven
- MySQL
- Git
- IntelliJ IDEA / Eclipse / Spring Tool Suite

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

---

## Clone the Repository

```bash
git clone https://github.com/Badalsingh25/spring-boot-practice.git
```

```bash
cd spring-boot-practice
```

---

## Run a Project

Navigate into any Spring Boot project:

```bash
cd restapi
```

Then run:

```bash
mvn spring-boot:run
```

Or use the Maven wrapper:

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

---

# 🔐 Configuration & Security

Configuration files containing local credentials are intentionally excluded from Git using `.gitignore`.

Examples:

```text
application.properties
application.yml
application.yaml
.env
```

The repository's root `.gitignore` prevents these files from being committed.

> ⚠️ Never commit database passwords, API keys, JWT secrets, OAuth client secrets, or other credentials to a public repository.

---

# 📚 What I'm Learning

This repository represents my progression through backend development:

- [x] Java fundamentals
- [x] Spring Core
- [x] IoC & Dependency Injection
- [x] Spring Boot fundamentals
- [x] REST API development
- [x] DTOs
- [x] Layered architecture
- [x] Spring Data JPA
- [x] Hibernate
- [x] Entity relationships
- [x] Database queries
- [x] Spring Security
- [x] JWT authentication
- [x] OAuth2
- [x] Role-based authorization
- [x] Unit testing
- [ ] Docker
- [ ] CI/CD
- [ ] AWS
- [ ] Redis
- [ ] Kafka
- [ ] Microservices

---

# 🎯 Future Goals

The next stage of this learning journey is to move from individual Spring Boot projects toward **production-oriented backend systems**.

Planned areas include:

```text
Spring Boot
     │
     ├── Advanced Security
     ├── Docker
     ├── CI/CD
     ├── AWS
     ├── Redis
     ├── Kafka
     ├── Microservices
     └── System Design
```

---

# 📈 Learning Philosophy

> **Learn → Build → Break → Debug → Improve → Repeat**

I use these projects to understand concepts by implementing them rather than only studying theory.

Every project in this repository represents a step toward becoming a stronger **Java & Spring Boot backend developer**.

---

# 👨‍💻 Author

<div align="center">

### Badal Singh

**Java | Spring Boot | Backend Development**

B.Tech — Computer Science & Engineering

<br/>

[![GitHub](https://img.shields.io/badge/GitHub-BadalSingh25-black?style=for-the-badge&logo=github)](https://github.com/Badalsingh25)

</div>

---

## ⭐ If this repository helps you

Feel free to explore the projects, learn from the implementations, and use them as references while learning Spring Boot.

If you find something useful, consider giving the repository a ⭐.

---

<div align="center">

### 🚀 Keep Learning. Keep Building. Keep Improving.

**Java → Spring → Spring Boot → Backend Engineering**

</div>