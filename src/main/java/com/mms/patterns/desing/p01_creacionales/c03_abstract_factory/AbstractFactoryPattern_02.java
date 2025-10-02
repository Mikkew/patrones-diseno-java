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
 * !Instrucciones:
 	1.Completen las Clases de Productos:
    •	ElectricCar debe implementar Vehicle y mostrar el mensaje "Ensamblando un auto eléctrico".
    •	GasCar debe implementar Vehicle y mostrar el mensaje "Ensamblando un auto de combustión".
    •	ElectricEngine debe implementar Engine y mostrar el mensaje "Arrancando motor eléctrico".
    •	GasEngine debe implementar Engine y mostrar el mensaje "Arrancando motor de combustión".

	2.	Completen las Clases de Fábricas:
    •	ElectricVehicleFactory debe crear un ElectricCar y un ElectricEngine.
    •	GasVehicleFactory debe crear un GasCar y un GasEngine.

	3. Prueben el Código:
	  •	Ejecuten el código para asegurarse de que cada fábrica produce el tipo correcto de vehículo y motor.

 */

// Producto abstracto: interfaz para vehículos
interface Vehicle {

    void assemble();
}

// Producto abstracto: interfaz para motores
interface Engine {

    void start();
}


// Producto concreto: auto eléctrico
class ElectricCar implements Vehicle {

    @Override
    public void assemble() {
        System.out.println(BLUE + "Ensamblando un auto eléctrico" + RESET);
    }
}


// Producto concreto: auto de combustión
class GasCar implements Vehicle {

    @Override
    public void assemble() {
        System.out.println(BROWN + "Ensamblando un auto de combustión" + RESET);
    }
}


// Producto concreto: motor eléctrico
class ElectricEngine implements Engine {

    @Override
    public void start() {
        System.out.println(BLUE + "Arrancando motor eléctrico" + RESET);
    }
}


// Producto concreto: motor de combustión
class GasEngine implements Engine {

    @Override
    public void start() {
        System.out.println(BROWN + "rrancando motor de combustión" + RESET);
    }
}

// Fábrica abstracta: define los métodos para crear productos de una familia
interface VehicleFactory {

    Vehicle createVehicle();

    Engine createEngine();
}


// Fábrica concreta: familia de vehículos y motores eléctricos
class ElectricVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        return new ElectricCar();
    }

    @Override
    public Engine createEngine() {
        return new ElectricEngine();
    }
}


// Fábrica concreta: familia de vehículos y motores de combustión
class GasVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        return new GasCar();
    }

    @Override
    public Engine createEngine() {
        return new GasEngine();
    }
}

/**
 * Clase demostrativa del patrón Abstract Factory.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que una fábrica de automóviles debe producir diferentes líneas de vehículos (eléctricos y de combustión),
 * asegurando que cada vehículo tenga el motor adecuado y compatible.
 *
 * Usando el patrón Abstract Factory, puedes crear nuevas familias de productos simplemente creando nuevas fábricas,
 * sin modificar el código cliente ni la lógica de ensamblaje.
 *
 * Así, el sistema es flexible, escalable y fácil de mantener.
 */
public class AbstractFactoryPattern_02 {

    public static void vehicle(VehicleFactory factory) {
        Vehicle vehicle = factory.createVehicle();
        Engine engine = factory.createEngine();

        vehicle.assemble();
        engine.start();
    }

    public static void main(String[] args) {
        System.out.println("Creando vehículo eléctrico: ");
        vehicle(new ElectricVehicleFactory());

        System.out.println("Creando vehículo de combustión:");
        vehicle(new GasVehicleFactory());
    }
}
