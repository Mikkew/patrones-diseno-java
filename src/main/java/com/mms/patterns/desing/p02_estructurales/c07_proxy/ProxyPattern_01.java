/**
 * ! Patrón Proxy
 * Este patrón se utiliza para controlar el acceso a un objeto, es decir,
 * se crea un objeto que actúa como intermediario entre el cliente y el objeto real.
 *
 * * Es útil cuando necesitamos controlar el acceso a un objeto,
 * * por ejemplo, para verificar si el cliente tiene permiso
 * * para acceder a ciertos métodos o propiedades.
 *
 * https://refactoring.guru/es/design-patterns/proxy
 *
 */

package com.mms.patterns.desing.p02_estructurales.c07_proxy;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

class Player {
    private String name;
    private Integer level;

    public Player(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

interface Room {
    void enter(Player player);
}

class SecretRoom implements Room {

    @Override
    public void enter(Player player) {
        final String fmt = String.format("%sBienvenido a la sala secreta, %s%s", BLUE, player.getName(), RESET);
        System.out.println(fmt);
        System.out.println("Una gran enemigo te espera");
    }
}

// 3. Clase Proxy - Magic Portal
class MagicPortal implements Room {
    private SecretRoom secretRoom;

    public MagicPortal(SecretRoom secretRoom) {
        this.secretRoom = secretRoom;
    }

    @Override
    public void enter(Player player) {
        if (player.getLevel() >=10) {
            this.secretRoom.enter(player);
        }
        final String fmt = String.format(
                "%sLo siento mucho %s, Tu nivel %d, es muy bajo, necesitas nivel 10%s",
                RED, player.getName(), player.getLevel(), RESET
        );
        System.out.println(fmt);
    }
}

/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un videojuego donde los jugadores intentan acceder a una sala secreta.
 * El patrón Proxy se utiliza para controlar el acceso: el portal mágico (proxy) verifica si
 * el jugador tiene el nivel suficiente antes de permitirle entrar a la sala real (SecretRoom).
 *
 * En el main, se crean dos jugadores con diferentes niveles y se muestra cómo el proxy permite
 * o deniega el acceso según el nivel del jugador, demostrando el control de acceso sin modificar
 * la lógica de la sala secreta.
 */
public class ProxyPattern_01 {
    public static void main(String[] args) {
        // Crear el proxy (portal mágico) que controla el acceso a la sala secreta
        Room portal = new MagicPortal(new SecretRoom());

        // Crear dos jugadores con diferentes niveles
        Player player1 = new Player("Aventurero A", 5);
        Player player2 = new Player("Aventurero B", 15);

        // El jugador 1 intenta entrar (no tiene nivel suficiente)
        System.out.println(BLUE + "Aventurero A intenta entrar al portal" + RESET);
        portal.enter(player1);

        // El jugador 2 intenta entrar (sí tiene nivel suficiente)
        System.out.println(BLUE + "Aventurero B intenta entrar al portal" + RESET);
        portal.enter(player2);
    }
}
