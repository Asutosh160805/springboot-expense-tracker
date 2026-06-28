# ---------- Build Stage ----------
FROM gradle:8.8-jdk21 AS builder

WORKDIR /app

COPY . .

RUN gradle clean bootJar -x test

# ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]