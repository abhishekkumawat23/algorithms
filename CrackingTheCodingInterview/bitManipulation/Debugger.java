package bitManipulation;

/**
 * Question:
 * Explain what the following code does: ((n&(n-1))==0)
 */
public class Debugger {

	/**
	 * Answer:
	 * n & (n-1) can be zero only and only if n is power of 2.
	 * So, this code is checking whether n is power of 2 or not.
	 * Example 16 & 15 is 0. Reason is 10000 & 01111 = 0
	 */
	public boolean isPowerOfTwo(int n) {
		return (n & (n-1)) == 0;
	}
}
