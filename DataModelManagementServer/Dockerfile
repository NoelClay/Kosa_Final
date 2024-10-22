FROM openjdk:17-jdk-slim

COPY build/libs/*.war app.war

EXPOSE 8081

ENTRYPOINT ["java", "-Xms2048m", "-Xmx4096m", "-jar", "app.war"]
