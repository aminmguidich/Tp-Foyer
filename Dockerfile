FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} tpFoyer-17-2.0.0.jar
ENTRYPOINT ["java", "-jar" ,"/tpFoyer-17-2.0.0.jar"]
EXPOSE 8082
