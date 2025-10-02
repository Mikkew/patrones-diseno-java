/**
 * ! Factory Method:
 * El patrón Factory Method permite crear objetos sin especificar
 * la clase exacta del objeto que se creará.
 *
 * En lugar de eso, delegamos la creación de objetos a subclases o métodos
 * que encapsulan esta lógica.
 *
 * * Es útil cuando una clase no puede anticipar la clase
 * * de objetos que debe crear.
 *
 * https://refactoring.guru/es/design-patterns/factory-method
 */

/**
 * 	!Descripción:
  1.	Completen las clases SalesReport e InventoryReport para implementar 
      la interfaz Report, generando el contenido de cada reporte en el método generate.
	  
  2.	Implementen las clases SalesReportFactory e InventoryReportFactory 
      para crear instancias de SalesReport y InventoryReport, respectivamente.

	3.	Prueben el programa generando diferentes tipos de reportes usando
      el prompt para seleccionar el tipo de reporte.
 */

package com.mms.patterns.desing.p01_creacionales.c02_factory_method;


import java.util.Scanner;

// Producto abstracto: interfaz para reportes
interface Report {
    void generate();
}


// Producto concreto: reporte de ventas
class SalesReport implements Report {

    @Override
    public void generate() {
        System.out.println("Generando reporte de ventas...");
    }
}


// Producto concreto: reporte de inventario
class InventoryReport implements Report {

    @Override
    public void generate() {
        System.out.println("Generando reporte de inventario...");
    }
}


// Creador abstracto: define el método factory para reportes
abstract class ReportFactory {
    // Método factory que las subclases deben implementar
    protected abstract Report crearReport();

    // Método plantilla que usa el factory para crear y generar el reporte
    void generateReport() {
        final Report report = this.crearReport();
        report.generate();
    }
}


// Creador concreto: fábrica de reportes de ventas
class SalesReportFactory extends ReportFactory {

    @Override
    protected Report crearReport() {
        return new SalesReport();
    }
}


// Creador concreto: fábrica de reportes de inventario
class InventoryReportFactory extends ReportFactory {

    @Override
    protected Report crearReport() {
        return new InventoryReport();
    }
}

/**
 * Clase demostrativa del patrón Factory Method.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que una aplicación empresarial necesita generar diferentes tipos de reportes según la necesidad del usuario.
 * Cada tipo de reporte puede tener su propia lógica de generación, pero el proceso de solicitud y generación es el mismo.
 *
 * Usando el patrón Factory Method, puedes agregar nuevos tipos de reportes simplemente creando nuevas subclases de Report y ReportFactory,
 * sin modificar el código del cliente ni la lógica de generación general.
 *
 * Así, el sistema es flexible, escalable y fácil de mantener.
 */
public class FactoryMethodPattern_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportFactory report;

        System.out.println("¿Qué tipo de reporte deseas? (sales/inventory): ");

        final String reportType = scanner.nextLine().trim().toLowerCase();

        if(reportType.equalsIgnoreCase("sales"))
            report = new SalesReportFactory();

        else if (reportType.equalsIgnoreCase("inventory"))
            report = new InventoryReportFactory();

        else {
            scanner.close();
            throw new IllegalArgumentException("Opción no válida");
        }

        scanner.close();
        report.generateReport();
    }
}
