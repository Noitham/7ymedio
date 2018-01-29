package com.proven.cat.a7ymedio;

/**
 * Created by alumne on 7/11/17.
 */

public class Card {

    boolean visible;
    double value;

    //Constructor
    public Card(double v) {
        value = v;
        visible = false;
    }

    //Returs card visibility
    public boolean isVisible() {

        return visible;
    }

    //Sets the card visibility to false
    public boolean setNotVisible() {
        visible = false;
        return visible;
    }

    //Sets the card to visible
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    //Gets card value
    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}