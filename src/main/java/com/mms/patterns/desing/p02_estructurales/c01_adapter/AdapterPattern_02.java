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

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// 1. Interfaz PaymentProcessor
interface PaymentProcessor {
    void processPayment(Double amount);
}

// 2. Clases de Servicios de Pago Externos
// Estas clases simulan los servicios externos de PayPal, Stripe y MercadoPago
class PayPalService {
    public void sendPayment(Double amount) {
        System.out.println("Procesando pago de $" + amount + " con " + BLUE +"PayPal" + RESET);
    }
}

class StripeService {
    public void makeCharge(Double amount) {
        System.out.println("Procesando pago de $" +amount + " con " + PURPLE + "Stripe" + RESET);
    }
}

class MercadoPagoService {
    public void pay(Double amount) {
        System.out.println("Procesando pago de $" +amount + " con " + YELLOW + "MercadoPago" + RESET);
    }
}

// 3. Clases Adaptadoras

// Adaptador para PayPal
class PayPalAdapter implements PaymentProcessor {
    private PayPalService payPalService;

    public PayPalAdapter(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @Override
    public void processPayment(Double amount) {
        this.payPalService.sendPayment(amount);
    }
}

// Adaptador para Stripe
class StripeAdapter implements PaymentProcessor {
    private StripeService stripeService;

    public StripeAdapter(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @Override
    public void processPayment(Double amount) {
        this.stripeService.makeCharge(amount);
    }
}

// Adaptador para MercadoPago
class MercadoPagoAdapter implements PaymentProcessor {
    private MercadoPagoService mercadoPagoService;

    public MercadoPagoAdapter(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @Override
    public void processPayment(Double amount) {
        this.mercadoPagoService.pay(amount);
    }
}


public class AdapterPattern_02 {

    /**
     * Caso de uso:
     * Imagina que tu sistema de pagos necesita trabajar con múltiples proveedores (PayPal, Stripe, MercadoPago),
     * pero cada uno tiene una interfaz diferente. El patrón Adapter permite crear una interfaz común (PaymentProcessor)
     * y adaptadores para cada proveedor, de modo que el resto del sistema puede procesar pagos de forma uniforme,
     * sin preocuparse por los detalles de cada API.
     *
     * Así, si en el futuro cambias de proveedor o agregas uno nuevo, solo necesitas crear un nuevo adaptador,
     * sin modificar el código de negocio.
     */
    public static void main(String[] args) {
        Double paymentAmount = 100.00;
        // Crear servicios
        PayPalService paypal = new PayPalService();
        StripeService stripe = new StripeService();
        MercadoPagoService mercadoPago = new MercadoPagoService();

        // Agregar los adaptadores para los servicios de pago
        PaymentProcessor paypalProcessor = new PayPalAdapter(paypal);
        PaymentProcessor stripeProcessor = new StripeAdapter(stripe);
        PaymentProcessor mercadoPagoProcessor = new MercadoPagoAdapter(mercadoPago);

        // Procesar pagos con los diferentes servicios
        // Los 3 procesadores de pago trabajan exactamente igual después de adaptaros
        System.out.println("Usando Paypal");
        paypalProcessor.processPayment(paymentAmount);

        System.out.println("Usando Stripe");
        stripeProcessor.processPayment(paymentAmount);

        System.out.println("Usando MercadoPago");
        mercadoPagoProcessor.processPayment(paymentAmount);
    }
}
