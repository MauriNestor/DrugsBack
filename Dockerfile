# FROM eclipse-temurin:17-jdk-alpine
# Etapa de construcción (build)
FROM maven:3.9.9-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY pom.xml .
# Copia solo los archivos necesarios para descargar dependencias primero
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copia el JAR desde la etapa de construcción
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


LABEL author=mauri_nestor
