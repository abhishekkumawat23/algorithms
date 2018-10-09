package recursionAndDynamicProgramming;

/**
 * Write a recursive function to multiply two numbers without using the * operator (or / operator).
 * You can use addition, subtraction, and bit shifting, but you should minimize the number of those operations.
 */
public class RecursiveMultiply {

	/**
	 * Time: O(log(min(a, b)); Space: O(1)
	 * 
	 * Important points:
	 * 1. We are using benefit of the fact that we can multiply a number by 2^n by simply left shifting the number by n.
	 * 2. We are converting small number in binary and then for every 1 bit, we are adding in multiply the left shift of big number.
	 * 3. Total operations are log(small) bitShift and log(small) additions. 
	 * 
	 * multiply(a, b):
	 *   big = a < b ? b : a;
	 *   small = a < b ? a : b;
	 *   multiply = 0
	 *   i  = 0
	 *   while small != 0
	 *     if small & 1 != 0
	 *       multiply += big << i
	 *     small >>= 1
	 *     i++;
	 *   return multiply
	 */
	public int multiply(int a, int b) {
		int big = a < b ?  b : a;
		int small = a < b ? a : b;
		int multiply = 0;
		int i = 0;
		while (small != 0) {
			if ((small & 1) != 0) {
				multiply += big << i;
			}
			small >>= 1;
			i++;
		}
		return multiply;
	}
	
	/**
	 * Time: O(logn); Space: O(logn) 
	 * 
	 * Important points:
	 * 1. We can use the fact that to multiply a with b, we need to add `a` b times.
	 * 2. Adding `a` b times will take b operations.
	 * 3. Instead we can just add `a` b/2 times and the final number with itself.
	 *    It will take b/2 operations + 1 add oepration + 1 bit oepration to get b/2 from b.
	 * 4. Similarly we can continue b/2 to b/4 and thus finally reach half-half approach. This will take O(logn) operations.
	 * 5. f(n) = f(n/2) + 2 where f(n) is number of operation required in multiply any number with smaller n. So its 2(logn) operations.
	 * 
	 * multiply_wrapper(a, b):
	 *   big = a < b ? b : a
	 *   small = a < b ? a : b
	 *   return multiply(big, small)
	 * multiply(a, b):
	 *   if b == 0
	 *     return 0
	 *   subMultiply = multiply(a, b >> 1)
	 *   multiply = subMultiply + subMultiply
	 *   if b & 1 != 0
	 *     multiply += a // If b is odd
	 *   return multiply
	 */
	public int multiply_recursive(int a, int b) {
		// Assuming a > b
		if (b == 0) {
			return 0;
		}
		int subMultiply = multiply_recursive(a, b >> 1);
		int multiply = subMultiply + subMultiply;
		if ((b & 1) != 0) {
			multiply += a; // If b is odd
		}
		return multiply;
	}
	
	public static void main(String[] args) {
		RecursiveMultiply rm = new RecursiveMultiply();
		System.out.println(rm.multiply_recursive(32, 17));
	}
}
