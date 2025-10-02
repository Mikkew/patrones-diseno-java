/**
 * ! Patrón Builder:
 * Es un patrón de diseño creacional que nos permite construir objetos complejos
 * paso a paso.
 *
 * El patrón nos permite producir distintos tipos y representaciones
 * de un objeto empleando el mismo código de construcción.
 *
 * * Es útil cuando necesitamos construir un objeto complejo con muchas partes
 * * y queremos que el proceso de construcción sea independiente de las partes
 * * que lo componen.
 *
 * https://refactoring.guru/es/design-patterns/builder
 */

package com.mms.patterns.desing.p01_creacionales.c01_builder;


// Clase que representa el producto complejo a construir: una computadora
class Computer {

    private String cpu = "cpu - not defined";
    private String ram = "ram - not defined";
    private String storage = "storage - not defined";
    private String gpu = "No tiene GPU";

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Configuración de la computadora: ");
        sb.append("cpu='").append(cpu).append('\'');
        sb.append(", ram='").append(ram).append('\'');
        sb.append(", storage='").append(storage).append('\'');
        sb.append(", gpu='").append(gpu).append('\'');
        return sb.toString();
    }
}

/**
 * Clase Builder responsable de construir el objeto Computer paso a paso.
 * Implementa el patrón Builder para permitir una construcción fluida (Fluent Interface).
 */
// Builder responsable de construir el objeto Computer paso a paso
class ComputerBuilder {
    // Mantiene una instancia del producto que se está construyendo
    private Computer computer;

    /**
     * Constructor que inicializa una nueva instancia de Computer
     */
    public ComputerBuilder() {
        this.computer = new Computer();
    }

    /**
     * Configura la CPU de la computadora
     * @param cpu Tipo de procesador
     * @return La misma instancia del builder para encadenar llamadas
     */
    public ComputerBuilder CPU(String cpu) {
        this.computer.setCpu(cpu);
        return this;
    }

    /**
     * Configura la RAM de la computadora
     * @param ram Cantidad de memoria RAM
     * @return La misma instancia del builder para encadenar llamadas
     */
    public ComputerBuilder RAM(String ram) {
        this.computer.setRam(ram);
        return this;
    }

    /**
     * Configura el almacenamiento de la computadora
     * @param storage Tipo y capacidad de almacenamiento
     * @return La misma instancia del builder para encadenar llamadas
     */
    public ComputerBuilder storage(String storage) {
        this.computer.setStorage(storage);
        return this;
    }

    /**
     * Configura la GPU de la computadora (opcional)
     * @param gpu Tarjeta gráfica
     * @return La misma instancia del builder para encadenar llamadas
     */
    public ComputerBuilder GPU(String gpu) {
        this.computer.setGpu(gpu);
        return this;
    }

    /**
     * Método final que retorna el objeto Computer completamente construido
     * @return Instancia de Computer configurada
     */
    public Computer build() {
        return this.computer;
    }
}

/**
 * Clase demostrativa del patrón Builder.
 * Muestra cómo crear diferentes configuraciones de computadoras usando el mismo builder.
 */
/**
 * Clase demostrativa del patrón Builder.
 *
 * Caso de uso en este ejemplo:
 *
 * Imagina que una tienda de computadoras necesita armar diferentes configuraciones para sus clientes.
 * Algunos clientes quieren una computadora básica (solo CPU, RAM y almacenamiento),
 * mientras que otros desean una computadora para juegos con GPU dedicada y más memoria.
 *
 * Usando el patrón Builder, la tienda puede crear ambas configuraciones usando el mismo proceso de construcción,
 * simplemente eligiendo qué pasos ejecutar y qué atributos establecer.
 *
 * Así, el código es más flexible, fácil de mantener y de extender para nuevas configuraciones.
 */
public class BuilderPattern_01 {
    public static void main(String[] args) {
        final Computer basicComputer = new ComputerBuilder()
                .CPU("Intel Core 2 Dúo")
                .RAM("4GB")
                .storage("256GB")
                .build();

        System.out.println("Computadora basica:");
        System.out.println(basicComputer.toString());

        final Computer gamingComputer = new ComputerBuilder()
                .CPU("Intel i9")
                .RAM("64GB")
                .storage("1TB M2")
                .GPU("Nvidia RTX 5090")
                .build();

        System.out.println("Computadora gammer:");
        System.out.println(gamingComputer.toString());
    }
}
