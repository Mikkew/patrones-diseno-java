/**
 * !Patrón Visitor
 * <p>
 * El patrón Visitor es un patrón de diseño de comportamiento
 * que te permite separar algoritmos de los objetos sobre
 * los que operan.
 * <p>
 * * Es útil cuando necesitas añadir nuevas operaciones a
 * * clases estables sin cambiar su código.
 * <p>
 * https://refactoring.guru/es/design-patterns/visitor
 */

/**
 * !Objetivo:
 * Implementar el patrón Visitor en un sistema de gestión de vehículos
 * que permite realizar operaciones específicas sobre diferentes
 * tipos de vehículos (automóviles, motocicletas y camiones).
 *
 * Estas operaciones incluyen calcular el costo de mantenimiento
 * y verificar si los vehículos cumplen con las normas de emisión.
 */

package com.mms.patterns.desing.p03_comportamiento.c10_visitor;

// Interfaz Visitor
interface VisitorV2 {
    void visitCar(Car car);

    void visitMotorcycle(Motorcycle motorcycle);

    void visitTruck(Truck truck);
}

// Interfaz Vehicle
interface Vehicle {
    void accept(VisitorV2 visitor);
}

class Car implements Vehicle {
    private Integer year;
    private Integer kilometers;

    public Car(Integer year, Integer kilometers) {
        this.year = year;
        this.kilometers = kilometers;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    @Override
    public void accept(VisitorV2 visitor) {
        visitor.visitCar(this);
    }
}

class Motorcycle implements Vehicle {
    private Integer year;
    private Integer kilometers;

    public Motorcycle(Integer year, Integer kilometers) {
        this.year = year;
        this.kilometers = kilometers;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    @Override
    public void accept(VisitorV2 visitor) {
        visitor.visitMotorcycle(this);
    }
}

class Truck implements Vehicle {
    private Integer year;
    private Integer kilometers;
    private Integer loadCapacity;

    public Truck(Integer year, Integer kilometers, Integer loadCapacity) {
        this.year = year;
        this.kilometers = kilometers;
        this.loadCapacity = loadCapacity;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public Integer getLoadCapacity() {
        return loadCapacity;
    }

    public void accept(VisitorV2 visitor) {
        visitor.visitTruck(this);
    }
}

class MaintenanceCostVisitor implements VisitorV2 {

    @Override
    public void visitCar(Car car) {
        Double cost = car.getKilometers() * 0.1 + (2024 - car.getYear()) * 50;
        System.out.printf("Costo de mantenimiento para el automóvil: $%.2f\n", cost);
    }

    @Override
    public void visitMotorcycle(Motorcycle motorcycle) {
        Double cost = motorcycle.getKilometers() * 0.05 + (2024 - motorcycle.getYear()) * 30;
        System.out.printf("Costo de mantenimiento para la motocicleta: $%.2f\n", cost);
    }

    @Override
    public void visitTruck(Truck truck) {
        Double cost = truck.getKilometers() * 0.15 + truck.getLoadCapacity() * 20 + (2024 - truck.getYear()) * 100;
        System.out.printf("Costo de mantenimiento para el camión: $%.2f\n", cost);
    }
}

class EmissionCheckVisitor implements VisitorV2 {

    @Override
    public void visitCar(Car car) {
        boolean passes = car.getYear() > 2000 && car.getKilometers() < 200_000;
        System.out.println("Automóvil cumple con emisiones: " + (passes ? "Sí" : "No"));
    }

    @Override
    public void visitMotorcycle(Motorcycle motorcycle) {
        boolean passes = motorcycle.getYear() > 2005 && motorcycle.getKilometers() < 100_000;
        System.out.println("Motocicleta cumple con emisiones: " + (passes ? "Sí" : "No"));
    }

    @Override
    public void visitTruck(Truck truck) {
        boolean passes = truck.getYear() > 2010 && truck.getKilometers() < 300_000;
        System.out.println("Camión cumple con emisiones: " + (passes ? "Sí" : "No"));
    }
}

public class VisitorPattern_02 {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new Car(2000, 150_000),
            new Motorcycle(2015, 30_000),
            new Truck(2012, 250_000, 20)
        };

        System.out.println("\nCalculando costos de mantenimiento:");
        VisitorV2 maintenanceVisitor = new MaintenanceCostVisitor();
        for (Vehicle v : vehicles) v.accept(maintenanceVisitor);

        System.out.println("\nVerificando emisiones:");
        VisitorV2 emissionVisitor = new EmissionCheckVisitor();
        for (Vehicle v : vehicles) v.accept(emissionVisitor);
    }
}
