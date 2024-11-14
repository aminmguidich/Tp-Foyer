# Utilise une image Java JDK 17 légère
FROM openjdk:17-jdk-slim

# Définit le chemin vers le fichier JAR généré
ARG JAR_FILE=target/tpFoyer-17-1.0.0.jar

# Copie le fichier JAR dans l'image Docker
COPY ${JAR_FILE} tpFoyer-17-1.0.0.jar

# Commande d'exécution de l'application
ENTRYPOINT ["java", "-jar", "/tpFoyer-17-1.0.0.jar"]

# Expose le port de l'application
EXPOSE 8082