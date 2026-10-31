# Todo Reactive

A simple Spring Boot project demonstrating **reactive programming** with Spring WebFlux and R2DBC (H2 in-memory database).

## Overview

This project showcases:
- **Spring WebFlux** — non-blocking, reactive web framework
- **Spring Data R2DBC** — reactive relational database access (H2 in-memory)
- **Project Reactor** — `Flux` and `Mono` for async streams
- **Functional routing** — modern Spring WebFlux endpoint style

## Tech Stack

| Component | Version |
|-----------|---------|
| Java | 21 (LTS) |
| Spring Boot | 4.1.2 |
| Spring WebFlux | 4.1.x |
| Spring Data R2DBC | 4.1.x |
| H2 Database | 2.5.x (in-memory) |
| R2DBC H2 | 1.0.x |
| Maven | 3.8+ |

## Quick Start

```bash
# Prerequisites: Java 21, Maven wrapper included
./mvnw spring-boot:run
```

The app starts on `http://localhost:8080`.

## Security

Spring Security is enabled with HTTP Basic Auth. **You must create** `src/main/resources/application.properties` with your credentials:

```properties
spring.security.user.name=your_username
spring.security.user.password=your_password

spring.r2dbc.url=r2dbc:h2:mem:///todos;DB_CLOSE_DELAY=-1;MODE=PostgreSQL
spring.r2dbc.username=user
spring.r2dbc.password=your_db_password
```

Use with curl:
```bash
curl -u your_username:your_password http://localhost:8080/todos
```

To disable security, remove or comment out the `spring.security.user.*` properties in `application.properties`.

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `GET` | `/todos` | Get all todos (returns `Flux`) |
| `GET` | `/todos/stream` | Stream todos with backpressure (Server-Sent Events) |
| `GET` | `/todos/{id}` | Get todo by ID (returns `Mono`) |
| `POST` | `/todos` | Create a new todo |
| `PUT` | `/todos/{id}` | Update todo |
| `DELETE` | `/todos/{id}` | Delete todo |

## Example Requests

```bash
# Create a todo
curl -X POST http://localhost:8080/todos \
  -u your_username:your_password \
  -H "Content-Type: application/json" \
  -d '{"title": "Learn Spring WebFlux", "completed": false}'

# Get all todos
curl -u your_username:your_password http://localhost:8080/todos

# Stream todos (SSE - demonstrates backpressure)
curl -u your_username:your_password http://localhost:8080/todos/stream

# Get single todo
curl -u your_username:your_password http://localhost:8080/todos/1

# Update todo
curl -X PUT http://localhost:8080/todos/1 \
  -u your_username:your_password \
  -H "Content-Type: application/json" \
  -d '{"title": "Learn Spring WebFlux", "completed": true}'

# Delete todo
curl -X DELETE -u your_username:your_password http://localhost:8080/todos/1
```

## Project Structure

```
src/main/java/com/prabhakar_kevat/todoreactive/
├── controller/     # REST endpoints (functional style)
├── entity/         # Domain models (R2DBC @Table)
├── repository/     # Reactive R2DBC repositories
├── service/        # Business logic
├── mapper/         # MapStruct mappers
├── model/          # DTOs
├── configuration/  # CORS, etc.
└── TodoReactiveApplication.java
```

## Database

- **H2 in-memory** via R2DBC (`r2dbc:h2:mem:///todos`)
- Auto-created on startup, destroyed on shutdown
- Console available at `/h2-console` (if enabled)

## Running Tests

```bash
./mvnw test
```

## Building

```bash
./mvnw clean package
```

Produces an executable JAR in `target/`.