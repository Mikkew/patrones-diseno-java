/**
 * ! Patrón Composite
 * Es un patrón de diseño estructural que permite componer objetos
 * en estructuras de árbol para representar jerarquías.
 * <p>
 * El patrón permite a los clientes tratar de manera uniforme a los objetos
 * individuales y a sus composiciones.
 * <p>
 * * Es útil cuando necesitas tratar a los objetos individuales
 * * y a sus composiciones de manera uniforme, y la estructura
 * * de los objetos forma una jerarquía en árbol.
 * <p>
 * https://refactoring.guru/es/design-patterns/composite
 */

package com.mms.patterns.desing.p02_estructurales.c03_composite;

import java.util.ArrayList;
import java.util.List;

// Componente base: define la interfaz común para hojas y compuestos
interface FileSystemComponent {
    // Método por defecto para mostrar detalles sin indentación
    default void showDetails() {
        showDetails("");
    }

    // Método que cada componente debe implementar para mostrar su información
    void showDetails(String indent);
}

// Hoja: representa un archivo individual en el sistema de archivos
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    // Muestra el nombre del archivo con la indentación correspondiente
    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "- Archivo: " + name);
    }
}

// Compuesto: representa una carpeta que puede contener archivos y otras
// carpetas
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> contents;

    public Folder(String name) {
        this.name = name;
        this.contents = new ArrayList<>();
    }

    // Permite agregar archivos o carpetas a la carpeta actual
    public void add(FileSystemComponent component) {
        this.contents.add(component);
    }

    // Muestra el nombre de la carpeta y recursivamente los detalles de su contenido
    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "+ Carpeta: " + this.name);
        this.contents.forEach(component -> component.showDetails(indent + "  "));
    }
}

// Código cliente: crea una estructura de carpetas y archivos y la muestra
/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de archivos donde existen archivos (hojas)
 * y carpetas (compuestos). Las carpetas pueden contener tanto archivos como
 * otras carpetas, formando una estructura jerárquica de árbol.
 *
 * El patrón Composite permite tratar de manera uniforme a archivos y carpetas,
 * facilitando operaciones recursivas y la extensión del sistema. En el main,
 * se construye una estructura de carpetas y archivos y se imprime toda la
 * jerarquía
 * usando una sola llamada a showDetails().
 */
public class CompositePattern_01 {

    public static void main(String[] args) {
        // Crear archivos individuales (hojas)
        File file1 = new File("archivo1.txt");
        File file2 = new File("archivo2.txt");
        File file3 = new File("archivo3.txt");
        File file4 = new File("archivo4.txt");

        // Crear carpetas (compuestos)
        Folder folder1 = new Folder("Carpeta 1");
        Folder folder5 = new Folder("Carpeta 5");

        // Agregar archivos a la carpeta 1
        folder1.add(file1);
        folder1.add(file2);

        // Crear y poblar carpeta 2
        Folder folder2 = new Folder("Carpeta 2");
        folder2.add(file3);

        // Carpeta 3 dentro de carpeta 2
        Folder folder3 = new Folder("Carpeta 3");
        folder3.add(file4);
        folder2.add(folder3);
        folder2.add(folder5);

        // Carpeta raíz
        Folder rootFolder = new Folder("Carpeta ROOT");

        // Agregar carpetas principales a la raíz
        rootFolder.add(folder1);
        rootFolder.add(folder2);

        // Mostrar toda la estructura de carpetas y archivos
        rootFolder.showDetails();
    }
}
