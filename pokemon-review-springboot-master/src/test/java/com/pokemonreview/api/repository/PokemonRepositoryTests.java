package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTests {


    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonRepositoryTests(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Test
    void pokemonRepository_SaveAll_ReturnSavePokemon() {
        // given
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();
        // when
        Pokemon savePokemon = pokemonRepository.save(pokemon);
        // then
        Assertions.assertThat(savePokemon).isNotNull();
    }

    @Test
    public void pokemonRepository_GetAll_ReturnMoreThenOnePokemon() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();
        Pokemon pokemon1 = Pokemon.builder()
                .name("pikachu 1")
                .type("electric 1")
                .build();

        pokemonRepository.save(pokemon);
        pokemonRepository.save(pokemon1);

        Assertions.assertThat(pokemonRepository.findAll()).isNotNull();
        Assertions.assertThat(pokemonRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    public void pokemonRepository_FindById_ReturnPokemon() {
        var pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon pokemon1 = pokemonRepository.save(pokemon);
        Pokemon pokemon2 = pokemonRepository.findById(pokemon1.getId()).get();

        Assertions.assertThat(pokemon2).isNotNull();
    }

    @Test
    public void pokemonRepository_FindByType_ReturnPokemonNotNull(){
        var pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();
        pokemonRepository.save(pokemon);
        Pokemon result = pokemonRepository.findByType(pokemon.getType()).get();
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void pokemonRepository_Update_ReturnPokemonNotNull(){
        var pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);
        Pokemon pokemonSave = pokemonRepository.findById(pokemon.getId()).get();
        pokemonSave.setName("Pikachu");
        pokemonSave.setType("Electric");
        Pokemon pokemonUpdate = pokemonRepository.save(pokemonSave);

        Assertions.assertThat(pokemonUpdate.getName()).isNotNull();
        Assertions.assertThat(pokemonUpdate.getType()).isNotNull();
    }

    @Test
    public void pokemonRepository_PokemonDelete_ReturnPokemonIsEmpty(){
        var pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepository.save(pokemon);
        pokemonRepository.deleteById(pokemon.getId());
        Optional<Pokemon> result = pokemonRepository.findById(pokemon.getId());

        Assertions.assertThat(result).isEmpty();
    }

}
