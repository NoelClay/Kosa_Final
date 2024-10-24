# FROM openjdk:17-jdk-slim

# COPY build/libs/*.war app.war

# EXPOSE 8081

# ENTRYPOINT ["java", "-Xms2048m", "-Xmx4096m", "-jar", "app.war"]

FROM openjdk:17-jdk-slim

WORKDIR /usr/src/app

# 필요한 경우, 종속성 설치
COPY . .
# Gradle 의존성 설치 및 빌드
RUN ./gradlew build --no-daemon

# 애플리케이션 실행 (Gradle로 실행)
CMD ["./gradlew", "bootRun", "--no-daemon"]
