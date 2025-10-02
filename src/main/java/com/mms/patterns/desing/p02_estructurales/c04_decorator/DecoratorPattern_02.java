/**
 * ! Patrón decorador
 * Es un patrón de diseño estructural que permite añadir
 * funcionalidades a objetos, colocando estos objetos dentro de
 * objetos encapsuladores especiales que contienen estas funcionalidades.
 *
 * No confundirlo con los decoradores de TypeScript que son anotaciones.
 *
 * * Es útil cuando necesitas añadir funcionalidades a objetos
 *  * de manera dinámica y flexible.
 *
 * https://refactoring.guru/es/design-patterns/decorator
 */

// 1. Interfaz Character

package com.mms.patterns.desing.p02_estructurales.c04_decorator;

// Clase para representar las estadísticas
class Stats {
    private final Integer attack;
    private final Integer defense;

    public Stats(Integer attack, Integer defense) {
        this.attack = attack;
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return "{attack=" + attack + ", defense=" + defense + "} \n";
    }
}

interface Character {
    String getDescription();
    Stats getStats();
}

// 2. Clase BasicCharacter
// Representa un personaje básico sin accesorios
class BasicCharacter implements Character {

    @Override
    public String getDescription() {
        return "Personaje básico";
    }

    @Override
    public Stats getStats() {
        return new Stats(10, 10);
    }
}

// 3. Clase Decoradora CharacterDecorator
// Actúa como base para los decoradores específicos
abstract class CharacterDecorator implements Character {
    protected Character character;

    CharacterDecorator(Character character) {
        this.character = character;
    }

    public String getDescription() {
        return this.character.getDescription();
    }

    public Stats getStats() {
        return this.character.getStats();
    }
}

// 4. Decorador Concreto HelmetDecorator
// Añade un casco que aumenta la defensa en +5
class HelmetDecorator extends CharacterDecorator {

    HelmetDecorator(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return super.getDescription().concat("\n * con Casco");
    }

    // Modifica las estadísticas sumando defensa
    @Override
    public Stats getStats() {
        Stats stats = this.character.getStats();
        return new Stats(stats.getAttack(), stats.getDefense() + 5);
    }
}

// 5. Decorador Concreto ShieldDecorator
// Añade un escudo que aumenta la defensa en +10
class ShieldDecorator extends CharacterDecorator {

    ShieldDecorator(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return super.getDescription().concat("\n * con Escudo");
    }

    @Override
    public Stats getStats() {
        Stats stats = this.character.getStats();
        return new Stats(stats.getAttack(), stats.getDefense() + 10);
    }
}

// 6. Decorador Concreto SwordDecorator
// Añade una espada que aumenta el ataque en +7
class SwordDecorator extends CharacterDecorator {

    SwordDecorator(Character character) {
        super(character);
    }

    // Añade la descripción de la espada
    @Override
    public String getDescription() {
        return super.getDescription().concat("\n * con Espada");
    }

    // Modifica las estadísticas sumando ataque
    @Override
    public Stats getStats() {
        Stats stats = this.character.getStats();
        return new Stats(stats.getAttack() + 10, stats.getDefense());
    }
}

// class RingDecorator ...
class RingDecorator extends CharacterDecorator {

    RingDecorator(Character character) {
        super(character);
    }

    // Añade la descripción del anillo
    @Override
    public String getDescription() {
        return super.getDescription().concat("\n * con Anillo");
    }

    // Modifica las estadísticas sumando ataque
    @Override
    public Stats getStats() {
        Stats stats = this.character.getStats();
        return new Stats(stats.getAttack() + 3, stats.getDefense());
    }
}

// Código cliente: compone decoradores para añadir accesorios dinámicamente
/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de personajes de videojuego donde se pueden agregar
 * accesorios (casco, escudo, espada, anillo, etc.) a un personaje básico,
 * incrementando sus estadísticas sin modificar la clase original ni crear
 * una jerarquía de herencia rígida.
 *
 * El patrón Decorator permite apilar decoradores para combinar funcionalidades
 * dinámicamente en tiempo de ejecución. En el main, se crea un personaje básico
 * y se le agregan decoradores de casco, escudo, espada y anillo, demostrando cómo
 * se pueden añadir responsabilidades adicionales de forma flexible y acumulativa.
 */
public class DecoratorPattern_02 {
    public static void main(String[] args) {
        // Crear un personaje básico (sin accesorios)
        Character character = new BasicCharacter();
        System.out.println("Personaje inicial: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        // Añadir un casco al personaje (decorador HelmetDecorator)
        character = new HelmetDecorator(character);
        System.out.println("Con Casco: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        // Añadir un escudo al personaje (decorador ShieldDecorator)
        character = new ShieldDecorator(character);
        System.out.println("Con Escudo: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        // Añadir una espada al personaje (decorador SwordDecorator)
        character = new SwordDecorator(character);
        System.out.println("Con Espada: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        // Añadir un anillo al personaje (decorador RingDecorator)
        character = new RingDecorator(character);
        System.out.println("Con Anillo: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());
    }
}
