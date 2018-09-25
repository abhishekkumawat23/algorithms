package mathAndLogicPuzzles;

/**
 * You have 20 bottles of pills. 19 bottles have 1.0 gm pills and one has pills of weight 1.1 grams.
 * Given a scale that provides an exact measurement, how would you find the heavy bottle? 
 */
public class TheHeavyPill {

	/**
	 * Answer
	 * 1. From ith bottle, pick i pills.
	 * 2. Weight all these pills together.
	 * 3. If ith is the bottle with heavy weight, then weight = 1.0 + 2.0 + 20.0 + 0.1*i = 210 + 0.1*i.
	 * 4. So, i = 10*(weight-210)
	 */
}
