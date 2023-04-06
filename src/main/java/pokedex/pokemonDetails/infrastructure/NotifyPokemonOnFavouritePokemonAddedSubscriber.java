package pokedex.pokemonDetails.infrastructure;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import trainers.trainer.domain.FavouritePokemonAddedEvent;

@Component
public class NotifyPokemonOnFavouritePokemonAddedSubscriber {
    @RabbitListener(queues = "favouritePokemon.added")
    public void on(FavouritePokemonAddedEvent event) {
        var recievedEvents = new InMemoryFavouritePokemonAddFavouritePokemonEventRepository();
        recievedEvents.add(event);

    }


}
