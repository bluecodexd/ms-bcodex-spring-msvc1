# ---------- Build ----------
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY app/pom.xml .

RUN mvn -B -e dependency:go-offline

COPY app/src ./src
RUN mvn -B clean package -DskipTests

# ---------- Runtime ----------
FROM gcr.io/distroless/java21-debian13 AS runtime

WORKDIR /app

COPY --from=0 /app/target/*.jar app.jar

RUN chown -R nonroot:nonroot /app

USER nonroot

EXPOSE 8080

CMD ["app.jar"]

