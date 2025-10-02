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

/**
 * !Objetivo:
 * Implementar el patrón Template Method para simular un sistema de limpieza
 * de diferentes tipos de habitaciones
 * (por ejemplo, una habitación de hotel y una sala de conferencias).
 *
 * Debes diseñar una clase base que defina el flujo general de limpieza
 * y subclases que implementen pasos específicos dependiendo del tipo
 * de habitación.
 *
 *
 *
 * ! Descripción del Ejercicio
  El proceso de limpieza general incluye los siguientes pasos:
    1.	Entrar a la habitación: Abrir la puerta y entrar.
    2.	Recoger basura: Eliminar la basura de los botes.
    3.	Limpieza específica: Depende del tipo de habitación:
    •	En una habitación de hotel, se hacen las camas.
    •	En una sala de conferencias, se limpian las mesas y se organizan las sillas.
    • En una oficina, se limpian los escritorios y se organizan los documentos.
    4.	Desinfectar superficies: Desinfectar las áreas principales.
    5.	Salir de la habitación: Cerrar la puerta y marcar como terminada
 */

package com.mms.patterns.desing.p03_comportamiento.c09_template_method;

// Clase Base - RoomCleaning
abstract class RoomCleaning {
    public void cleanRoom() {
        enterRoom();
        collectTrash();
        specificCleaning(); // Método abstracto
        disinfectSurfaces();
        exitRoom();
        System.out.println("Limpieza terminada.\n");
    }

    // Pasos comunes
    private void enterRoom() {
        System.out.println("Entrando a la habitación...");
    }

    private void collectTrash() {
        System.out.println("Recogiendo la basura...");
    }

    private void disinfectSurfaces() {
        System.out.println("Desinfectando superficies...");
    }

    private void exitRoom() {
        System.out.println("Saliendo de la habitación y marcándola como limpia.");
    }

    protected abstract void specificCleaning();
}

class HotelRoomCleaning extends RoomCleaning {

    @Override
    protected void specificCleaning() {
        System.out.println("Haciendo las camas y reponiendo artículos de baño.");
    }
}

class ConferenceRoomCleaning extends RoomCleaning {

    @Override
    protected void specificCleaning() {
        System.out.println("Limpiando mesas y organizando sillas.");
    }
}

class OfficeCleaning extends RoomCleaning {

    @Override
    protected void specificCleaning() {
        System.out.println("Limpiando escritorios y organizando documentos.");
    }
}

public class TemplateMethodPattern_02 {
    public static void main(String[] args) {
        System.out.println("Limpieza de una habitación de hotel:");
        RoomCleaning hotelRoom = new HotelRoomCleaning();
        hotelRoom.cleanRoom();

        System.out.println("Limpieza de una sala de conferencias:");
        RoomCleaning conferenceRoom = new ConferenceRoomCleaning();
        conferenceRoom.cleanRoom();

        System.out.println("Limpieza de una oficina:");
        RoomCleaning office = new OfficeCleaning();
        office.cleanRoom();
    }
}
