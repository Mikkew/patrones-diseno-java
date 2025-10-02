/**
 * ! Singleton:
 * Es un patrón de diseño creacional que garantiza que una clase
 * tenga una única instancia y proporciona un punto de acceso global a ella.
 *
 * * Es útil cuando necesitas controlar el acceso a una única instancia
 * * de una clase, como por ejemplo, en un objeto de base de datos o en un
 * * objeto de configuración.
 */

package com.mms.patterns.desing.p01_creacionales.c06_singleton;

import static com.mms.patterns.desing.utils.ConsoleColors.*;


// Clase Singleton que representa la conexión única a la base de datos
class DatabaseConnection {
    // Instancia única (estática) de la clase
    private static DatabaseConnection instance;
    // Estado: indica si la conexión está activa
    private Boolean connected = false;

    // Constructor privado para evitar instanciación externa
    private DatabaseConnection() {
    }

    /**
     * Método estático para obtener la instancia única (Singleton)
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
            System.out.println(BLUE + "Conectado a la base de datos" + RESET);
        }
        return instance;
    }

    /**
     * Método para conectar a la base de datos
     */
    public void connect() {
        if (this.connected) {
            System.out.println(BLUE + "Ya estábamos conectados a la base de datos" + RESET);
            return;
        }

        this.connected = true;
        System.out.println(GREEN + "Nueva conexión a la base de datos" + RESET);
    }

    /**
     * Método para desconectar la base de datos
     */
    public void disconnect() {
        if (this.connected) {
            System.out.println(BLUE + "Desconectamos la conexión a la base de datos" + RESET);
            connected = false;
            return;
        }
        System.out.println(RED + "No hay una conexión activa" + RESET);
    }
}


/**
 * Clase demostrativa del patrón Singleton.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que en una aplicación solo puede haber una conexión activa a la base de datos.
 * Todos los módulos deben interactuar con la misma instancia para evitar conflictos y garantizar la integridad.
 * Usando el patrón Singleton, se garantiza que solo exista una instancia global y compartida.
 */
public class SingletonPattern_02 {
    public static void main(String[] args) {
        // Obtener la instancia única de la conexión a base de datos
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.connect(); // Debería conectar a la base de datos

        // Obtener la misma instancia desde otra variable
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.connect(); // Debería mostrar que ya existe una conexión activa

        // Verificar que ambas referencias apuntan a la misma instancia
        System.out.println("Son iguales: " + (db1.equals(db2)));

        // Desconectar usando ambas referencias
        db1.disconnect();  // Debería cerrar la conexión
        db2.disconnect();

        // Conectar de nuevo
        db2.connect(); // Ahora debería conectar de nuevo, ya que se cerró la anterior
    }
}
