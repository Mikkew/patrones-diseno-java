package com.mms.patterns.desing.p01_creacionales.c06_singleton;

import com.mms.patterns.desing.p01_creacionales.singleton.ConfigManager;
import java.util.Map;

/**
 * Clase demostrativa del patrón Singleton aplicado a la configuración global.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que en una aplicación necesitas que todos los módulos accedan y modifiquen la misma configuración global.
 * Usando el patrón Singleton, se garantiza que solo exista una instancia de ConfigManager y que todos los cambios sean visibles globalmente.
 */
public class SingletonPattern_03 {
    public static void main(String[] args) {
        // Obtener la instancia singleton de configuración
        ConfigManager config = ConfigManager.getInstance();

        // Establecer configuraciones
        config.setConfig("apiUrl", "https://api.example.com");
        config.setConfig("timeout", "5000");

        // Obtener configuraciones
        System.out.println("API URL: " + config.getConfig("apiUrl"));
        System.out.println("Timeout: " + config.getConfig("timeout"));

        // Obtener todas las configuraciones
        Map<String, String> allConfigs = config.getAllConfig();
        System.out.println("Todas las configuraciones: " + allConfigs);
    }
}
