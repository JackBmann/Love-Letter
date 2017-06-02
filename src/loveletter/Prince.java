package loveletter;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Prince extends Card {
	
	Scanner scan = new Scanner(System.in);

	
	public Prince(){
		name = "Prince";
		description = "Choose any player (including yourself) to discard his or her hand and draw a new card.";
		value = 5;
		quantity = 2;
	}

	@Override
	public void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard) {
		int targetNum = 0;
		while(targetNum < 1 || targetNum > 4) {
			System.out.println("Enter the number of the player you would like to force to discard (1-" + players.size() + "): ");
			targetNum = scan.nextInt();
			for(Player p: players)
				if(p.getPlayerNumber() == targetNum && (p.isOutOfRound() || p.isHandmaidActive()))
					targetNum = 0;
		}
		
		if(targetNum != discarder.getPlayerNumber()) {
			Player targetPlayer = null;
			for(Player p: players)
				if(p.getPlayerNumber() == targetNum)
					targetPlayer = p;
			
			if(targetPlayer.getHand().getValue() == 8) {
				System.out.println(discarder.getName() + " forced " + targetPlayer.getName() + " to discard.");
				
				targetPlayer.getHand().discard(players, targetPlayer, deck, roundDeck, buriedCard);
			}
			else if(roundDeck.size() == 0){
				System.out.println(discarder.getName() + " forced " + targetPlayer.getName() + " to discard.  "  + targetPlayer.getName() 
									+ " discarded a " + targetPlayer.getHand().getName() + " and drew the buried card.");
				
				targetPlayer.addToDiscardPile(targetPlayer.getHand());
				targetPlayer.setHand(buriedCard);
				buriedCard = null;
			}
			
			else {
				System.out.println(discarder.getName() + " forced " + targetPlayer.getName() + " to discard.  "  + targetPlayer.getName() 
									+ " discarded a " + targetPlayer.getHand().getName() + " and drew another card.");
				
				targetPlayer.addToDiscardPile(targetPlayer.getHand());
				targetPlayer.setHand(roundDeck.poll());
			}
		}
		else {
			if(discarder.getHand().getValue() == 8) {
				System.out.println(discarder.getName() + " forced himself to discard.");
				
				discarder.getHand().discard(players, discarder, deck, roundDeck, buriedCard);
			}
			
			else if(roundDeck.size() == 0){
				System.out.println(discarder.getName() + " forced himself to discard.  "  + discarder.getName() 
									+ " discarded a " + discarder.getHand().getName() + " and drew the buried card.");
				
				discarder.addToDiscardPile(discarder.getHand());
				discarder.setHand(buriedCard);
				buriedCard = null;
			}
			
			else {
				System.out.println(discarder.getName() + " forced himself to discard.  "  + discarder.getName() 
									+ " discarded a " + discarder.getHand().getName() + " and drew another card.");
				
				discarder.addToDiscardPile(discarder.getHand());
				discarder.setHand(roundDeck.poll());
			}
		}
	}
	
}
