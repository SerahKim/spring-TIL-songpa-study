package com.section02.annotation.subsection03.collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.section02");

        PokemonService pokemonService = context.getBean("pokemonServiceCollection", PokemonService.class);

        pokemonService.pokemonAttack();
    }
}
