package objectOrientedDesign.deckOfCards;

public class BlackJackCard extends Card {

	public BlackJackCard(int faceValue, Suit suit) {
		super(faceValue, suit);
	}
	
	public boolean isFaceCard() {
		return faceValue >= 11 && faceValue <= 13;
	}

	@Override
	public int value() {
		if (faceValue >= 11 && faceValue <= 13) {
			return faceValue;
		}
		return faceValue;
	}
}
