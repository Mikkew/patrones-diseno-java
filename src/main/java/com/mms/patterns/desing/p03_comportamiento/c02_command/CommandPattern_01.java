/**
 * ! Patrón Command
 * Este patrón encapsula una solicitud como un objeto,
 * lo que le permite parametrizar otros objetos con diferentes solicitudes,
 * encolar solicitudes, o registrar solicitudes, y soporta operaciones que pueden deshacerse.
 *
 * Me gustó mucho la explicación de Refactoring Guru
 * https://refactoring.guru/es/design-patterns/command
 *
 * * Es útil cuando se necesita desacoplar el objeto que invoca
 * * la operación del objeto que sabe cómo realizarla.
 *
 */

package com.mms.patterns.desing.p03_comportamiento.c02_command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

interface Command {
  void execute();
}

class Light {
    void turnOn() {
        System.out.println(String.format("%sLa luz está encendida%s", YELLOW, RESET));
    }

    void turnOff() {
        System.out.println(String.format("%sLa luz está apagada%s", YELLOW, RESET));
    }
}


class Fan {
    void on(){
        System.out.println(String.format("%sEl ventilador está encendido%s", GREEN, RESET));
    }

    void off(){
        System.out.println(String.format("%sEl ventilador está apagada%s", GREEN, RESET));
    }
}

// Comandos
class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class FanOnCommand implements Command {
    private final Fan fan;

    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }
}

class FanOffCommand implements Command {
    private final Fan fan;

    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.off();
    }
}

class RemoteControl {
    private Map<String, Command> commands;

    public RemoteControl() {
        this.commands = new HashMap<>();
    }

    public void setCommand(String button, Command command) {
        this.commands.put(button, command);
    }

    public void pressButton(String button) {
        Command command = this.commands.get(button);
        if (command != null) {
            command.execute();
            return;
        }
        System.out.println(String.format("%sNo se ha asignado un comando a ese botón%s", RED, RESET));
    }
}

/**
 * Caso de uso: Control remoto universal para dispositivos
 *
 * Este ejemplo simula un control remoto que puede operar diferentes dispositivos (luz y ventilador).
 * Cada botón del control está asociado a un comando que encapsula la acción a realizar sobre el dispositivo.
 * El control remoto no necesita saber cómo se realiza la acción, solo ejecuta el comando asignado.
 *
 * El patrón Command permite desacoplar el invocador (control remoto) de los receptores (dispositivos),
 * facilitando la extensión y reutilización de comandos para nuevos dispositivos o acciones.
 */
public class CommandPattern_01 {
    public static void main(String[] args) {
        // Se crea el control remoto y los dispositivos
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();
        Fan fan = new Fan();

        // Se crean los comandos para los dispositivos
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        FanOnCommand fanOnCommand = new FanOnCommand(fan);
        FanOffCommand fanOffCommand = new FanOffCommand(fan);

        // Se asignan los comandos a los botones del control remoto
        remoteControl.setCommand("1", lightOnCommand);      // Botón 1: Encender luz
        remoteControl.setCommand("2", lightOffCommand);     // Botón 2: Apagar luz
        remoteControl.setCommand("3", fanOnCommand);        // Botón 3: Encender ventilador
        remoteControl.setCommand("4", fanOffCommand);       // Botón 4: Apagar ventilador

        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Menú de opciones para el usuario
            System.out.println("Presiona un botón del control:");
            System.out.println("1. Encender luz");
            System.out.println("2. Apagar luz");
            System.out.println("3. Encender ventilador");
            System.out.println("4. Apagar ventilador");
            System.out.println("Botón: ");

            String pressedButton = scanner.nextLine().trim();
            remoteControl.pressButton(pressedButton); // Ejecuta el comando asociado

            System.out.print("\n¿Deseas continuar? (y/n): ");
            String continueResponse = scanner.nextLine().trim().toLowerCase();

            if (continueResponse.equals("n")) {
                continueProgram = false;
            }
        }

        scanner.close();
        System.out.println("Programa finalizado.");
    }
}
