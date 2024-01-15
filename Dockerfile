# Utilisez une image de base appropriée pour votre application, par exemple :
FROM openjdk:8-jre-alpine

# Créez le répertoire de travail
WORKDIR /app

# Copiez le fichier JAR dans le conteneur
COPY target/CRUD-0.0.1-SNAPSHOT.jar /app/CRUD-0.0.1-SNAPSHOT.jar

# Exposez le port sur lequel votre application s'exécute (remplacez <PORT> par le port réel)
EXPOSE 5500

# Commande pour exécuter votre application (ajustez selon vos besoins)
CMD ["java", "-jar", "CRUD-0.0.1-SNAPSHOT.jar"]
