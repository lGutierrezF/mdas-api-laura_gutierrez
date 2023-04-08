package trainers.trainer.application;

import trainers.trainer.domain.PokemonID;
import trainers.trainer.domain.TrainerID;
import trainers.trainer.domain.TrainerRepository;
import trainers.trainer.domain.exceptions.MessageBrokerConnectionException;
import trainers.trainer.domain.exceptions.PokemonAlreadyExistInFavouritePokemonsException;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;
import shared.EventPublisher;

public class AddFavouritePokemon {
    private final TrainerRepository trainerRepository;
    private final EventPublisher publisher;

    public AddFavouritePokemon(TrainerRepository trainerRepository, EventPublisher publisher) {
        this.trainerRepository = trainerRepository;
        this.publisher = publisher;
    }

    public void execute(String trainerId, int pokemonID) throws MessageBrokerConnectionException, TrainerDontExistException, PokemonAlreadyExistInFavouritePokemonsException, PokemonIdOutOfRangeException {
        var addFavouritePokemon = new trainers.trainer.domain.AddFavouritePokemon(trainerRepository);
        addFavouritePokemon.execute(new TrainerID(trainerId), new PokemonID(pokemonID));
        this.publisher.publish(addFavouritePokemon.pullDomainEvent());
    }
}
