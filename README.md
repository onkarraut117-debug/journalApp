# Journal App 📝

A Spring Boot application with:
- User authentication (Spring Security 6, JWT)
- MongoDB Atlas for persistence
- Redis caching
- Kafka integration (optional for async events)
- Email notifications
- Scheduled tasks

## Features
- User registration & login with JWT authentication
- Create, read, update, and delete journal entries
- Weather API integration
- Redis caching
- Kafka for event streaming (optional)

## Tech Stack
- **Spring Boot 3**
- **Spring Security 6 (JWT)**
- **MongoDB Atlas**
- **Redis**
- **Kafka**
- **Java 17+**

## Running the App
```bash
# Clone repository
git clone https://github.com/<your-username>/journalApp.git
cd journalApp

# Build & run
./mvnw spring-boot:run
