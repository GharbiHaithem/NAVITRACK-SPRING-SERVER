FROM adoptopenjdk:11-jre-hotspot

# Créez le répertoire de travail
WORKDIR /CRUD

# Copiez le fichier JAR de votre application dans le conteneur
COPY target/CRUD-0.0.1-SNAPSHOT.jar target/CRUD-0.0.1-SNAPSHOT.jar

# Exposez le port sur lequel votre application s'exécute
EXPOSE 5500

# Commande pour exécuter votre application lorsque le conteneur démarre
CMD ["java", "-jar", "CRUD-0.0.1-SNAPSHOT.jar"]
