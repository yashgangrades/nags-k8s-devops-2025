# NAGP 2025 - Advanced DevOps Assignment

A Spring Boot microservice for managing user records with full CRUD operations, built as part of the NAGP 2025 assignment.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Quick Links](#quick-links)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Local Development](#local-development)
  - [Docker Deployment](#docker-deployment)
  - [Kubernetes Deployment](#kubernetes-deployment)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Configuration](#configuration)
- [Monitoring](#monitoring)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [Author](#author)

## 🔍 Overview

This microservice provides a REST API for managing user records with complete CRUD (Create, Read, Update, Delete) operations. It's designed as a cloud-native application with Docker containerization and Kubernetes deployment support.

## ✨ Features

- **Full CRUD Operations**: Create, Read, Update, and Delete user records
- **Input Validation**: Comprehensive validation for all user inputs
- **API Documentation**: Integrated Swagger/OpenAPI documentation
- **Health Monitoring**: Spring Boot Actuator endpoints for health checks
- **Database Integration**: PostgreSQL database with JPA/Hibernate
- **Containerized**: Docker support for easy deployment
- **Cloud-Ready**: Kubernetes manifests for orchestration
- **Audit Trail**: JPA auditing for tracking entity changes

## 🛠 Technology Stack

- **Framework**: Spring Boot 3.5.4
- **Language**: Java 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA with Hibernate
- **Validation**: Jakarta Bean Validation
- **Documentation**: Swagger/OpenAPI 3
- **Build Tool**: Maven
- **Containerization**: Docker
- **Orchestration**: Kubernetes
- **Monitoring**: Spring Boot Actuator

## 🔗 Quick Links

### 📁 Repository
- **Source Code**: [GitHub Repository](https://github.com/yashgangrades/nags-k8s-devops-2025.git)

### 🐳 Docker Hub
- **Docker Image**: [Docker Hub - userrecord-service](https://hub.docker.com/r/oreilleewwew/user-record-ms)
- **Pull Command**: 
  ```bash
  docker pull oreilleewwew/user-record-ms:latest
  ```

### 🌐 Live Service URLs
- **API Base URL**: `http://k8s-default-userreco-50ea7e1430-2080954465.us-east-1.elb.amazonaws.com`
- **Swagger Documentation**: `http://k8s-default-userreco-50ea7e1430-2080954465.us-east-1.elb.amazonaws.com/swagger-ui/index.html`
- **Health Check**: `http://k8s-default-userreco-50ea7e1430-2080954465.us-east-1.elb.amazonaws.com/actuator/health`
- **Service Status**: `http://k8s-default-userreco-50ea7e1430-2080954465.us-east-1.elb.amazonaws.com/actuator/info`

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- Docker (for containerization)
- Kubernetes cluster (for K8s deployment)
- PostgreSQL database

## 🚀 Getting Started

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/yashgangrades/nags-k8s-devops-2025.git
   cd assignment
   ```

2. **Set up PostgreSQL database (to run locally)**
   ```bash
   # Create a PostgreSQL database named 'user_db'
   createdb user_db
   ```

3. **Configure environment variables**
   ```bash
   export SPRING_APP_NAME=User Management Service
   export DATABASE_HOST=localhost
   export DATABASE_PORT=5432
   export DATABASE_NAME=user_db
   export DATABASE_USERNAME=<your_username>
   export DATABASE_PASSWORD=<your_password>
   export JPA_SHOW_SQL=true
   export HIBERNATE_FORMAT_SQL=true
   export SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE=10
   export SPRING_DATASOURCE_HIKARI_MINIMUM_IDLE=2
   export SPRING_DATASOURCE_HIKARI_IDLE_TIMEOUT=600000
   export SPRING_DATASOURCE_HIKARI_CONNECTION_TIMEOUT=30000
   ```

4. **Build and run the application**
   ```bash
   ./mvnw clean package
   ./mvnw spring-boot:run
   ```

5. **Access the application**
   - API Base URL: `http://localhost:9090`
   - Swagger UI: `http://localhost:9090/swagger-ui.html`
   - Actuator Health: `http://localhost:9090/actuator/health`

### Docker Deployment

1. **Using Docker Hub Image** (recommended)
   ```bash
   # Pull the pre-built image from Docker Hub
   docker pull oreilleewwew/user-record-ms:latest
   
   # Run the container
   docker run -d \
     --name user-record-service \
     -p 9090:9090 \
     -e DATABASE_HOST=your-db-host \
     -e DATABASE_USERNAME=your-username \
     -e DATABASE_PASSWORD=your-password \
     <your-username>/user-record-ms:latest
   ```

2. **Build Docker image locally**
   ```bash
   docker build -t user-record-ms:latest .
   ```

3. **Run with Docker Compose** (recommended for local development)
   ```bash
   # Create docker-compose.yml with PostgreSQL and application services
   docker-compose up -d
   ```

4. **Push to Docker Hub** (for maintainers)
   ```bash
   # Tag the image
   docker tag user-record-ms:latest your-username/user-record-ms:latest
   
   # Push to Docker Hub
   docker push your-username/user-record-ms:latest
   ```

### Kubernetes Deployment

1. **Apply Kubernetes manifests**
   ```bash
   # Apply in the following order:
   kubectl apply -f k8s/secrets.yaml
   kubectl apply -f k8s/configmap.yaml
   kubectl apply -f k8s/db-init-configmap.yaml
   kubectl apply -f k8s/postgres-pv.yaml
   kubectl apply -f k8s/postgres-pvc.yaml
   kubectl apply -f k8s/deployment-db.yaml
   kubectl apply -f k8s/service-db.yaml
   kubectl apply -f k8s/deployment-backend.yaml
   kubectl apply -f k8s/service-backend.yaml
   kubectl apply -f k8s/ingress.yaml
   ```

2. **Verify deployment**
   ```bash
   kubectl get pods
   kubectl get services
   ```

## 📚 API Documentation

### Base URL
```
http://localhost:9090/records
```

### Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/create` | Create a new user record | UserRecordDto |
| GET | `/fetch` | Fetch user by mobile number | Query param: mobileNumber |
| PUT | `/update` | Update existing user record | UserRecordDto |
| DELETE | `/delete` | Delete user by mobile number | Query param: mobileNumber |

### User Record Schema

```json
{
  "name": "Alice Johnson",
  "description": "Senior Software Engineer - Java + Kubernetes",
  "email": "alice.johnson@example.com",
  "mobileNumber": "9876543210"
}
```

### Validation Rules

- **Name**: 3-50 characters, required
- **Description**: 10-100 characters, required
- **Email**: Valid email format, required
- **Mobile Number**: Exactly 10 digits, required

### Example API Calls

**Create User Record:**
```bash
curl -X POST http://localhost:9090/records/create \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "description": "Full Stack Developer with 5 years experience",
    "email": "john.doe@example.com",
    "mobileNumber": "1234567890"
  }'
```

**Fetch User Record:**
```bash
curl -X GET "http://localhost:9090/records/fetch?mobileNumber=1234567890"
```

**Update User Record:**
```bash
curl -X PUT http://localhost:9090/records/update \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Smith",
    "description": "Senior Full Stack Developer with 6 years experience",
    "email": "john.smith@example.com",
    "mobileNumber": "1234567890"
  }'
```

**Delete User Record:**
```bash
curl -X DELETE "http://localhost:9090/records/delete?mobileNumber=1234567890"
```

## 🗄 Database Schema

### User Record Table

```sql
CREATE TABLE user_record (
    user_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(10) NOT NULL UNIQUE,
    created_at TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP,
    updated_by VARCHAR(255)
);
```

## ⚙️ Configuration

The application uses environment variables for configuration. Key configurations include:

- **Database Configuration**: Connection details, pooling settings
- **JPA Settings**: SQL logging, dialect configuration
- **Actuator**: Health checks and monitoring endpoints
- **Application Settings**: Port, name, and custom properties

## 📊 Monitoring

### Health Checks

- **Liveness Probe**: `/actuator/health/liveness`
- **Readiness Probe**: `/actuator/health/readiness`
- **General Health**: `/actuator/health`

### Available Actuator Endpoints

- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics
- `/actuator/env` - Environment properties
- `/actuator/loggers` - Logger configuration


## 📁 Project Structure

```
assignment/
├── src/
│   ├── main/
│   │   ├── java/com/nagp/advancedevops/assignment/
│   │   │   ├── controller/          # REST controllers
│   │   │   ├── service/             # Business logic
│   │   │   ├── repository/          # Data access layer
│   │   │   ├── entity/              # JPA entities
│   │   │   ├── dto/                 # Data transfer objects
│   │   │   ├── mapper/              # Entity-DTO mappers
│   │   │   ├── exception/           # Custom exceptions
│   │   │   ├── audit/               # JPA auditing
│   │   │   └── constants/           # Application constants
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # Test classes
├── k8s/                            # Kubernetes manifests
├── Dockerfile                      # Docker configuration
├── pom.xml                        # Maven configuration
└── README.md
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## 👨‍💻 Author

**Yash Gangrade**
- Email: yash.gangrade@nagarro.com
- Organization: Nagarro
- Project: NAGP 2025 Advanced DevOps Assignment

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](https://www.apache.org/licenses/LICENSE-2.0.html) file for details.

---

For detailed API documentation, visit the Swagger UI at `http://k8s-default-userreco-50ea7e1430-2080954465.us-east-1.elb.amazonaws.com/swagger-ui/index.html` when the application is running.