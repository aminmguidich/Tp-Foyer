FROM openjdk:17-jdk-slim

# Arguments pour le téléchargement depuis Nexus
ARG NEXUS_URL=http://192.168.1.200:8081/repository/maven-releases
ARG GROUP_ID=tn/esprit
ARG ARTIFACT_ID=tpFoyer-17
ARG VERSION=2.0.1

# Remplacer les points par des slashs pour la structure de dossier Nexus
RUN apk add --no-cache curl && \
    curl -o /app.jar "${NEXUS_URL}/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar"

ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8082
