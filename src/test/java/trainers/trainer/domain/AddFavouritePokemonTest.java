package trainers.trainer.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import trainers.trainer.domain.exceptions.PokemonIdOutOfRangeException;
import trainers.trainer.domain.exceptions.TrainerDontExistException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AddFavouritePokemonTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Test
    void shouldThrowException_ifTrainerDoesNotExist() throws PokemonIdOutOfRangeException {

        // GIVEN
        AddFavouritePokemon addFavouritePokemon = new AddFavouritePokemon(trainerRepository);
        TrainerID ID = new TrainerID("1234");
        try {
            PokemonID pokemonID = new PokemonID(1);
            // THEN
            assertThrows(TrainerDontExistException.class, () -> addFavouritePokemon.execute(ID, pokemonID));
        } catch (PokemonIdOutOfRangeException e) {
            fail();
        }
    }
}
