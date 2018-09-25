package objectOrientedDesign.deckOfCards;

// We are not making Card as abstract as Card makes sense without any game or without anything.
public class Card {

	protected int faceValue;
	protected Suit suit;
	private boolean isAvailable;
	
	public Card(int faceValue, Suit suit) {
		this.faceValue = faceValue;
		this.suit = suit;
	}
	
	public int value() {
		return faceValue;
	}
	
	public boolean isAvailbale() {
		return isAvailable;
	}
	
	public void markAvailable() {
		isAvailable = true;
	}
}
