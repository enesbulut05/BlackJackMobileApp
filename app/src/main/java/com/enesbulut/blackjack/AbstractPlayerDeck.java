package com.enesbulut.blackjack;

public abstract class AbstractPlayerDeck extends AbstractDeck {
    protected int cardTotal = 0;
    protected Status status = Status.ACTIVE;
    protected SgStatus sgstatus = SgStatus.SIGORTASIZ;

    public String calculateCardTotal() {
        int total = 0;
        int aceCount = 0;

        for (Card card : this.cards) {
            total += card.getValue();

            // Eğer kart "A" ise "aceCount"i arttır
            if (card.getName().equals("A")) {
                aceCount++;
            }
        }

        String result = String.valueOf(total);

        // "A" varsa ve 11 olarak kullanılabilirse sonucu düzenle
        if (aceCount > 0 && total <= 11) {
            result = result + " ya da " + String.valueOf(total + 10);
        }
        if (getStatus() == Status.BLACKJACK){
            result = "21";
        }

        try {
            setCardTotal(Integer.parseInt(result));
        }catch (Exception e){
            String[] parts = result.split("ya da");
            String biggest = parts[1].trim();
            setCardTotal(Integer.parseInt(biggest));
        }
        if(result.contains("21") && this.getStatus() != Status.BLACKJACK){
            this.setStatus(Status.INACTIVE);
        }
        return result;
    }
    public int getCardTotal() {
        return cardTotal;
    }

    public void setCardTotal(int cardTotal) {
        this.cardTotal = cardTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public SgStatus getSgStatus() {
        return sgstatus;
    }

    public void setSgStatus(SgStatus sgstatus) {
        this.sgstatus = sgstatus;
    }


}
