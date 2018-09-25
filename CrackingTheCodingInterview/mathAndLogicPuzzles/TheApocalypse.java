package mathAndLogicPuzzles;

/**
 * In the new post-apocalyptic world, the world queen is desperately concerned about the birth rate.
 * Therefore, she decrees that all families should ensure that they have one girl or else they face massive fines.
 * If all families abide by this policy- that is, they have continue to have chidlren until they have one girl, at which they immediately stop-
 * what will be the gender ratio of the new generation be? (Assume that the odds of having a boy or girl is equal)
 * Solve this out logically and then a computer simulation of it.
 */
public class TheApocalypse {

	/**
	 * Possibilities are: G, BG, BBG, BBBG, BBBBG, BBBBBG, ...
	 * No. of girls are: 1/2 + 1/2^2 + 1/2^3 + ... + 1/2^i + ... = 1
	 * No. of boys are: 0 + 1/2^2 + 2/2^3 + 3/2^4 + ... + i/2^(i+1) + ... ~= 1
	 * So ratio tends to 1 for infinite set 
	 */
}
