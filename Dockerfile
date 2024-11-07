FROM openjdk:17-jdk-slim

ARG NEXUS_URL
ARG GROUP_ID
ARG ARTIFACT_ID
ARG VERSION

RUN apt-get update && apt-get install -y curl && \
    curl -o /app.jar "${NEXUS_URL}/${GROUP_ID}/${ARTIFACT_ID}/${VERSION}/${ARTIFACT_ID}-${VERSION}.jar" && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8082
