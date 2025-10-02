/**
 * ! Patrón Chain of Responsibility
 * Es un patrón de diseño de comportamiento que te permite pasar solicitudes
 * a lo largo de una cadena de manejadores.
 *
 * * Es útil cuando se necesita procesar datos de diferentes maneras, pero no
 * * se sabe de antemano qué tipo de procesamiento se necesita o en qué orden
 * * pero se sabe que se necesita procesar en una secuencia.
 *
 * https://refactoring.guru/es/design-patterns/chain-of-responsibility
 */

package com.mms.patterns.desing.p03_comportamiento.c01_chain_responsibility;


import static com.mms.patterns.desing.utils.ConsoleColors.*;
import static com.mms.patterns.desing.utils.ConsoleColors.PURPLE;
import static com.mms.patterns.desing.utils.ConsoleColors.RED;
import static com.mms.patterns.desing.utils.ConsoleColors.RESET;
import static com.mms.patterns.desing.utils.ConsoleColors.YELLOW;

interface Handler {
    Handler setNext(Handler handler);
    void handle(String request);
}

abstract class BaseHandler implements Handler {
    private Handler nextHandler;

    @Override
    public Handler setNext(Handler handler) {
        this.nextHandler = handler;
        return handler;
    }

    @Override
    public void handle(String request) {
        if (nextHandler != null)
            nextHandler.handle(request);
    }
}

// Soporte básico
class BasicSupport extends BaseHandler {

    @Override
    public void handle(String request) {
        if (request == "básico") {
            System.out.println(String.format("Soporte básico: %sResolviendo problema básico%s", GREEN, RESET));
            return;
        }
        System.out.println("Soporte básico: Pasando el problema a soporte avanzado");
        super.handle(request);
    }
}

class AdvancedSupport extends BaseHandler {

    @Override
    public void handle(String request) {
        if (request == "avanzado") {
            System.out.println(String.format("Soporte avanzado: %sResolviendo problema avanzado%s", YELLOW, RESET));
            return;
        }
        System.out.println(String.format("Soporte avanzado: %sPasando el problema a soporte experto%s", PURPLE, RESET));
        super.handle(request);
    }
}

class ExpertSupport extends BaseHandler {

    @Override
    public void handle(String request) {
        if (request == "experto") {
            System.out.println(String.format("Soporte experto: %sResolviendo problema experto%s", YELLOW, RESET));
            return;
        }
        System.out.println(String.format("%sSoporte experto: No hay nada que hacer... bye bye%s", RED, RESET));
    }
}

/**
 * Caso de uso: Sistema de soporte técnico escalonado
 *
 * Imagina un sistema de atención al cliente donde las solicitudes pueden ser resueltas por diferentes niveles de soporte (básico, avanzado, experto).
 * Cada nivel intenta resolver el problema; si no puede, lo pasa al siguiente nivel de la cadena.
 * Así, el cliente no necesita saber quién resolverá su problema, solo envía la solicitud y el sistema la procesa en orden.
 *
 * Este patrón permite desacoplar el emisor de la solicitud de los posibles receptores y facilita la extensión de la cadena sin modificar el código existente.
 */
public class ChainResponsibilityPattern_01 {
    public static void main(String[] args) {
        // Se crean los manejadores de soporte
        Handler basicSupport = new BasicSupport();
        Handler advancedSupport = new AdvancedSupport();
        Handler expertSupport = new ExpertSupport();

        // Se establece la cadena: básico -> avanzado -> experto
        basicSupport.setNext(advancedSupport).setNext(expertSupport);

        // Se envían diferentes solicitudes de soporte
        basicSupport.handle("básico");    // Lo resuelve soporte básico
        basicSupport.handle("avanzado");  // Lo resuelve soporte avanzado
        basicSupport.handle("experto");   // Lo resuelve soporte experto
        basicSupport.handle("nuclear");   // Ningún nivel lo resuelve
    }

    public static void main2(String[] args){
        ;
    }
}
