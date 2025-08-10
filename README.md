# EasyLoan (Fullstack)

Contains:
- backend: Spring Boot REST API (H2 by default). Swagger UI available.
- frontend: React app that talks to backend endpoints at /api/* (proxy or run on same host).

How to run locally (simple):
1. Start backend:
   - cd backend
   - mvn spring-boot:run
2. Start frontend:
   - cd frontend
   - npm install
   - npm start

Notes:
- Backend runs on port 8080. To run frontend dev server and proxy API requests, set "proxy": "http://localhost:8080" in frontend/package.json if needed.
