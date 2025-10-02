/**
 * ! Inmutabilidad con copia
 * Aunque la inmutabilidad es una buena práctica, no siempre es posible.
 * En estos casos, se puede hacer una copia del objeto y modificar la copia.
 * <p>
 * * Es útil para mantener un historial de estados en aplicaciones interactivas.
 */

package com.mms.patterns.desing.p01_creacionales.c05_inmutabilidad;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Clase inmutable que representa el estado de un jugador
class Player {

    // Atributos inmutables del jugador
    private final String name;
    private final Integer score;
    private final Integer level;


    // Constructor que inicializa el estado del jugador
    public Player(String name, Integer score, Integer level) {
        this.name = name;
        this.score = score;
        this.level = level;
    }


    /**
     * Crea una copia del jugador actual, permitiendo modificar solo los atributos deseados.
     * Es el mecanismo principal para "modificar" un objeto inmutable.
     */
    public Player copyWith(String name, Integer score, Integer level) {
        return new Player(
                name != null ? name : this.name,
                score != null ? score : this.score,
                level != null ? level : this.level
        );
    }


    // Muestra el estado actual del jugador por consola
    public void displayState() {
        System.out.println(GREEN + String.format("Jugador %s", this.name) + RESET);
        System.out.println(YELLOW + String.format("Puntaje %s", this.score) + RESET);
        System.out.println(BLUE + String.format("Nivel %s", this.level) + RESET);
    }
}


/**
 * Clase demostrativa del patrón de inmutabilidad.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que tienes un videojuego donde el estado del jugador debe ser seguro y predecible.
 * Cada vez que el jugador sube de nivel, cambia de nombre o puntaje, se crea una nueva instancia inmutable,
 * permitiendo mantener un historial de cambios o implementar funcionalidades como undo/redo fácilmente.
 */
public class InmutabilidadPattern_02 {
    public static void main(String[] args) {
        // Crear jugador inicial
        Player player = new Player("Carlos", 1, 0);
        System.out.println("Estado Inicial");
        player.displayState();

        // Incrementar el puntaje
        player = player.copyWith(null, 10, null);
        System.out.println("\nDespués de incrementar el puntaje:");
        player.displayState();

        // Subir de nivel
        player = player.copyWith(null, null, 2);
        System.out.println("\nDespués de subir de nivel:");
        player.displayState();
        
        // Cambiar el nombre del jugador
        player = player.copyWith("Carlos Pro", null, null);
        System.out.println("\nDespués de cambiar el nombre:");
        player.displayState();
    }
}
