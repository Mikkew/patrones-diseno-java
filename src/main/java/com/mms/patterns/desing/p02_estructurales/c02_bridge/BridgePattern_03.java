/**
 * ! Patrón Bridge
 * Este patrón nos permite desacoplar una abstracción de su implementación,
 * de tal forma que ambas puedan variar independientemente.
 *
 * * Es útil cuando se tienen múltiples implementaciones de una abstracción
 * * Se puede utilizar para separar la lógica de negocio de la lógica de presentación
 * * Se puede utilizar para separar la lógica de la interfaz de usuario también.
 *
 */

package com.mms.patterns.desing.p02_estructurales.c02_bridge;

import java.util.Arrays;
import java.util.List;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// 3. Clase Abstracta Notification
// Define la propiedad `channel` y el método `notify`
abstract class Notifications {
    protected List<NotificationChannel> channels;

    // Constructor recibe la lista de canales a utilizar
    Notifications(List<NotificationChannel> channels) {
        this.channels = channels;
    }

    // Método abstracto para enviar la notificación por todos los canales
    abstract void notify(String message);

    // Permite agregar un canal adicional en tiempo de ejecución
    abstract void addChannel(NotificationChannel channel);
}

// Refinamiento de la abstracción: notificación de alerta
// Envía el mensaje por todos los canales configurados
class AlertNotifications extends Notifications {

    AlertNotifications(List<NotificationChannel> channels) {
        super(channels);
    }

    // Envía la alerta por todos los canales disponibles
    @Override
    void notify(String message) {
        System.out.println(RED + "Notificación de Alerta:" + RESET);
        this.channels.forEach(channel -> channel.send(message));
    }

    // Permite agregar un canal adicional
    @Override
    void addChannel(NotificationChannel channel) {
        this.channels.add(channel);
    }
}

// Código cliente: crea una notificación de alerta y la envía por múltiples
// canales
/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de notificaciones donde una alerta debe
 * enviarse
 * por varios canales (correo, SMS, push, etc.) de forma simultánea. El patrón
 * Bridge
 * permite que la lógica de notificación (AlertNotifications) y los canales de
 * envío
 * (NotificationChannel) evolucionen de manera independiente, facilitando la
 * extensión
 * y el mantenimiento del sistema. Así, se pueden agregar nuevos canales o tipos
 * de
 * notificación sin modificar el resto del código.
 *
 * En el main, se crea una lista de canales y se envía una alerta por todos
 * ellos.
 */
public class BridgePattern_03 {

    public static void main(String[] args) {
        // Crear una lista de canales de notificación (implementadores)
        List<NotificationChannel> channels = Arrays.asList(
                new EmailChannel(),
                new SMSChannel(),
                new PushNotificationChannel(),
                new PushNotificationChannel(),
                new PushNotificationChannel(),
                new SMSChannel(),
                new EmailChannel());

        // Crear una notificación de alerta usando los canales definidos
        Notifications alert = new AlertNotifications(channels);

        // Enviar la notificación de alerta por todos los canales
        alert.notify("Alguien en frente de la casa");
    }
}
