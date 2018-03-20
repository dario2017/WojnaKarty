package cardwar;

import java.util.Scanner;

public class CardWar {
	DeckOfCards deck;
	Card[] player1Cards;
	Card[] player2Cards;
	String nextRound = "";
	boolean isGameActive = true;
	int roundsCounter = 1;
	
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
//		for (int i=0; i<player1Cards.length; i++) {
//			System.out.print(player1Cards[i].getCardName());
//		}
//		System.out.println();
//		for (int i=0; i<player2Cards.length; i++) {
//			System.out.print(player2Cards[i].getCardName());
//		}
		printPlayerCards(player1Cards);
		printPlayerCards(player2Cards);
//		isGameActive = false;
	}
	
	public void changePlayersDeck(Card win) {
		if (win == null) {
			makeWar();
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
	
	public void makeWar() {
		System.out.println("Doprowadzono do remisu wi�c WOJNA!!!");
	}
	
	public boolean exitGame() {
		return isGameActive = false;
	}
	
}
