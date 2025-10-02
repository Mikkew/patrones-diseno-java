/**
 * !Patrón Visitor
 *
 * El patrón Visitor es un patrón de diseño de comportamiento
 * que te permite separar algoritmos de los objetos sobre
 * los que operan.
 *
 * * Es útil cuando necesitas añadir nuevas operaciones a
 * * clases estables sin cambiar su código.
 *
 * https://refactoring.guru/es/design-patterns/visitor
 */

package com.mms.patterns.desing.p03_comportamiento.c10_visitor;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

/**
 * Contexto: Imagina que estás diseñando un sistema para un parque
 * temático con diferentes tipos de atracciones:
 * montañas rusas, casas del terror y ruedas de la fortuna.
 *
 * Cada atracción tiene su propio precio de entrada y ofrece un descuento
 * dependiendo del tipo de visitante (niño, adulto o adulto mayor).
 *
 * Aquí es donde entra el patrón Visitor, que permite aplicar operaciones
 * específicas (como calcular el precio con descuento) dependiendo tanto
 * de la atracción como del tipo de visitante,
 * sin modificar las clases originales.
 */

interface Visitor {
    void visitRollerCoaster(RollerCoaster rollerCoaster);

    void visitHauntedHouse(HauntedHouse hauntedHouse);

    void visitFerrisWheel(FerrisWheel ferrisWheel);
}

interface Attraction {
    void accept(Visitor visitor);
    
    int getPrice();
}

class RollerCoaster implements Attraction {
    private final int price = 50;

    public int getPrice() { 
        return price; 
    }

    public void accept(Visitor visitor) { 
        visitor.visitRollerCoaster(this); 
    }
}

class HauntedHouse implements Attraction {
    private final int price = 40;
    
    public int getPrice() { 
        return price; 
    }

    public void accept(Visitor visitor) { 
        visitor.visitHauntedHouse(this); 
    }
}

class FerrisWheel implements Attraction {
    private final int price = 30;

    public int getPrice() { 
        return price; 
    }

    public void accept(Visitor visitor) { 
        visitor.visitFerrisWheel(this); 
    }
}

class ChildVisitor implements Visitor {
    public void visitRollerCoaster(RollerCoaster r) {
        System.out.println("Niño en Montaña Rusa: Precio con descuento de $" + (r.getPrice() * 0.5));
    }
    public void visitHauntedHouse(HauntedHouse h) {
        System.out.println("Niño en Casa del Terror: Precio con descuento de $" + (h.getPrice() * 0.7));
    }
    public void visitFerrisWheel(FerrisWheel f) {
        System.out.println("Niño en la Rueda de la Fortuna: Precio con descuento de $" + (f.getPrice() * 0.6));
    }
}

class AdultVisitor implements Visitor {
    public void visitRollerCoaster(RollerCoaster r) {
        System.out.println("Adulto en Montaña Rusa: Precio con descuento de $" + r.getPrice());
    }
    public void visitHauntedHouse(HauntedHouse h) {
        System.out.println("Adulto en Casa del Terror: Precio con descuento de $" + h.getPrice());
    }
    public void visitFerrisWheel(FerrisWheel f) {
        System.out.println("Adulto en la Rueda de la Fortuna: Precio con descuento de $" + f.getPrice());
    }
}

class SeniorVisitor implements Visitor {
    public void visitRollerCoaster(RollerCoaster r) {
        System.out.println("Adulto mayor en Montaña Rusa: Precio con descuento de $" + (r.getPrice() * 0.85));
    }
    public void visitHauntedHouse(HauntedHouse h) {
        System.out.println("Adulto mayor en Casa del Terror: Precio con descuento de $" + (h.getPrice() * 0.85));
    }
    public void visitFerrisWheel(FerrisWheel f) {
        System.out.println("Adulto mayor en la Rueda de la Fortuna: Precio con descuento de $" + (f.getPrice() * 0.85));
    }
}

public class VisitorPattern_01 {
    public static void main(String[] args) {
        Attraction[] attractions = {
            new RollerCoaster(),
            new HauntedHouse(),
            new FerrisWheel()
        };

        System.out.println("Montaña Rusa: " + new RollerCoaster().getPrice());
        System.out.println("Casa del Terror: " + new HauntedHouse().getPrice());
        System.out.println("La Rueda de la fortuna: " + new FerrisWheel().getPrice());
        System.out.println();

        System.out.println(String.format("\n%sVisitante Niño%s",  GREEN, RESET));
        Visitor child = new ChildVisitor();
        for (Attraction a : attractions) a.accept(child);

        System.out.println(String.format("\n%sVisitante Adulto%s", PURPLE, RESET));
        Visitor adult = new AdultVisitor();
        for (Attraction a : attractions) a.accept(adult);

        System.out.println(String.format("\n%sVisitante Adulto Mayor%s", PURPLE, RESET));
        Visitor senior = new SeniorVisitor();

        for (Attraction a : attractions) a.accept(senior);
    }

}
