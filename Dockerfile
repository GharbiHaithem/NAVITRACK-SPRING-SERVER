# Utilisez une image de base appropriée pour Java et Spring Boot
FROM openjdk:11-jre-slim

# Copiez le fichier JAR de votre application dans le conteneur
COPY target/CRUD-0.0.1-SNAPSHOT.jar /app.jar

# Exposez le port sur lequel votre application s'exécute
EXPOSE 8080

# Commande pour exécuter votre application lorsque le conteneur démarre
CMD ["java", "-jar", "/app.jar"]
