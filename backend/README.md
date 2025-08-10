# EasyLoan Backend (Spring Boot)

Run with:
- `./mvnw spring-boot:run` (if mvnw present) or `mvn spring-boot:run`

APIs:
- /api/users (CRUD)
- /api/loans (apply, list, filter by status or userId)
- Admin: POST /api/loans/{id}/approve and /api/loans/{id}/reject

Swagger UI: http://localhost:8080/swagger-ui.html
H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:easyloandb)
