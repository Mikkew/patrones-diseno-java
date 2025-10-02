/**
 * ! Factory Function
 * Es un patrón de diseño que nos permite crear objetos o funciones de manera dinámica que serán
 * usados posteriormente en el código.
 *
 * * Es útil cuando necesitamos crear objetos o funciones de manera dinámica,
 * * es decir, en tiempo de ejecución y no en tiempo de compilación.
 *
 */

//! Salida esperada
//! Colocar colores de log según el nivel
//* [INFO:2025-10-21:07] Aplicación iniciada correctamente.
//* [WARNING:2025-10-21:07] El uso de memoria está alto.
//* [ERROR:2025-10-21:07] Error de conexión a la base de dato

package com.mms.patterns.desing.p01_creacionales.c07_factory_function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

public class FactoryFunctionPattern_02 {


    /**
     * Formatea la fecha y hora actual en un string legible.
     * @param date Fecha a formatear
     * @return Fecha formateada como string
     */
    public static String formatDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }


    // Enum para los niveles de log soportados
    public enum LogLevel {
        info, warn, error
    }


    // Interfaz funcional para la función de log
    @FunctionalInterface
    public interface LoggerFunction {
        void logger(String message);
    }


    /**
     * Factory Function: crea una función de log personalizada según el nivel.
     *
     * @param level Nivel de log (info, warn, error)
     * @return Una función que imprime mensajes con formato y color según el nivel
     */
    public static LoggerFunction createLogger(LogLevel level) {
        return (String message) -> {
            String timestamp = formatDate(LocalDateTime.now());

            String prefix;
            String color = switch (level) {
                case info -> {
                    prefix = "INFO";
                    yield WHITE;
                }
                case warn -> {
                    prefix = "WARNING";
                    yield YELLOW;
                }
                case error -> {
                    prefix = "ERROR";
                    yield RED;
                }
                default -> {
                    prefix = "LOG";
                    yield WHITE;
                }
            };

            System.out.println(
                color + "[" + prefix + ": " + timestamp + "] " + message + RESET
            );
        };
    }

    /**
     * Ejemplo de uso del patrón Factory Function.
     *
     * Se crean funciones de log para diferentes niveles y se usan para imprimir mensajes con formato y color.
     */
    public static void main(String[] args) {
        // Crear funciones de log para cada nivel
        LoggerFunction infoLogger = createLogger(LogLevel.info);
        LoggerFunction warnLogger = createLogger(LogLevel.warn);
        LoggerFunction errorLogger = createLogger(LogLevel.error);

        // Usar las funciones de log
        infoLogger.logger("Aplicación iniciada correctamente.");
        warnLogger.logger("El uso de memoria está alto.");
        errorLogger.logger("Error de conexión a la base de datos.");
    }
}
