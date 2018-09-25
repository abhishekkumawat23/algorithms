package bitManipulation;

/**
 * Question:
 * You are given two 32 bit numbers, N and M, and two bit positions i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit i.
 * You can assume that the bits j through i have enough space to fit all of M.
 * That is, if M=1011, you can assume that there are at least 5 bits between j and i.
 * You would not, for example have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 * Example
 * Input: N = 10000000000, M = 10011, i = 2, j = 6
 * Output: N = 10001001100
 */
public class Insertion {

	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. We are assuming signed 32 bit integer.
	 * 
	 * insertion(n, m, i, j):
	 *   // Clear bits between i and j
	 *   left = (1 << i) - 1
	 *   right = -1 << (j+1)
	 *   mask = left | right
	 *   n = n & mask
	 *   // Move m to i
	 *   m = m << i
	 *   // Merge n and m
	 *   return n | m
	 */
	public int insertion(int n, int m, int i, int j){
		// Clear bits between i and j
		int left = (1 << i) - 1;
		int right = -1 << (j+1);
		int mask = left | right;
		n = n & mask;
		// Move m to i
		m = m << i;
		// Merge n and m
		return n | m;
	}
	
	
}
