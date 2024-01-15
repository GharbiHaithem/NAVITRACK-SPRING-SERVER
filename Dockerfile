# Utilisez une image de base appropriée pour votre application, par exemple :
FROM maven:3.8.5-openjdk-17 AS build
COPY . . 
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

# Copiez le fichier JAR dans le conteneur et renommez-le
COPY --from=build /target/CRUD-0.0.1-SNAPSHOT.jar CRUD.jar

# Exposez le port sur lequel votre application s'exécute (remplacez <PORT> par le port réel)
EXPOSE 5500

# Commande pour exécuter votre application (ajustez selon vos besoins)
CMD ["java", "-jar", "CRUD.jar"]
