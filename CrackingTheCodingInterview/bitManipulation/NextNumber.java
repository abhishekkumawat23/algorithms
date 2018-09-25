package bitManipulation;

/**
 * Question:
 * Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in their binary represenation.
 */
public class NextNumber {

	/**
	 * Time: O(b); Space: O(1)
	 * 
	 * Important points:
	 * 1. Find first zero after a one.
	 * 2. Replace the zero with 1.
	 * 3. All the ones which came till getting first zero after one, should go at the end.
	 * 
	 * nextLargestNumber(n):
	 *   c = n, c1 = 0, i = -1
	 *   // Find first zero after a one.
	 *   while c != 0
	 *     i++
	 *     bit = c & 1
	 *     if bit == 1
	 *       c1++
	 *       foundOne = true
	 *     else if foundOne
	 *       break
	 *     c = c >> 1
	 *   // Unable to find a zero after a one.
	 *   if c == 0
	 *     print -1
	 *     return
	 *   // Add one at i
	 *   n = n | 1 << i
	 *   // Clear all bits from 0 to i-1
	 *   n = n & ~((1 << i) - 1)
	 *   // Add one's from 0 to c1-1
	 *   n = n | (1 << c1) - 1
	 *   print n
	 */
	public void nextLargestNumber(int n) {
		int c = n; int c1 = 0; int i = -1;
		boolean foundOne = false;
		while (c != 0) {
			i++;
			int bit = c & 1;
			if (bit == 1) {
				c1++;
				foundOne = true;
			}
			else if (foundOne) {
				break;
			}
			c = c >> 1;
		}
		// Unable to find a zero after a one.
		if (c == 0) {
			System.out.println(-1);
			return;
		}
		// Add one at i
		n = n | 1 << i;
		// Clear all bits from 0 to i-1
		n = n & ~((1 << i)-1);
		// Add one's from 0 to c1-1
		n = n | (1 << c1) - 1;
		System.out.println(n);
	}
	
	/**
	 * Time: O(b); Space: O(1)
	 * 
	 * Important points:
	 * 1. Find first one after a zero.
	 * 2. Replace the one with zero.
	 * 3. All the zeros which came till getting first one after zero, should go at the end
	 * 
	 * nextSmallestNumber(n):
	 *   c = n, c0 = 0, i = -1
	 *   // Find first one after a zero.
	 *   while c != 0
	 *     i++
	 *     bit = c & 1
	 *     if bit == 0
	 *       c0++
	 *       foundZero = true
	 *     else if foundZero
	 *       break
	 *     c = c >> 1
	 *   // Unable to find a zero after a one.
	 *   if c == 0
	 *     print -1
	 *     return
	 *   // Add zero at i
	 *   n = n & ~(1 << i)
	 *   // Add all ones from 0 to i-1
	 *   n = n | (1 << i) - 1
	 *   // Add zero's from 0 to c0-1
	 *   n = n | (1 << c0) - 1
	 *   print n
	 */
	public void nextSmallestNumber(int n) {
		int c = n; int c0 = 0; int i = -1;
		boolean foundZero = false;
		while (c != 0) {
			i++;
			int bit = c & 1;
			if (bit == 0) {
				c0++;
				foundZero = true;
			}
			else if (foundZero) {
				break;
			}
			c = c >> 1;
		}
		// Unable to find a one after a zero.
		if (c == 0) {
			System.out.println(-1);
			return;
		}
		// Add zero at i
		n = n & ~(1 << i);
		// Add all ones from 0 to i-1
		n = n | (1 << i) - 1;
		// Add zero's from 0 to c0-1
		n = n | (1 << c0) - 1;
		System.out.println(n);
	}
}
