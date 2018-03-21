package cardwar;
 
import java.util.Scanner;
 
public class CardWar {
    DeckOfCards deck;
    Card[] player1Cards;
    Card[] player2Cards;
    String nextRound = "";
    boolean isGameActive = true;
    int roundsCounter = 1;
    int warInRowFactor = 0;
    int subtractCards = 0; 
    public CardWar() {
        startGame();       
    }
   
    public void startGame() {
        deck = new DeckOfCards();
        deck.printAllCards();
        deck.mixDeckOfCards();
        player1Cards = new Card[deck.deckOfCards.length/2];
        player2Cards = new Card[deck.deckOfCards.length - player1Cards.length];
        player1Cards = deck.dealTheCards(player1Cards.length );
        player2Cards = deck.dealTheCards(player2Cards.length);
        printPlayerCards(player1Cards);
        printPlayerCards(player2Cards);
       
        System.out.println("The game starts. Are you ready for it? (press enter)");
        Scanner sc = new Scanner(System.in);
        String nextRound = sc.nextLine();
       
        while (isGameActive) {
            if (roundsCounter > 1) {
                roundsCounter++;
                if (checkPlayersStatus(1)) {
                    break;
                }
                System.out.println("_____________________________________________________________________________________________________________");
                System.out.println("Round " + roundsCounter + ". Would you like to see another Round? (press enter to proceed, type 'exit' to abort game)");
                nextRound = sc.nextLine();
                if (!(nextRound.equals("exit"))) {
                    showPlayersCards();
                } else {
                    isGameActive = false;
                    break;
                }
            } else {
                roundsCounter++;
                showPlayersCards();
            }
        }
    }
   
    public void printPlayerCards(Card[] player) {
        for (int i=0; i<player.length; i++) {
            System.out.print(player[i].getCardName() + " ");
        }
        System.out.println();
    }
 
    public void showPlayersCards() {
        System.out.println(player1Cards.length + " cards  ////   " + player2Cards.length + " cards");
        System.out.println("\n Player 1    vs     Player 2\n" + "   " + player1Cards[0].getCardName() + "    vs    " + player2Cards[0].getCardName());
        changePlayersDeck(deck.strongerCard(player1Cards[0], player2Cards[0]));
        System.out.println("Player 1 dlugosc: " + player1Cards.length);
        System.out.println("Player 2 dlugosc: " + player2Cards.length);
        printPlayerCards(player1Cards);
        printPlayerCards(player2Cards);
//      isGameActive = false;
    }
   
    public void changePlayersDeck(Card win) {
        if (win == null) {
            if (checkPlayersStatus(3)) {
            	return;
            } else {
            	makeWar();
            }
        } else if (win.equals(player1Cards[0])) {
            player1Cards = addCards(player1Cards, player2Cards[0]);
            player2Cards = subtractCards(player2Cards);
            System.out.println("");
            System.out.println();
        } else if (win.equals(player2Cards[0])) {
            player2Cards = addCards(player2Cards, player1Cards[0]);
            player1Cards = subtractCards(player1Cards);
            System.out.println();
            System.out.println();
        }
    }
//  adds card for the winner player
    public Card[] addCards(Card[] winner, Card cardWon) {
        Card[] tempArr = new Card[winner.length];
        for (int i=0; i<winner.length; i++) {
            tempArr[i] = winner[i];
        }
        winner = new Card[tempArr.length + 1];
        for (int i=0; i<tempArr.length; i++) {
            if (i < tempArr.length -1) {
                winner[i] = tempArr[i+1];
            }
        }
        winner[winner.length-2] = tempArr[0];
        winner[winner.length-1] = cardWon;
        return winner;
    }
 
// adds cards for the winner player in a war (adds his 3 cards to the end of his deck and 3 cards taken from the loser)
    public Card[] addCards(Card[] winner, Card cardWon1, Card cardWon2, Card cardWon3) {
        Card[] tempArr = new Card[winner.length];
        for (int i=0; i<tempArr.length; i++) {
            tempArr[i] = winner[i];
        }
        winner = new Card[tempArr.length + 3];
        for (int i=0; i<tempArr.length; i++) {
            if (i < tempArr.length - 3) {
                winner[i] = tempArr[i+3];
            }
        }
        winner[winner.length-6] = tempArr[0];
        winner[winner.length-5] = tempArr[1];
        winner[winner.length-4] = tempArr[2];
        winner[winner.length-3] = cardWon1;
        winner[winner.length-2] = cardWon2;
        winner[winner.length-1] = cardWon3;
        return winner;
    }
   
//  subtracts lost card from a loser's deck
    public Card[] subtractCards(Card[] loser) {
        Card[] tempArr = new Card[loser.length];
        for (int i=0; i<tempArr.length; i++) {
            tempArr[i] = loser[i];
        }
        loser = new Card[tempArr.length -1];
        for (int i=0; i<loser.length; i++) {
            if (i < loser.length) {
                loser[i] = tempArr[i+1];
            }
        }
        return loser;
    }
   
//  subtracts lost 3 cards from a loser's deck after war
    public Card[] subtractCards(Card[] loser, int numberOfLostCards) {
    	if (checkPlayersStatus(numberOfLostCards)) {
    		return null;
    	}
        Card[] tempArr = new Card[loser.length];
        for (int i=0; i<tempArr.length; i++) {
            tempArr[i] = loser[i];
        }
        loser = new Card[tempArr.length - numberOfLostCards];
        for (int i=0; i<loser.length; i++) {
            loser[i] = tempArr[i+numberOfLostCards];
        }
        return loser;
    }
   
    public void makeWar() {
        warInRowFactor += 2;
        subtractCards = warInRowFactor + 1;
        System.out.println("Player puts another two cards on the table");
        System.out.println(player1Cards[warInRowFactor].getCardName() + "   vs   " + player2Cards[warInRowFactor].getCardName());
        if (player1Cards[warInRowFactor].getCardValue() == player2Cards[warInRowFactor].getCardValue()) {
            System.out.println("We have another tie, Time for next war!");
            subtractCards++;
            makeWar();
        } else if (player1Cards[warInRowFactor].getCardValue() > player2Cards[warInRowFactor].getCardValue()) {
            System.out.println("Player 1 is stronger");
            player1Cards = addCards(player1Cards, player2Cards[0], player2Cards[1], player2Cards[2]);
            player2Cards = subtractCards(player2Cards, subtractCards);
            warInRowFactor = 0;
        } else if (player1Cards[warInRowFactor].getCardValue() < player2Cards[warInRowFactor].getCardValue()) {
            System.out.println("Player 2 is stronger");
            player2Cards = addCards(player2Cards, player1Cards[0], player1Cards[1], player1Cards[2]);
            player1Cards = subtractCards(player1Cards, subtractCards);
            warInRowFactor = 0;
        }
    }
   
    public boolean checkPlayersStatus(int amountOfCards) {
        if (amountOfCards > player1Cards.length) {
            System.out.println("Player 1 has not enough card to play game");
            System.out.println("PLAYER 2 IS WINNER!!!!");
            isGameActive = false;
            return true;
        } else if (amountOfCards > player2Cards.length) {
            System.out.println("Player 2 has not enough card to play game");
            System.out.println("PLAYER 1 IS WINNER!!!!");
            isGameActive = false;
            return true;
        }
        return false;
    }
   
   
    public boolean exitGame() {
        return isGameActive = false;
    }
   
}