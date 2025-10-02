/**
 * ! Patron Chain of Responsibility
 * Es un patrón de diseño de comportamiento que te permite pasar solicitudes
 * a lo largo de una cadena de manejadores.
 *
 * * Es útil cuando se necesita procesar datos de diferentes maneras, pero no
 * * se sabe de antemano qué tipo de procesamiento se necesita o en qué orden
 * * pero se sabe que se necesita procesar en una secuencia.
 */

package com.mms.patterns.desing.p03_comportamiento.c01_chain_responsibility;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// 1. Interfaz Approver
interface Approver {
    Approver setNext(Approver approver);
    void approveRequest(Double amount);
}

// 2. Clase Abstracta BaseApprover para manejar la cadena
abstract class BaseApprover implements Approver {
    private Approver nextApprover;

    @Override
    public Approver setNext(Approver approver) {
        this.nextApprover = approver;
        return approver;
    }

    public abstract void approveRequest(Double amount);

    protected void next(Double amount) {
        if (this.nextApprover != null) {
            this.nextApprover.approveRequest(amount);
        } else {
            System.out.println("Solicitud no pudo ser aprobada.");
        }
    }
}

// 3. Clases Concretas de Aprobadores
class Supervisor extends BaseApprover {

    @Override
    public void approveRequest(Double amount) {
        if (amount <= 1000) {
            System.out.println(String.format("Supervisor aprueba la compra de %s$%.2f%s", YELLOW, amount, RESET));
            return;
        }
        this.next(amount);
    }
}

class Manager extends BaseApprover {

    @Override
    public void approveRequest(Double amount) {
        if (amount <= 5000) {
            System.out.println(String.format("Manager aprueba la compra de %s$%.2f%s", YELLOW, amount, RESET));
            return;
        }
        this.next(amount);
    }
}

class Director extends BaseApprover {

    @Override
    public void approveRequest(Double amount) {
        System.out.println(String.format("Director aprueba la compra de %s$%.2f%s", YELLOW, amount, RESET));
    }
}

/**
 * Caso de uso: Aprobación escalonada de compras
 *
 * Este ejemplo simula un flujo de aprobación de compras en una empresa.
 * Dependiendo del monto, la solicitud es aprobada por el supervisor, el gerente o el director.
 * Si el monto excede el límite de un aprobador, la solicitud se pasa al siguiente en la cadena.
 * Así, el cliente no necesita saber quién aprobará la compra, solo envía la solicitud y el sistema la procesa en orden.
 */
public class ChainResponsibilityPattern_02 {
    public static void main(String[] args) {
        // Se crean los aprobadores con diferentes niveles de autorización
        Supervisor supervisor = new Supervisor();      // Aprueba hasta $1000
        Manager manager = new Manager();              // Aprueba hasta $5000
        Director director = new Director();           // Aprueba cualquier monto

        // Se configura la cadena: supervisor -> manager -> director
        supervisor.setNext(manager).setNext(director);

        // Se envían diferentes solicitudes de compra
        System.out.println("Solicitud de compra de $500:");
        supervisor.approveRequest(500.00);    // Aprueba el supervisor

        System.out.println("Solicitud de compra de $3000:");
        supervisor.approveRequest(3000.00);   // Aprueba el manager

        System.out.println("Solicitud de compra de $7000:");
        supervisor.approveRequest(7000.00);   // Aprueba el director
    }
}
