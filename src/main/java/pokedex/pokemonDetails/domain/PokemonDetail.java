package pokedex.pokemonDetails.domain;

public class PokemonDetail {
    private final PokemonID ID;
    private final PokemonName name;
    private final PokemonHeight height;
    private final PokemonWeight weight;
    private PokemonFavourite favourited;

    public PokemonDetail(PokemonID ID,
                         PokemonName name,
                         PokemonHeight height,
                         PokemonWeight weight,
                         PokemonFavourite favourited) {
        this.ID = ID;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.favourited = favourited;

    }

    public PokemonID ID() {
        return ID;
    }

    public PokemonName name() {
        return name;
    }

    public PokemonHeight height() {
        return height;
    }

    public PokemonWeight weight() {
        return weight;
    }
    public PokemonFavourite favouritedTimes() {
        return favourited;
    }

}
