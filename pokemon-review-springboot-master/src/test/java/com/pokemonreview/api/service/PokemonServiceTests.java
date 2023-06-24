package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.impl.PokemonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //cho phép tích hợp mockito vào junit test
public class PokemonServiceTests {
    // Mockito là một mock framework cho phép chúng ta giả lập các kết quả trả về khi triển khai unit-test
    @Mock // để đánh dấu những class mà chúng ta không quan tâm, và giá trị trả về của các method của class này sẽ được giả lập.
    private PokemonRepository pokemonRepository;

    @InjectMocks // @InjectMocks annotation để đánh dấu class mà chúng ta muốn kiểm thử.
    private PokemonServiceImpl pokemonService;

    @Test
    public void pokemonService_CreatePokemon_ReturnsPokemon(){
        // Pokemon là dữ liệu giả
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();
        PokemonDto pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric")
                .build();

        // Để giả lập kết quả trả về chúng ta cần hướng dẫn cho Mockito biết bằng cách sử dụng mệnh đề when-thenReturn.
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);
        PokemonDto savePokemon = pokemonService.createPokemon(pokemonDto);

        Assertions.assertThat(savePokemon).isNotNull();
    }

    @Test
    public void pokemonService_GetAllPokemon_ReturnsResponseDto(){
        Page<Pokemon> pokemons = Mockito.mock(Page.class);

        when(pokemonRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pokemons);
        PokemonResponse pokemonResponse = pokemonService.getAllPokemon(1, 10);

        Assertions.assertThat(pokemonResponse).isNotNull();
    }

}
