# Étape 1 : Construction de l'application
FROM maven:3.8.5-openjdk-17 AS build
# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances
COPY pom.xml .

# Télécharger les dépendances sans exécuter de tests
RUN mvn dependency:go-offline
# Copier le code source de l'application
COPY src ./src

# Étape 2 : Exécution de l'application
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app
# Copier le JAR construit depuis l'étape précédente
COPY --from=build /app/target/tpFoyer-17-1.0.0.jar app.jar
# Exposer le port sur lequel l'application écoute
EXPOSE 8082

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
