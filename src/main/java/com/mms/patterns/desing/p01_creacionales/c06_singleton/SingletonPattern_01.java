/**
 * ! Singleton:
 * Es un patrón de diseño creacional que garantiza que una clase
 * tenga una única instancia y proporciona un punto de acceso global a ella.
 *
 * * Es útil cuando necesitas controlar el acceso a una única instancia
 * * de una clase, como por ejemplo, en un objeto de base de datos o en un
 * * objeto de configuración.
 *
 * https://refactoring.guru/es/design-patterns/singleton
 */

package com.mms.patterns.desing.p01_creacionales.c06_singleton;

import static com.mms.patterns.desing.utils.ConsoleColors.*;


// Clase Singleton que representa el conjunto único de esferas del dragón
class DragonBall {
    // Instancia única (estática) de la clase
    private static DragonBall instance;
    // Estado: cantidad de esferas recolectadas
    private Integer ballsCollected;

    // Constructor privado para evitar instanciación externa
    public DragonBall() {
        this.ballsCollected = 0;
    }

    /**
     * Método estático y sincronizado para obtener la instancia única (Singleton)
     */
    public static synchronized DragonBall getInstance() {
        if (instance == null) {
            DragonBall.instance = new DragonBall();
            System.out.println(GREEN + "Las pelotas del Dragón han sido creadas!" + RESET);
        }
        return instance;
    }

    /**
     * Método sincronizado para recolectar una esfera
     */
    public synchronized void collectBall() {
        if (this.ballsCollected < 7) {
            this.ballsCollected++;
            System.out.println(GREEN + "Pelota recolectada. Total de esferas: " + this.ballsCollected + RESET);
            return;
        }
        System.out.println("Ya se han recolectado las 7 esferas del Dragón! Invoca a Shenlong");
    }

    /**
     * Método sincronizado para invocar a Shenlong si se tienen las 7 esferas
     */
    public synchronized void summonShenlong() {
        if (this.ballsCollected == 7) {
            System.out.println("Shenlong ha sido invocado, Pide tu deseo!");
            this.ballsCollected = 0;
            return;
        }
        System.out.println("Aún faltan " + (7 - this.ballsCollected) + " pelotas para invocar a Shenlong");
    }

    // Getter para consultar la cantidad de esferas recolectadas
    public Integer getBallsCollected() {
        return ballsCollected;
    }
}



/**
 * Clase demostrativa del patrón Singleton.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que en el universo de Dragon Ball solo puede existir un conjunto de esferas del dragón.
 * Todos los personajes (Goku, Vegeta, etc.) deben interactuar con la misma instancia para recolectarlas e invocar a Shenlong.
 * Usando el patrón Singleton, se garantiza que solo exista una instancia global y compartida.
 */
public class SingletonPattern_01 {
    public static void main(String[] args) {
        // Obtener la instancia única de las esferas del dragón
        DragonBall gokuDragonBalls = DragonBall.getInstance();

        // Goku recolecta esferas
        gokuDragonBalls.collectBall();
        gokuDragonBalls.collectBall();
        gokuDragonBalls.collectBall();

        gokuDragonBalls.summonShenlong();

        // Vegeta accede a la misma instancia y continúa recolectando
        DragonBall vegetaDragonBalls = DragonBall.getInstance();
        vegetaDragonBalls.collectBall();
        vegetaDragonBalls.collectBall();
        vegetaDragonBalls.collectBall();
        vegetaDragonBalls.collectBall();

        // Ambos personajes interactúan con la misma instancia
        gokuDragonBalls.summonShenlong();
        vegetaDragonBalls.summonShenlong();
    }
}
