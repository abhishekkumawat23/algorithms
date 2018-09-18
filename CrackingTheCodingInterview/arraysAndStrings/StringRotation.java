package arraysAndStrings;

/**
 * Question:
 * isSubstring method exists which checks if one word is substring of another.
 * Now given two string s1 and s2, check if s2 is rotation of s1 using only one call to isSubstring.
 */
public class StringRotation {
	
	/**
	 * Time: O(n) where n is length of string. Here we are assuming isSubString uses rabin karp algo with good hash function.
	 * For bad hash function, O(n^2) will be time complexity
	 * 
	 * Pseudo code:
	 * isRotation(S1, S2):
	 *   return length[S1] == length[S2] and isSubString(S1+S1, S2)
	 */
	public boolean isRotation(String s1, String s2) {
		return s1.length() == s2.length() && isSubString(s1+s1, s2);
	}

	private boolean isSubString(String s1, String s2) {
		return false;
	}
}
