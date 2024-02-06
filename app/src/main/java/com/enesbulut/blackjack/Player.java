package com.enesbulut.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<PlayerDeck> playerDecks;

    public  Player(String name){
        this.name = name;
        this.playerDecks = new ArrayList<>();
    }

    public List<PlayerDeck> getPlayerDecks() {
        return this.playerDecks;
    }

    public PlayerDeck getPlayerDeckByIndex(int index) {
        return this.playerDecks.get(index);
    }

    public void setPlayerDeck(PlayerDeck playerDeck) {
        playerDeck.setPlayer(this);
        this.playerDecks.add(playerDeck);
    }

    public void resetPlayerDeck() {
        this.playerDecks.clear();
    }

    public int result(int index){
        this.getPlayerDeckByIndex(index).calculateCardTotal();
        int playerT = this.getPlayerDeckByIndex(index).getCardTotal();
        UtilService.getVault().getVaultDeck().calculateCardTotal();
        int caseT =UtilService.getVault().getVaultDeck().getCardTotal();
        if (playerT > 21 || (playerT < caseT && caseT <= 21)){
            //Oyuncu kaybetti = 0
            return 0;
        } else if ( playerT == caseT ) {
            if (getPlayerDeckByIndex(index).getStatus() == Status.BLACKJACK &&
                    UtilService.getVault().getVaultDeck().getStatus() != Status.BLACKJACK){
                //Oyuncu kazandı = 1
                return 1;
            } else if (getPlayerDeckByIndex(index).getStatus() != Status.BLACKJACK &&
                    UtilService.getVault().getVaultDeck().getStatus() == Status.BLACKJACK) {
                return 0;
            }else {
                //berabere
                return 2;
            }

        } else {
            return 1;
        }
    }

}
