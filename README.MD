## Project Description

This backend is built for a simple e-commerce application using **Spring Boot** and **Spring Security**. The application implements **role-based access control**, where users with the **USER** role can view product content, while users with the **ADMIN** role can add and manage products in the database. Authentication and authorization are handled securely using **Spring Security**.


1 Install Java JDK

Download and install Java JDK 17 or higher.

Recommended version: JDK 17

java  --version

2 Install Docker

Download Docker Desktop:

docker --version

3 Install Docker Compose

version must be higher than equal to 2


Database Setup Using Docker

The database runs inside a Docker container using docker-compose.

Step 1 Navigate to project folder
cd Assignment

Step 2 Run database container
docker compose up -d

Running the Spring Boot Application (Using JAR)
Step 1 Build the project

If using Maven:

mvn clean package

The jar file will be generated in:

target/

Example:

target/app.jar


Step 2 Run the application
java -jar target/app.jar

Application will start at:

http://localhost:8080


Stop the Database

To stop the Docker database container:

docker compose down

Project Tech Stack

Backend Framework: Spring Boot
Language: Java
Database: PostgreSQL (Docker Container)
Containerization: Docker Compose
Build Tool: Maven



Project Tech Stack

Backend Framework: Spring Boot
Language: Java
Database: PostgreSQL (Docker Container)
Containerization: Docker Compose
Build Tool: Maven
