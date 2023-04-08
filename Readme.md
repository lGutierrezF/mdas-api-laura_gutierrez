## Prerequisites
- Docker
- Docker-Compose
- CURL 

## Stack used 
- Java 17
- SpringBoot 3

## How to use
At the root of the project you have two maven wrapper executables, that will allow you to compile, execute and running tests
without the need of installing maven.

You have the linux executable(mvnw) and the windows executable(mvnw.cmd). Just check you have execute permission set on these files to be able to run it.
In the following sections you will see example commands made with the linux maven wrapper.

## Unit testing
```
./mvnw clean test
```

## How to run
There are to ways to execute this project, via terminal commands or a http request.

### Commands via terminal
Follow this steps:
  - Open a terminal and change the directory to the root of this project.
  - Execute the following command: 
```
./mvnw clean compile -Dexec.mainClass=pokedex.pokemonType.infrastructure.GetPokemonTypeWithConsole exec:java -Dexec.args="pikachu"
```

The pokemon type should appear on console.

### Running an http server, integration and acceptance testing.
This application uses RabbitMQ as a message broker in order to keep the independent bounded contexts communicated.
So in order for the acceptance tests to pass, it's necessary to have the RabbitMQ service up and running.

Follow this steps:
- Open a terminal and change the directory to the root of the project.
- Execute the following command:
``` 
docker-compose up -d rabbitmql
```
- After this we can test and build our application in an independent docker container executing the following command:
``` 
docker-compose up -d spring-boot-app  
```

## Database
This application is just a proof of software design concepts, so isn't needed a real database. We have use a simple java Map to simulate InMemoryDatabase

## Endpoints
As stated in our docker compose, we've exposed the localhost:8080 port for the springboot application and localhost:15672 port for managing the RabbitMQ message broker.
The credentials for accesing the RabbitMQ manager are in the src/main/resources/application.properties directory.
Any http client could be used, but just for the convenience of testing header injection, we will use CURL in the next examples.

### /get-pokemon-types-by-name/{pokemonName}
```
curl -X GET -s http://localhost:8080/get-pokemon-types-by-name/pikachu
```
### /create-trainer/{ID}
Create a trainer in the given ID
```
curl -X POST -s http://localhost:8080/create-trainer/99
```

### /add-favourite-pokemon-to-trainer/{pokemonID}
Note: you need to inject an existing user_id custom header, to be able to add a FavoritePokemon from a Trainer
```
curl -X POST -H "user_id:99" http://localhost:8080/add-favourite-pokemon-to-trainer/1
```

### /remove-favourite-pokemon-to-trainer/{pokemonID}
Note: you need to inject an existing user_id custom header, to be able to remove a FavoritePokemon from a Trainer
```
curl -X PUT -H "user_id:99" http://localhost:8080/remove-favourite-pokemon-to-trainer/1
```

### /get-pokemon-details-by-id/{pokemonID}
```
curl -X GET -s http://localhost:8080/get-pokemon-details-by-id/1
```