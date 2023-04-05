#! /bin/sh
# Commit stage and integration test
./mvnw clean verify
#Build docker image
docker build -t pokeapi/mdas-api-g2 .

#docker run --name mdas-api-g2 -dp 8080:8080 -p 15672:15672 -p 5672:5672 pokeapi/mdas-api-g2
#docker exec -it mdas-api-g2 wget -q -O- localhost:8080/getPokemonTypesByName/pikachu