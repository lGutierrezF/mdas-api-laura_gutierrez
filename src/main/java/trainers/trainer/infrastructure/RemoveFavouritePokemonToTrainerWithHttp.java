package trainers.trainer.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import trainers.trainer.application.RemoveFavouritePokemon;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.PokemonNotExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;

@RestController
public class RemoveFavouritePokemonToTrainerWithHttp {
    @PutMapping("remove-favourite-pokemon-to-trainer/{pokemonID}")
    public static void RemoveFavouritePokemonToTrainer(@RequestHeader("user_id") String trainerID, @PathVariable int pokemonID) {
        var trainerRepository = new InMemoryTrainerRepository();
        var removeFavouritePokemon = new RemoveFavouritePokemon(trainerRepository);
        blankIdGuard(trainerID);

        try {
            removeFavouritePokemon.execute(trainerID,pokemonID);
        } catch (TrainerDontExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"TrainerDontExistException");
        } catch (PokemonIdOutOfRangeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"PokemonIdOutOfRangeException");
        } catch (PokemonNotExistInFavouritePokemonsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"PokemonNotExistInFavouritePokemonsException");
        }
    }

    private static void blankIdGuard(String ID) {
        if (ID.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "blankID");
        }
    }
}


