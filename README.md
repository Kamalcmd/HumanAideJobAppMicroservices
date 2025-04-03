
# HumanAIde Job Application Portal

A microservices-based job application platform built with **Spring Boot**, providing APIs for managing **Companies**, **Jobs**, and **Reviews**. The system is orchestrated using **Eureka Service Registry**, **Spring Cloud Gateway**, and a **Centralized Config Server**.

---

## 🧠 Overview

This platform allows users to:
- **Manage companies** (add, update, delete, fetch)
- **Manage job listings** linked to companies
- **Write and manage reviews** for companies
- Use **service discovery** and **gateway routing** for scalable microservice communication
- Fetch **enriched job details** including associated company and reviews

---

## 🧩 Architecture

```
+-------------------+
|  Config Server    |
+--------+----------+
         |
         v
+--------+----------+
|  Eureka Server    |
+--------+----------+
         |
         v
+--------+-----------+       +-------------------+
| Spring Cloud Gateway +<--->+  External Clients |
+--------+-----------+       +-------------------+
         |
         +--> /companies ---> Company Service
         |
         +--> /jobs ---------> Job Service
         |
         +--> /reviews ------> Review Service
```

---

## 🔧 Technologies Used

- **Java 17+**
- **Spring Boot 3+**
- **Spring Cloud Gateway**
- **Spring Cloud Eureka (Netflix Eureka)**
- **Spring Cloud Config**
- **OpenFeign**
- **Resilience4j (RateLimiter, CircuitBreaker)**
- **JPA (with H2/MySQL)**
- **REST APIs**

---

## 🗂️ Microservices

### 1. **Company Service**
- `GET /companies` - Get all companies
- `GET /companies/{id}` - Get a company by ID
- `POST /companies` - Create a new company
- `PUT /companies/{id}` - Update company
- `DELETE /companies/{id}` - Delete company

---

### 2. **Job Service**
- `GET /jobs` - List all jobs (includes company & reviews)
- `GET /jobs/{id}` - Get job by ID with enriched data
- `POST /jobs` - Add a new job
- `PUT /jobs/{id}` - Update a job
- `DELETE /jobs/{id}` - Delete a job

*Uses Feign clients to communicate with Company and Review services.*

---

### 3. **Review Service**
- `GET /reviews?companyId={id}` - List all reviews for a company
- `GET /reviews/{reviewId}` - Get a review by ID
- `POST /reviews?companyId={id}` - Add review
- `PUT /reviews/{reviewId}` - Update review
- `DELETE /reviews/{reviewId}` - Delete review

---

### 4. **Gateway Service**
Acts as the unified entry point to all services. It routes requests to:
- `/companies` → Company Service
- `/jobs` → Job Service
- `/reviews` → Review Service

---

### 5. **Service Registry (Eureka)**
All services register themselves here for dynamic discovery.

---

### 6. **Config Server**
Centralized management of all service configurations using Git-based config repo (to be configured).

---

## ⚙️ Setup Instructions

### Prerequisites

- Java 17+
- Maven
- Git
- (Optional) Docker / MySQL

---

### Run the services

1. **Start Config Server**
```bash
cd configserver
mvn spring-boot:run
```

2. **Start Eureka Server**
```bash
cd service-reg
mvn spring-boot:run
```

3. **Start Gateway**
```bash
cd gateway
mvn spring-boot:run
```

4. **Start Company, Job, and Review Services**
```bash
cd companyms
mvn spring-boot:run

cd jobapp
mvn spring-boot:run

cd reviewms
mvn spring-boot:run
```

> 🔁 Ensure all services are registered with Eureka and the gateway routes are configured properly in application.yml.

---

## 📥 Sample API Payloads

### ➕ Create Company
`POST /companies`
```json
{
  "name": "OpenAI",
  "description": "AI research and deployment company"
}
```

---

### ➕ Create Job
`POST /jobs`
```json
{
  "title": "Software Engineer",
  "description": "Develop AI models",
  "minSalary": "120000",
  "maxSalary": "160000",
  "location": "Remote",
  "companyId": 1
}
```

---

### ➕ Add Review
`POST /reviews?companyId=1`
```json
{
  "title": "Innovative workplace",
  "description": "Great culture and tech focus",
  "rating": 4.8
}
```

---

## 🧪 Sample Endpoints to Test

- `GET http://localhost:8080/companies`
- `GET http://localhost:8080/jobs`
- `GET http://localhost:8080/jobs/1`
- `GET http://localhost:8080/reviews?companyId=1`

> The gateway (`localhost:8080`) routes to appropriate services.

---

## 🛡️ Fault Tolerance

JobService uses `Resilience4j` for:
- **Rate Limiting**: Prevents overloading company/review APIs
- **Circuit Breaking**: Prevents cascading failures

---

## 📁 Future Improvements

- Swagger UI for API documentation
- JWT-based Authentication
- Docker Compose setup for easy local deployment
- Kubernetes deployment
- Config Git repository setup

---

## 👥 Contributors

Created as part of the **HumanAIdeJobApp** microservices project.

---

## 📜 License

Licensed under MIT. Feel free to use and contribute.
