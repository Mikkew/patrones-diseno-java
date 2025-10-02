/**
 * ! Inmutabilidad con copia
 * Aunque la inmutabilidad es una buena práctica, no siempre es posible.
 * En estos casos, se puede hacer una copia del objeto y modificar la copia.
 * <p>
 * * Es útil para mantener un historial de estados en aplicaciones interactivas.
 */

package com.mms.patterns.desing.p01_creacionales.c05_inmutabilidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Clase inmutable que representa el estado del editor de código
final class CodeEditorState {

    // Atributos inmutables del estado
    private final String content;
    private final Integer cursorPosition;
    private final Boolean unsavedChanges;


    // Constructor que inicializa el estado del editor
    public CodeEditorState(String content, Integer cursorPosition, Boolean unsavedChanges) {
        this.content = content;
        this.cursorPosition = cursorPosition;
        this.unsavedChanges = unsavedChanges;
    }


    /**
     * Crea una copia del estado actual, permitiendo modificar solo los atributos deseados.
     * Es el mecanismo principal para "modificar" un objeto inmutable.
     */
    public CodeEditorState copyWith(String content, Integer cursorPosition, Boolean unsavedChanges) {
        return new CodeEditorState(
                content != null ? content : this.content,
                cursorPosition != null ? cursorPosition : this.cursorPosition,
                unsavedChanges != null ? unsavedChanges : this.unsavedChanges
        );
    }


    // Muestra el estado actual del editor por consola
    public void displayState() {
    System.out.println(GREEN + "Estado del editor: " + RESET);
    System.out.println(String.format(
        "    Contenido: %s\n" +
            "    Posición cursor: %d\n" +
            "    Cambios no guardados: %b\n",
        content,
        cursorPosition,
        unsavedChanges
    ));
    }

    public String getContent() {
        return content;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public boolean isUnsavedChanges() {
        return unsavedChanges;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CodeEditorState{");
        sb.append("content='").append(content).append('\'');
        sb.append(", cursorPosition=").append(cursorPosition);
        sb.append(", unsavedChanges=").append(unsavedChanges);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeEditorState that = (CodeEditorState) o;
        return cursorPosition == that.cursorPosition &&
                unsavedChanges == that.unsavedChanges &&
                content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, cursorPosition, unsavedChanges);
    }
}

// Clase que mantiene el historial de estados del editor (undo/redo)
class CodeEditorHistory {
    private List<CodeEditorState> history;
    private Integer currentIndex;

    public CodeEditorHistory() {
        this.history = new ArrayList<>();
        this.currentIndex = -1;
    }

    /**
     * Guarda un nuevo estado en el historial
     *
     * @param state Estado del editor a guardar
     */

    /**
     * Guarda un nuevo estado en el historial.
     * Si se hace un cambio después de un undo, elimina los estados futuros.
     */
    public void save(CodeEditorState state) {
        // Elimina los estados futuros si estamos en medio del historial
        if (currentIndex < history.size() - 1) {
            history.subList(currentIndex + 1, history.size()).clear();
        }

        history.add(state);
        currentIndex++;
    }

    /**
     * Deshace la última acción
     *
     * @return Estado anterior o null si no hay más acciones para deshacer
     */

    /**
     * Deshace la última acción y retorna el estado anterior.
     */
    public CodeEditorState undo() {
        if (currentIndex > 0) {
            currentIndex--;
            return history.get(currentIndex);
        }
        return null;
    }

    /**
     * Rehace la última acción deshecha
     *
     * @return Estado siguiente o null si no hay más acciones para rehacer
     */

    /**
     * Rehace la última acción deshecha y retorna el estado siguiente.
     */
    public CodeEditorState redo() {
        if (currentIndex < history.size() - 1) {
            currentIndex++;
            return history.get(currentIndex);
        }
        return null;
    }

    /**
     * @return El estado actual del editor
     */
    public CodeEditorState getCurrentState() {
        if (currentIndex >= 0 && currentIndex < history.size()) {
            return history.get(currentIndex);
        }
        return null;
    }

    /**
     * @return Cantidad de estados en el historial
     */
    public int getHistorySize() {
        return history.size();
    }

    /**
     * @return Índice del estado actual
     */
    public int getCurrentIndex() {
        return currentIndex;
    }
}


/**
 * Clase demostrativa del patrón de inmutabilidad.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que tienes un editor de código donde cada vez que el usuario realiza un cambio,
 * se guarda un nuevo estado inmutable. Esto permite implementar funcionalidades como undo/redo
 * de manera sencilla y segura, sin preocuparse por efectos colaterales.
 */
public class InmutabilidadPattern_01 {
    public static void main(String[] args) {
        // Crear historial y estado inicial del editor
        CodeEditorHistory history = new CodeEditorHistory();
        CodeEditorState editorState = new CodeEditorState("System.out.println(\"Hola Mundo\");", 2, false);

        // Guardar el estado inicial
        history.save(editorState);

        System.out.println(BLUE + "Estado inicial" + RESET);
        editorState.displayState();

        // Realizar un cambio en el contenido y guardar el nuevo estado
        editorState = editorState.copyWith(
                "System.out.println(\"Hola Mundo\"); System.out.println(\"Nueva Linea\");",
                3, true);
        history.save(editorState);

        System.out.println(BLUE + "Después del primer cambio" + RESET);
        editorState.displayState();

        // Mover el cursor y guardar el nuevo estado
        System.out.println(BLUE + "Después de mover el cursor" + RESET);
        editorState = editorState.copyWith(null, 5, null);
        history.save(editorState);
        editorState.displayState();

        // Deshacer el último cambio
        System.out.println(BLUE + "Después del Undo" + RESET);
        editorState = history.undo();
        editorState.displayState();

        // Rehacer el cambio deshecho
        System.out.println(BLUE + "Después del Redo" + RESET);
        editorState = history.redo();
        editorState.displayState();
    }
}
