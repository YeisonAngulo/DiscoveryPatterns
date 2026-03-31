# YeisonAngulo

Small Spring Boot service that creates items in memory and publishes an event to Kafka.

## Prerequisites

- Java 21
- Maven 3.9+
- Docker / Docker Compose

## Stack

- Java 21
- Spring Boot 4.0.5
- Maven
- Spring Web
- Spring Validation
- Spring for Apache Kafka
- springdoc OpenAPI / Swagger UI
- JUnit 5 / Mockito / MockMvc
- Docker Compose

## Main Configuration

Application configuration is in:

- `src/main/resources/application.yml`

Kafka configuration and their components are created when docker start:

- `docker-compose.yml`
 

## Functional Scope

The application exposes a single endpoint:

- `POST /items`

## Project Structure

This structure has been designed in Layer Architecture

```text
co.com.dp.yeisonangulo
 ├── controller
 ├── service
 ├── repository
 ├── domain
 ├── dto
 ├── kafka
 ├── config
 └── exception
```

## Design Notes

This solution intentionally keeps the implementation small but production-aware:

- **Controller** only handles HTTP concerns
- **Service** orchestrates the use case
- **Repository** encapsulates in-memory persistence
- **Kafka publisher** is abstracted behind an interface to keep the service layer decoupled from Kafka infrastructure
- **Global exception handling** is centralized with `@RestControllerAdvice`
- **Clock** is injected to keep time-related tests deterministic
- **Swagger** is enabled for quick API exploration



## Run 

The commands must be executed in your prefer Terminal in Project Root 

### Run Kafka locally

From the project root:

```bash
docker compose up -d
```

Kafka will be available at:

- `localhost:9092`

Kafka UI will be available at:

- `http://localhost:8081`

### Clean and Package the application

```bash
mvn clean package
```


### Run the application

```bash
mvn spring-boot:run
```

The API will start at:

- `http://localhost:8080`

## Swagger UI

After starting the application, open:

- `http://localhost:8080/swagger-ui.html`

You can find the Request and Responses about use from items controller

## Run tests

To use the test application you can use:

```bash
mvn test
```

## Test Coverage Included

- **Controller layer** with `MockMvc`
  - valid request
  - blank name
  - missing name
- **Service layer**
  - item creation
  - repository save invocation
  - Kafka publish invocation
- **Kafka producer logic**
  - verifies `KafkaTemplate.send(...)` invocation

## Notes

For this assignment, a **single Kafka broker** is used to keep local execution simple and fast.

In a production-grade environment, I would use:

- multiple Kafka brokers
- topic replication
- `min.insync.replicas`
- stronger producer delivery guarantees

This keeps the solution aligned with the assignment while still showing awareness of real-world reliability concerns.
