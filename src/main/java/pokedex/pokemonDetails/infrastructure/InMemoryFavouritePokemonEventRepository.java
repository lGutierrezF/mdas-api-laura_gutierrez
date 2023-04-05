package pokedex.pokemonDetails.infrastructure;

import org.springframework.stereotype.Repository;
import pokedex.pokemonDetails.domain.PokemonID;
import pokedex.pokemonDetails.domain.DomainEventRepository;
import trainers.trainer.domain.FavouritePokemonAddedEvent;

import java.util.HashSet;
import java.util.Set;

@Repository
public class InMemoryFavouritePokemonEventRepository implements DomainEventRepository {
    private static final Set<FavouritePokemonAddedEvent> memoryOfFavouritePokemonEvents = new HashSet<>();

    @Override
    public boolean exists(PokemonID ID) {
        for (FavouritePokemonAddedEvent element : memoryOfFavouritePokemonEvents) {
           if (element.getPokemonID() == ID.ID()){
               return true;
           }
        }
        return false;
    }

    @Override
    public int count(PokemonID ID) {
        if(exists(ID))
            return get(ID).size();
        else return 0;
    }

    @Override
    public Set<FavouritePokemonAddedEvent> get(PokemonID ID) {
        Set<FavouritePokemonAddedEvent> foundEvents = new HashSet<>();
        for (FavouritePokemonAddedEvent element : memoryOfFavouritePokemonEvents) {
            System.out.println(element.getPokemonID());
            System.out.println(ID.ID());
            if (element.getPokemonID() == ID.ID()) {
                foundEvents.add(element);
            }
        }
        return foundEvents;
    }
    @Override
    public void add(FavouritePokemonAddedEvent event){
        memoryOfFavouritePokemonEvents.add(event);
    }

}
