FROM openjdk:8-alpine
WORKDIR /app
COPY ./build/libs/teachingInstitution-0.0.1-SNAPSHOT.jar /app/backend.jar

EXPOSE 8095

ENTRYPOINT ["java", "-jar", "backend.jar"]



