/**
 * !Patrón Memento
 * Permite capturar y externalizar un estado interno de un objeto,
 * de manera que el objeto pueda ser restaurado a ese estado más tarde.
 *
 * * Es útil cuando se necesita guardar el estado de un objeto para poder
 * * volver a él en un futuro.
 *
 * https://refactoring.guru/es/design-patterns/memento
 */

package com.mms.patterns.desing.p03_comportamiento.c05_memento;

import java.util.ArrayList;
import java.util.List;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

class GameMemento {
    private Integer level;
    private Integer health;
    private String position;

    public GameMemento(Integer level, Integer health, String position) {
        this.level = level;
        this.health = health;
        this.position = position;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

class Game {
    private Integer level;
    private Integer health;
    private String position;

    public Game() {
        level = 1;
        health = 100;
        position = "inicio";

        System.out.println(String.format("""
                Jugando en el nivel %d
                        salud: %d
                        posición: %s
                """, this.level, this.health, this.position));
    }

    public GameMemento save() {
        return new GameMemento(this.level, this.health, this.position);
    }

    public void play(Integer level, Integer health, String position) {
        this.level = level;
        this.health = health;
        this.position = position;

        System.out.println(String.format("""
                Jugando en el nivel %d
                        salud: %d
                        posición: %s
                """, this.level, this.health, this.position));
    }

    public void restore(GameMemento memento) {
        this.level = memento.getLevel();
        this.health = memento.getHealth();
        this.position = memento.getPosition();

        System.out.println(String.format("""
                %sProgreso restaurado
                
                %sJRestauración en el nivel %s%d
                        salud: %d
                        posición: %s
                """, YELLOW, BLUE, RESET, this.level, this.health, this.position));
    }
}

class GameHistory {
    private List<GameMemento> mementos;

    public GameHistory() {
        mementos = new ArrayList<>();
    }

    public void push(GameMemento memento) {
        this.mementos.add(memento);
    }

    public GameMemento pop() {
        if (mementos.isEmpty()) {
            return null;
        }
        return mementos.removeLast();
    }
}

/**
 * Caso de uso: Guardar y restaurar el progreso de un juego
 *
 * Este ejemplo simula un videojuego donde el jugador puede guardar su progreso (nivel, salud, posición)
 * y restaurarlo más tarde si lo necesita. El patrón Memento permite capturar el estado interno del juego
 * y almacenarlo en una pila de historial, para luego restaurar ese estado cuando el jugador lo desee.
 *
 * El patrón Memento es útil para implementar funcionalidades de "deshacer" o "guardar y cargar" en aplicaciones.
 */
public class MementoPattern_01 {
    public static void main(String[] args) {
        // Se crea el juego y el historial de estados
        Game game = new Game();
        GameHistory history = new GameHistory();

        // Se guarda el estado inicial
        history.push(game.save());

        // El jugador avanza en el juego y guarda el progreso
        game.play(2, 90, "Bosque Encantado");
        history.push(game.save());

        game.play(3, 70, "Cueva Oscura");
        history.push(game.save());

        game.play(4, 50, "Castillo del Dragón");
        System.out.println(String.format("%sEstado actual%s", GREEN, RESET));

        // El jugador decide restaurar el último estado guardado
        game.restore(history.pop());
        System.out.println(String.format(
            "%sDespués de restaurar el último estado guardado%s", GREEN, RESET
        ));
    }
}
