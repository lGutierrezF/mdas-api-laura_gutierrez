package trainers.trainer.domain;

import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.PokemonNotExistInFavouritePokemonsException;

public class Trainer {
    private final TrainerID ID;
    private final FavouritePokemons favoritePokemons;


    public Trainer(TrainerID ID) {
        this.ID = ID;
        this.favoritePokemons = new FavouritePokemons();

    }

    public TrainerID ID() {
        return ID;
    }

    public void addFavouritePokemon(PokemonID pokemonID) throws PokemonAlreadyExistInFavouritePokemonsException {
        favoritePokemons.addFavouritePokemon(pokemonID);

    }
    public void removeFavouritePokemon(PokemonID pokemonID) throws PokemonNotExistInFavouritePokemonsException {
        favoritePokemons.removeFavouritePokemon(pokemonID);
    }

    public boolean hasFavouritePokemon(PokemonID pokemonID) {
        return favoritePokemons.pokemonExist(pokemonID);
    }

}
