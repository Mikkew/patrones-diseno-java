/**
 * ! Factory Function
 * Es un patrón de diseño que nos permite crear objetos o funciones de manera dinámica que serán
 * usados posteriormente en el código.
 *
 * * Es útil cuando necesitamos crear objetos o funciones de manera dinámica,
 * * es decir, en tiempo de ejecución y no en tiempo de compilación.
 *
 */

package com.mms.patterns.desing.p01_creacionales.c07_factory_function;

import java.util.HashMap;
import java.util.Map;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

public class FactoryFunctionPattern_01 {

    // Enum para los idiomas soportados
    public enum Language {
        es, en, fr
    }


    // Interfaz funcional para la función de saludo
    @FunctionalInterface
    public interface GreeterFunction {
        void greet(String name);
    }


    /**
     * Factory Function: crea una función de saludo personalizada según el idioma.
     *
     * @param lang Idioma para el saludo
     * @return Una función que saluda en el idioma especificado
     */
    public static GreeterFunction createGreeter(Language lang) {
        return (String name) -> {
            Map<Language, String> messages = new HashMap<>();
            messages.put(Language.es, "Hola, %s!");
            messages.put(Language.en, "Hello, %s!");
            messages.put(Language.fr, "Bonjour, %s!");

            System.out.println(RED + String.format(messages.get(lang), name) + RESET);
        };
    }

    /**
     * Ejemplo de uso del patrón Factory Function.
     *
     * Se crean funciones de saludo para diferentes idiomas y se usan para saludar a distintos usuarios.
     */
    public static void main(String[] args) {
        // Crear funciones de saludo para cada idioma
        GreeterFunction spanishGreeter = createGreeter(Language.es);
        GreeterFunction englishGreeter = createGreeter(Language.en);
        GreeterFunction frenchGreeter  = createGreeter(Language.fr);

        // Usar las funciones de saludo
        spanishGreeter.greet("Fernando");
        englishGreeter.greet("Alice");
        frenchGreeter.greet("Pierre");
    }
}
