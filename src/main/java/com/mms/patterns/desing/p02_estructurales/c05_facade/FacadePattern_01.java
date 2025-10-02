/**
 * ! Patrón Facade
 * Este patrón proporciona una interfaz unificada para un conjunto de interfaces
 * en un subsistema.
 *
 * Facade define una interfaz de nivel más alto que hace que el subsistema
 * sea más fácil de usar.
 *
 * * Es útil cuando un subsistema es complejo o difícil de entender para
 * * proporcionar una interfaz simplificada para el cliente.
 *
 * https://refactoring.guru/es/design-patterns/facade
 */

package com.mms.patterns.desing.p02_estructurales.c05_facade;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

class Projector {
    public void turnOn() {
        System.out.println("Proyector encendido");
    }

    public void turnOff() {
        System.out.println("Proyector apagado");
    }
}

class SoundSystem {
    public void on() {
        System.out.println("Sistema de sonido encendido");
    }

    public void off() {
        System.out.println("Sistema de sonido apagado");
    }
}

class VideoPlayer {

    public void on() {
        System.out.println("Video player encendido");
    }

    public void play(String movie) {
        System.out.println("Reproduciendo: " + BLUE + movie + RESET);
    }

    public void stop() {
        System.out.println("Película detenida");
    }

    public void off() {
        System.out.println("Video player apagado");
    }
}

class PopcornMaker {
    public void poppingPopcorn() {
        System.out.println("Haciendo palomitas");
    }

    public void turnOffPoppingPopcorn() {
        System.out.println("Deteniendo las palomitas");
    }
}

interface HomeTheaterFacadeOptions {
    Projector getProjector();
    SoundSystem getSoundSystem();
    VideoPlayer getVideoPlayer();
    PopcornMaker getPopcornMaker();
}

class HomeTheaterComponents implements HomeTheaterFacadeOptions {
    private final Projector projector;
    private final SoundSystem soundSystem;
    private final VideoPlayer videoPlayer;
    private final PopcornMaker popcornMaker;

    public HomeTheaterComponents(Projector projector,
                                 SoundSystem soundSystem,
                                 VideoPlayer videoPlayer,
                                 PopcornMaker popcornMaker) {
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.videoPlayer = videoPlayer;
        this.popcornMaker = popcornMaker;
    }

    @Override
    public Projector getProjector() {
        return projector;
    }

    @Override
    public SoundSystem getSoundSystem() {
        return soundSystem;
    }

    @Override
    public VideoPlayer getVideoPlayer() {
        return videoPlayer;
    }

    @Override
    public PopcornMaker getPopcornMaker() {
        return popcornMaker;
    }
}

class HomeTheaterFacade {
    private Projector projector;
    private SoundSystem soundSystem;
    private VideoPlayer videoPlayer;
    private PopcornMaker popcornMaker;

    public HomeTheaterFacade(HomeTheaterFacadeOptions components) {
        this.projector = components.getProjector();
        this.soundSystem = components.getSoundSystem();
        this.videoPlayer = components.getVideoPlayer();
        this.popcornMaker = components.getPopcornMaker();
    }

    public void watchMovie(String movie) {
        System.out.printf("%sPreparando para ver la película%s \n", BLUE, RESET);
        this.projector.turnOn();
        this.soundSystem.on();
        this.popcornMaker.poppingPopcorn();
        this.videoPlayer.on();
        this.videoPlayer.play(movie);

        System.out.printf("%sDisfrute la película%s\n", BLUE, RESET);
    }

    public void endWatchingMovie() {
        System.out.printf("%sPreparando para detener la película%s\n", BLUE, RESET);
        this.projector.turnOff();
        this.soundSystem.off();
        this.popcornMaker.turnOffPoppingPopcorn();
        this.videoPlayer.stop();
        this.videoPlayer.off();

        System.out.printf("%sSistema apagado%s\n", BLUE, RESET);
    }
}

/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de cine en casa donde existen varios subsistemas
 * (proyector, sistema de sonido, reproductor de video, palomitas). El patrón Facade
 * permite al cliente interactuar con todos estos subsistemas a través de una interfaz
 * simplificada (HomeTheaterFacade), ocultando la complejidad y los detalles de coordinación.
 *
 * En el main, el cliente puede ver una película o terminar la sesión de cine usando solo
 * dos métodos de la fachada, sin preocuparse por el orden ni la interacción de los componentes.
 */
public class FacadePattern_01 {
    public static void main(String[] args) {
        // Crear instancias de los subsistemas (cada uno representa un dispositivo del cine en casa)
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        VideoPlayer videoPlayer = new VideoPlayer();
        PopcornMaker popcornMaker = new PopcornMaker();

        // Agrupar los componentes en un objeto de configuración (HomeTheaterComponents implementa la interfaz de opciones)
        HomeTheaterComponents components = new HomeTheaterComponents(projector, soundSystem, videoPlayer, popcornMaker);

        // Crear la fachada que simplifica el uso del sistema de cine en casa
        // El cliente solo interactúa con HomeTheaterFacade y no con los subsistemas directamente
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(components);

        // Iniciar la experiencia de ver una película
        // La fachada se encarga de encender y coordinar todos los dispositivos necesarios
        homeTheater.watchMovie("Los Avengers");

        // Terminar la experiencia de cine
        // La fachada apaga y detiene todos los dispositivos en el orden correcto
        homeTheater.endWatchingMovie();
    }
}
