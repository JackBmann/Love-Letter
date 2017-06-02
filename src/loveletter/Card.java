package loveletter;

import java.util.ArrayList;
import java.util.Queue;

abstract class Card {
	
	String name;
	String description;
	int value;
	int quantity;
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public abstract void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard);
	
}
