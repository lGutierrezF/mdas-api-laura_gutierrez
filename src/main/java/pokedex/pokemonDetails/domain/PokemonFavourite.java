package pokedex.pokemonDetails.domain;

public class PokemonFavourite {
    private int favouriteCount;

    public PokemonFavourite(int favourite) {
        this.favouriteCount = favourite;
    }

    public int getFavouriteCount() {
        return this.favouriteCount;
    }

    public void increaseFavouriteCount(int favourite){
        this.favouriteCount=favourite;
    }
}
