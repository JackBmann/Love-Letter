package loveletter;

import java.util.ArrayList;
import java.util.Queue;

public class Princess extends Card {
	
	public Princess(){
		name = "Princess";
		description = "If you discard this card, you are out of the round.";
		value = 8;
		quantity = 1;
	}

	@Override
	public void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard) {
		System.out.println(discarder.getName() + " was forced to discard the Princess.  " 
						 + discarder.getName() + " has been eliminated from the round.");
		
		discarder.setOutOfRound(true);
		discarder.addToDiscardPile(discarder.getHand());
		discarder.setHand(null);
	}
	
}
