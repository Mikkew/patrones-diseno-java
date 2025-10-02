/**
 * ! Patrón Bridge
 * Este patrón nos permite desacoplar una abstracción de su implementación,
 * de tal forma que ambas puedan variar independientemente.
 *
 * * Es útil cuando se tienen múltiples implementaciones de una abstracción
 * * Se puede utilizar para separar la lógica de negocio de la lógica de presentación
 * * Se puede utilizar para separar la lógica de la interfaz de usuario también.
 *
 *  * Caso de uso en este ejemplo:
 * ---------------------------------------------
 * Imagina un sistema de notificaciones que puede enviar mensajes por diferentes canales
 * (correo electrónico, SMS, push, etc.) y que además puede tener distintos tipos de notificaciones
 * (alertas, recordatorios, notificaciones generales, etc.).
 *
 * El patrón Bridge permite que los tipos de notificación (abstracción) y los canales de envío
 * (implementación) evolucionen de forma independiente, evitando una explosión de clases y
 * facilitando la extensión del sistema.
 *
 * Ventajas:
 * - Permite agregar nuevos canales o tipos de notificación sin modificar las clases existentes.
 * - Favorece la composición sobre la herencia.
 * - Reduce el acoplamiento entre la lógica de negocio y la de comunicación.
 *
 * Estructura:
 * - Abstracción: Notification (y sus subclases)
 * - Implementador: NotificationChannel (y sus implementaciones)
 *
 * Uso típico: sistemas de mensajería, renderizado gráfico, controladores de dispositivos, etc.
 */

package com.mms.patterns.desing.p02_estructurales.c02_bridge;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// 1. Interfaz NotificationChannel
// ---------------------------------------------
// Define el "implementador" en el patrón Bridge.
// Cada canal de comunicación concreto implementará este método para enviar mensajes.
interface NotificationChannel {
    void send(String message);
}

// 2. Implementaciones de Canales de Comunicación
class EmailChannel implements NotificationChannel {

    @Override
    public void send(String message) {
        System.out.println("Enviando correo electrónico: " + message);
    }
}

class SMSChannel implements NotificationChannel {

    @Override
    public void send(String message) {
        System.out.println("Enviando SMS: " + message);
    }
}

class PushNotificationChannel implements NotificationChannel {

    @Override
    public void send(String message) {
        System.out.println("Enviando Push: " + message);
    }
}

// 3. Clase Abstracta Notification (Abstracción)
// ---------------------------------------------
// Esta clase representa el "lado de la abstracción" en el patrón Bridge.
// Contiene una referencia a un NotificationChannel, que es el implementador.
// Permite cambiar el canal en tiempo de ejecución.
abstract class Notification {
    protected NotificationChannel channel;

    Notification(NotificationChannel channel) {
        this.channel = channel;
    }

    // Método abstracto para enviar la notificación
    abstract void notify(String message);

    // Permite cambiar el canal de envío en tiempo de ejecución
    abstract void setChannel(NotificationChannel channel);
}

// 4. Clases Concretas de Notificaciones (Refinamientos de la Abstracción)
// ---------------------------------------------
// Cada subclase representa un tipo de notificación con su propio
// comportamiento.
// Todas delegan el envío al canal configurado.
class AlertNotification extends Notification {
    // Constructor recibe el canal de envío
    AlertNotification(NotificationChannel channel) {
        super(channel);
    }

    // Envía una notificación de alerta usando el canal actual
    @Override
    void notify(String message) {
        System.out.println(RED + "Notificación de Alerta:" + RESET);
        this.channel.send(message);
    }

    // Permite cambiar el canal de envío
    @Override
    void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }
}

class ReminderNotification extends Notification {
    ReminderNotification(NotificationChannel channel) {
        super(channel);
    }

    // Envía una notificación de recordatorio usando el canal actual
    @Override
    void notify(String message) {
        System.out.println(BLUE + "Notificación de Recordatorio:" + RESET);
        this.channel.send(message);
    }

    @Override
    void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }
}

class PushNotification extends Notification {
    PushNotification(NotificationChannel channel) {
        super(channel);
    }

    // Envía una notificación push usando el canal actual
    @Override
    void notify(String message) {
        System.out.println(GREEN + "Notificación de Push:" + RESET);
        this.channel.send(message);
    }

    @Override
    void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }
}

// 5. Código Cliente para Probar el Bridge
// ---------------------------------------------
// Aquí se demuestra cómo el cliente puede crear diferentes tipos de
// notificaciones
// y cambiar dinámicamente el canal de envío, sin modificar las clases
// concretas.

/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de notificaciones donde existen distintos
 * tipos
 * de notificación (alerta, recordatorio, push) y diferentes canales de envío
 * (correo, SMS, push). El patrón Bridge permite que ambos evolucionen de forma
 * independiente: puedes agregar nuevos tipos de notificación o nuevos canales
 * sin modificar el resto del sistema.
 *
 * En el main, se crean notificaciones de diferentes tipos y se demuestra cómo
 * se puede cambiar el canal de envío en tiempo de ejecución, mostrando la
 * flexibilidad
 * y extensibilidad del patrón.
 */

public class BridgePattern_02 {

    public static void main(String[] args) {
        // Crear una notificación de alerta usando el canal de correo electrónico
        Notification alert = new AlertNotification(new EmailChannel());

        // Enviar alerta por correo electrónico
        alert.notify("Alerta de seguridad: Se ha detectado un acceso no autorizado.");

        // Cambiar el canal a SMS y volver a enviar la alerta
        alert.setChannel(new SMSChannel());
        alert.notify("Alerta de seguridad: Se ha detectado un acceso no autorizado.");

        // Crear una notificación de recordatorio usando el canal de SMS
        Notification reminder = new ReminderNotification(new SMSChannel());
        reminder.notify(
                "Recordatorio: Tu cita con el médico es mañana a las 10:00 a.m.");

        // Cambiar el canal de recordatorio a correo electrónico y enviar nuevamente
        reminder.setChannel(new PushNotificationChannel());
        reminder.notify(
                "Recordatorio: Tu cita con el médico es mañana a las 10:00 a.m.");

        // Crear una notificación de push usando el canal de notificación push
        Notification push = new PushNotification(new PushNotificationChannel());
        push.notify("Nueva actualización disponible. Haz clic para instalar.");
    }
}
