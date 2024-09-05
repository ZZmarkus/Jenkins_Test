#Dockerfile
FROM openjdk:17-jdk-slim

COPY build/libs/SpringBootTest-0.0.1-SNAPSHOT.jar /app/SpringBootTest.jar

CMD ["java", "-jar", "/app/SpringBootTest.jar"]

EXPOSE 8080