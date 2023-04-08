package pokedex.pokemonDetails.infrasctructure;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pokedex.pokemonDetails.infrastructure.NotifyPokemonOnFavouritePokemonAddedSubscriber;
import shared.application.RabbitMqEventPublisher;
import trainers.trainer.domain.FavouritePokemonAddedEvent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavouritePokemonAddedEventListnerTestIT {
    @Mock
    private RabbitMqEventPublisher rabbitTemplate;
    @Mock
    private NotifyPokemonOnFavouritePokemonAddedSubscriber listener;

    @Test
    public void shouldBeAble_ToListenToEvents() {
        // GIVEN
        FavouritePokemonAddedEvent favouritePokemonAddedEvent = mock(FavouritePokemonAddedEvent.class);
        listener =  mock(NotifyPokemonOnFavouritePokemonAddedSubscriber.class);
        doNothing().when(listener).on(any(FavouritePokemonAddedEvent.class));

        // WHEN
        listener.on(favouritePokemonAddedEvent);

        // THEN
        verify(listener, times(1)).on(any(FavouritePokemonAddedEvent.class));
    }
}