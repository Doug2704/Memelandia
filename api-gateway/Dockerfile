FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar /app/apigateway.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "apigateway.jar"]

