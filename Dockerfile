# Use a slim JDK 17 base image
FROM openjdk:17-jdk-slim

# Define build arguments for Nexus download
ARG NEXUS_URL
ARG GROUP_ID
ARG ARTIFACT_ID
ARG VERSION

# Install curl and download the latest artifact from Nexus
RUN apt-get update && apt-get install -y curl && \
    curl -o /app.jar "${NEXUS_URL}/repository/maven-releases/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar" && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Set entry point to run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the application port
EXPOSE 8082
