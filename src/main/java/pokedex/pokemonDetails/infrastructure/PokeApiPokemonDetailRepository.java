package pokedex.pokemonDetails.infrastructure;

import org.json.JSONObject;
import pokedex.pokemonDetails.domain.*;
import pokedex.pokemonDetails.domain.exceptions.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PokeApiPokemonDetailRepository implements PokemonDetailRepository {

    private String apiUrl = "https://pokeapi.co/api/v2/pokemon/";

     public PokeApiPokemonDetailRepository() {

    }

    public PokeApiPokemonDetailRepository(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public PokemonDetail getById(PokemonID pokemonID) throws PokemonDetailRepositoryConnectionException, PokemonNotFoundException, PokemonIdOutOfRangeException, PokemonNameNotEmptyException, PokemonNegativeWeightException, PokemonNegativeHeightException {
        var recievedEvents = new InMemoryFavouritePokemonAddFavouritePokemonEventRepository();
        HttpResponse<String> response;
        try {
            response = apiCall(pokemonID);
        } catch (IOException | InterruptedException e) {
            throw new PokemonDetailRepositoryConnectionException("An error has ocurred conecting to pokeapi");
        }
        guardPokemonNameExists(response);
        JSONObject obj = new JSONObject(response.body());

//        var typesArray = obj.getJSONArray("types");
//        List<PokemonType> pokemonTypes = new ArrayList<PokemonType>();
//        for (int i = 0; i < typesArray.length(); i++) {
//            String type = typesArray.getJSONObject(i).getJSONObject("type").getString("name");
//            pokemonTypes.add(new PokemonType(type));
//        }
//
//        PokemonType[] result = pokemonTypes.toArray(new PokemonType[0]);
        PokemonID ID = new PokemonID(obj.getInt("id"));
        PokemonName name = new PokemonName(obj.getString("name"));
        PokemonWeight weight = new PokemonWeight(obj.getDouble("weight"));
        PokemonHeight height = new PokemonHeight(obj.getDouble("height"));
        PokemonFavourite favourite = new PokemonFavourite(recievedEvents.count(ID));
        return new PokemonDetail(ID, name, height, weight, favourite);
    }

    private HttpResponse<String> apiCall(PokemonID pokemonID) throws IOException, InterruptedException {
        HttpResponse<String> response;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s%s", apiUrl, pokemonID.ID())))
                .build();
        response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response;
    }

    private void guardPokemonNameExists(HttpResponse<String> response) throws PokemonNotFoundException {
        if (response.statusCode() == 404)
            throw new PokemonNotFoundException();
    }
}
