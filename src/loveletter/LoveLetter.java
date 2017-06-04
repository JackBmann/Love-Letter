package loveletter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Jack Baumann
 * Started: 5/31/17
 * Last Updated: 6/1/17
 * Plays a game of Love Letter with up to 4 computers or humans.
 */
public class LoveLetter {
	
	ArrayList<Card> deck = new ArrayList<Card>();
	Queue<Card> roundDeck = new LinkedList<Card>();
	Card buriedCard;
	Card[] faceUp2PlayerCards = new Card[3];
	Player player1;
	Player player2;
	Player player3;
	Player player4;
	ArrayList<Player> players = new ArrayList<Player>();
	int[] numTokensForWin = {1, 5, 4};
	final int deckSize = 16;
	int whoseTurn;
	int numTotalPlayers;
	int numHumanPlayers;
	Scanner scan = new Scanner(System.in);
	
	public LoveLetter() {
		createDeck();
		whoseTurn = 1;
		numTotalPlayers = 4;
		numHumanPlayers = 1;
		player1 = new Player("Player 1", true, 1, 0);
		player2 = new Player("Player 2", false, 2, 1);
		player3 = new Player("Player 3", false, 3, 1);
		player4 = new Player("Player 4", false, 4, 1);
		player1.setNumTokens(0);
		player2.setNumTokens(0);
		player3.setNumTokens(0);
		player4.setNumTokens(0);
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
	}

	public LoveLetter(int numTotal, int numHuman, ArrayList<Integer> difficulty) {
		createDeck();
		whoseTurn = 1;
		numTotalPlayers = numTotal;
		numHumanPlayers = numHuman;
		player1 = new Player("Player 1", true, 1, 0);
		player1.setNumTokens(0);
		players.add(player1);
		if(numHuman > 1)
			player2 = new Player("Player 2", true, 2, 0);
		else
			player2 = new Player("Player 2", false, 2, difficulty.remove(0));
		player2.setNumTokens(0);
		players.add(player2);
		if(numTotal > 2){
			if(numHuman > 2)
				player3 = new Player("Player 3", true, 3, 0);
			else
				player3 = new Player("Player 3", false, 3, difficulty.remove(0));
			player3.setNumTokens(0);
			players.add(player3);
		}
		if(numTotal > 3){
			if(numHuman > 3)
				player4 = new Player("Player 4", true, 4, 0);
			else
				player4 = new Player("Player 4", false, 4, difficulty.remove(0));
			player4.setNumTokens(0);
			players.add(player4);
		}
	}
	
	/**
	 * Create and add cards to deck
	 */
	private void createDeck() {
		Card guard = new Guard();
		Card priest = new Priest();
		Card baron = new Baron();
		Card handmaid = new Handmaid();
		Card prince = new Prince();
		Card king = new King();
		Card countess = new Countess();
		Card princess = new Princess();
		
		deck.add(guard);
		deck.add(guard);
		deck.add(guard);
		deck.add(guard);
		deck.add(guard);
		deck.add(priest);
		deck.add(priest);
		deck.add(baron);
		deck.add(baron);
		deck.add(handmaid);
		deck.add(handmaid);
		deck.add(prince);
		deck.add(prince);
		deck.add(king);
		deck.add(countess);
		deck.add(princess);
	}
	
	/**
	 * Clears the roundDeck Queue and every player's discard pile, shuffles the deck, and re-deals.
	 */
	void shuffleAndDeal() {
		// Reset roundDeck
		roundDeck.removeAll(deck);
		for(Player p: players)
			p.clearDiscardPile();
		
		// Shuffle
		Collections.shuffle(deck);
		
		// Deal
		roundDeck.addAll(deck);
		buriedCard = roundDeck.poll();
		if(numTotalPlayers == 2) {
			faceUp2PlayerCards[0] = roundDeck.poll();
			faceUp2PlayerCards[1] = roundDeck.poll();
			faceUp2PlayerCards[2] = roundDeck.poll();
		}
		for(Player p: players)
			p.setHand(roundDeck.poll());
		
//		System.out.println(deck);
//		System.out.println(roundDeck);
//		System.out.println(buriedCard);
//		System.out.println(faceUp2PlayerCards[0] + " " + faceUp2PlayerCards[1] + " " + faceUp2PlayerCards[2]);
//		for(Player p: players)
//			System.out.println(p.getHand());
//		System.out.println();
	}

	public void play() {
		while(!isGameOver()){
			for(Player p: players)
				p.setOutOfRound(false);
			shuffleAndDeal();
			System.out.println("\nA new round is beginning!");
			if(numTotalPlayers == 2)
				System.out.println("The three face up cards are: " + faceUp2PlayerCards[0] + ", " + 
									faceUp2PlayerCards[1] + ", and " + faceUp2PlayerCards[2] + ".");
			while(!isRoundOver()) {
				Player player = getPlayerFromNumber(whoseTurn);
				if(!player.isOutOfRound()) {
					takeTurn(player);
					incrementWhoseTurn();
				}
				else
					incrementWhoseTurn();
			}
			roundOver();
		}
		gameOver();
		
		scan.nextLine();
		char answer = 'a';
		while(answer != 'y' && answer != 'n'){
			System.out.println("Would you like to play again with the same setup? (y/n)"); 
			answer = scan.nextLine().charAt(0);
		}
		if(answer == 'y')
			play();
	}

	private void incrementWhoseTurn() {
		if(whoseTurn == numTotalPlayers)
			whoseTurn = 1;
		else
			whoseTurn++;
	}

	private boolean isGameOver() {
		for(Player p: players)
			if(p.getNumTokens() == numTokensForWin[numTotalPlayers-2])
				return true;
		
		return false;
	}
	
	private boolean isRoundOver() {
		if(roundDeck.size() == 0)
			return true;
		
		if(getPlayersStillInRound().size() == 1)
			return true;
		
		return false;
	}
	
	private void gameOver() {
		Player winner = null;
		for(Player p: players)
			if(p.getNumTokens() == numTokensForWin[numTotalPlayers-2]) {
				winner = p;
				p.setNumTokens(0);
			}
		
		winner.incrementNumTotalWins();
		System.out.println(winner.getName() + " has won the affection of the Princess by collecting " + 
						numTokensForWin[numTotalPlayers-2] + " tokens of affection.  " + winner.getName() + 
						" has won " + winner.getNumTotalWins() + " total games.");
		
	}

	private void roundOver() {
		// If the deck is empty find who has the highest card
		if(roundDeck.size() == 0) {
			// Find the highest card still in a player's hand
			Card highestCard = new Guard();
			for(Player p: getPlayersStillInRound())
				if(p.getHand().getValue() > highestCard.getValue())
					highestCard = p.getHand();
			
			// Find all the players holding that card
			ArrayList<Player> tiedPlayers = new ArrayList<Player>();
			for(Player p: getPlayersStillInRound())
				if(p.getHand().getValue() == highestCard.getValue())
					tiedPlayers.add(p);
			
			// If only one player has the highest card, he wins the round
			if(tiedPlayers.size() == 1) {
				Player winner = tiedPlayers.get(0);
				winner.incrementNumTokens();
				whoseTurn = winner.getPlayerNumber();	// Set the winner to go first next turn
				System.out.println(winner.getName() + " won the round by having the highest card at the end of the round.");
			}
			// If there is a tie, break it by whichever player has the highest total value in his discard pile
			else {
				// Find the highest total discard pile value out of the tied players
				int highestDiscardPileValue = 0;
				for(Player p: tiedPlayers)
					if(p.addTotalDiscardPileValue() > highestDiscardPileValue)
						highestDiscardPileValue = p.addTotalDiscardPileValue();
				
				// Find all the tied players with that discard pile value
				ArrayList<Player> stillTiedPlayers = new ArrayList<Player>();
				for(Player p: tiedPlayers)
					if(p.addTotalDiscardPileValue() == highestDiscardPileValue)
						stillTiedPlayers.add(p);
				
				// Every player who is still tied wins the round
				for(Player p: stillTiedPlayers) {
					p.incrementNumTokens();
					whoseTurn = p.getPlayerNumber();	// Set a winner to go first next turn
					System.out.println(p.getName() + " won the round by having the highest card and highest total "
										+ "value in his discard pile at the end of the round.");
				}
			}
		}
		// Otherwise there must only be one player still in the round, he wins
		else {
			Player winner = getPlayersStillInRound().get(0);
			winner.incrementNumTokens();
			whoseTurn = winner.getPlayerNumber();	// Set the winner to go first next turn
			System.out.println(winner.getName() + " won the round by eliminating all other players.");
		}
		// Print total number of tokens of affection for each player
		String totals = "";
		for(Player p: players)
			totals += (p.name + " - " + p.getNumTokens() + ", ");
		totals = totals.substring(0, totals.length()-2);
		System.out.println("The token of affection totals for each player are now: " + totals + ".");
	}
	
	private void takeTurn(Player player) {
		Card drawnCard = roundDeck.poll();
		
		if(player.isHandmaidActive())
			player.setHandmaidActive(false);
		
		if(player.isHuman())
			takeHumanTurn(player, drawnCard);
		else
			takeComputerTurn(player, drawnCard);
	}

	private void takeHumanTurn(Player player, Card drawnCard) {	
		Card hand = player.getHand();
		int choice = 0;
		
		// Find out if every opposing player has a Handmaid active
		ArrayList<Player> otherPlayers = getPlayersStillInRound();
		otherPlayers.remove(player);
		boolean allHaveActiveHandmaid = true;
		for(Player p: otherPlayers)
			if(!p.isHandmaidActive())
				allHaveActiveHandmaid = false;
		
		// If yes, simply chose a card to discard (unless player chooses a prince, then is forced to discard both cards and draw again)
		if(allHaveActiveHandmaid){
			while((choice != hand.getValue() && choice != drawnCard.getValue()) || choice == 8) {
				System.out.println(player.getName() + ", you have a " + hand.getName() + " in your hand and you drew a " + 
							drawnCard.getName() + ".  Every other player has a Handmaid active; please enter the number of "
							+ "the card you would like to discard (" + hand.getValue() + " or " + drawnCard.getValue() + "): ");
				choice = scan.nextInt();
				
				// Check to see if countess must be played.
				if((hand.getValue() == 7 || drawnCard.getValue() == 7) && (choice == 5 || choice == 6))
					choice = 0;
			}
			
			if(drawnCard.getValue() == choice) {
				if(choice == 5){
					player.addToDiscardPile(drawnCard);
					drawnCard.discard(players, player, deck, roundDeck, drawnCard);
				}
				else {
					player.addToDiscardPile(drawnCard);
					System.out.println(player.getName() + " discarded a " + drawnCard.getName() + ".");
				}
			}
			else {
				if(choice == 5){
					player.addToDiscardPile(hand);
					player.setHand(drawnCard);
					hand.discard(players, player, deck, roundDeck, drawnCard);
				}
				else{
					player.addToDiscardPile(hand);
					player.setHand(drawnCard);
					System.out.println(player.getName() + " discarded a " + drawnCard.getName() + ".");
				}
			}
		}
		// Otherwise, proceed as normally
		else {
			while((choice != hand.getValue() && choice != drawnCard.getValue()) || choice == 8) {
				System.out.println(player.getName() + ", you have a " + hand.getName() + " in your hand and you drew a " +
									drawnCard.getName() + ".  Please enter the number of the card you would like to discard (" 
									+ hand.getValue() + " or " + drawnCard.getValue() + "): ");
				choice = scan.nextInt();
				
				// Check to see if countess must be played.
				if((hand.getValue() == 7 || drawnCard.getValue() == 7) && (choice == 5 || choice == 6))
					choice = 0;
			}
			
			if(drawnCard.getValue() == choice) {
				player.addToDiscardPile(drawnCard);
				drawnCard.discard(players, player, deck, roundDeck, drawnCard);
			}
			else {
				player.addToDiscardPile(hand);
				player.setHand(drawnCard);
				hand.discard(players, player, deck, roundDeck, drawnCard);
			}
		}
	}

	private void takeComputerTurn(Player player, Card drawnCard) {
		// TODO Auto-generated method stub
		
	}

	private Player getPlayerFromNumber(int who) {
		for(Player p: players)
			if(p.getPlayerNumber() == who)
				return p;
		return null;
	}
	
	private ArrayList<Player> getPlayersStillInRound() {
		ArrayList<Player> playersStillInRound = new ArrayList<Player>();
		for(Player p: players){
			if(!p.isOutOfRound())
				playersStillInRound.add(p);
		}
		return playersStillInRound;
	}

}