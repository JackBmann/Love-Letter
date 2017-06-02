package loveletter;

import java.util.ArrayList;
import java.util.Queue;

public class Countess extends Card {
	
	public Countess(){
		name = "Countess";
		description = "If you have this card and the King or Prince in your hand, you must discard this card.";
		value = 7;
		quantity = 1;
	}

	@Override
	public void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard) {
		System.out.println(discarder.getName() + " discarded a Countess.");
	}
	
}
