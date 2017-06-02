package loveletter;

import java.util.ArrayList;
import java.util.Queue;

public class Handmaid extends Card {
	
	public Handmaid(){
		name = "Handmaid";
		description = "Until your next turn, ignore all effects from other players' cards.";
		value = 4;
		quantity = 2;
	}

	@Override
	public void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard) {
		System.out.println(discarder.getName() + " played a handmaid and cannot be targeted again until his next play.");
		
		discarder.setHandmaidActive(true);
	}
	
}
