package loveletter;

import java.util.ArrayList;
import java.util.Scanner;

public class Play {

	static Scanner scan = new Scanner(System.in);
	static int numTotalPlayers = 0;
	static int numHumanPlayers = 0;
	static ArrayList<Integer> difficulty = new ArrayList<Integer>();
	
	public static void main(String[] args) {
//		System.out.println("Enter 1 to play Love Letter, 2 to play Love Letter Deluxe: ");
		while(numTotalPlayers < 2 || numTotalPlayers > 4){
			System.out.println("Enter the number of total players (human and computer, 2-4): ");
			numTotalPlayers = scan.nextInt();
		}
		while(numHumanPlayers < 1 || numHumanPlayers > numTotalPlayers){
			System.out.println("Enter the number of human players (1-4): ");
			numHumanPlayers = scan.nextInt();
		}
		for(int i = 1; i <= (numTotalPlayers - numHumanPlayers); i++){
			int diff = 0;
			while(diff < 1 || diff > 3){
				System.out.println("Enter the difficulty of computer " + i + " (1-3): ");
				diff = scan.nextInt();
			}
			difficulty.add(diff);
		}
		LoveLetter game = new LoveLetter(numTotalPlayers, numHumanPlayers, difficulty);
		game.shuffleAndDeal();
		game.shuffleAndDeal();
		game.shuffleAndDeal();
		game.shuffleAndDeal();
		//game.play();
	}

}
