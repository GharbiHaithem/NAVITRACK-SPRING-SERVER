# Utilisez une image de base appropriée pour Java et Spring Boot
FROM adoptopenjdk:11-jre-hotspot
# Votre configuration Docker supplémentaire ici


# Copiez le fichier JAR de votre application dans le conteneur
COPY  target/CRUD-0.0.1-SNAPSHOT.jar /app.jar

# Exposez le port sur lequel votre application s'exécute
EXPOSE 5500

# Commande pour exécuter votre application lorsque le conteneur démarre
CMD ["java", "-jar", "/app.jar"]
ENTRYPOINT [ "java","-jar","crud.jar","app.jar" ]