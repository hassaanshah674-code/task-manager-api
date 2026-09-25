# Task Manager API

This is a backend task management project I built using Java and Spring Boot.

I started this project to get more confident with backend development and understand how different parts of a Spring Boot application work together, instead of only following tutorials.

The project started as a simple CRUD API and I gradually added PostgreSQL, DTOs, validation, exception handling, Flyway migrations, Swagger documentation and some basic testing.

## What the project can do

* Create a task
* Get all tasks
* Get a task by ID
* Update a task
* Delete a task
* Store tasks in PostgreSQL
* Validate incoming data
* Return useful error messages
* Use DTOs for request and response data
* Manage database changes with Flyway
* Test endpoints using Swagger
* Run basic unit tests

## Tech I used

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Hibernate
* Flyway
* Maven
* Swagger / OpenAPI
* JUnit
* Mockito

## Project structure

I separated the project into different layers:

```text
Controller
↓
Service
↓
Repository
↓
PostgreSQL
```

I also added DTOs so the API does not directly use the database entity for everything.

```text
TaskRequest  -> data coming into the API
Task         -> database entity
TaskMapper   -> converts Task into TaskResponse
TaskResponse -> data sent back from the API
```

## Endpoints

| Method | Endpoint          | What it does  |
| ------ | ----------------- | ------------- |
| GET    | `/api/tasks`      | Get all tasks |
| GET    | `/api/tasks/{id}` | Get one task  |
| POST   | `/api/tasks`      | Create a task |
| PUT    | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |

## Example task

```json
{
  "title": "Learn Spring Boot",
  "description": "Continue working on backend project",
  "completed": false
}
```

## Error handling

I created a custom `TaskNotFoundException` and a global exception handler.

For example, if a task does not exist, the API can return:

```json
{
  "status": 404,
  "message": "Task with id 10 not found"
}
```

Validation errors return a `400 Bad Request`.

## Database

The project uses PostgreSQL.

I first used Hibernate to create and update the database automatically, but later changed the project to use Flyway for database migrations.

Hibernate is now set to:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

Flyway handles schema changes and Hibernate checks that the database matches the Java entity.

The PostgreSQL password is also stored using an environment variable instead of being written directly in the project.

## Swagger

Swagger UI can be used to view and test the API while the application is running.

```text
http://localhost:8080/swagger-ui/index.html
```

## Testing

I added a few basic service tests using JUnit and Mockito.

At the moment I test:

* returning a task when the ID exists
* throwing `TaskNotFoundException` when the ID does not exist

I am still learning automated testing, so I kept the tests simple for now.

## What I learned

This project helped me understand:

* how REST APIs work
* how Controller, Service and Repository layers work together
* dependency injection
* how Spring Data JPA works with PostgreSQL
* why DTOs are useful
* validation
* exception handling
* HTTP status codes
* database migrations with Flyway
* environment variables
* Swagger
* basic unit testing

I plan to keep improving the project as I learn more backend development.
