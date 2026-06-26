# Use Java 21 as the base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the generated JAR into the container
COPY build/libs/*.jar app.jar

# Spring Boot runs on port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]