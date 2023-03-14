Prerequisites:
- Java SDK 17

There are to ways to execute this project, via terminal commands and an http request.
  
To run via terminal request follow this steps:
  - Open a terminal and change the directory to the root of the project.
  - Execute the following command: mvn clean compile exec:java -Dexec.mainClass=pokedex.pokemonType.infrastructure.GetPokemonTypeWithConsole -Dexec.args="the name of the pokemon that you want to search". For example: mvn clean compile -Dexec.mainClass=pokedex.pokemonType.infrastructure.GetPokemonTypeWithConsole exec:java -Dexec.args="pikachu".

The pokemon type should appear on console.

To run via http request follow this steps:
-  Open a terminal and change the directory to the root of the project.
-  Execute the following command: mvn clean package spring-boot:run.
- Open a web browser and type the following URL: http://localhost:8080/getPokemonTypesByName/(the name of the pokemon that you want to search). For example: http://localhost:8080/getPokemonTypesByName/pikachu
  
The pokemon type should appear in the browser.
