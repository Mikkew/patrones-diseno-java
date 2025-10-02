/**
 * ! Patrón Facade
 * Este patrón proporciona una interfaz unificada para un conjunto de interfaces
 * en un subsistema.
 * <p>
 * Facade define una interfaz de nivel más alto que hace que el subsistema
 * sea más fácil de usar.
 * <p>
 * * Es útil cuando un subsistema es complejo o difícil de entender para
 * * proporcionar una interfaz simplificada para el cliente.
 * <p>
 * https://refactoring.guru/es/design-patterns/facade
 */

package com.mms.patterns.desing.p02_estructurales.c05_facade;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// 1. Clases del Subsistema
class CPU {
    public void stopOperations() {
        System.out.println("CPU: Deteniendo operaciones.");
    }

    public void jump(Integer position) {
        System.out.printf("CPU: Saltando a la posición de memoria %d. ", position);
    }

    public void execute() {
        System.out.println("CPU: Ejecutando instrucciones.");
    }
}

class HardDrive {
    public String read(Integer position, Integer size) {
        System.out.printf("HardDrive: Leyendo %d bytes desde la posición %d. ", size, position);
        return "001010001010100";
    }

    public void close() {
        System.out.println("HardDrive: Deteniendo disco duro.");
    }
}

class Memory {
    public void load(Integer position, String data) {
        System.out.printf("Memory: Cargando datos en la posición %d %s. ", position, data);
    }

    public void free() {
        System.out.println("Memory: Liberando memoria.");
    }
}

// 2. Clase Facade - ComputerFacade
class ComputerFacade {
    private CPU cpu = new CPU();
    private Memory memory = new Memory();
    private  HardDrive hardDrive = new HardDrive();

    public void startComputer() {
        System.out.printf("%sIniciando la computadora...%s", CYAN, RESET);

        // Ejecutar las operaciones necesarias para encender la computadora
        this.memory.load(0, this.hardDrive.read(0, 1024));
        this.cpu.jump(0);
        this.cpu.execute();

        System.out.println("Computadora lista para usar.");
    }

    public void shutDownComputer() {
        System.out.printf("%sApagando la computadora...%s", RED, RESET);
        System.out.println("Cerrando procesos y guardando datos...");

        // Ejecutar las operaciones necesarias para apagar la computadora
        this.cpu.stopOperations();
        this.memory.free();
        this.hardDrive.close();

        System.out.println("Computadora apagada.");
    }
}

/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula el encendido y apagado de una computadora, donde existen varios
 * subsistemas (CPU, memoria, disco duro). El patrón Facade permite al cliente interactuar
 * con todos estos subsistemas a través de una interfaz simplificada (ComputerFacade),
 * ocultando la complejidad y los detalles de coordinación.
 *
 * En el main, el cliente puede encender o apagar la computadora usando solo dos métodos
 * de la fachada, sin preocuparse por el orden ni la interacción de los componentes internos.
 */
public class FacadePattern_02 {
    public static void main(String[] args) {
        // Crear la fachada que simplifica el uso de la computadora
        ComputerFacade computer = new ComputerFacade();

        // Encender la computadora usando la fachada (coordina CPU, memoria y disco)
        computer.startComputer();

        // Apagar la computadora usando la fachada (coordina el apagado de todos los subsistemas)
        computer.shutDownComputer();
    }
}
