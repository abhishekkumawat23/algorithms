package mathAndLogicPuzzles;

/**
 * There is a building of 100 floors. If an egg drops from the Nth floor or above, it will break.
 * If it's dropped from any floor below, it will not break. You are given two eggs.
 * Find N, while minimizing the number of drops for the worst case.
 */
public class TheEggDropProblem {

	/**
	 * Approach 1: Minimizing worst case
	 * 1. drops = 100/x + (x-1) where x is the first floor to try on. Minimize this.
	 * 2. Try first at x, 2x, 3x till we reach 100. When egg breaks at 100, we need egg2 to move from safe floor to 100.
	 * 3. TO minimize drops 100/x + x-1 should be minimum. For minimum, differentiation -100/x^2 + 1 should be 0. SO, x = 10/
	 * 4. SO drops = 19.
	 * 
	 * Approach 2: Minimizing average case
	 * 1. Lets say we drop egg at xth floor. If it breaks, then drops = 1+(x-1) = x
	 * 2. If egg doesn't break, then lets increment floor by x-1 as we have already used one drop. So if egg1 breaks, drops = 1 + 1 + (x-2) = x
	 * 3. So, floor increment will be x + x-1 + x-2 + .. 1 = 100 => x = 13.65 ~= 14 
	 */
}
