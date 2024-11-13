# Use the official Jenkins LTS image as a base
FROM jenkins/jenkins:lts

# Install Java 17 and Maven 3
USER root

# Update package list and install Java 17 and Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    apt-get clean

# Set JAVA_HOME and MAVEN_HOME environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-arm64
ENV MAVEN_HOME=/usr/share/maven
ENV PATH="${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}"

# Ensure environment variables are available for all users
RUN echo "export JAVA_HOME=${JAVA_HOME}" >> /etc/profile
RUN echo "export PATH=${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}" >> /etc/profile

# Switch back to the Jenkins user
USER jenkins
