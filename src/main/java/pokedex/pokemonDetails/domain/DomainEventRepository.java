package pokedex.pokemonDetails.domain;


import trainers.trainer.domain.FavouritePokemonAddedEvent;

import java.util.Set;

public interface DomainEventRepository {
    boolean exists(PokemonID ID);
    int count(PokemonID ID);
    Set<FavouritePokemonAddedEvent> get(PokemonID ID);
    void add(FavouritePokemonAddedEvent event);
}