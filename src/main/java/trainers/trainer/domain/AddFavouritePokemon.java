package trainers.trainer.domain;

import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;
import shared.domain.DomainEvent;

import java.util.HashSet;
import java.util.Set;

public class AddFavouritePokemon {
    private final TrainerRepository trainerRepository;
    private final Set<DomainEvent> events;

    public AddFavouritePokemon(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
        this.events = new HashSet<>();
    }

    public void execute(TrainerID ID, PokemonID pokemonID) throws TrainerDontExistException, PokemonAlreadyExistInFavouritePokemonsException {
        trainerExistGuard(ID);
        Trainer trainer = trainerRepository.get(ID);
        trainer.addFavouritePokemon(pokemonID);
        this.raise(new FavouritePokemonAddedEvent(pokemonID.ID()));
        trainerRepository.update(trainer);
    }
    private void raise(DomainEvent favouritePokemonAddedEvent) {
        this.events.add(favouritePokemonAddedEvent);
    }
    public Set<DomainEvent> pullDomainEvent(){
        var recordedEvents = this.events;
        return recordedEvents;
    }
    private void trainerExistGuard(TrainerID id) throws TrainerDontExistException {
        if (!trainerRepository.exist(id)) {
            throw new TrainerDontExistException();
        }
    }
}
