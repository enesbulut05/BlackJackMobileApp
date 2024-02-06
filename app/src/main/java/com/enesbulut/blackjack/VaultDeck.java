package com.enesbulut.blackjack;

import java.util.List;

public class VaultDeck extends AbstractPlayerDeck {
    public VaultDeck() {
    }

    public VaultDeck(List<Card> cards) {
        this.cards = cards;
    }

}