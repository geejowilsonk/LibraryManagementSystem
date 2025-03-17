FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the JAR file
COPY target/VLibrary-1.0-SNAPSHOT.jar librarymanagement.jar

# Copy the application.properties file
COPY application.properties /app/application.properties

# Expose port
EXPOSE 8080

# Set the Spring Boot configuration path explicitly
ENTRYPOINT ["java", "-jar", "librarymanagement.jar", "--spring.config.location=/app/application.properties"]
