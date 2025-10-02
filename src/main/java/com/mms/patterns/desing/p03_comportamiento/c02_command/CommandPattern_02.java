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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// 2. Clase Receptor - TextEditor
class TextEditor {
    private String text;
    private String clipboard;
    private List<String> history;

    public TextEditor() {
        this.text = "";
        this.clipboard = "";
        this.history = new ArrayList<>();
    }

    // Agregar texto al editor
    public void type(String newText) {
        this.history.add(this.text);
        this.text += text;
    }

    // Copiar el texto actual
    public void copy() {
        this.clipboard = this.text;
        System.out.println(String.format("Texto copiado al portapapeles: \n%s%s%s", BLUE, clipboard, RESET));
    }

    // Pegar el texto del portapapeles
    public void paste() {
        this.history.add(this.text);
        this.text += this.clipboard;
        System.out.println(String.format("Texto después de pegar: \n%s%s%s", BLUE, text, RESET));
    }

    // Deshacer la última acción
    public void undo() {
        if (this.history.size() > 0) {
            this.text = this.history.removeLast();
            System.out.println(String.format("Texto después de deshacer: \n%s%s%s", BLUE, text, RESET));
            return;
        }
        System.out.println("No hay nada para deshacer.");
    }

    public String getText() {
        return text;
    }
}

// 3. Clases de Comandos Concretos
class CopyCommand implements Command {
    private TextEditor editor;

    public CopyCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        this.editor.copy();
    }
}

class PasteCommand implements Command {
    private TextEditor editor;

    public PasteCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        this.editor.paste();
    }
}

class UndoCommand implements Command {
    private TextEditor editor;

    public UndoCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        this.editor.undo();
    }
}


// 4. Clase Cliente - Toolbar
class Toolbar {
    private Map<String, Command> commands;

    public Toolbar() {
        commands = new HashMap<>();
    }

    public void setCommand(String button, Command command) {
        this.commands.put(button, command);
    }

    public void clickButton(String button) {
        Command command = this.commands.get(button);
        if (command != null) {
            command.execute();
            return;
        }
        System.out.println(String.format("%sNo hay un comando asignado al botón \"%s\"%s", RED, button, RESET));
    }
}

// 5. Código Cliente para probar el patrón Command
// !Nada del código main debe ser modificado
/**
 * Caso de uso: Barra de herramientas para editor de texto
 *
 * Este ejemplo simula una barra de herramientas que permite realizar acciones sobre un editor de texto (copiar, pegar, deshacer).
 * Cada botón de la barra está asociado a un comando que encapsula la acción a realizar sobre el editor.
 * El editor no necesita saber cómo se implementa cada acción, solo ejecuta el comando asignado.
 *
 * El patrón Command permite desacoplar el invocador (toolbar) de los receptores (editor),
 * facilitando la extensión y reutilización de comandos para nuevas acciones.
 */
public class CommandPattern_02 {
    public static void main(String[] args) {
        // Se crea el editor de texto y la barra de herramientas
        TextEditor editor = new TextEditor();
        Toolbar toolbar = new Toolbar();

        // Se crean los comandos para el editor
        CopyCommand copyCommand = new CopyCommand(editor);
        PasteCommand pasteCommand = new PasteCommand(editor);
        UndoCommand undoCommand = new UndoCommand(editor);

        // Se asignan los comandos a los botones de la barra de herramientas
        toolbar.setCommand("copy", copyCommand);   // Botón copiar
        toolbar.setCommand("paste", pasteCommand); // Botón pegar
        toolbar.setCommand("undo", undoCommand);   // Botón deshacer

        // Simulación de escritura en el editor
        editor.type("H");
        editor.type("o");
        editor.type("l");
        editor.type("a");
        editor.type(" ");
        editor.type("M");
        editor.type("u");
        editor.type("n");
        editor.type("d");
        editor.type("o");
        editor.type("!");
        System.out.println(String.format("Texto actual: %s\"%s\"%s", GREEN, editor.getText(), RESET));

        // Uso de la barra de herramientas para ejecutar comandos
        System.out.println("Copiando texto:");
        toolbar.clickButton("copy");      // Copia el texto actual

        System.out.println("Pegando texto:");
        toolbar.clickButton("paste");     // Pega el texto copiado

        System.out.println("Deshaciendo la última acción:");
        toolbar.clickButton("undo");      // Deshace el último cambio

        System.out.println("Deshaciendo de nuevo:");
        toolbar.clickButton("undo");      // Deshace el cambio anterior

        System.out.println(String.format("Texto final: \"%s\"", editor.getText()));
    }
}
