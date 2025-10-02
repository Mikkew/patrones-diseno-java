/**
 * ! Patrón Strategy
 *
 * El patrón Strategy es un patrón de diseño de software que define una
 * familia de algoritmos, los encapsula y los hace intercambiables.
 *
 *
 * * Es útil cuando se tiene una clase que tiene un comportamiento que puede
 * * cambiar en tiempo de ejecución y se quiere delegar la responsabilidad de
 * * la implementación a otra clase.
 *
 * https://refactoring.guru/es/design-patterns/strategy
 */

/**
 * !Objetivo: Explicar el patrón Strategy usando un ejemplo donde varios
 * ! patitos compiten en una carrera y cada uno tiene su propia
 * ! estrategia de movimiento (por ejemplo, nadar, volar o caminar).
 */

package com.mms.patterns.desing.p03_comportamiento.c08_strategy;

import static com.mms.patterns.desing.utils.ConsoleColors.*;


// Interfaz común para todas las estrategias de movimiento
interface MovementStrategy {
    void move();
}

// Estrategia #1 - Rápida pero costosa
class SwimFast implements MovementStrategy {
    @Override
    public void move() {
        System.out.println(String.format("%sEl pato nada rápidamente sobre el agua%s", BLUE, RESET));
    }
}

// Estrategia #2 - No tan rápida pero no tan costosa
class FlyOverWater implements MovementStrategy {
    @Override
    public void move() {
        System.out.println(String.format("%sEl pato vuela elegantemente sobre el agua%s", PINK, RESET));
    }
}

// Estrategia #3 - Lenta y económica
class WalkClumsily implements MovementStrategy {

    @Override
    public void move() {
        System.out.println(String.format("%sEl pato camina torpemente por la orilla%s", GREEN, RESET));
    }
}

// Consumidor de estrategias
class Duck {
    private String name;
    private MovementStrategy movementStrategy;

    public Duck(String name, MovementStrategy movementStrategy) {
        this.name = name;
        this.movementStrategy = movementStrategy;
    }

    // El pato ejecuta su estrategia actual de movimiento
    public void performMove() {
        System.out.print(String.format("%s se prepara para moverse...", this.name));
        this.movementStrategy.move();
    }

    // Permite cambiar la estrategia de movimiento en tiempo de ejecución
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
        System.out.println(String.format("%s cambió de estrategia", this.name));
    }
}


public class StrategyPattern_01 {
    /**
     * Ejemplo principal: Carrera de patos
     * Cada pato inicia con una estrategia de movimiento distinta.
     * Durante la carrera, el "Pato torpe" cambia su estrategia dos veces para intentar mejorar su desempeño.
     * Esto demuestra cómo el patrón Strategy permite modificar el comportamiento de un objeto en tiempo de ejecución.
     */
    public static void main(String[] args) {
        Duck duck1 = new Duck("Pato rapido", new SwimFast()); // Estrategia: nadar rápido
        Duck duck2 = new Duck("Pato volador", new FlyOverWater()); // Estrategia: volar
        Duck duck3 = new Duck("Pato torpe", new WalkClumsily()); // Estrategia: caminar

        System.out.println(String.format("%sComienza la carrera de patos!%s", RED, RESET));

        duck1.performMove();
        duck2.performMove();
        duck3.performMove();

        // El pato torpe decide cambiar de estrategia durante la carrera
        duck3.setMovementStrategy(new FlyOverWater());
        duck3.performMove();

        // El pato torpe vuelve a cambiar de estrategia
        duck3.setMovementStrategy(new SwimFast());
        duck3.performMove();
    }
}
