# Start with Maven to build the JAR
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Now use JDK only for running the built app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

