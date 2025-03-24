FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/calculator-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties /app/application.properties
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080
