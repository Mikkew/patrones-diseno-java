/**
 * ! Patrón Strategy
 *
 * El patrón Strategy es un patrón de diseño de software que define una
 * familia de algoritmos, los encapsula y los hace intercambiables.
 *
 *
 * * Es útil cuando se tiene una clase que tiene un comportamiento que puede
 * * cambiar en tiempo de ejecución y se quiere delegar la responsabilidad de
 * * la implementación a otra clase.
 *
 * https://refactoring.guru/es/design-patterns/strategy
 */

/**
 * !Objetivo:
 * Implementar el patrón Strategy para calcular los impuestos de diferentes países.
 *
 * !Descripción del Ejercicio

  Imagina que trabajas en una plataforma de comercio electrónico que opera en varios países.
  Cada país tiene su propio método para calcular impuestos,
  y necesitas implementar un sistema que sea:

    1. Flexible: Permita agregar nuevos cálculos de impuestos
       sin modificar la lógica existente.
    2. Dinámico: Cambie la estrategia de cálculo de impuestos en tiempo
       de ejecución según el país seleccionado.
 */

/**
    1.	Implementar una interfaz TaxStrategy que defina un método
        calculateTax(amount: number): number.

    2.	Crear clases específicas de estrategia para los países disponibles:
      •	USA: Impuesto del 10%.
      •	Canada: Impuesto del 13%.
      •	Germany: Impuesto del 19%.

    3.	Implementar una clase TaxCalculator que utilice las estrategias
        para calcular los impuestos.
 */

package com.mms.patterns.desing.p03_comportamiento.c08_strategy;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Interfaz Strategy
interface TaxStrategy {
  Double calculateTax(Double amount);
}

// Estrategia 1: Impuestos en USA
class USATaxStrategy implements TaxStrategy {

    @Override
    public Double calculateTax(Double amount) {
        return amount * 0.10; // 10% de impuesto
    }
}

// Estrategia 2: Impuestos en Canada
class CanadaTaxStrategy implements TaxStrategy {

    @Override
    public Double calculateTax(Double amount) {
        return amount * 0.13; // 13% de impuesto
    }
}

// Estrategia 3: Impuestos en Germany
class GermanyTaxStrategy implements TaxStrategy {

    @Override
    public Double calculateTax(Double amount) {
        return amount * 0.19; // 19% de impuesto
    }
}

// Clase Contexto - TaxCalculator
class TaxCalculator {
    private TaxStrategy strategy;

    public TaxCalculator(TaxStrategy strategy) {
        this.strategy = strategy;
    }

    // Cambiar la estrategia de cálculo de impuestos
    public void setStrategy(TaxStrategy strategy) {
        this.strategy = strategy;
    }

    // Calcular impuestos
    public Double calculate(Double amount) {
        return this.strategy.calculateTax(amount);
    }
}

public class StrategyPattern_02 {
    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator(new USATaxStrategy());
        Double amount = 100.0;

        System.out.println(String.format("%sCálculo de impuestos:%s", RED, RESET));
        System.out.println(String.format("USA: $%.2f", taxCalculator.calculate(amount)));

        System.out.println("Cambiando a estrategia para Canada...");
        taxCalculator.setStrategy(new CanadaTaxStrategy());
        System.out.println(String.format("Canada: $%.2f", taxCalculator.calculate(amount)));

        System.out.println("Cambiando a estrategia para Germany...");
        taxCalculator.setStrategy(new GermanyTaxStrategy());
        System.out.println(String.format("Germany: $%.2f", taxCalculator.calculate(amount)));
    }
}
