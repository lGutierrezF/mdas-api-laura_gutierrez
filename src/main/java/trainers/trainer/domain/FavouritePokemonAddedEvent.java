package trainers.trainer.domain;

import shared.DomainEvent;

public class FavouritePokemonAddedEvent extends DomainEvent {
    private int pokemonID;

    public FavouritePokemonAddedEvent(int pokemonID) {
        super(pokemonID);
    }
    public int getPokemonID() {
        return pokemonID;
    }
    @Override
    public String type() {
        return "favouritePokemon.added";
    }
}
