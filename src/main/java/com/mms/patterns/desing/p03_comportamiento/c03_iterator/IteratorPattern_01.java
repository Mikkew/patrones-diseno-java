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
import java.util.List;

interface Iterator<T> {
    T next();
    Boolean hasNext();
    T current();
}

class Pokemon {
    private String name;
    private String type;

    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class PokemonCollection {
    private List<Pokemon> pokemons;

    public PokemonCollection() {
        pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public Pokemon getPokemonAt(Integer index) {
        if (index >= 0 && index < this.pokemons.size()) {
            return this.pokemons.get(index);
        }
        return null;
    }

    public Integer getLength() {
        return this.pokemons.size();
    }

    public PokemonIterator createIterator() {
         return new PokemonIterator(this);
    }
}

class PokemonIterator implements Iterator<Pokemon> {
    private PokemonCollection collection;
    private Integer position = 0;

    public PokemonIterator(PokemonCollection collection) {
        this.collection = collection;
    }

    @Override
    public Pokemon next() {
        if (Boolean.TRUE.equals(this.hasNext())) {
            return this.collection.getPokemonAt(this.position++);
        }
        return null;
    }

    @Override
    public Boolean hasNext() {
        return this.position < this.collection.getLength();
    }

    @Override
    public Pokemon current() {
        return this.collection.getPokemonAt(this.position);
    }
}

/**
 * Caso de uso: Recorrido de una colección de Pokémon
 *
 * Este ejemplo simula una Pokédex que almacena diferentes Pokémon.
 * El patrón Iterator permite recorrer la colección de Pokémon sin exponer cómo están almacenados internamente.
 * El cliente solo utiliza el iterador para acceder secuencialmente a cada elemento.
 *
 * Este patrón facilita la extensión de la colección y el recorrido, sin modificar la estructura interna ni el código cliente.
 */
public class IteratorPattern_01 {
    public static void main(String[] args) {
        // Se crea la colección de Pokémon
        PokemonCollection pokedex = new PokemonCollection();

        // Se agregan Pokémon a la colección
        pokedex.addPokemon(new Pokemon("Pikachu", "Eléctrico"));
        pokedex.addPokemon(new Pokemon("Charmander", "Fuego"));
        pokedex.addPokemon(new Pokemon("Squirtle", "Agua"));
        pokedex.addPokemon(new Pokemon("Bulbasaur", "Planta"));
        pokedex.addPokemon(new Pokemon("Jigglypuff", "Normal"));

        // Se obtiene el iterador para recorrer la colección
        PokemonIterator iterator = pokedex.createIterator();

        // Se recorre la colección usando el iterador
        while (Boolean.TRUE.equals(iterator.hasNext())) {
            Pokemon pokemon = iterator.next();

            if (pokemon != null) {
                System.out.println(String.format("Pokémon: %s, Tipo: %s", pokemon.getName(), pokemon.getType()));
            }
        }
    }
}
