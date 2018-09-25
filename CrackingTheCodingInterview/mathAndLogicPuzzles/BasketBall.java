package mathAndLogicPuzzles;

/**
 * You have a basketball hoop and someone says that you can play one of two games.
 * Game 1: You get one shot to make the hoop
 * Game 2: You get 3 shots and you have to make two out of three shots.
 * If p is the probability of making a particular shot, for which values of p should you pick one game or the other?
 */
public class BasketBall {

	/**
	 * Answer:
	 * 1. Game 1: Probability to win game 1 is p.
	 * 2. Game 2: Probability to win game 2 is p*p + p*(1-p)*p + (1-p)*p*p = p^2 + 2p*p(1-p) = 3p^2 - 2p^3
	 * 3. For game 1, p > 3p^2-2p^3 => 1 > 3p - 2p^2 => -2p^2 + 3p - 1 < 0 => 2p^2 - 3p + 1 > 0 => (p-1)(p-0.5) > 0
	 * 4. So for p < 0.5, choose game 1. For p=0.5, choose either of game 1 or game 2. For p > 0.5, choose game 2.
	 */
}
