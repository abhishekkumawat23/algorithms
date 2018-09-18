package arraysAndStrings;

/**
 * Question:
 * Replace all spaces in string with `%20`.
 * In case of java, use char array instead of string to implement algorithm in-place. (being immutable string, we can't manupulate string in-place)
 * Assume sufficient space in char array to hold additional characters.
 * Also, true length of string is also given as input.
 * 
 */
public class Urlify {

	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. insertion in array takes O(n) time due to right shifting. O(n) happens when insert at start i.e. i = 1. O(1) happens when insert at end i.e. i = n.
	 * 
	 * Pseudo code:
	 * urlify(S, length):
	 *   space <- ' '
	 *   extraSpaceLength <- 2
	 *   for i = 1 to length
	 *     spaceCount <- spacesCount + 1
	 *   for i = length to 1
	 *     if S[i] != space
	 *       S[i+extraSpaceLength*spaceCount] <- S[i]
	 *     else
	 *       S[i] <- '%'
	 *       S[i+1] <- '2'
	 *       S[i+2] <- '0'
	 *       spaceCount <- spaceCount - 1
	 */
	public void urlify(char[] str, int length) {
		char space = ' ';
		int extraSpaceLength = 2;
		int spaceCount = 0;
		for (int i = 0; i < length; i++) {
			if (str[i] == space) {
				spaceCount += 1;
			}
		}
		
		for (int i = length-1; i >= 0 ; i--) {
			if (str[i] != space) {
				str[i+extraSpaceLength*spaceCount] = str[i];
			}
			else {
				str[i] = '%';
				str[i+1] = '2';
				str[i+2] = '0';
			}
		}
	}
}
