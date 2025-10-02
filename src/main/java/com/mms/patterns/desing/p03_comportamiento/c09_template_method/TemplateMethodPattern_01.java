/**
 * ! Patrón Template Method
 *
 * El patrón Template Method es un patrón de diseño de comportamiento
 * que define el esqueleto de un algoritmo en una operación,
 * delegando algunos pasos a las subclases.
 *
 * Permite que las subclases redefinan ciertos pasos de un algoritmo
 * sin cambiar su estructura.
 *
 * * Es útil cuando se tiene un algoritmo que sigue una secuencia de pasos
 * * y se quiere permitir a las subclases que redefinan algunos de esos pasos.
 *
 * https://refactoring.guru/es/design-patterns/template-method
 */

package com.mms.patterns.desing.p03_comportamiento.c09_template_method;

/**
 * Contexto: Vamos a implementar un sistema que permite preparar
 * diferentes bebidas calientes, como café y té.
 *
 * Aunque el proceso general para preparar ambas bebidas es similar
 * (hervir agua, añadir el ingrediente principal, servir en una taza),
 * hay pasos específicos que varían dependiendo de la bebida.
 *
 * El patrón Template Method es perfecto para este caso,
 * ya que define un esqueleto general del algoritmo en una clase base
 * y delega los detalles específicos a las subclases.
 */

abstract class HotBeverage {
    public void prepare() {
        boilWater();
        addMainIngredient();
        pourInCup();
        addCondiments();
    }

    // Pasos comunes
    private void boilWater() {
        System.out.println("Hirviendo agua...");
    }

    private void pourInCup() {
        System.out.println("Sirviendo en la taza...");
    }

    protected abstract void addMainIngredient();

    protected abstract void addCondiments();
}

class Tea extends HotBeverage {

    @Override
    protected void addMainIngredient() {
        System.out.println("Añadiendo una bolsa de té");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Añadiendo miel y limón");
    }
}

class Coffee extends HotBeverage {

    @Override
    protected void addMainIngredient() {
        System.out.println("Añadiendo café molido");
    }

    @Override
    protected void addCondiments()  {
        System.out.println("Añadiendo azúcar y leche");
    }
}

public class TemplateMethodPattern_01 {
    public static void main(String[] args) {
        System.out.println("Preparando el té");
        HotBeverage tea = new Tea();
        tea.prepare();

        System.out.println("\nPreparando café");
        HotBeverage coffee = new Coffee();
        coffee.prepare();
    }
}
