FROM openjdk:17-jdk-slim

# Arguments pour le téléchargement depuis Nexus
ARG NEXUS_URL=http://192.168.1.200:8081/repository/maven-releases
ARG GROUP_ID=tn/esprit
ARG ARTIFACT_ID=tpFoyer-17
ARG VERSION=2.0.1

# Utiliser apt-get au lieu de apk pour installer curl
RUN apt-get update && apt-get install -y curl && \
    curl -o /app.jar "${NEXUS_URL}/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar" && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8082
