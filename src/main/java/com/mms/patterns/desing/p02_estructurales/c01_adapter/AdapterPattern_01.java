/**
 * ! Patrón Adapter
 *  Permite que objetos con interfaces incompatibles trabajen juntos, también es muy
 *  util para utilizar librerías de terceros en nuestra aplicación sin depender
 *  directamente de ellas.
 *
 * * Es útil cuando se quiere reutilizar una clase que no tiene la interfaz que
 * * necesitamos o cuando queremos crear una capa de abstracción para una librería
 * * de terceros.
 *
 * https://refactoring.guru/es/design-patterns/adapter
 */

package com.mms.patterns.desing.p02_estructurales.c01_adapter;

//import com.mms.patterns.desing.p02_estructurales.adapter_files.LocalLogger;
import com.mms.patterns.desing.p02_estructurales.adapter_files.LocalAdapter;

public class AdapterPattern_01 {

//    public static void main(String[] args) {
//        final LocalLogger logger = new LocalLogger("AdapterPattern_01");
//
//        logger.writeLog("Mensaje de un log normal");
//        logger.writeWarning("Una alerta normal, información");
//        logger.writeError("Algo muy malo salió por aquí");
//    }



    /**
     * Caso de uso:
     * Supón que tu aplicación necesita registrar logs, pero la librería de logging que tienes no cumple con la interfaz que usas en tu sistema.
     * El patrón Adapter permite crear una clase (LocalAdapter) que traduce las llamadas de tu interfaz a la de la librería externa.
     * Así, puedes cambiar la implementación interna del logger sin modificar el resto de tu aplicación.
     *
     * En este ejemplo, el adaptador permite escribir logs, advertencias y errores usando una interfaz unificada,
     * independientemente de la implementación interna del logger.
     */
    public static void main(String[] args) {
        // Crear el logger usando el adaptador
        final LocalAdapter logger = new LocalAdapter(AdapterPattern_01.class.getName());

        // Usar el logger para distintos tipos de mensajes
        logger.writeLog("Mensaje de un log normal");
        logger.writeWarning("Una alerta normal, información");
        logger.writeError("Algo muy malo salió por aquí");
    }
}
