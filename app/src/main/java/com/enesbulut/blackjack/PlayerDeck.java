package com.enesbulut.blackjack;

import java.util.List;

public class PlayerDeck extends AbstractPlayerDeck{
    private Player player;
    public PlayerDeck() {
    }
    public PlayerDeck(List<Card> cards) {
        this.cards = cards;
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void kartCek(){
        addCard(UtilService.getGameDeck().buyCard());
    }
}
