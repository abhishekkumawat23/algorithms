package bitManipulation;

/**
 * Question:
 * You have an integer and you can flip exactly one bit from a 0 to a 1.
 * Write code to find the length of the longest sequence of 1s you could create
 * Example
 * Input: 1775 (11011101111)
 * Output: 8
 */
public class FlipBitToWin {

	/**
	 * Time: O(b); Space: O(1)
	 * b is no bits in integer. In java its 32
	 * 
	 * flipBit(n):
	 *   for i = 0 to 31
	 *     mask = 1 << i
	 *     bit = n & mask
	 *     if bit != 0 and foundZero
	 *       leftCount++
	 *     else if bit != 0
	 *       rightCount++
	 *     else if bit == 0 and !foundZero
	 *       foundZero = true
	 *     else
	 *       longestSeqCount = max(longestSeqCount, leftCount+rightCount+1)
	 *       rightCount = leftCount
	 *       leftCount = 0
	 *   return max(longestSeqCount, leftCount+rightCount+1)
	 */
	public int flipBit(int n) {
		boolean foundZero = false;
		int leftCount = 0;
		int rightCount = 0;
		int longestSeqCount = 0;
		for (int i = 0; i <= 31; i++) {
			int mask = 1 << i;
			int bit = n & mask;
			if (bit != 0 && foundZero) {
				leftCount++;
			}
			else if (bit != 0) {
				rightCount++;
			}
			else if (bit == 0 && !foundZero) {
				foundZero = true;
			}
			else {
				longestSeqCount = Math.max(longestSeqCount, leftCount + rightCount + 1);
				rightCount = leftCount;
				leftCount = 0;
			}
		}
		return Math.max(longestSeqCount, leftCount + rightCount + 1);
	}
	
	public static void main(String[] args) {
		FlipBitToWin f = new FlipBitToWin();
		System.out.println(f.flipBit(1775));
	}
}
