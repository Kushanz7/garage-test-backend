# Use an official OpenJDK runtime as the base image
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

#Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

#Copy the scr code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY --from=build /app/target/spring-boot-crud-0.0.1-SNAPSHOT.jar .

# Expose the port the application runs on (default: 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "spring-boot-crud-0.0.1-SNAPSHOT.jar"]