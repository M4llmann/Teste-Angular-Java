# Base image for Java
FROM openjdk:21-jdk-slim


# Work directory inside the container
WORKDIR /app

# Copy the application JAR (assume build with Gradle/Maven)
COPY target/AppBanco-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
