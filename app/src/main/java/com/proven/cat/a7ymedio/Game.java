package com.proven.cat.a7ymedio;

/**
 * Created by dmora on 7/11/17.
 */

import java.util.ArrayList;

public class Game {

    //We define our elements
    private int counter = 0;
    private ArrayList<Card> listCards;

    //Constructor
    public Game() {
        this.counter = 0;
        this.listCards = new ArrayList<>();
    }

    //Get counter
    public int getCounter() {
        return counter;
    }

    //Increments counter
    public void incrementCounter() {
        this.counter++;
    }

    //Resets counter
    public int resetCounter() {
        counter = 0;
        return counter;
    }

    /**
     * We add the 8 cards
     */
    public void addCards() {
        listCards.add(new Card(1));
        listCards.add(new Card(2));
        listCards.add(new Card(3));
        listCards.add(new Card(4));
        listCards.add(new Card(5));
        listCards.add(new Card(1));
        listCards.add(new Card(3));
        listCards.add(new Card(0.5));
    }

    /**
     * Changes the visible state of a card
     * Initial position is 0
     *
     * @param position
     */
    public void changeCard(int position) {
        if (position < listCards.size()) {

            Card c = listCards.get(position);
            if (c.isVisible()) {
                c.setVisible(false);
            } else {
                c.setVisible(true);
            }
        }
    }

    /**
     * Counts the total value of visible cards
     *
     * @return valor
     */
    public double valueTotalCards() {
        double value = 0;
        for (Card c : listCards) {
            if (c.isVisible()) {
                value += c.getValue();
            }
        }
        return value;
    }

    /**
     * get Card
     *
     * @param position in list of card
     * @return c: Card or null if overflow
     */
    public Card getCard(int position) {

        Card c = null;
        if (position < listCards.size()) {
            c = listCards.get(position);
        }
        return c;
    }

    /**
     * Resets the total value of the cards.
     *
     * @return value
     */
    public double resetTotalValue() {
        double value = 0;
        for (Card c : listCards) {
            if (c.setNotVisible()) {
                value = 0;
            }
        }
        return value;
    }

}
