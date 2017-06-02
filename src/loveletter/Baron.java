package loveletter;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Baron extends Card {
	
	Scanner scan = new Scanner(System.in);
	
	public Baron(){
		name = "Baron";
		description = "You and another player secretly compare hands.  The player with the lower value is out of the round.";
		value = 3;
		quantity = 2;
	}

	@Override
	public void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard) {
		int targetNum = 0;
		while(targetNum < 1 || targetNum > 4 || targetNum == discarder.getPlayerNumber()) {
			System.out.println("Enter the number of the opposing player you would like to compare hands with (1-" + players.size() + "): ");
			targetNum = scan.nextInt();
			for(Player p: players)
				if(p.getPlayerNumber() == targetNum && (p.isOutOfRound() || p.isHandmaidActive()))
					targetNum = 0;
		}
		
		Player targetPlayer = null;
		for(Player p: players)
			if(p.getPlayerNumber() == targetNum)
				targetPlayer = p;
		
		if(targetPlayer.getHand().getValue() < discarder.getHand().getValue()) {
			System.out.println(discarder.getName() + " compared hands with " + targetPlayer.getName() + ".  " + targetPlayer.getName() 
								+ " had a " + targetPlayer.getHand().getName() + " is was lower than " + discarder.getName() + 
								"'s card!  " + targetPlayer.getName() + " is eliminated from the round.");
			
			targetPlayer.setOutOfRound(true);
			targetPlayer.addToDiscardPile(targetPlayer.getHand());
			targetPlayer.setHand(null);
		}
		else if (targetPlayer.getHand().getValue() > discarder.getHand().getValue()) {
			System.out.println(discarder.getName() + " compared hands with " + targetPlayer.getName() + ".  " + targetPlayer.getName() 
								+ " had a " + targetPlayer.getHand().getName() + " which is higher than " + discarder.getName() + 
								"'s card!  " + discarder.getName() + " is eliminated from the round.");
			
			discarder.setOutOfRound(true);
			discarder.addToDiscardPile(discarder.getHand());
			discarder.setHand(null);
		}
		else {
			System.out.println(discarder.getName() + " compared hands with " + targetPlayer.getName() + 
								".  Both players have the same card.");
		}
	}
	
}
