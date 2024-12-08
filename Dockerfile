FROM gradle:jdk21-alpine AS builder
WORKDIR /app
COPY ./ ./
RUN gradle clean build --no-daemon

FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=builder app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]