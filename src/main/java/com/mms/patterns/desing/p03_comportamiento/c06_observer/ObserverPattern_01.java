/**
 * ! Patrón Observer
 * El patrón Observer es un patrón de diseño de comportamiento que establece
 * una relación de uno a muchos entre un objeto, llamado sujeto,
 * y otros objetos, llamados observadores, que son notificados
 * y actualizados automáticamente por el sujeto
 * cuando se producen cambios en su estado.
 *
 * * Es útil cuando necesitamos que varios objetos estén
 * * pendientes de los cambios
 *
 * !No confundirlo con RXJS Observables
 *
 * https://refactoring.guru/es/design-patterns/observer
 */

package com.mms.patterns.desing.p03_comportamiento.c06_observer;

import java.util.ArrayList;
import java.util.List;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

interface Observer {
    void notify(String videoTitle);
}

class YouTubeChannel {
    private List<Observer> subscribers;
    private String name;

    public YouTubeChannel(String name) {
        subscribers = new ArrayList<>();
        this.name = name;
    }

    public void subscribe(Observer observer) {
        this.subscribers.add(observer);
        System.out.println(String.format("Nuevo suscriptor al canal %s%s%s", GREEN, this.name, RESET));
    }

    public void unsubscribe(Observer observer) {
        this.subscribers = this.subscribers.stream()
                .filter(sub -> sub != observer).toList();
        System.out.println(String.format("Un suscriptor se ha dado de baja \"%s\"", this.name));
    }

    public void uploadVideo(String videoTitle) {
        System.out.println(String.format("Canal %s ha subido un nuevo video %s%s%s", this.name, GREEN, videoTitle, RESET));

        for (Observer subscriber : subscribers) {
            subscriber.notify(videoTitle);
        }
    }
}

class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void notify(String videoTitle) {
        System.out.println(String.format("%s%s%s ha sido notificado: %sNuevo video %s%s",
                BLUE, this.name,RESET, YELLOW, videoTitle,RESET));
    }
}

/**
 * Caso de uso: Notificaciones automáticas de nuevos videos en un canal de YouTube
 *
 * Este ejemplo simula un canal de YouTube donde los suscriptores reciben notificaciones
 * automáticas cada vez que el canal sube un nuevo video. El canal actúa como sujeto y los
 * suscriptores como observadores. Cuando el canal cambia su estado (sube un video),
 * todos los suscriptores son notificados automáticamente.
 *
 * El patrón Observer es útil para implementar sistemas de eventos, notificaciones o suscripciones.
 */
public class ObserverPattern_01 {
    public static void main(String[] args) {
        // Se crea el canal de YouTube
        YouTubeChannel channel = new YouTubeChannel("Cocinando con Fernando");

        // Se crean los suscriptores
        Subscriber melissa = new Subscriber("Melissa");
        Subscriber cesar = new Subscriber("César");
        Subscriber emin = new Subscriber("Emin");

        // Los suscriptores se suscriben al canal
        channel.subscribe(melissa);
        channel.subscribe(cesar);

        // El canal sube un video y notifica a los suscriptores
        channel.uploadVideo("Receta de Tamales de Angular");

        // Un nuevo suscriptor se une
        channel.subscribe(emin);

        // El canal sube otro video
        channel.uploadVideo("Receta de React al pastor");

        // Un suscriptor se da de baja
        channel.unsubscribe(cesar);

        channel.uploadVideo("Receta de Vue de choclo");

        channel.unsubscribe(emin);

        channel.uploadVideo("Parrillada de NodeJS");

        channel.unsubscribe(melissa);

        channel.uploadVideo("Docker a la plancha");
    }
}
