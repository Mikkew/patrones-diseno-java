/**
 * ! Patrón mediator
 * Es un patrón de diseño de comportamiento que ayuda a reducir
 * las dependencias desordenadas entre objetos.
 * Este patrón limita la comunicación directa entre ellos,
 * haciendo que solo interactúen a través de un objeto mediador.
 * <p>
 * * Es útil reducir la complejidad de las relaciones entre objetos
 * <p>
 * https://refactoring.guru/es/design-patterns/mediator
 */

/**
 * 1.	Clase ControlTower:
 •	Actúa como el Mediador entre los aviones.
 La torre de control coordina las comunicaciones entre los aviones
 para evitar colisiones y recibir sus solicitudes de despegue
 o aterrizaje.

 2.	Clase Airplane:
 •	Representa a un avión que puede enviar y recibir mensajes
 a través de la torre de control.
 Los aviones no se comunican directamente entre sí,
 sino a través de la torre de control, que gestiona la información.

 3.	Interacciones:
 •	Los aviones pueden solicitar permiso para aterrizar o despegar,
 y la torre de control enviará mensajes a los demás aviones
 notificándoles de la actividad de cada avión.
 */

package com.mms.patterns.desing.p03_comportamiento.c04_mediator;

import java.util.ArrayList;
import java.util.List;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Clase Mediador - ControlTower
class ControlTower {
    private List<Airplane> airplanes;

    public ControlTower() {
        airplanes = new ArrayList<>();
    }

    // Registrar un avión en la torre de control
    public void registerAirplane(Airplane airplane) {
        this.airplanes.add(airplane);
    }

    // Enviar un mensaje de un avión a todos los demás
    public void sendMessage(Airplane sender, String message) {
        this.airplanes.stream()
                .filter(airplane -> airplane != sender)
                .forEach(airplane -> airplane.receiveMessage(sender, message));
    }

    // Coordinación de aterrizaje
    public void requestLanding(Airplane sender) {
        System.out.println(String.format("%sTorre de Control: %sPermiso de aterrizaje concedido a %s%s", GREEN, WHITE, sender.getId(), RESET));

        this.sendMessage(sender, String.format("%s está aterrizando.", sender.getId()));
    }

    // Coordinación de despegue
    public void requestTakeoff(Airplane sender) {
        System.out.println(String.format("%sTorre de Control: %sPermiso de despegue concedido a %s%s", GREEN, WHITE, sender.getId(), RESET));

        this.sendMessage(sender, String.format("%s está despegando.", sender.getId()));
    }
}

// Clase Colega - Airplane
class Airplane {
    private String id;
    private ControlTower controlTower;

    public Airplane(String id, ControlTower controlTower) {
        this.id = id;
        this.controlTower = controlTower;
    }

    public String getId() {
        return id;
    }

    // Solicitar aterrizaje a la torre de control
    public void requestLanding() {
        System.out.println(String.format("%s solicita permiso para aterrizar.", this.id));

        this.controlTower.requestLanding(this);
    }

    // Solicitar despegue a la torre de control
    public void requestTakeoff() {
        System.out.println(String.format("%s solicita permiso para despegar.", this.id));

        this.controlTower.requestTakeoff(this);
    }

    // Recibir mensaje de otros aviones
    public void receiveMessage(Airplane sender, String message) {
        System.out.println(String.format("%s recibe mensaje de %s%s: \"%s\"%s", this.id, BLUE, sender.getId(), message, RESET));
    }
}

/**
 * Caso de uso: Coordinación de aviones en una torre de control
 *
 * Este ejemplo simula la comunicación entre aviones y una torre de control en un aeropuerto.
 * Los aviones solicitan permiso para aterrizar o despegar, y la torre de control actúa como mediador,
 * notificando a los demás aviones sobre la actividad de cada uno.
 * Los aviones no se comunican directamente entre sí, sino a través de la torre de control.
 *
 * El patrón Mediator reduce el acoplamiento entre los objetos participantes y facilita la extensión del sistema.
 */
public class MediatorPattern_02 {
    public static void main(String[] args) {
        // Se crea el mediador (torre de control)
        ControlTower controlTower = new ControlTower();

        // Se crean los aviones y se registran en la torre de control
        Airplane airplane1 = new Airplane("Vuelo 101", controlTower);
        Airplane airplane2 = new Airplane("Vuelo 202", controlTower);
        Airplane airplane3 = new Airplane("Vuelo 303", controlTower);

        // Los aviones solicitan aterrizaje o despegue a través del mediador
        airplane1.requestLanding();
        airplane2.requestTakeoff();
        airplane3.requestLanding();
    }
}
