FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN ./gradlew clean buildFatJar --no-daemon

CMD ["java", "-jar", "build/libs/app.jar"]
