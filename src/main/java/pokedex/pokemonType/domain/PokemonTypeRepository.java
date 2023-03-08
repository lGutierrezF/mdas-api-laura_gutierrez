package pokedex.pokemonType.domain;

import pokedex.pokemonType.domain.exceptions.EmptyPokemonNameParameterException;
import pokedex.pokemonType.domain.exceptions.PokemonNotFoundException;
import pokedex.pokemonType.domain.exceptions.PokemonWithoutTypesException;

public interface PokemonTypeRepository {
    PokemonTypes get(String pokemonName) throws PokemonWithoutTypesException, PokemonTypeRepositoryConnectionException, EmptyPokemonNameParameterException, PokemonNotFoundException;
}
