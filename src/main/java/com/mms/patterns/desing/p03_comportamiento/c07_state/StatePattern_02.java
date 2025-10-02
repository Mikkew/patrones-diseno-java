/**
 * ! Patrón State
 * Este patrón permite a un objeto cambiar su comportamiento
 * cuando su estado interno cambia.
 *
 * * Es útil cuando un objeto tiene un comportamiento que depende de su estado
 * * y debe cambiar su comportamiento en tiempo de ejecución dependiendo de ese estado.
 */

package com.mms.patterns.desing.p03_comportamiento.c07_state;

import java.util.Scanner;

import static com.mms.patterns.desing.utils.ConsoleColors.*;
import static com.mms.patterns.desing.utils.Sleep.*;

/**
 * !Objetivo:
 * Implementar el patrón State para simular el funcionamiento de una puerta
 * automática.
 *
 * La puerta tiene diferentes estados:
 *  - Cerrada
 *  - Abriéndose
 *  - Abierta
 *  - Cerrándose
 * Su comportamiento varía dependiendo del estado actual.
 */

// Interfaz State
interface StateV2 {
  String getName();

  void open();
  void close();
}

// Clase Context - AutomaticDoor
class AutomaticDoor {
    private StateV2 state;

    public AutomaticDoor() {
        this.state = new Closed(this);
    }

    public void setState(StateV2 state) {
        this.state = state;
        System.out.println(String.format("%sEstado cambiado a: %s%s", GREEN, state.getName(), RESET));
    }

    public void open() {
        this.state.open();
    }

    public void close() {
        this.state.close();
    }

    public String getStateName() {
        return this.state.getName();
    }
}

// Estado 1 - Cerrada
class Closed implements StateV2 {
    private AutomaticDoor door;
    private String name;

    public Closed(AutomaticDoor door) {
        this.name = "Cerrada";
        this.door = door;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void open() {
        System.out.println("Abriendo la puerta...");
        this.door.setState(new Opening(this.door));
    }

    @Override
    public void close() {
        System.out.println("La puerta ya está cerrada.");
    }

    public void setName(String name) {
        this.name = name;
    }
}

// Estado 2 - Abriéndose
class Opening implements StateV2 {
    private AutomaticDoor door;
    private String name;

    public Opening(AutomaticDoor door) {
        this.door = door;
        this.name = "Abriendo...";

        this.afterOpen();
    }

    private void afterOpen() {
        sleep(3000);

        System.out.println("La puerta se ha abierto.");
        this.door.setState(new Open(this.door));
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void open() {
        System.out.println("La puerta ya se está abriendo.");
    }

    @Override
    public void close() {
        System.out.println("La puerta no puede cerrarse mientras se abre.");
    }
}

// Estado 3 - Abierta
class Open implements StateV2 {
    private AutomaticDoor door;
    private String name;

    public Open(AutomaticDoor door) {
        this.name = "Abierta";
        this.door = door;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void open() {
        System.out.println("La puerta ya está abierta.");
    }

    @Override
    public void close() {
        System.out.println("Cerrando la puerta...");
        this.door.setState(new Closing(this.door));
    }
}

// Estado 4 - Cerrándose
class Closing implements StateV2 {
    private AutomaticDoor door;
    private String name;

    public Closing(AutomaticDoor door) {
        this.door = door;
        this.name = "Cerrandose";

        this.afterClose();
    }

    private void afterClose() {
        sleep(3000);

        System.out.println("La puerta se ha cerrado.");
        this.door.setState(new Closed(this.door));
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void open() {
        System.out.println("Detectando movimiento. Abriendo la puerta nuevamente...");
        this.door.setState(new Opening(this.door));
    }

    @Override
    public void close() {
        System.out.println("La puerta se ha cerrado.");
        this.door.setState(new Closed(this.door));
    }
}

/**
 * Caso de uso: Puerta automática con estados dinámicos
 *
 * Este ejemplo simula una puerta automática que cambia su comportamiento según el estado interno:
 * cerrada, abriéndose, abierta y cerrándose. El patrón State permite que la puerta altere su comportamiento
 * en tiempo de ejecución dependiendo de su estado actual, sin condicionales complejos.
 *
 * El patrón State es útil para modelar sistemas con múltiples estados y transiciones claras entre ellos.
 */
public class StatePattern_02 {
    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si falla el clear, simplemente imprimimos líneas en blanco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        // Se crea la puerta automática y el menú de opciones
        AutomaticDoor door = new AutomaticDoor();
        Scanner scanner = new Scanner(System.in);

        String selectedOption = "3";

        do {
            clearConsole();
            System.out.println("Estado actual: " + door.getStateName());

            System.out.print("""
                    1. Abrir puerta
                    2. Cerrar puerta
                    3. Salir
                    
                    Selecciona una opción: """);

            selectedOption = scanner.nextLine();

            switch (selectedOption.trim()) {
                case "1":
                    door.open();
                    break;
                case "2":
                    door.close();
                    break;
                case "3":
                    System.out.println("Saliendo del simulador...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            sleep(3000);

        } while (!"3".equals(selectedOption));
        scanner.close();
    }
}
