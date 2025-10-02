/**
 * ! Patrón Iterator
 * Este patrón permite recorrer los elementos de una colección sin exponer
 * la estructura interna de la colección.
 *
 * * Es útil cuando se necesita recorrer una colección de elementos sin importar
 * * cómo se almacenan los elementos.
 *
 * https://refactoring.guru/es/design-patterns/iterator
 */

package com.mms.patterns.desing.p03_comportamiento.c03_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Clase que representa una Carta de la baraja
class Card {
    private String name;
    private Integer value;

    public Card(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Carta: %s, Valor: %s", getName(), getValue());
    }
}

class CardCollection implements Iterable<Card>{
    private List<Card> cards;

    public CardCollection() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }


    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}

/**
 * Caso de uso: Recorrido de una baraja de cartas
 *
 * Este ejemplo simula una colección de cartas de una baraja.
 * El patrón Iterator permite recorrer la colección de cartas usando un bucle for-each,
 * sin exponer cómo están almacenadas internamente.
 * El cliente solo utiliza el iterador para acceder secuencialmente a cada carta.
 *
 * Este patrón facilita la extensión de la colección y el recorrido, sin modificar la estructura interna ni el código cliente.
 */
public class IteratorPattern_03 {
    public static void main(String[] args) {
        // Se crea la colección de cartas
        CardCollection deck = new CardCollection();

        // Se agregan cartas a la colección
        deck.addCard(new Card("As de Corazones", 1));
        deck.addCard(new Card("Rey de Corazones", 13));
        deck.addCard(new Card("Reina de Corazones", 12));
        deck.addCard(new Card("Jota de Corazones", 11));

        System.out.println("Recorriendo la colección de cartas:");

        // Se recorre la colección usando el iterador (for-each)
        for (Card card : deck) {
            System.out.println(card.toString());
        }
    }
}
