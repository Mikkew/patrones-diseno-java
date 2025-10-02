/**
 * ! Factory Method:
 * El patrón Factory Method permite crear objetos sin especificar
 * la clase exacta del objeto que se creará.
 *
 * En lugar de eso, delegamos la creación de objetos a subclases o métodos
 * que encapsulan esta lógica.
 *
 * * Es útil cuando una clase no puede anticipar la clase
 * * de objetos que debe crear.
 *
 * https://refactoring.guru/es/design-patterns/factory-method
 *
 */

package com.mms.patterns.desing.p01_creacionales.c02_factory_method;

import java.util.Scanner;

// Producto abstracto: interfaz para hamburguesas
interface Hamburger {
    void prepare();
}


// Producto concreto: hamburguesa de pollo
class ChickenHamburger implements Hamburger {

    @Override
    public void prepare() {
        System.out.println("Preparando una hamburgesa de Pollo");
    }
}


// Producto concreto: hamburguesa de res
class BeefHamburger implements Hamburger {

    @Override
    public void prepare() {
        System.out.println("Preparando una hamburgesa de Res");
    }
}


// Producto concreto: hamburguesa de frijol
class BeanHamburger implements Hamburger {

    @Override
    public void prepare() {
        System.out.println("Preparando una hamburgesa de Frijol");
    }
}


// Creador abstracto: define el método factory
abstract class Restaurant {
    // Método factory que las subclases deben implementar
    protected abstract Hamburger crearHamburger();

    // Método plantilla que usa el factory para crear y preparar la hamburguesa
    void orderHamburger() {
        final Hamburger hamburger = this.crearHamburger();
        hamburger.prepare();
    }
}


// Creador concreto: restaurante de pollo
class ChickenRestaurant extends Restaurant {

    @Override
    protected Hamburger crearHamburger() {
        return new ChickenHamburger();
    }
}


// Creador concreto: restaurante de res
class BeefRestaurant extends Restaurant {

    @Override
    protected Hamburger crearHamburger() {
        return new BeefHamburger();
    }
}


// Creador concreto: restaurante de frijol
class BeanRestaurant extends Restaurant {

    @Override
    protected Hamburger crearHamburger() {
        return new BeanHamburger();
    }
}

/**
 * Clase demostrativa del patrón Factory Method.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que tienes una cadena de restaurantes que ofrece diferentes tipos de hamburguesas.
 * Cada sucursal puede especializarse en un tipo de hamburguesa, pero el proceso de pedido es el mismo para el cliente.
 *
 * Usando el patrón Factory Method, puedes agregar nuevos tipos de hamburguesas o restaurantes
 * simplemente creando nuevas subclases, sin modificar el código del cliente ni la lógica de pedido.
 *
 * Así, el sistema es flexible, escalable y fácil de mantener.
 */
public class FactoryMethodPattern_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant;

        System.out.print("¿Qué tipo de hamburguesa quieres? ( chicken/beef/bean): ");

        final String burgerType = scanner.nextLine().trim().toLowerCase();

        switch (burgerType) {
            case "chicken":
                restaurant = new ChickenRestaurant();
                break;

            case "beef":
                restaurant = new BeefRestaurant();
                break;

            case "bean":
                restaurant = new BeanRestaurant();
                break;

            default:
                scanner.close();
                throw new IllegalArgumentException("Opción no válida");
        }

        scanner.close();
        restaurant.orderHamburger();
    }
}
