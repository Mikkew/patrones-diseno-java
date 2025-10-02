package com.mms.patterns.desing.p01_creacionales.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

/**
 * ! Singleton para gestión de configuración global
 *
 * Caso de uso:
 * El patrón Singleton es útil cuando necesitas garantizar que una clase tenga una única instancia global,
 * y que todos los módulos accedan y modifiquen la misma configuración compartida.
 *
 * En este ejemplo, ConfigManager centraliza la gestión de parámetros de configuración de la aplicación.
 *
 * Ventajas:
 * - Permite acceso global y consistente a la configuración.
 * - Evita la duplicidad y los conflictos de configuración.
 * - Facilita la administración centralizada de parámetros.
 */
public class ConfigManager {
    // Instancia única (Singleton) de la clase
    private static final ConfigManager instance = new ConfigManager();
    // Mapa para almacenar la configuración
    private final Map<String, String> config;

    // Constructor privado para evitar instanciación externa
    private ConfigManager() {
        config = new HashMap<>();
    }

    // Método estático para obtener la instancia única
    public static ConfigManager getInstance() {
        return instance;
    }

    // Establece un parámetro de configuración
    public void setConfig(String key, String value) {
        config.put(key, value);
    }

    // Obtiene el valor de un parámetro de configuración
    public String getConfig(String key) {
        return config.get(key);
    }

    // Devuelve un mapa inmodificable con toda la configuración
    public Map<String, String> getAllConfig() {
        return Collections.unmodifiableMap(new HashMap<>(config));
    }
}
