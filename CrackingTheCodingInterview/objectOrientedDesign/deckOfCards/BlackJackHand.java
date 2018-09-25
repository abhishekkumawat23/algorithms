package objectOrientedDesign.deckOfCards;

import java.util.ArrayList;

public class BlackJackHand extends Hand<BlackJackCard> {

	@Override
	public int score() {
		ArrayList<Integer> possibleScores = possibleScores();
		// Find max value under 21 and min value over 21
		int maxScoreUnder21 = Integer.MIN_VALUE;
		int minScoreOver21 = Integer.MAX_VALUE;
		for (int possibleScore: possibleScores) {
			if (possibleScore > 21 && possibleScore < minScoreOver21) {
				minScoreOver21 = possibleScore;
			}
			else if (possibleScore <= 21 && possibleScore >= maxScoreUnder21) {
				maxScoreUnder21 = possibleScore;
			}
		}
		return maxScoreUnder21 != Integer.MIN_VALUE ? maxScoreUnder21 : minScoreOver21;
	}
	
	public boolean busted() {
		return score() > 21;
	}
	
	public boolean is21() {
		return score() == 21;
	}
	
	// Get all possible scores as ace can be considered as 1 or 11.
	private ArrayList<Integer> possibleScores(){
		ArrayList<Integer> possibleScores = new ArrayList<Integer>();
		
		int onesCount = 0;
		int scoreExceptOnes = 0;
		for(BlackJackCard card: cards) {
			int value = card.value();
			if (value != 1) {
				scoreExceptOnes += value;
			}
			else {
				onesCount++;
			}
		}
		
		getPossibleScores(possibleScores, scoreExceptOnes, onesCount);
		return possibleScores;
	}

	private void getPossibleScores(ArrayList<Integer> possibleScores, int score, int onesCount) {
		if (onesCount == 0) {
			possibleScores.add(score);
			return;
		}
		getPossibleScores(possibleScores, score+1, onesCount-1);
		getPossibleScores(possibleScores, score+11, onesCount-1);
	}
}
