package loveletter;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Guard extends Card {
	
	Scanner scan = new Scanner(System.in);
	
	public Guard(){
		name = "Guard";
		description = "Name a non-Guard card and choose another player.  If that player has that card, he or she is out of the round.";
		value = 1;
		quantity = 5;
	}

	@Override
	public void discard(ArrayList<Player> players, Player discarder, ArrayList<Card> deck, Queue<Card> roundDeck, Card buriedCard) {
		int targetNum = 0;
		while(targetNum < 1 || targetNum > players.size() || targetNum == discarder.getPlayerNumber()) {
			System.out.println("Enter the number of the opposing player you would like to guess (1-" + players.size() + "): ");
			targetNum = scan.nextInt();
			for(Player p: players)
				if(p.getPlayerNumber() == targetNum && (p.isOutOfRound() || p.isHandmaidActive()))
					targetNum = 0;
		}
		
		Player targetPlayer = null;
		for(Player p: players)
			if(p.getPlayerNumber() == targetNum)
				targetPlayer = p;
		
		int guess = 0;
		while(guess < 2 || guess > 8) {
			System.out.println("Enter the number of the card you would like to guess " + targetPlayer.name + " has (2-8): ");
			guess = scan.nextInt();
		}
		
		Card targetCard = null;
		for(Card c: deck)
			if(c.getValue() == guess)
				targetCard = c;
		
		if(targetPlayer.getHand() == targetCard){
			System.out.println(discarder.getName() + " guessed that " + targetPlayer.getName() + " had a " + targetCard.getName() 
								+ " and was correct!  " + targetPlayer.getName() + " is eliminated from the round.");
			
			targetPlayer.setOutOfRound(true);
			targetPlayer.addToDiscardPile(targetPlayer.getHand());
			targetPlayer.setHand(null);
			
			// discarder.incrementNumTokens(); // For alternate rules of Love Letter, like the Batman version
		}
		
		else
			System.out.println(discarder.getName() + " guessed that " + targetPlayer.getName() + " had a " 
								+ targetCard.getName() + " and was incorrect!");
	}
	
}
