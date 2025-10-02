/**
 * ! Patrón Flyweight
 * Es un patrón de diseño estructural que nos permite usar objetos compartidos
 * para soportar eficientemente grandes cantidades de objetos.
 *
 * * Es útil cuando necesitamos una gran cantidad de objetos y queremos reducir
 * * la cantidad de memoria que utilizan.
 *
 * https://refactoring.guru/es/design-patterns/flyweight
 */

package com.mms.patterns.desing.p02_estructurales.c06_flyweight;

import org.apache.commons.lang3.BooleanUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

class Coordinates {
    private final Integer x;
    private final Integer y;

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}

interface Location {
    void display(Coordinates coordinates);
}

// Flyweight
class LocationIcon implements Location {
    private String type;
    private String iconImage;

    public LocationIcon(String type, String iconImage) {
        this.type = type;
        this.iconImage = iconImage;
    }

    @Override
    public void display(Coordinates coordinates) {
        System.out.printf("Coords: %s en %d, %d con ícono %s[%s]%s \n",
                type, coordinates.getX(), coordinates.getY(), GREEN, iconImage, RESET);
    }
}

// Fábrica de Flyweights
// {
//   escuela: assets/school.png,
//   hospital: assets/hospital.png,
// }
class LocationFactory {
    private final Map<String, LocationIcon> icons = new HashMap<>();

    // Escuela, hospital, parque,
    public LocationIcon getLocationIcon(String type) {
        if (BooleanUtils.isFalse(icons.containsKey(type))) {
            System.out.printf("%sCreando una instancia del ícono de %s%s \n", RED, type, RESET);
            final String iconName = String.format("imagen_de_%s.png", type.toLowerCase());
            this.icons.put(type, new LocationIcon(type, iconName));
        }
        return icons.get(type);
    }
}

class MapLocation {
    private final Coordinates coordinates;
    private final LocationIcon icon;

    public MapLocation(Coordinates coordinates, LocationIcon icon) {
        this.coordinates = coordinates;
        this.icon = icon;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocationIcon getLocationIcon() {
        return icon;
    }

    public void display() {
        icon.display(coordinates);
    }
}

/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de mapas donde se muestran múltiples ubicaciones
 * (hospitales, parques, escuelas, etc.) en diferentes coordenadas. El patrón Flyweight
 * permite compartir los íconos de cada tipo de ubicación, evitando crear múltiples
 * instancias idénticas y ahorrando memoria.
 *
 * En el main, se crean muchas ubicaciones en el mapa, pero los íconos se reutilizan
 * gracias a la fábrica de flyweights, demostrando eficiencia en el uso de recursos.
 */
public class FlyweightPattern_01 {
    public static void main(String[] args) {
        // Crear la fábrica de flyweights (íconos compartidos)
        LocationFactory factory = new LocationFactory();

        // Crear una lista de ubicaciones en el mapa, cada una con coordenadas y un ícono compartido
        List<MapLocation> locations = Arrays.asList(
                new MapLocation(new Coordinates(10, 20), factory.getLocationIcon("hospital")),
                new MapLocation(new Coordinates(20, 40), factory.getLocationIcon("hospital")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("hospital")),

                new MapLocation(new Coordinates(35, 65), factory.getLocationIcon("parque")),
                new MapLocation(new Coordinates(35, 65), factory.getLocationIcon("parque")),
                new MapLocation(new Coordinates(35, 65), factory.getLocationIcon("parque")),
                new MapLocation(new Coordinates(35, 65), factory.getLocationIcon("parque")),
                new MapLocation(new Coordinates(35, 65), factory.getLocationIcon("parque")),
                new MapLocation(new Coordinates(35, 65), factory.getLocationIcon("parque")),
                new MapLocation(new Coordinates(35, 65), factory.getLocationIcon("parque")),

                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("hospital")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("hospital")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("hospital")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("hospital")),

                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela")),
                new MapLocation(new Coordinates(30, 60), factory.getLocationIcon("Escuela"))
        );

        // Mostrar todas las ubicaciones en el mapa (cada una usa un ícono compartido según su tipo)
        locations.forEach(MapLocation::display);
    }
}
