FROM openjdk:17-jdk-slim

# Install curl and download the artifact directly from Nexus
RUN apt-get update && \
    apt-get install -y curl && \
    curl -f -o /app.jar "http://192.168.1.200:8081/repository/maven-releases/tn/esprit/tpFoyer-17/0.0.1/tpFoyer-17-0.0.1.jar" && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8082
FROM openjdk:17-jdk-slim

# Replace these with your actual Nexus username and password
ARG NEXUS_USER="admin"
ARG NEXUS_PASS="Qmar.2024"

RUN apt-get update && \
    apt-get install -y curl && \
    curl -u ${NEXUS_USER}:${NEXUS_PASS} -f -o /app.jar "http://192.168.1.200:8081/repository/maven-releases/tn/esprit/tpFoyer-17/2.0.1/tpFoyer-17-2.0.1.jar" && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8082
