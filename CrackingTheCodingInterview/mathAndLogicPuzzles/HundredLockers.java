package mathAndLogicPuzzles;

/**
 * There are 100 closed lockers in a hallway. A man begins by opening all 100 lockers.
 * Next, he closed every second locker. The, on this third pass, he toggles every third locker.
 * This process continues for 100 passes, such taht on each pass i, the man toggles every ith locker.
 * After his 100th pass in the hallway, in which he toggles only locker 100, how many lockers are open?
 */
public class HundredLockers {

	/**
	 * ith door will be toggled for every factor of i.
	 * Example: 15 factors are 1, 3, 5, 15.
	 * If number of factors are even, then locker will be closed.
	 * If number of factors are odd, then lockers will be open.
	 * Factors exists in pair. Example: factors of 42 are (1, 42), (2, 21), (3, 14), (6, 7) and thus are even in number
	 * In only the case of perfect square number, factors are odd as sqrt of number comes 2 times.
	 * Example: factors of 36 are (1, 36), (2, 18), (3, 12), (4, 9), (6, 6). here 6 is duplciate. So number of factors are odd.
	 * So, for perfect square, locker will finally be open.
	 * So, locker 1*1, 2*2, 3*3, ..., 10*10 are the 10 lockers which will be open.  
	 */
}
