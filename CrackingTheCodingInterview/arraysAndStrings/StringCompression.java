package arraysAndStrings;

/**
 * Question:
 * Given a string, return a compressed string using the count of repeated characters.
 * If the compressed string length is greater than original string, then return original string.
 * Example:
 * aabcccccaaa shold return a2b1c5a3
 * abbcde should return abbcde (as a1b2c1d1e1 length is greater than original string)
 */
public class StringCompression {

	/**
	 * Time: O(n), Space: O(n) because stringbuilder interally stores char array.
	 * 
	 * Important points:
	 * 1. In case we return original string becuase compressed string is larger, we waste time and space creating the stringbuilder.
	 * 2. In this case, we can optimize by first calculating the compressed length by using same code but maintaining counter instead of stringbuilder. 
	 * In case, compressed length is small, then we will again use same code but maintian stringbuilder this time.  
	 * 
	 * Pseudo code:
	 * compress(S):
	 *   int countConsecutive = 0;
	 *   for i = 1 to length[S]
	 *     countConsecutive <- countConsecutive + 1
	 *     if i+1 > length[S] or S[i] != S[i+1]
	 *       append(sb, S[i], countConsecutive)
	 *       countConsecutive <- 0
	 *   return string(sb) if length[sb] <= length[S] else S
	 */
	public String compress(String str) {
		int length = str.length();
		
		java.lang.StringBuilder sb = new java.lang.StringBuilder();
		int countConsecutive = 0;
		for (int i = 0; i < length; i++) {
			countConsecutive++;
			if (i+1 >= length || str.charAt(i) != str.charAt(i-1)) {
				sb.append(str.charAt(i));
				sb.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return sb.length() <= length ? sb.toString() : str;
	}
}
