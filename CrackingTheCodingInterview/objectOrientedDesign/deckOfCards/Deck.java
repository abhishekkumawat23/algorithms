package objectOrientedDesign.deckOfCards;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card>  cards;
	private int dealtIndex = 0; // marks first undealt card.
	
	public void resetDeck() {
		// Sort deck again.
		// Set dealt index to zero.
		// Mark all cards availbale.
	}
	
	public void shuffle() {
		// Shuffle all cards.
		// Only shuffle before dealing i.e. when dealtIndex = 0
	}
	
	public int remainingCards() {
		// remaining cards which are undealt.
		return cards.size() - dealtIndex;
	}
	
	public Card dealCard() {
		// Deal the first undealt to a player.
		// Increase the dealtIndex
		// Set isAvailable of card to false as card is with player now and not in deck.
		return null;
	}
	
	public Card[] dealHand(int number) {
		// deal first undealt given number of cards to a player.
		// Increase the dealtIndex by number.
		// Set isAvailable of card to false as card is with player now and not in deck.
		return null;
	}
}
