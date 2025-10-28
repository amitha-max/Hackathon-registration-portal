Hackathon Registration Portal
A RESTful Spring Boot web application for managing hackathon event registration, tracking, and participant management.

Features
User registration and management
Hackathon event creation and management
Registration with capacity limits and waitlist functionality
Registration status tracking (Registered/Cancelled/Waitlisted)
Email uniqueness validation
Technology Stack
Spring Boot 3.5.7
Spring Web
Spring Data JPA
MySQL Database
Lombok
Bean Validation
Database Setup
Create a MySQL database named hackathon_portal
Update database credentials in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/hackathon_portal
spring.datasource.username=your_username
spring.datasource.password=your_password
API Endpoints
Users
POST /api/users - Add a new student
GET /api/users - View all students
GET /api/users/{id} - View user by ID
Hackathons
POST /api/hackathons - Create a new hackathon
GET /api/hackathons - View all hackathons
GET /api/hackathons/{id} - View hackathon by ID
Registrations
POST /api/registrations - Register a student for a hackathon
GET /api/registrations/user/{id} - View hackathons registered by a user
GET /api/registrations/hackathon/{id} - View participants of a hackathon
PUT /api/registrations/{id}/cancel - Cancel a registration
Sample API Requests
Create User
POST /api/users
{
    "name": "John Doe",
    "email": "john@example.com",
    "contactNo": "1234567890"
}
Create Hackathon
POST /api/hackathons
{
    "title": "AI Innovation Challenge",
    "theme": "Artificial Intelligence",
    "date": "2024-03-15",
    "maxParticipants": 100
}
Register User
POST /api/registrations
{
    "userId": 1,
    "hackathonId": 1
}
Running the Application
Clone the repository
Configure database settings in application.properties
Run mvn spring-boot:run
Access the API at http://localhost:8080
Database Schema
Users Table
user_id (PK)
name
email (unique)
contact_no
join_date
Hackathons Table
hackathon_id (PK)
title
theme
date
max_participants
created_at
Registrations Table
registration_id (PK)
user_id (FK)
hackathon_id (FK)
registration_date
status (REGISTERED/CANCELLED/WAITLISTED)
