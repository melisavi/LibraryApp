FROM openjdk:17
COPY /target/library-core-1.0-SNAPSHOT.jar opt/app.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "opt/app.jar"]
