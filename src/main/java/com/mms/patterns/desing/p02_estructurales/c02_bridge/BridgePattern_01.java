/**
 * ! Patrón Bridge
 * Este patrón nos permite desacoplar una abstracción de su implementación,
 * de tal forma que ambas puedan variar independientemente.
 *
 * * Es útil cuando se tienen múltiples implementaciones de una abstracción
 * * Se puede utilizar para separar la lógica de negocio de la lógica de presentación
 * * Se puede utilizar para separar la lógica de la interfaz de usuario también.
 *
 * https://refactoring.guru/es/design-patterns/bridge
 */

package com.mms.patterns.desing.p02_estructurales.c02_bridge;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Implementación: interfaz para las habilidades de los personajes
interface Ability {
    void use();
}

// Implementación concreta: ataque con espada
class SwordAttack implements Ability {
    @Override
    public void use() {
        System.out.println("Ataca con una " + BLUE + "espada ferozmente" + RESET);
    }
}

// Implementación concreta: ataque con hacha
class AxeAttack implements Ability {
    @Override
    public void use() {
        System.out.println("Ataca con una " + BLUE + "hacha brutalmente" + RESET);
    }
}

// Implementación concreta: hechizo mágico
class MagicSpell implements Ability {
    @Override
    public void use() {
        System.out.println("Lanza un hechizo " + GREEN + "mágico poderoso" + RESET);
    }
}

// Implementación concreta: bola de fuego
class FireballSpell implements Ability {
    @Override
    public void use() {
        System.out.println("Lanza una " + GREEN + "bola de fuego" + RESET);
    }
}

// Abstracción: clase base para los personajes
abstract class Character {
    // Referencia a la habilidad (implementación)
    protected Ability ability;

    // El personaje recibe una habilidad al crearse
    Character(Ability ability) {
        this.ability = ability;
    }

    // Permite cambiar la habilidad en tiempo de ejecución
    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    // Método abstracto para ejecutar la habilidad
    abstract void performAbility();
}

// Abstracción refinada: Guerrero
class Warrior extends Character {
    Warrior(Ability ability) {
        super(ability);
    }

    @Override
    void performAbility() {
        System.out.println("El guerrero está listo para luchar");
        this.ability.use();
    }
}

// Abstracción refinada: Mago
class Mage extends Character {
    Mage(Ability ability) {
        super(ability);
    }

    @Override
    void performAbility() {
        System.out.println("El mago prepara su magia");
        this.ability.use();
    }
}

/**
 * Caso de uso:
 * Imagina que tienes personajes en un videojuego (guerreros, magos, etc.) y
 * cada uno puede tener diferentes habilidades
 * (espada, hacha, hechizo, bola de fuego). El patrón Bridge permite desacoplar
 * la abstracción (el personaje)
 * de la implementación (la habilidad), de modo que puedes combinar libremente
 * cualquier personaje con cualquier habilidad
 * sin crear una clase para cada combinación posible.
 *
 * Así, puedes agregar nuevas habilidades o nuevos tipos de personajes sin
 * modificar las clases existentes,
 * solo creando nuevas implementaciones o subclases.
 */
public class BridgePattern_01 {

    public static void main(String[] args) {
        // Crear un guerrero con ataque de espada
        Warrior warrior = new Warrior(new SwordAttack());
        warrior.performAbility();

        // Cambiar la habilidad del guerrero a ataque con hacha
        warrior.setAbility(new AxeAttack());
        warrior.performAbility();

        // Crear un mago con hechizo de bola de fuego
        Mage mago = new Mage(new FireballSpell());
        mago.performAbility();
    }
}
