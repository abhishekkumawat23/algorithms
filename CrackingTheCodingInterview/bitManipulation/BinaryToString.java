package bitManipulation;

/**
 * Question:
 * Given a real number between 0 and 1 (e.g. 0.72) that is passed as a double.
 * Print the binary representation.
 * If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR."
 */
public class BinaryToString {

	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. Instead of string print, we should use stringBuilder.
	 * 
	 * binaryToString(n):
	 *   i = 1
	 *   while n != 0 and i <= 32
	 *     i++
	 *     n = 2*n
	 *     if n >= 1
	 *       n = n - 1
	 *       print 1
	 *     else
	 *       print 0
	 *   if i > 32
	 *     print ERROR.
	 */
	public void binaryToString(double n) {
		int i = 1;
		while (n != 0 && i <= 32) {
			i++;
			n = 2*n;
			if (n >= 1 ) {
				n = n - 1;
				System.out.print(1);
			}
			else {
				System.out.print(0);
			}
		}
		
		if (i > 32) {
			System.out.println("ERROR.");
		}
	}
}
