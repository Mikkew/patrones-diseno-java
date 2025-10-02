/**
 * ! Patrón Prototype:

 * Es un patrón de diseño creacional que nos permite copiar objetos existentes sin hacer
 * que el código dependa de sus clases.
 *
 * * Es útil cuando queremos duplicar el contenido,
 * * el título y el autor de un documento, por ejemplo o cualquier objeto complejo.
 *
 * https://refactoring.guru/es/design-patterns/prototype
 */

package com.mms.patterns.desing.p01_creacionales.c04_prototype;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Clase que representa el producto a clonar (prototipo)
class Pokemon {

    // Atributos del Pokémon
    private String name;
    private String type;
    private Integer level;
    private List<String> attacks;


    // Constructor que inicializa el Pokémon con sus atributos
    public Pokemon(String name, String type, Integer level, List<String> attacks) {
        this.name = name;
        this.type = type;
        this.level = level;
        // Se crea una nueva lista para evitar aliasing
        this.attacks = new ArrayList<>(attacks);
    }


    // Métodos getter y setter para los atributos
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    // Devuelve una copia inmodificable de la lista de ataques
    public List<String> getAttacks() { return Collections.unmodifiableList(attacks); }
    // Permite establecer una nueva lista de ataques
    public void setAttacks(List<String> attacks) { this.attacks = new ArrayList<>(attacks); }
    // Permite agregar un ataque a la lista
    public void addAttack(String attack) { this.attacks.add(attack); }


    // Representación en texto del Pokémon
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pokemon{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", level=").append(level);
        sb.append(", attacks=[").append(String.join(",", attacks)).append("]");
        sb.append('}');
        return sb.toString();
    }


    // Método Prototype: clona el Pokémon actual
    public Pokemon clone() {
        return new Pokemon(name, type, level, attacks);
    }
}

// Tarea:
// 1. Crear un Pokémon base.
// 2. Clonar el Pokémon base y modificar algunos atributos en los clones.
// 3. Llamar a displayInfo en cada Pokémon para mostrar sus detalles.


/**
 * Clase demostrativa del patrón Prototype.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que en un videojuego necesitas crear múltiples variantes de un Pokémon base (por ejemplo, para evoluciones o personalizaciones).
 * Usando el patrón Prototype, puedes clonar un Pokémon existente y modificar solo los atributos necesarios,
 * ahorrando tiempo y evitando errores de copia manual.
 */
public class PrototypePattern_02 {
    public static void main(String[] args) {
        // Crear un Pokémon base (prototipo)
        Pokemon basePokemon = new Pokemon("Charmander", "Fuego", 1, Arrays.asList("Llamarada", "Arañazo"));

        // Clonar el Pokémon base y modificar atributos para simular una evolución
        Pokemon clone1 = basePokemon.clone();
        clone1.setName("Charmeleon");
        clone1.setLevel(16);
        clone1.addAttack("Lanzallamas");

        // Mostrar los detalles de ambos Pokémon
        System.out.println(ORANGE_ALT + "Charmander = " + RESET + basePokemon);
        System.out.println(ORANGE + "Charmeleon = " + RESET + clone1);
    }
}
