/**
 * !Patrón Memento
 * Permite capturar y externalizar un estado interno de un objeto,
 * de manera que el objeto pueda ser restaurado a ese estado más tarde.
 *
 * * Es útil cuando se necesita guardar el estado de un objeto para poder
 * * volver a él en un futuro.
 *
 * https://refactoring.guru/es/design-patterns/memento
 */

package com.mms.patterns.desing.p03_comportamiento.c05_memento;

import java.util.ArrayList;
import java.util.List;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Una pizarra donde se pueden agregar figuras

// Clase Memento - DrawingMemento
class DrawingMemento {
    private List<String> shapes;

    public DrawingMemento(List<String> shapes) {
        // Guardamos una copia de la lista para evitar mutaciones
        this.shapes = new ArrayList<>(shapes);
    }

    public List<String> getShapes() {
        // Devolvemos una copia para evitar mutaciones externas
        return new ArrayList<>(shapes);
    }
}

// Clase Originator - DrawingBoard
class DrawingBoard {
    private List<String> shapes;

    public DrawingBoard() {
        shapes = new ArrayList<>();
    }

    // Agregar una figura a la pizarra
    public void addShape(String shape) {
        this.shapes.add(shape);

        System.out.println("Figura agregada: " + shape);
    }

    // Mostrar el estado actual de la pizarra
    public void showBoard() {
        if (shapes.isEmpty()) {
            System.out.println("Pizarra actual: Vacía");
            return;
        }
        System.out.println("Pizarra actual: " + String.join(", ", shapes));
    }

    // Crear un Memento del estado actual de la pizarra
    public DrawingMemento save() {
        return new DrawingMemento(this.shapes);
    }

    // Restaurar el estado de la pizarra desde un Memento
    public void restore(DrawingMemento memento) {
        this.shapes = memento.getShapes();
        System.out.println(String.format("%sEstado de la pizarra restaurado.%s", BLUE, RESET));
    }
}

// Clase Caretaker - History
class History {
    private List<DrawingMemento> mementos;

    public History() {
        mementos = new ArrayList<>();
    }

    // Guardar un Memento
    // TODO: Implementar push para guardar en la historia
    public void push(DrawingMemento memento) {
        this.mementos.add(memento);
    }

    // Recuperar el último Memento
    // TODO: Implementar pop para recuperar el último memento
    public DrawingMemento pop() {
        if (mementos.isEmpty()) {
            return null;
        }
        return mementos.removeLast();
    }
}

/**
 * Caso de uso: Deshacer acciones en una pizarra de dibujo
 *
 * Este ejemplo simula una pizarra donde el usuario puede agregar figuras y guardar el estado en cada paso.
 * El patrón Memento permite capturar el estado de la pizarra (las figuras dibujadas) y almacenarlo en una historia,
 * para luego restaurar ese estado y deshacer cambios si el usuario lo desea.
 *
 * El patrón Memento es útil para implementar funcionalidades de "deshacer" en aplicaciones gráficas o editores.
 */
public class MementoPattern_02 {
    public static void main(String[] args) {
        // Se crea la pizarra de dibujo y el historial de estados
        DrawingBoard drawingBoard = new DrawingBoard();
        History history = new History();

        // El usuario agrega figuras y guarda el estado en cada paso
        drawingBoard.addShape("Círculo");
        history.push(drawingBoard.save());

        drawingBoard.addShape("Cuadrado");
        history.push(drawingBoard.save());

        drawingBoard.addShape("Triángulo");
        drawingBoard.showBoard(); // Mostrar estado actual de la pizarra

        // Deshacer el último cambio
        drawingBoard.restore(history.pop());
        drawingBoard.showBoard(); // Mostrar estado después de deshacer

        // Deshacer otro cambio
        drawingBoard.restore(history.pop());
        drawingBoard.showBoard(); // Mostrar estado después de deshacer nuevamente

        // Se puede seguir deshaciendo si hay más estados guardados
        // drawingBoard.restore(history.pop());
        // drawingBoard.showBoard(); // Mostrar estado después de deshacer nuevamente
    }
}
