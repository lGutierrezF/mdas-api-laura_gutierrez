package trainers.trainer.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import trainers.trainer.application.AddFavouritePokemon;
import trainers.trainer.domain.exceptions.MessageBrokerConnectionException;
import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;
import shared.application.RabbitMqEventPublisher;


@RestController
public class AddFavouritePokemonToTrainerWithHttp {
    @PostMapping("add-favourite-pokemon-to-trainer/{pokemonID}")
    public ResponseEntity<String> AddFavouritePokemonToTrainer(@RequestHeader("user_id") String trainerID, @PathVariable int pokemonID) {
        var trainerRepository = new InMemoryTrainerRepository();
        var addFavouritePokemon = new AddFavouritePokemon(trainerRepository, new RabbitMqEventPublisher());
        blankIdGuard(trainerID);

        try {
            addFavouritePokemon.execute(trainerID,pokemonID);
        } catch (TrainerDontExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"TrainerDontExistException");
        } catch (PokemonAlreadyExistInFavouritePokemonsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"PokemonAlreadyExistInFavouritePokemons");
        } catch (PokemonIdOutOfRangeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"PokemonIdOutOfRangeException");
        } catch (MessageBrokerConnectionException e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"ConnectionError");
        }
        return ResponseEntity.ok().body("ok");
    }

    private static void blankIdGuard(String ID) {
        if (ID.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "blankID");
        }
    }
}


