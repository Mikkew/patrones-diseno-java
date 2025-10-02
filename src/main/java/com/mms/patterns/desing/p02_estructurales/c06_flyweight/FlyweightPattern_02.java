/**
 * ! Patrón Flyweight
 * Es un patrón de diseño estructural que nos permite usar objetos compartidos
 * para soportar eficientemente grandes cantidades de objetos.
 * <p>
 * * Es útil cuando necesitamos una gran cantidad de objetos y queremos reducir
 * * la cantidad de memoria que utilizan.
 * <p>
 * https://refactoring.guru/es/design-patterns/flyweight
 */

package com.mms.patterns.desing.p02_estructurales.c06_flyweight;

import org.apache.commons.lang3.BooleanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

class BulletType {
    private String name;
    private Integer damage;
    private String color;

    public BulletType(String name, Integer damage, String color) {
        this.name = name;
        this.damage = damage;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

// 2. Fábrica de Flyweights - BulletTypeFactory
class BulletTypeFactory {
    private final Map<String, BulletType> bulletTypes = new HashMap<>();

    public BulletType getBulletType(String name, Integer damage, String color) {
        final String key = String.format("%s-%d-%s", name, damage, color);

        if (BooleanUtils.isFalse(bulletTypes.containsKey(key))) {
            System.out.printf("%sCreando una instancia %s%s \n", RED, key, RESET);
            this.bulletTypes.put(key, new BulletType(name, damage, color));
        }

        return this.bulletTypes.get(key);
    }
}

// 3. Clase que representa una Bala - Bullet
class Bullet {
    private Integer x;
    private Integer y;
    private Integer direction;
    private BulletType bulletType;

    public Bullet(Integer x, Integer y, Integer direction, BulletType bulletType) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.bulletType = bulletType;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public void setBulletType(BulletType bulletType) {
        this.bulletType = bulletType;
    }

    public void display() {
        final String text = String.format("""
                            Bala del tipo: %s%s%s
                            %sCoords: %s, %s
                            Dirección: %s
                            Daño: %s
                            Color: %s
                        """,
                GREEN, bulletType.getName(), RESET,
                WHITE, this.x, this.y,
                this.direction,
                this.bulletType.getDamage(),
                this.getBulletType().getColor()
        );

        System.out.println(text);
    }
}

// 4. Sistema de Disparos - ShootingSystem
class ShootingSystem {
    private List<Bullet> bullets = new ArrayList<>();
    private BulletTypeFactory factory;

    public ShootingSystem(BulletTypeFactory factory) {
        this.factory = factory;
    }

    public void shoot(Integer x, Integer y, Integer direction, String type, Integer damage, String color) {
        BulletType bulletType = this.factory.getBulletType(type, damage, color);
        Bullet bullet = new Bullet(x, y, direction, bulletType);
        this.bullets.add(bullet);
        bullet.display();
    }

    public Integer getBulletCount() {
        return this.bullets.size();
    }
}

/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de disparos en un videojuego, donde existen múltiples balas
 * de diferentes tipos (pistola, escopeta, rifle, etc.). El patrón Flyweight permite compartir
 * la información intrínseca de cada tipo de bala (daño, color, nombre), evitando crear múltiples
 * instancias idénticas y ahorrando memoria.
 *
 * En el main, se disparan varias balas de diferentes tipos y posiciones, pero los tipos de bala
 * se reutilizan gracias a la fábrica de flyweights, demostrando eficiencia en el uso de recursos.
 */
public class FlyweightPattern_02 {
    public static void main(String[] args) {
        // Crear la fábrica de tipos de bala (flyweights)
        BulletTypeFactory factory = new BulletTypeFactory();
        // Crear el sistema de disparos que usará la fábrica para compartir tipos de bala
        ShootingSystem shootingSystem = new ShootingSystem(factory);

        // Disparar varias balas de diferentes tipos y posiciones
        shootingSystem.shoot(10, 20, 0, "Pistola", 10, "Gris");
        shootingSystem.shoot(15, 25, 90, "Escopeta", 20, "Rojo");
        shootingSystem.shoot(20, 30, 180, "Rifle", 15, "Verde");
        shootingSystem.shoot(10, 20, 45, "Pistola", 10, "Gris");
        shootingSystem.shoot(25, 35, 270, "Escopeta", 20, "Rojo");

        // Mostrar el total de balas disparadas
        final String fmt = String.format("Total de balas disparadas: %s%d%s", YELLOW, shootingSystem.getBulletCount(), RESET);
        System.out.println(fmt);
    }
}
