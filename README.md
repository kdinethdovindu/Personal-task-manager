# Personal Task Manager — Backend

A secure REST API for a full-stack Personal Task Manager built with **Java, Spring Boot, Spring Security, JWT, Spring Data JPA and PostgreSQL**.

The backend provides user authentication and user-specific task management while ensuring that users can only access their own tasks.

## Frontend

**Live Application:**  
https://personal-task-manager-frontend-six.vercel.app/register

**Frontend Repository:**  
https://github.com/kdinethdovindu/personal-task-manager-frontend

## Features

### Authentication

- User registration
- User login
- BCrypt password hashing
- JWT generation
- JWT validation
- Stateless authentication
- Protected REST endpoints
- Role-based authorization
- Invalid and expired JWT handling

### Task Management

- Create task
- Get authenticated user's tasks
- Get task by ID
- Update task
- Delete task
- Filter by status
- Filter by priority
- Pagination
- Sorting
- User ownership protection

### Validation & Error Handling

- DTO-based request validation
- Global exception handling
- Validation error responses
- `400 Bad Request`
- `401 Unauthorized`
- `403 Forbidden`
- `404 Not Found`
- `409 Conflict`

## Tech Stack

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT / JJWT
- BCrypt
- Jakarta Validation
- Maven
- Docker
- Neon PostgreSQL
- Vercel

## Architecture

```text
React Frontend
      ↓
REST API
      ↓
Spring Security / JWT
      ↓
Controllers
      ↓
Services
      ↓
Repositories
      ↓
Spring Data JPA / Hibernate
      ↓
PostgreSQL
```

## Main Entities

### User

```text
id
name
email
password
role
```

### Task

```text
id
title
description
status
priority
user
```

Relationship:

```text
User 1 ────────── * Task
```

Each task belongs to one authenticated user.

## Task Status

```text
TODO
IN_PROGRESS
DONE
```

## Task Priority

```text
LOW
MEDIUM
HIGH
```

## API Endpoints

### Authentication

| Method | Endpoint | Description | Authentication |
|---|---|---|---|
| POST | `/api/auth/register` | Register user | Public |
| POST | `/api/auth/login` | Login and receive JWT | Public |

### Tasks

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/tasks` | Create task |
| GET | `/api/tasks` | Get current user's tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| PUT | `/api/tasks/{id}` | Update task |
| DELETE | `/api/tasks/{id}` | Delete task |
| GET | `/api/tasks?status=TODO` | Filter by status |
| GET | `/api/tasks?priority=HIGH` | Filter by priority |
| GET | `/api/tasks/page?page=0&size=5&sort=id,desc` | Pagination and sorting |

Task endpoints require:

```text
Authorization: Bearer <JWT>
```

## Example Registration Request

```json
{
  "name": "Demo User",
  "email": "demo.user@example.com",
  "password": "Test1234"
}
```

## Example Login Request

```json
{
  "email": "demo.user@example.com",
  "password": "Test1234"
}
```

## Example Task Request

```json
{
  "title": "Study Spring Security",
  "description": "Review authentication, authorization, BCrypt and roles.",
  "status": "TODO",
  "priority": "HIGH"
}
```

## Getting Started

### Prerequisites

Install:

- Java 21
- PostgreSQL
- Git

Maven Wrapper is included in the repository.

### Clone repository

```bash
git clone https://github.com/kdinethdovindu/Personal-task-manager.git
cd Personal-task-manager
```

### Create PostgreSQL database

For local development, create a PostgreSQL database such as:

```text
task_manager_db
```

## Environment Variables

The application expects the following environment variables:

```env
DB_URL=jdbc:postgresql://localhost:5432/task_manager_db
DB_USERNAME=postgres
DB_PASSWORD=your_database_password

JWT_SECRET=your_base64_jwt_secret

FRONTEND_URL=http://localhost:5173
```

`PORT` is optional locally because the application defaults to port `8080`.

```env
PORT=8080
```

Do not commit real database passwords or JWT secrets to GitHub.

## Run the Backend

### Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

The API will normally run at:

```text
http://localhost:8080
```

## Security Flow

```text
User Login
    ↓
AuthenticationManager
    ↓
UserDetailsService
    ↓
BCrypt password verification
    ↓
JWT generated
    ↓
Client sends Bearer JWT
    ↓
JWT Authentication Filter
    ↓
Spring SecurityContext
    ↓
Protected Task API
```

## Task Ownership

Task operations are scoped to the currently authenticated user.

```text
User A → User A's tasks
User B → User B's tasks
```

A user cannot retrieve, update or delete another user's tasks.

## Database

### Local Development

PostgreSQL

### Production

Neon PostgreSQL

Hibernate is configured to update the schema automatically:

```properties
spring.jpa.hibernate.ddl-auto=update
```

## Deployment

The backend is containerized with Docker and deployed on **Vercel**.

Production configuration is supplied using environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET
FRONTEND_URL
PORT
```

The production database is hosted on **Neon PostgreSQL**.

## CORS

The backend allows requests from the configured frontend origin using:

```text
FRONTEND_URL
```

This allows the React application and Spring Boot API to be deployed separately while communicating securely.

## Related Repository

**React Frontend:**  
https://github.com/kdinethdovindu/personal-task-manager-frontend

## Purpose

This project was built as a practical full-stack learning project to strengthen knowledge of:

- Spring Boot
- Layered architecture
- REST APIs
- Spring Data JPA
- PostgreSQL
- DTOs and validation
- Exception handling
- Spring Security
- BCrypt
- JWT authentication
- Authorization
- CORS
- Docker
- Cloud deployment

## Author

**kdinethdovindu**

GitHub:  
https://github.com/kdinethdovindu
