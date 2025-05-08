FROM openjdk:17

LABEL authors="xianyume"

COPY backend-0.0.1-SNAPSHOT.jar /tmp/app.jar

WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "app.jar"]