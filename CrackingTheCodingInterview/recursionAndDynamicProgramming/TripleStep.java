package recursionAndDynamicProgramming;

/**
 * A child is running up a staircase with n steps and can hop either 1 step, 2 step or 3 steps at a time.
 * Implement a method to count how many possible ways the child can run up the stairs.
 */
public class TripleStep {

	/**
	 * Time complexity: O(3^n); Space: O(h) where h == n
	 * 
	 * Important points:
	 * 1. f(n) = f(n-1) + f(n-2) + f(n-3)
	 * 2. f( < 0) = 0; f(0) = 1; f(1) = 1
	 * 3. Time complexity O(3^n) is upper bound. In actual it will be less than 3 but still exponential.
	 *    This complexity is assuming that the trinary tree formed is perfect. In actual its fibonacci trinary tree.
	 * 4. Not a efficient algorithm.
	 * 
	 * countSteps(n):
	 *   if n < 0 then return 0
	 *   if n <= 1 then return n
	 *   return countSteps(n-1) + countSteps(n-2) + countSteps(n-3)
	 */
	public int countSteps_recursion(int n) {
		if (n < 0) {
			return 0;
		}
		if (n <= 1) {
			return n;
		}
		
		return countSteps_recursion(n-1) + countSteps_recursion(n-2) + countSteps_recursion(n-3);
	}
	
	/**
	 * Time complexity: O(n); Space: O(h) where h is n
	 * 
	 * Important points:
	 * 1. Due to memoization, we need to calculate f(n-1) only for f(n). f(n-2) and f(n-3) are already calculated because of memoizaton.
	 * 
	 * countSteps(n, memo):
	 *   if memo[n] != 0
	 *     return memo[n]
	 *   if n < 0
	 *     memo[n] = 0
	 *   else if n <= 1
	 *     memo[n] = n
	 *   else 
	 *     memo[n] = countSteps(n-1) + countSteps(n-2) + countSteps(n-3)
	 *   return memo[n]
	 */
	public int countSteps_topDown_memoization(int n, int[] memo) {
		if (memo[n] != 0) {
			return memo[n];
		}
		if(n < 0) {
			memo[n] = 0;
		}
		else if (n <= 1) {
			memo[n] = n;
		}
		else {
			memo[n] = countSteps_topDown_memoization(n-1, memo) + countSteps_topDown_memoization(n-2, memo) + countSteps_topDown_memoization(n-3, memo);
		}
		return memo[n];
	}
	
	/**
	 * Time complexity: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. Most efficient of all. We should always think problem from bottom up first.
	 * 
	 * countSteps(n){
	 *   if n < 0
	 *     return 0
	 *   a = 0, b = 1, c = 1, d = 1
	 *   for i = 2 to n
	 *     d = a + b + c
	 *     a = b
	 *     b = c
	 *     c = d
	 *   return d
	 */
	public int countSteps_bottomUp_dp(int n) {
		if (n < 0) {
			return 0;
		}
		int a = 0, b = 1, c = 1, d = 1;
		for (int i = 1; i < n; i++) {
			d = a + b + c;
			a = b; b = c; c = d;
		}
		return d;
	}
}
