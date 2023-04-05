FROM eclipse-temurin:17
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mdas-api-g2-vs-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mdas-api-g2-vs-0.0.1-SNAPSHOT.jar"]