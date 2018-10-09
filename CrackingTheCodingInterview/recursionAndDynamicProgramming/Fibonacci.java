package recursionAndDynamicProgramming;

/**
 * Compute the nth fibonacci number.
 * 
 * Important points:
 * 1. Fibonacci series is: f(n) = f(n-1) + f(n-2) where f(0) = 0 and f(1) = 1
 */
public class Fibonacci {

	/**
	 * Time complexity: O(2^n); Space:O(h) here h = n
	 * 
	 * Important points:
	 * 1. This recursion forms a fibonacci tree
	 * 2. Max height of tree is O(n)
	 * 3. Total number of elements in tree is 2^n assuming every number goes through two numbers.
	 *    In actual its a fibonacci tree and not perfect binary tree. So no of elements is 1.6^n but still its exponential.
	 * 
	 * fibonacci(n):
	 *   if n == 0 || n == 1
	 *     return n
	 *   return fibonacci(n-1) + fibonacci(n-2)
	 */
	public int fibonacci_topDown(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		return fibonacci_topDown(n-1) + fibonacci_topDown(n-2);
	}
	
	/**
	 * Time complexity: O(n); Space:O(h) where h = n
	 * 
	 * Important points:
	 * 1. Here time complexity is O(n) because every n needs to find n-1 only. n-2 will be get from memory.
	 * 2. Here height is n as we need to go till end till 1.
	 * 
	 * fibonacci(n, memo):
	 *   if memo[n] != 0
	 *     return memo[n]
	 *   if n == 0 || n == 1
	 *     memo[n] = n
	 *   else
	 *     memo[n] = fibonacci(n-1) + fibonacci(n-2)
	 *   return memo[n]
	 */
	public int fibonacci_topDown_memoization(int n, int[] memo) {
		if (memo[n] != 0) {
			return memo[n];
		}
		if (n == 0 || n == 1) {
			memo[n] = n;
		}
		else {
			memo[n] = fibonacci_topDown_memoization(n-1, memo) + fibonacci_topDown_memoization(n-2, memo); 	
		}
		return memo[n];
	}
	
	/**
	 * Time complexity: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. Efficient of all as time is O(n) and space is O(1)
	 * 
	 * fibonacci(n):
	 *   if n == 0
	 *     return 0
	 *   a = 0, b = 1, c = 1
	 *   for i = 2 to n
	 *     c = a + b
	 *     a = b
	 *     b = c
	 *   return c
	 */
	public int fibonacci_bottomUp_dp(int n) {
		if (n == 0) {
			return 0;
		}
		int a = 1, b = 1, c = 1;
		for (int i = 2; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}
}
