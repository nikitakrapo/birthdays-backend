FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean buildFatJar --no-daemon

RUN cp build/libs/app.jar app.jar

CMD ["java", "-jar", "app.jar"]
