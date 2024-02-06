package com.enesbulut.blackjack;


public class Card {
    private String name;
    private int value;
    private String card;

    public Card(String name, int value, String card) {
        this.name = name;
        this.value = value;
        this.card = card;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name;
    }
    public String getCard() {
        return this.card;
    }

    public void setCard(String card) {
        this.card = card;
    }
    public String getCardFullName(){
        return this.card;
    }
    public void setCardFullName(String newName){
        this.card = newName;
    }


}

