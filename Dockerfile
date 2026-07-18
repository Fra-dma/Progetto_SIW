# Stage 1: Costruzione dell'applicazione con Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila il progetto ignorando i test per velocizzare il deploy
RUN mvn clean package -DskipTests

# Stage 2: Esecuzione dell'applicazione
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copia solo il file JAR generato nello stage precedente
COPY --from=build /app/target/*.jar app.jar
# Esponi la porta standard
EXPOSE 8080
# Avvia l'applicazione Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]