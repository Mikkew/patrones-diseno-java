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

// Interfaz Observer
interface Observer_V2 {
    void update(String weatherData);
}

// Clase Subject - WeatherStation
// TODO: Terminal la implementación
class WeatherStation {
    private List<Observer_V2> observers;
    private String weatherData = "Soleado";

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    // Agregar un Observer
    public void subscribe(Observer_V2 observer) {
        observers.add(observer);
        observer.update(weatherData);

        System.out.println(String.format("%sNueva aplicación suscrita al sistema meteorológico.%s", GREEN, RESET));
    }

    // Eliminar un Observer
    public void unsubscribe(Observer_V2 observer) {
        observers = observers.stream()
                .filter(sub -> sub != observer).toList();
        System.out.println(String.format("%sUna aplicación se ha dado de baja%s", RED, RESET));
    }

    // Actualizar el clima y notificar a todos los Observers
    public void setWeatherData(String weatherData) {
        System.out.println(String.format("Clima actualizado: %s%s%s", BLUE, weatherData, RESET));
        this.weatherData = weatherData;

        notifyObservers();
    }

    // Notificar a todos los Observers
    private void notifyObservers() {
        for (Observer_V2 observer : observers) {
            observer.update(weatherData);
        }
    }
}

// Clase Observer - WeatherApp
class WeatherApp implements Observer_V2 {
    private String name;

    public WeatherApp(String name) {
        this.name = name;
    }

    // Recibir actualización del clima
    @Override
    public void update(String weatherData) {
        System.out.println(String.format("%s%s%s ha recibido notificación del clima: %s%s%s",
                RED, name,RESET, YELLOW, weatherData, RESET));
    }
}

/**
 * Caso de uso: Notificaciones automáticas de cambios meteorológicos a aplicaciones móviles
 *
 * Este ejemplo simula una estación meteorológica que notifica automáticamente a varias aplicaciones móviles
 * cada vez que el clima cambia. La estación actúa como sujeto y las aplicaciones como observadores.
 * Cuando la estación actualiza el clima, todas las aplicaciones suscritas reciben la notificación.
 *
 * El patrón Observer es útil para implementar sistemas de eventos, notificaciones o suscripciones en tiempo real.
 */
public class ObserverPattern_02 {
    public static void main(String[] args) {
        // Se crea la estación meteorológica
        WeatherStation weatherStation = new WeatherStation();

        // Se crean las aplicaciones móviles
        Observer_V2 flutterWeatherApp = new WeatherApp("Flutter WeatherApp");
        Observer_V2 reactNativeWeatherApp = new WeatherApp("React Native WeatherApp");
        Observer_V2 weatherTrackerApp = new WeatherApp("Weather Tracker App");

        // Las aplicaciones se suscriben a la estación meteorológica
        weatherStation.subscribe(flutterWeatherApp);
        weatherStation.subscribe(reactNativeWeatherApp);

        // La estación actualiza el clima y notifica a las aplicaciones
        weatherStation.setWeatherData("Lluvioso");

        // Se agrega una nueva aplicación y se actualiza el clima
        weatherStation.subscribe(weatherTrackerApp);
        weatherStation.setWeatherData("Nublado");

        // Una aplicación se da de baja y se actualiza el clima
        weatherStation.unsubscribe(reactNativeWeatherApp);
        weatherStation.setWeatherData("Tormenta eléctrica");
    }
}
