/**
 * ! Patrón Iterator
 * Este patrón permite recorrer los elementos de una colección sin exponer
 * la estructura interna de la colección.
 *
 * * Es útil cuando se necesita recorrer una colección de elementos sin importar
 * * cómo se almacenan los elementos.
 *
 * https://refactoring.guru/es/design-patterns/iterator
 */

package com.mms.patterns.desing.p03_comportamiento.c03_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Clase que representa la colección de Pokemons con soporte para iteración
class PokemonCollectionV2 implements Iterable<Pokemon> {
    private List<Pokemon> pokemons;

    public PokemonCollectionV2() {
        pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    // Implementación del iterador usando Iterable
    @Override
    public Iterator<Pokemon> iterator() {
        return pokemons.iterator();
    }
}

/**
 * Caso de uso: Recorrido de una colección de Pokémon usando Iterable
 *
 * Este ejemplo muestra cómo implementar el patrón Iterator usando la interfaz Iterable de Java.
 * La colección de Pokémon puede ser recorrida fácilmente con un bucle for-each,
 * sin exponer cómo están almacenados internamente.
 * El cliente solo utiliza el iterador para acceder secuencialmente a cada Pokémon.
 *
 * Este patrón facilita la extensión de la colección y el recorrido, sin modificar la estructura interna ni el código cliente.
 */
public class IteratorPattern_02 {
    public static void main(String[] args) {
        // Se crea la colección de Pokémon
        PokemonCollectionV2 pokedex = new PokemonCollectionV2();

        // Se agregan Pokémon a la colección
        pokedex.addPokemon(new Pokemon("Pikachu", "Eléctrico"));
        pokedex.addPokemon(new Pokemon("Charmander", "Fuego"));
        pokedex.addPokemon(new Pokemon("Squirtle", "Agua"));
        pokedex.addPokemon(new Pokemon("Bulbasaur", "Planta"));

        System.out.println("Recorriendo la colección de Pokemons:");
        // Se recorre la colección usando el iterador (for-each)
        for (Pokemon pokemon : pokedex) {
            System.out.println("Pokémon: " + pokemon.getName() + ", Tipo: " + pokemon.getType());
        }
    }
}
