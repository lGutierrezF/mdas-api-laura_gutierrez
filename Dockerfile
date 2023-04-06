#FROM eclipse-temurin:17
#EXPOSE 8080
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} mdas-api-g2-vs-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/mdas-api-g2-vs-0.0.1-SNAPSHOT.jar"]
FROM rabbitmq:3-management
VOLUME ./rabbitmq:/var/lib/rabbitmq
ENV RABBITMQ_DEFAULT_USER=guest
ENV RABBITMQ_DEFAULT_PASS=guest
ENV RABBITMQ_DEFAULT_VHOST=mdas-api
EXPOSE 5672
EXPOSE 15672