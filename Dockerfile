# Start with a base image containing Java 17
FROM openjdk:17-jdk-slim

# Define build arguments for Nexus download
ARG NEXUS_URL
ARG GROUP_ID
ARG ARTIFACT_ID
ARG VERSION

# Use curl to fetch the latest artifact from Nexus and save it as /app.jar
RUN apt-get update && apt-get install -y curl && \
    curl -o /app.jar "${NEXUS_URL}/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar" && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Set entry point for running the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port the application runs on
EXPOSE 8082
