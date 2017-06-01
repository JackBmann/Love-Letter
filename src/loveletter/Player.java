package loveletter;

import java.util.ArrayList;

public class Player {
	
	String name;
	boolean human;
	int difficulty; // 0 = human, 1 = getDumbComputerMove(), 2 = getSmartComputerMove(), 3 = getHeuristicComputerMove()
	Card hand;
	ArrayList<Card> discardPile;
	int numTokensOfAffection;
	int numOfTotalWins;
	
	public Player() {
		name = "Player 1";
		human = true;
		hand = null;
		discardPile = new ArrayList<Card>();
		numTokensOfAffection = 0;
		numOfTotalWins = 0;
	}
	
	public Player(String n, boolean h, int diff) {
		name = n;
		human = h;
		difficulty = diff;
		hand = null;
		discardPile = new ArrayList<Card>();
		numTokensOfAffection = 0;
		numOfTotalWins = 0;
	}

	public Player(String n, boolean h, int diff, Card c, ArrayList<Card> discard, int tokens, int wins) {
		name = n;
		human = h;
		difficulty = diff;
		hand = c;
		discardPile = discard;
		numTokensOfAffection = tokens;
		numOfTotalWins = wins;
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public boolean isHuman() {
		return human;
	}
	
	public Card getHand() {
		return hand;
	}
	
	public void setHand(Card c) {
		hand = c;
	}
	
	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}
	
	public void addToDiscardPile(Card c) {
		discardPile.add(c);
	}
	
	public void clearDiscardPile() {
		discardPile.clear();
	}
	
	public int getNumTokens() {
		return numTokensOfAffection;
	}
	
	public void setNumTokens(int newNum) {
		numTokensOfAffection = newNum;
	}
	
}
