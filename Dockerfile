FROM openjdk:17-jdk-alpine

EXPOSE 8085

ADD target/tpFoyer-17-0.0.1.jar tpFoyer-17-0.0.1.jar

ENTRYPOINT ["java", "-jar", "/tpFoyer-17-0.0.1.jar"]