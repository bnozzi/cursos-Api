FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/cursos-0.0.1-SNAPSHOT.jar /cursos-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/cursos-0.0.1-SNAPSHOT.jar"]
