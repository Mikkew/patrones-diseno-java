/**
 * ! Patrón Composite
 * Es un patrón de diseño estructural que permite componer objetos
 * en estructuras de árbol para representar jerarquías.
 * <p>
 * El patrón permite a los clientes tratar de manera uniforme a los objetos
 * individuales y a sus composiciones.
 * <p>
 * * Es útil cuando necesitas tratar a los objetos individuales
 * * y a sus composiciones de manera uniforme, y la estructura
 * * de los objetos forma una jerarquía en árbol.
 * <p>
 * https://refactoring.guru/es/design-patterns/composite
 *
 */

package com.mms.patterns.desing.p02_estructurales.c03_composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Interfaz base para el patrón Composite
interface MenuComponent {
    default void showDetails() {
        showDetails("");
    }
    void showDetails(String indent);
}

// Implementación para ítems del menú (Leaf)
class MenuItem implements MenuComponent {
    private final String name;
    private final double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showDetails(String indent) {
        System.out.printf("%s- %s: %s$%.2f%s\n",
                         indent,
                         name,
                         GREEN,
                         price,
                         RESET);
    }
}

// Implementación para categorías del menú (Composite)
class MenuCategory implements MenuComponent {
    private final String name;
    private final List<MenuComponent> items;

    public MenuCategory(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void add(MenuComponent item) {
        items.add(item);
    }

    public void addAll(List<MenuComponent> items) {
        this.items.addAll(items);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(BLUE + indent + "+ " + name + RESET);
        items.forEach(item -> item.showDetails(indent + "  "));
    }
}

// Clase principal para demostración
/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula la estructura de un menú de restaurante, donde existen ítems individuales
 * (hojas, como "Ensalada" o "Café") y categorías que pueden contener tanto ítems como otras categorías
 * (compuestos, como "Bebidas" o "Menú Principal").
 *
 * El patrón Composite permite tratar de manera uniforme a ítems y categorías, facilitando la construcción
 * y visualización de menús jerárquicos de cualquier profundidad. En el main, se construye un menú completo
 * y se imprime toda la jerarquía usando una sola llamada a showDetails().
 */
public class CompositePattern_02 {
    public static void main(String[] args) {
        // Crear ítems individuales
        MenuItem salad = new MenuItem("Ensalada", 5.99);
        MenuItem soup = new MenuItem("Sopa de tomate", 4.99);
        MenuItem steak = new MenuItem("Bistec", 15.99);
        MenuItem soda = new MenuItem("Refresco", 2.5);
        MenuItem dessert = new MenuItem("Pastel de chocolate", 6.5);
        MenuItem coffee = new MenuItem("Café", 1.99);
        MenuItem tea = new MenuItem("Té", 0.99);

        // Crear estructura jerárquica del menú
        MenuCategory appetizers = new MenuCategory("Entradas");
        appetizers.add(salad);
        appetizers.add(soup);

        MenuCategory mainCourse = new MenuCategory("Plato Principal");
        mainCourse.add(steak);

        MenuCategory beverages = new MenuCategory("Bebidas");
        MenuCategory hotBeverages = new MenuCategory("Bebidas Calientes");
        MenuCategory coldBeverages = new MenuCategory("Bebidas Frías");

        hotBeverages.addAll(Arrays.asList(coffee, tea));
        coldBeverages.add(soda);
        beverages.addAll(Arrays.asList(hotBeverages, coldBeverages));

        MenuCategory desserts = new MenuCategory("Postres");
        desserts.add(dessert);

        // Crear menú principal
        MenuCategory mainMenu = new MenuCategory("Menú Principal");
        mainMenu.addAll(Arrays.asList(appetizers, mainCourse, beverages, desserts));

        // Mostrar estructura completa
        System.out.println("\nMenú del Restaurante:");
        mainMenu.showDetails();
    }
}