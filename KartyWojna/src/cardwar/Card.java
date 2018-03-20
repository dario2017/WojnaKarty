package cardwar;
 
public class Card {
    private String cardName;
    private int cardValue;
   
    public Card() {}
    public Card(String cardName, int cardValue) {
        this.cardName = cardName;
        this.cardValue = cardValue;
    }
   
    public String getCardName() {
        return this.cardName;
    }
   
    public int getCardValue() {
        return this.cardValue;
    }
}