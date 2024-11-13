# Use the official Jenkins LTS image as a base
FROM jenkins/jenkins:lts

# Switch to root user to install dependencies
USER root

# Install dependencies: Java, Maven, Docker dependencies and utilities
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven apt-transport-https ca-certificates curl gnupg lsb-release && \
    apt-get clean

# Add Docker's official GPG key
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | tee /etc/apt/trusted.gpg.d/docker.asc

# Add Docker repository manually using a direct URL for stability
RUN echo "deb [arch=amd64] https://download.docker.com/linux/debian bullseye stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null

# Update and install Docker (docker.io instead of docker-ce)
RUN apt-get update && \
    apt-get install -y docker.io && \
    apt-get clean

# Install Docker Compose
RUN curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose

# Set JAVA_HOME and MAVEN_HOME environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV MAVEN_HOME=/usr/share/maven
ENV PATH="${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}"

# Add Jenkins user to the Docker group so Jenkins can run Docker commands
RUN usermod -aG docker jenkins

# Switch back to the Jenkins user
USER jenkins
