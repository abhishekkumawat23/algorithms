package bitManipulation;

/**
 * Question:
 * Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
 * Example
 * Input: 29(11101), 15(01111)
 * Output: 2
 */
public class Conversion {

	/**
	 * conversion(a, b):
	 *   i = a ^ b
	 *   while i != 0
	 *     count += (i & 1)
	 *     i >>= 1
	 *   return count
	 */
	public int conversion(int a, int b) {
		return 0;
	}
}
