package recursionAndDynamicProgramming;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent),
 * write code to calculate the number of ways to represent n cents.
 */
public class Coins {

	/**
	 * Important points:
	 * 1. It makes a tree with max 4 childs(1, 5, 10, 25) at each node.
	 *  
	 * findWay(0, amount, 0, 0)
	 * findWay(val, amount, sum, ways):
	 *   sum += val
	 *   if sum == amount
	 *     ways++
	 *     return ways
	 *   else if sum > amount
	 *     return ways
	 *   if val <= 1 ways = findWay(1, amount, sum, ways)
	 *   if val <= 5 ways = findWay(5, amount, sum, ways)
	 *   if val <= 10 ways = findWay(10, amount, sum, ways)
	 *   if val <= 25 ways = findWay(25, amount, sum, ways)
	 *   return ways
	 */
	public int findWay(int val, int amount, int sum, int ways) {
		sum += val;
		if (sum == amount) {
			ways++;
			return ways;
		}
		else if (sum > amount) {
			return ways;
		}
		
		if (val <= 1) ways = findWay(1, amount, sum, ways);
		if (val <= 5) ways = findWay(5, amount, sum, ways);
		if (val <= 10) ways = findWay(10, amount, sum, ways);
		if (val <= 25) ways = findWay(25, amount, sum, ways);
		return ways;
	}
	
	public static void main(String[] args) {
		Coins c = new Coins();
		System.out.println(c.findWay(0, 21, 0, 0));
	}
}
