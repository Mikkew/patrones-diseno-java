/**
 * ! Abstract Factory:
 * Es un patrón de diseño que permite crear familias de objetos relacionados
 * sin especificar sus clases concretas.
 *
 * En lugar de crear objetos individuales directamente,
 * creamos fábricas que producen un conjunto de objetos relacionados.
 *
 * * Es útil cuando necesitas crear objetos que son parte de una familia
 * * y quieres asegurarte de que estos objetos se complementen entre sí.
 *
 * https://refactoring.guru/es/design-patterns/abstract-factory
 */

package com.mms.patterns.desing.p01_creacionales.c03_abstract_factory;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

/**
 *  El propósito del Abstract Factory es crear familias de objetos relacionados
 *  (en este caso, hamburguesas y bebidas) sin especificar las clases concretas
 *  de cada uno de esos objetos en el código principal.
 */

// Producto abstracto: interfaz para hamburguesas
interface Hamburger {

    void prepare();
}

// Producto abstracto: interfaz para bebidas
interface Drink {

    void pour();
}


// Producto concreto: hamburguesa de pollo
class ChickenHamburger implements Hamburger {

    @Override
    public void prepare() {
        System.out.println(YELLOW + "Preparando una hamburgesa de Pollo" + RESET);
    }
}


// Producto concreto: hamburguesa de res
class BeefHamburger implements Hamburger {

    @Override
    public void prepare() {
        System.out.println(RED + "Preparando una hamburgesa de Res" + RESET);
    }
}


// Producto concreto: agua
class Water implements Drink {

    @Override
    public void pour() {
        System.out.println(BLUE + "Sirviendo un vaso de agua" + RESET);
    }
}


// Producto concreto: refresco
class Soda implements Drink {

    @Override
    public void pour() {
        System.out.println(PINK + "Sirviendo un vaso de refresco" + RESET);
    }
}

// Fábrica abstracta: define los métodos para crear productos de una familia
interface RestaurantFactory {

    Hamburger createHamburger();

    Drink createDrink();
}


// Fábrica concreta: menú regular (Fast Food)
class FastFoodRestaurantFactory implements RestaurantFactory {

    @Override
    public Hamburger createHamburger() {
        return new BeefHamburger();
    }

    @Override
    public Drink createDrink() {
        return new Soda();
    }
}


// Fábrica concreta: menú saludable
class HealthyRestaurantFactory implements RestaurantFactory {

    @Override
    public Hamburger createHamburger() {
        return new ChickenHamburger();
    }

    @Override
    public Drink createDrink() {
        return new Water();
    }
}


/**
 * Clase demostrativa del patrón Abstract Factory.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que tienes una aplicación para restaurantes que debe ofrecer diferentes menús (familias de productos).
 * Cada menú debe ser consistente: si eliges el menú saludable, tanto la hamburguesa como la bebida deben ser saludables.
 *
 * Usando el patrón Abstract Factory, puedes crear nuevas familias de menús simplemente creando nuevas fábricas,
 * sin modificar el código cliente ni la lógica de pedido.
 *
 * Así, el sistema es flexible, escalable y fácil de mantener.
 */
public class AbstractFactoryPattern_01 {

    public static void restaurant(RestaurantFactory factory) {
        Hamburger hamburger = factory.createHamburger();
        Drink drink = factory.createDrink();

        hamburger.prepare();
        drink.pour();
    }

    public static void main(String[] args) {
        System.out.println(GREEN + "Pedido del menú regular:" + RESET);
        restaurant(new FastFoodRestaurantFactory());

        System.out.println(GREEN + "Pedido del menú saludable:" + RESET);
        restaurant(new HealthyRestaurantFactory());
    }
}
