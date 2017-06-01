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
	Player player1;
	Player player2;
	Player player3;
	Player player4;
	ArrayList<Player> players = new ArrayList<Player>();
	int[] numTokensForWin = {8, 6, 4};
	final int deckSize = 16;
	int whoseTurn;
	int numTotalPlayers;
	int numHumanPlayers;
	
	public LoveLetter() {
		createDeck();
		whoseTurn = 1;
		numTotalPlayers = 4;
		numHumanPlayers = 1;
		player1 = new Player("Player 1", true, 0);
		player2 = new Player("Player 2", false, 1);
		player3 = new Player("Player 3", false, 1);
		player4 = new Player("Player 4", false, 1);
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
		player1 = new Player("Player 1", true, 0);
		player1.setNumTokens(0);
		players.add(player1);
		if(numHuman > 1)
			player2 = new Player("Player 2", true, 0);
		else
			player2 = new Player("Player 2", false, difficulty.remove(0));
		player2.setNumTokens(0);
		players.add(player2);
		if(numTotal > 2){
			if(numHuman > 2)
				player3 = new Player("Player 3", true, 0);
			else
				player3 = new Player("Player 3", false, difficulty.remove(0));
			player3.setNumTokens(0);
			players.add(player3);
		}
		if(numTotal > 3){
			if(numHuman > 3)
				player4 = new Player("Player 4", true, 0);
			else
				player4 = new Player("Player 4", false, difficulty.remove(0));
			player4.setNumTokens(0);
			players.add(player4);
		}
	}
	
	/**
	 * Create and add cards to deck
	 */
	private void createDeck() {
		Card guard1 = new Guard();
		Card guard2 = new Guard();
		Card guard3 = new Guard();
		Card guard4 = new Guard();
		Card guard5 = new Guard();
		Card priest1 = new Priest();
		Card priest2 = new Priest();
		Card baron1 = new Baron();
		Card baron2 = new Baron();
		Card handmaid1 = new Handmaid();
		Card handmaid2 = new Handmaid();
		Card prince1 = new Prince();
		Card prince2 = new Prince();
		Card king = new King();
		Card countess = new Countess();
		Card princess = new Princess();
		
		deck.add(guard1);
		deck.add(guard2);
		deck.add(guard3);
		deck.add(guard4);
		deck.add(guard5);
		deck.add(priest1);
		deck.add(priest2);
		deck.add(baron1);
		deck.add(baron2);
		deck.add(handmaid1);
		deck.add(handmaid2);
		deck.add(prince1);
		deck.add(prince2);
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
		for(Player p: players)
			p.setHand(roundDeck.poll());
		
//		System.out.println(deck);
//		System.out.println(roundDeck);
//		System.out.println(buriedCard);
//		for(Player p: players)
//			System.out.println(p.getHand());
//		System.out.println();
	}

}