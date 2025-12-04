FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY ../pom.xml .
COPY ../domain/pom.xml domain/
COPY ./pom.xml springboot/

COPY .. .

RUN mvn -f springboot/pom.xml clean package -DskipTests


# STAGE 2 — Imagem final (RUNNING)
# ===============================
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

COPY --from=build /app/springboot/target/*.jar app.jar

EXPOSE 8080

# Comando padrão
ENTRYPOINT ["java", "-jar", "app.jar"]
