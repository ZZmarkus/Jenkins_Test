#Dockerfile
FROM openjdk:17-jdk-slim

COPY build/libs/SpringBootTest-0.0.1-SNAPSHOT.jar /Jenkins/SpringBootTest.jar

CMD ["java", "-jar", "/Jenkins/SpringBootTest.jar"]

EXPOSE 8080