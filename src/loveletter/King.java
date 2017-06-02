package loveletter;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class King extends Card {
	
	Scanner scan = new Scanner(System.in);
	
	public King(){
		name = "King";
		description = "Trade hands with another player of your choice.";
		value = 6;
		quantity = 1;
	}

	@Override
	public void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard) {
		int targetNum = 0;
		while(targetNum < 1 || targetNum > 4 || targetNum == discarder.getPlayerNumber()) {
			System.out.println("Enter the number of the opposing player you would like to trade hands with (1-" + players.size() + "): ");
			targetNum = scan.nextInt();
			for(Player p: players)
				if(p.getPlayerNumber() == targetNum && (p.isOutOfRound() || p.isHandmaidActive()))
					targetNum = 0;
		}
		
		Player targetPlayer = null;
		for(Player p: players)
			if(p.getPlayerNumber() == targetNum)
				targetPlayer = p;
		
		System.out.println(discarder.getName() + " traded hands with " + targetPlayer.getName() + ".");
		
		Card temp = discarder.getHand();
		discarder.setHand(targetPlayer.getHand());
		targetPlayer.setHand(temp);
	}
	
}
