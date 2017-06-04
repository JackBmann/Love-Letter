package loveletter;

import java.util.ArrayList;

public class Player {
	
	String name;
	boolean human;
	int playerNumber;
	int difficulty; // 0 = human, 1 = getDumbComputerMove(), 2 = getSmartComputerMove(), 3 = getHeuristicComputerMove()
	boolean outOfRound;
	boolean handmaidActive;
	Card hand;
	ArrayList<Card> discardPile;
	int numTokensOfAffection;
	int numTotalWins;
	
	public Player() {
		name = "Player 1";
		human = true;
		playerNumber = 1;
		difficulty = 0;
		outOfRound = false;
		handmaidActive = false;
		hand = null;
		discardPile = new ArrayList<Card>();
		numTokensOfAffection = 0;
		numTotalWins = 0;
	}
	
	public Player(String n, boolean h, int num, int diff) {
		name = n;
		human = h;
		playerNumber = num;
		difficulty = diff;
		outOfRound = false;
		handmaidActive = false;
		hand = null;
		discardPile = new ArrayList<Card>();
		numTokensOfAffection = 0;
		numTotalWins = 0;
	}

	public Player(String n, boolean h, int num, int diff, boolean out, boolean handmaid, Card c, ArrayList<Card> discard, int tokens, int wins) {
		name = n;
		human = h;
		playerNumber = num;
		difficulty = diff;
		outOfRound = out;
		handmaidActive = handmaid;
		hand = c;
		discardPile = discard;
		numTokensOfAffection = tokens;
		numTotalWins = wins;
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
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public void setPlayerNumber(int num) {
		playerNumber = num;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public boolean isOutOfRound() {
		return outOfRound;
	}
	
	public void setOutOfRound(boolean out) {
		outOfRound = out;
	}
	
	public boolean isHandmaidActive(){
		return handmaidActive;
	}
	
	public void setHandmaidActive(boolean active) {
		handmaidActive = active;
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
	
	public int addTotalDiscardPileValue() {
		int totalValue = 0;
		for(Card c: discardPile)
			totalValue += c.getValue();
		return totalValue;
	}
	
	public int getNumTokens() {
		return numTokensOfAffection;
	}
	
	public void setNumTokens(int newNum) {
		numTokensOfAffection = newNum;
	}
	
	public void incrementNumTokens() {
		numTokensOfAffection++;
	}
	
	public int getNumTotalWins() {
		return numTotalWins;
	}
	
	public void setNumTotalWins(int newNum) {
		numTotalWins = newNum;
	}
	
	public void incrementNumTotalWins() {
		numTotalWins++;
	}
	
}
