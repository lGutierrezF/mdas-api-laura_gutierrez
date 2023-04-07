#! /bin/sh
# Commit stage and integration test
#./mvnw clean verify
#Build docker image
#docker build -t rabbitmq-management .
docker build -t mdas-api .
#Create and run container
#docker run --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq-management
docker run --name mdas-api-laura -p 8080:8080 mdas-api