# Utilisez une image de base appropriée pour Java et Spring Boot
FROM adoptopenjdk:11-jre-hotspot
# Votre configuration Docker supplémentaire ici

# Créez un répertoire pour l'application
WORKDIR /app

# Copiez le fichier JAR de votre application dans le conteneur
COPY target/CRUD-0.0.1-SNAPSHOT.jar app.jar

# Exposez le port sur lequel votre application s'exécute (si votre application utilise le port 5500)
EXPOSE 5500

# Commande pour exécuter votre application lorsque le conteneur démarre
CMD ["java", "-jar", "app.jar"]
