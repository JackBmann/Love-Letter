package loveletter;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Priest extends Card {
	
	Scanner scan = new Scanner(System.in);
	
	public Priest(){
		name = "Priest";
		description = "Look at another player's hand.";
		value = 2;
		quantity = 2;
	}

	@Override
	public void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard) {
		int targetNum = 0;
		while(targetNum < 1 || targetNum > 4 || targetNum == discarder.getPlayerNumber()) {
			System.out.println("Enter the number of the opposing player you would like to look at the hand of (1-" + players.size() + "): ");
			targetNum = scan.nextInt();
			for(Player p: players)
				if(p.getPlayerNumber() == targetNum && (p.isOutOfRound() || p.isHandmaidActive()))
					targetNum = 0;
		}
		
		Player targetPlayer = null;
		for(Player p: players)
			if(p.getPlayerNumber() == targetNum)
				targetPlayer = p;
		
		System.out.println(discarder.getName() + " opted to look at " + targetPlayer.getName() + "'s card.  " 
							+ targetPlayer.getName() + " had a " + targetPlayer.getHand().getName() + ".");
	}
	
}
