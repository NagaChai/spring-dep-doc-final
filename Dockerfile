# Start with Maven to build the JAR
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Use a lightweight JDK to run the app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Limit memory usage to prevent OOM
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]


