/**
 * ! Patrón Prototype:

 * Es un patrón de diseño creacional que nos permite copiar objetos existentes sin hacer
 * que el código dependa de sus clases.
 *
 * * Es útil cuando queremos duplicar el contenido,
 * * el título y el autor de un documento, por ejemplo o cualquier objeto complejo.
 *
 * https://refactoring.guru/es/design-patterns/prototype
 */

package com.mms.patterns.desing.p01_creacionales.c04_prototype;

// Clase que representa el producto a clonar (prototipo)
class Document {

    // Atributos del documento
    private String title;
    private String content;
    private String author;


    // Constructor que inicializa el documento con sus atributos
    public Document(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


    // Métodos getter y setter para los atributos
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }


    // Representación en texto del documento
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Document{");
        sb.append("title='").append(title).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append('}');
        return sb.toString();
    }


    // Método Prototype: clona el documento actual
    public Document clone() {
        return new Document(title, content, author);
    }
}



/**
 * Clase demostrativa del patrón Prototype.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que una empresa necesita generar múltiples documentos similares (por ejemplo, cotizaciones o contratos)
 * a partir de un modelo base. Usando el patrón Prototype, puede clonar un documento existente y personalizar solo los campos necesarios,
 * ahorrando tiempo y evitando errores de copia manual.
 */
public class PrototypePattern_01 {
    public static void main(String[] args) {
        // Crear un documento original (prototipo base)
        Document document1 = new Document("Cotización", "500 dólares", "Fernando");
        System.out.println("document1 = " + document1);

        // Clonar el documento y modificar solo el título
        Document document2 = document1.clone();
        document2.setTitle("Nueva cotización");
        System.out.println("document2 = " + document2);
    }
}
