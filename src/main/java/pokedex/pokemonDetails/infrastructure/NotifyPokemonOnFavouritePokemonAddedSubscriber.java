package pokedex.pokemonDetails.infrastructure;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import shared.DomainEvent;
@Component
public class NotifyPokemonOnFavouritePokemonAddedSubscriber {
    //private  UserNotifierUseCase useCase;
    @RabbitListener(queues = "favourite_pokemon_added")
    public void on(String event) {
        System.out.println(event);

    }


}
