FROM openjdk:17-jdk-slim

COPY build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-Xms2048m", "-Xmx4096m", "-jar", "app.jar"]
