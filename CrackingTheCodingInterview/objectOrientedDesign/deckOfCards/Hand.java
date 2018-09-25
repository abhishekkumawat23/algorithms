package objectOrientedDesign.deckOfCards;

import java.util.ArrayList;

public class Hand<T extends Card> {

	protected ArrayList<T> cards = new ArrayList<T>();
	
	public void addCard(T card) {
		cards.add(card);
	}
	
	// Deafult implementation of score of a hand is based on standard game. For specific games, we can override this method.
	public int score() {
		int score = 0;
		for (T card: cards) {
			score += card.value();
		}
		return score;
	}
}
