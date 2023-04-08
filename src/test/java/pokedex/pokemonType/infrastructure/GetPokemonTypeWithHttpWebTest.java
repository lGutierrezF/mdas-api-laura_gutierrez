package pokedex.pokemonType.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import springboot.MdasSpringbootAplication;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(classes = MdasSpringbootAplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetPokemonTypeWithHttpWebTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnPokemonTypes_whenPokemonExists() {

        // GIVEN
        final String POKEMON_NAME = "pikachu";
        final String EXPECTED_TYPES = "[\"electric\"]";

        this.webTestClient
            .get()
            .uri("/get-pokemon-types-by-name/" + POKEMON_NAME)
            .header(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(String.class).isEqualTo(EXPECTED_TYPES);
    }
}