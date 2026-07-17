# Fase 1: Scarica Java 17 e Maven per "costruire" il progetto
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copia tutti i file del progetto dentro il server
COPY . .

# Esegue il comando di build saltando i test per velocizzare
RUN mvn clean package -DskipTests

# Fase 2: Crea l'ambiente finale leggero per avviare l'app
FROM openjdk:17-jdk-slim
WORKDIR /app

# Prende il file .jar appena costruito nella Fase 1 e lo prepara per l'avvio
COPY --from=build /app/target/*.jar app.jar

# Comunica che l'app funzionerà sulla porta 8080
EXPOSE 8080

# Il comando che fa partire Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]