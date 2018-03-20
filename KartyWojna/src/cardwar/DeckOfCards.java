package cardwar;

import java.util.Random;
 
public class DeckOfCards {
    Card[] deckOfCards;
   
    public DeckOfCards() {
        deckOfCards = new Card[52];
        int cardCounter = 0;
        for (int i=2; i<11; i++) {
        	for (int j=0; j<4; j++) {
        		String singelCard = Integer.toString(i);
        		deckOfCards[cardCounter] = new Card(singelCard, i);
        		cardCounter++;
        	}
        }
        for (int i=cardCounter; i<40; i++) {
        	deckOfCards[i] = new Card("Jupek", 11);
        	cardCounter++;
        }
        for (int i=cardCounter; i<44; i++) {
        	deckOfCards[i] = new Card("Dama", 12);
        	cardCounter++;
        }
        for (int i=cardCounter; i<48; i++) {
        	deckOfCards[i] = new Card("King", 13);
        	cardCounter++;
        }
        for (int i=cardCounter; i<52; i++) {
        	deckOfCards[i] = new Card("Ace", 14);
        	cardCounter++;
        }
    }
   
    public int howManyCards() {
        return this.deckOfCards.length;
    }
   
    public void printAllCards() {
        for (int i=0; i<this.deckOfCards.length; i++) {
            System.out.print(this.deckOfCards[i].getCardName()+ " ");
        }
        System.out.println();
    }
   
//  which card is stroneger.
    public Card strongerCard(String card1, String card2) {
        int card1Value = 0;
        int card2Value = 0;    
        for (int i=0; i<deckOfCards.length; i++) {
            if (card1.equals(deckOfCards[i].getCardName())) {
                card1Value = deckOfCards[i].getCardValue();
            }
            if (card2.equals(deckOfCards[i].getCardName())) {
                card2Value = deckOfCards[i].getCardValue();
            }
        }
        if ((card1Value == 0) || (card2Value == 0)) {
            System.out.println("Wrong card");
            return deckOfCards[0];
        } else if (card1Value > card2Value) {
            System.out.println("Card " + card1 + " is stronger");
            return deckOfCards[card1Value-2];
        } else if (card1Value < card2Value) {
            System.out.println("Card " + card2 + " is stronger");
            return deckOfCards[card2Value-2];
        } else {
            System.out.println("Both cards are the same value");
            return deckOfCards[card1Value-2];
        }
    }
    
    public Card strongerCard(Card card1, Card card2) {
    	int card1Value = card1.getCardValue();
    	int card2Value = card2.getCardValue();
    	if (card1.getCardValue() > card2.getCardValue()) {
    		System.out.println("Player's 1 card is stronger");
    		return card1;
    	} else if (card1.getCardValue() < card2.getCardValue()) {
    		System.out.println("Player's 2 card is stronger");
    		return card2;
    	} else {
    		System.out.println("Looks like trouble! We've got a war!");
    		return null;
    	}
    }
   
 
    public void mixDeckOfCards() {
//      kopiujac tablice obiektow odnosimy sie do referencji a nie do kopii wartosci!!
//      dlatego nie mozna bylo zrobic: Card[] tempArr = deckOfCars.... ??
        Card[] tempArr = new Card[deckOfCards.length];
        for (int i=0; i<tempArr.length; i++) {
            tempArr[i] = deckOfCards[i];
        }
        int randomNumber = 0;
//  usuwamy wstawiony znak i przesuwamy dalsze elementy tablicy w lewo, nastepnie zmniejszamy rozmiar tablicy
        for (int i=0; i<deckOfCards.length; i++) {
            randomNumber = new Random().nextInt(deckOfCards.length-i);
            deckOfCards[i] = tempArr[randomNumber];
            for (int j=randomNumber; j<tempArr.length; j++) {
                if (j+1 < tempArr.length) {
                    tempArr[j] = tempArr[j+1];
                }              
            }
            Card[] temp2Arr = tempArr;
            tempArr = new Card[temp2Arr.length-1];
            for (int j=0; j<tempArr.length; j++) {
                tempArr[j] = temp2Arr[j];
            }
        }
    }
   
    public Card[] dealTheCards(int n) {
        Card[] givenCards = new Card[n];
        Card[] tempArr = new Card[deckOfCards.length -n];
        for (int i=0; i<givenCards.length; i++) {
            givenCards[i] = this.deckOfCards[i];
        }
        for (int i=0; i<tempArr.length; i++) {
        	tempArr[i] = deckOfCards[i+n];
        }
        deckOfCards = new Card[tempArr.length];
        for (int i=0; i<deckOfCards.length; i++) {
        	deckOfCards[i] = tempArr[i];
        }
        return givenCards;
    }
    
//    public void dealTheCards(Card[] player1, Card[] player2) {
//    	int amountOfCards = deckOfCards.length;
//    	   	
//    }
}