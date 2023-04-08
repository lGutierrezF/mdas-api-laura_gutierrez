package pokedex.pokemonDetails.infrasctructure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import springboot.MdasSpringbootAplication;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(classes = MdasSpringbootAplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetPokemonDetailsWithHttpWebTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnPokemonDetails_whenPokemonExists() {

        // GIVEN
        final String POKEMON_ID = "1";

        this.webTestClient
                .get()
                .uri("/get-pokemon-details-by-id/" + POKEMON_ID)
                .header(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("bulbasaur")
                .jsonPath("$.height").isEqualTo("7.00000")
                .jsonPath("$.weight").isEqualTo("69.0000")
                .jsonPath("$.favouritedTimes").isEqualTo("0");
    }

}