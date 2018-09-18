package arraysAndStrings;

/**
 * Question:
 * Given two strings, write a function to check if they are one or zero edits away.
 * Type of edits are: insert a char, remove a char or replace a char.
 */
public class OneAway {

	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Pseudo code:
	 * oneEditAway(S1, S2):
	 *   if mod(length[S1] - length[s2]) > 1
	 *     return false
	 *   S1' = S1 if length[S1] >= length[S2] else S2
	 *   S2' = S2 if length[S1] >= length[S2] else S1
	 *   foundDifference <- false
	 *   for i = 1 to length[S1'] and j = 1 to length[S2']
	 *     if S1'[i] != S2'[j]
	 *       if foundDifference = true
	 *         return false
	 *       foundDifference = true
	 *       if length[S1'] > length[S2']
	 *         j <- j - 1
	 *   return true
	 */
	public boolean oneEditAway(String s1, String s2) {
		int lengthS1 = s1.length();
		int lengthS2 = s2.length();
		if (Math.abs(lengthS1 - lengthS2) > 1) {
			return false;
		}
		
		String str1 = lengthS1 >= lengthS2 ? s1 : s2;
		String str2 = lengthS1 >= lengthS2 ? s2 : s1;
		int lengthStr1 = str1.length();
		int lengthStr2 = str2.length();
		boolean foundDifference = false;
		int i = 0;
		int j = 0;
		while ( i < lengthStr1 && j < lengthStr2) {
			if (str1.charAt(i) != str2.charAt(j)) {
				if (foundDifference) {
					return false;
				}
				foundDifference = true;
				if (lengthStr1 > lengthStr2) {
					j--; // Only decreased when lengths differ i.e. case of insertion/removal (but not of replace)
				}
			}
			i++;
			j++;
		}
		
		return true;
	}
}
