# Fase 1: Compilazione del progetto con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Fase 2: Creazione dell'ambiente di esecuzione leggero
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copia il file .jar generato nella fase precedente
COPY --from=build /app/target/notifiche-0.0.1-SNAPSHOT.jar app.jar
# Espone la porta usata da Spring Boot
EXPOSE 8080
# Avvia l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]