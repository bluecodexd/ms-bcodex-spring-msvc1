# ---------- Build ----------
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

# ---------- Runtime ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd -r -u 10001 appuser && \
    groupadd -r appgroup && \
    usermod -aG appgroup appuser

COPY --from=0 /app/target/*.jar app.jar

RUN chown -R appuser:appgroup /app

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

