package mathAndLogicPuzzles;

/**
 * You have 1000 bottles of soda and exactly one is poisoned.
 * You have 10 test strips which can be used to detect poison.
 * A single drop of poison will trun the test strip positive permanently.
 * You can put any number of drops on a test strip at once and you can reuse a test strip as many times as you would like
 * However you can only run tests once per day and it takes seven days to return a result.
 * How would you figure out the poisoned bottle in as few days as possible?
 */
public class Poison {

	/**
	 * Approach 1:
	 * 1. Put one drop from 1000/11 = 91 bottles on 1 strip. Next 91 on another strip. Last batch will not be put outside.
	 * 2. After 7 days, we will know that one of the strip is positive.
	 * 3. From positive strip 91 bottles, put one drop from 91/10 = 9 on each remaining 9 strips. One batch will be put outside.
	 * 4. From 9 strips, put one drop from 9/9 = 1 on each of remaining 8 strips.
	 * 5. In 21 days, we will know the result.
	 * 
	 * Approach 2: binary numbers
	 * 1. Think each strip of a bit.
	 * 2. 10 bits can cover 2^10 = 1024 numbers.
	 * 3. So we can easily know 1000 bottles number from 10 strips.
	 * 4. Convert every bottle number in binary form and whereever one is there, on that strip put a drop.
	 * 5. Do this for all 1000 bottles.
	 * 6. In 7 days, we will get the results. All the strips whose color got changed, from those we will know which bottle was poisoned.
	 */
}
