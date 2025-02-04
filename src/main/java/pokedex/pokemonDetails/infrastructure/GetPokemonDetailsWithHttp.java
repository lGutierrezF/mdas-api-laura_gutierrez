package pokedex.pokemonDetails.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pokedex.pokemonDetails.application.GetPokemonDetails;
import pokedex.pokemonDetails.domain.PokemonDetail;
import pokedex.pokemonDetails.domain.exceptions.*;

@RestController
public class GetPokemonDetailsWithHttp {
    @GetMapping("get-pokemon-details-by-id/{pokemonID}")
    public static String getPokemonDetailsByID(@PathVariable int pokemonID) {
        if (pokemonID < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"InvalidPokemonData");
        }
        var getPokemonDetails = new GetPokemonDetails(new PokeApiPokemonDetailRepository());
        try {
            PokemonDetail pokemonTypeCollection = getPokemonDetails.execute(pokemonID);
            return transformToJSON(pokemonTypeCollection);
        } catch (PokemonNameNotEmptyException |
                 PokemonNegativeHeightException | PokemonIdOutOfRangeException | PokemonNegativeWeightException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"InvalidPokemonData");
        } catch (PokemonNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"PokemonNotFoundException");
        } catch (PokemonDetailRepositoryConnectionException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"ConnectionError");
        }
    }

    private static String transformToJSON(PokemonDetail pokemonTypeCollection) {
        return String.format("""
                        {
                            "id":"%d",
                            "name":"%s",
                            "height":"%G",
                            "weight":"%G",
                            "favouritedTimes":"%s"
                        }
                        """, 
                pokemonTypeCollection.ID().ID(),
                pokemonTypeCollection.name().toString(),
                pokemonTypeCollection.height().height(),
                pokemonTypeCollection.weight().weight(),
                pokemonTypeCollection.favouritedTimes().getFavouriteCount()
        );
    }
}


