package loveletter;

abstract class Card {
	
	String name;
	String description;
	int value;
	int quantity;
	
	public String toString() {
		return name;
	}
}
