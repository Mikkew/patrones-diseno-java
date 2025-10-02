/**
 * ! Patrón decorador
 * Es un patrón de diseño estructural que permite añadir
 * funcionalidades a objetos, colocando estos objetos dentro de
 * objetos encapsuladores especiales que contienen estas funcionalidades.
 *
 * No confundirlo con los decoradores de TypeScript que son anotaciones.
 *
 * * Es útil cuando necesitas añadir funcionalidades a objetos
 *  * de manera dinámica y flexible.
 *
 * https://refactoring.guru/es/design-patterns/decorator
 */

package com.mms.patterns.desing.p02_estructurales.c04_decorator;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Componente base: define la interfaz común para notificaciones
interface Notification {
    void send(String message);
}

// Componente concreto: implementación básica de notificación
class BasicNotification implements Notification {

    @Override
    public void send(String message) {
        final String fmt = String.format("%sEnviando notificación básica: %s%s%s%s",
                BLUE, RESET, BLUE, message, RESET);
        System.out.println(fmt);
    }
}

// Decorador base: implementa la interfaz y almacena una referencia al componente
abstract class NotificationDecorator implements Notification {
    protected Notification notification;

    NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    // Por defecto, delega la llamada al componente decorado
    public void send(String message) {
        this.notification.send(message);
    }
}

// Decorador concreto: añade funcionalidad de notificación por email
class EmailDecorator extends NotificationDecorator {

    EmailDecorator(Notification notification) {
        super(notification);
    }

    // Método privado que simula el envío de email
    private void sendEmail(String message) {
        final String fmt = String.format("%sEnviando notificación por correo electrónico:%s %s%s%s",
                GREEN, RESET, WHITE, message, RESET);
        System.out.println(fmt);
    }

    // Llama primero al decorado y luego añade el comportamiento extra
    @Override
    public void send(String message) {
        super.send(message);
        sendEmail(message);
    }
}

// Decorador concreto: añade funcionalidad de notificación por SMS
class SMSDecorator extends NotificationDecorator {

    SMSDecorator(Notification notification) {
        super(notification);
    }

    // Método privado que simula el envío de SMS
    private void sendEmail(String message) {
        final String fmt = String.format("%sEnviando notificación por SMS:%s %s%s%s",
                RED, RESET, WHITE, message, RESET);
        System.out.println(fmt);
    }

    // Llama primero al decorado y luego añade el comportamiento extra
    @Override
    public void send(String message) {
        super.send(message);
        sendEmail(message);
    }
}

/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de notificaciones donde se puede agregar
 * funcionalidad adicional (enviar por email, SMS, etc.) a una notificación básica,
 * sin modificar la clase original ni crear una jerarquía de herencia rígida.
 *
 * El patrón Decorator permite apilar decoradores para combinar funcionalidades
 * dinámicamente en tiempo de ejecución. En el main, se crea una notificación básica
 * y se le agregan decoradores de email y SMS, demostrando cómo se pueden añadir
 * responsabilidades adicionales de forma flexible.
 */
public class DecoratorPattern_01 {
    public static void main(String[] args) {
        // Crear una notificación básica
        Notification notification = new BasicNotification();

        // Decorar con funcionalidad de email
        notification = new EmailDecorator(notification);
        // Decorar con funcionalidad de SMS (apilado)
        notification = new SMSDecorator(notification);

        // Enviar la notificación: se ejecutan todas las capas de decoradores
        notification.send("Alerta de sistema!");
        // El resultado será que se imprime la notificación básica, luego la de email y luego la de SMS
    }
}
