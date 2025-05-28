FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean buildFatJar --no-daemon

CMD ["java", "-jar", "build/libs/app.jar"]
