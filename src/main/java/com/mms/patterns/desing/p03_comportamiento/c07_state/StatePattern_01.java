/**
 * ! Patrón State
 * Este patrón permite a un objeto cambiar su comportamiento
 * cuando su estado interno cambia.
 *
 * * Es útil cuando un objeto tiene un comportamiento que depende de su estado
 * * y debe cambiar su comportamiento en tiempo de ejecución dependiendo de ese estado.
 *
 * https://refactoring.guru/es/design-patterns/state
 */

package com.mms.patterns.desing.p03_comportamiento.c07_state;

import java.util.Scanner;

import static com.mms.patterns.desing.utils.ConsoleColors.*;
import static com.mms.patterns.desing.utils.Sleep.*;

interface State {
    String getName();

    void insertMoney();
    void selectProduct();
    void dispenseProduct();
}

class VendingMachine {
    private State state;

    public VendingMachine() {
        this.state = new WaitingForMoney(this);
    }

    public void insertMoney() {
        this.state.insertMoney();
    }

    public void selectProduct() {
        this.state.selectProduct();
    }

    public void dispenseProduct() {
        this.state.dispenseProduct();
    }

    public String getState() {
        return this.state.getName();
    }

    public void setState(State state) {
        this.state = state;
        System.out.println(String.format("Estado cambió a: %s%s%s", YELLOW, state.getName(), RESET));
    }
}

class WaitingForMoney implements State {
    private String name = "Esperando Dinero";
    private VendingMachine vendingMachine;

    public WaitingForMoney(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void insertMoney() {
        System.out.println(String.format("Dinero insertado: %sAhora puedes seleccionar un producto%s", GREEN, RESET));
        this.vendingMachine.setState(new ProductSelected(this.vendingMachine));
    }

    @Override
    public void selectProduct() {
        System.out.println(String.format("%sPrimero debes de insertar dinero.%s", RED, RESET));
    }

    @Override
    public void dispenseProduct() {
        System.out.println(String.format("%sPrimero debes de insertar dinero.%s", RED, RESET));
    }
}

class ProductSelected implements State {
    private String name = "Seleccionando Producto";
    private VendingMachine vendingMachine;

    public ProductSelected(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void insertMoney() {
        System.out.println(String.format("%sPor favor selecciona un producto - dinero ya insertado%s", RED, RESET));
    }

    @Override
    public void selectProduct() {
        this.vendingMachine.setState(new DispensingProduct(this.vendingMachine));
    }

    @Override
    public void dispenseProduct() {
        System.out.println(String.format("%sPor favor selecciona un producto - antes de despacharlo%s", RED, RESET));
    }
}

class DispensingProduct implements State {
    private String name = "Despachando producto";
    private VendingMachine vendingMachine;

    public DispensingProduct(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void insertMoney() {
        System.out.println(String.format("%sPor favor espera a que se entregue el producto%s", RED, RESET));
    }

    @Override
    public void selectProduct() {
        System.out.println(String.format("%sProducto ya seleccionado y despachando%s", RED, RESET));

    }

    @Override
    public void dispenseProduct() {
        System.out.println(String.format("%sProducto despachado, Cambiando estado a EsperandoDinero%s", GREEN, RESET));
        this.vendingMachine.setState(new WaitingForMoney(this.vendingMachine));
    }
}

/**
 * Caso de uso: Máquina expendedora con estados dinámicos
 *
 * Este ejemplo simula una máquina expendedora que cambia su comportamiento según el estado interno:
 * esperando dinero, seleccionando producto y despachando producto. El patrón State permite que la máquina
 * altere su comportamiento en tiempo de ejecución dependiendo de su estado actual, sin condicionales complejos.
 *
 * El patrón State es útil para modelar sistemas con múltiples estados y transiciones claras entre ellos.
 */
public class StatePattern_01 {
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
        // Se crea la máquina expendedora y el menú de opciones
        VendingMachine vendingMachine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        String selectedOption = "4";

        do {
            clearConsole();
            System.out.println("Selecciona una opción: " + BLUE + vendingMachine.getState() + RESET);

            System.out.print("""
                    1. Insertar dinero
                    2. Seleccionar producto
                    3. Dispensar producto
                    4. Salir

                    opción: """);

            selectedOption = scanner.nextLine();

            switch (selectedOption) {
                case "1":
                    vendingMachine.insertMoney();
                    break;
                case "2":
                    vendingMachine.selectProduct();
                    break;
                case "3":
                    vendingMachine.dispenseProduct();
                    break;
                case "4":
                    System.out.println("Saliendo de sistema");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            sleep(3000);

        } while (!"4".equals(selectedOption));
        scanner.close();
    }
}
