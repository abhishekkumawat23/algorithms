package arraysAndStrings;

import java.util.HashMap;

/**
 * Question:
 * Check if one string is permutation of the other.
 */
public class CheckPermutation {
	
	/**
	 * Assumption: ascii chars
	 * 
	 * Time: O(n); Space: O(1) with space of 128
	 * 
	 * Important points:
	 * 1. When are reducing the count in s2 loop, we are checking if the new value will be negative. If yes, we return false as this means character count doesn't match.
	 * 2. After s2 loop finishes, we dont need to check whether in charset array there is any non zero element or not. This is because if s1, s2 were not a permutation and their string length are same then we will surely catch it from point 1 check.
	 * 
	 * Pseudo code:
	 * permutation(s1, s2):
	 *   if length[s1] != length[s2]
	 *     return false
	 *   for i = 1 to length[s1]
	 *     charset[s1[i]] <- charset[s1[i]] + 1
	 *   for i = 1 to length[s2]
	 *     charset[s2[i]] <- charset[s2[i]] - 1
	 *     if charset[s2[i]] < 0
	 *       return false
	 *   return true
	 *   
	 */
	public boolean permutation_ascii(String s1, String s2) {
		int lengthS1 = s1.length();
		int lengthS2 = s2.length();
		
		if (lengthS1 != lengthS2) {
			return false;
		}
		
		int charsetLength = 128;
		int[] charset = new int[charsetLength];
		
		for (int i = 0; i < lengthS1; i++) {
			charset[s1.charAt(i)] += 1;
		}
		
		for (int i = 0; i < lengthS2; i++) {
			int newVal = charset[s2.charAt(i)] - 1;
			if (newVal < 0) {
				return false;
			}
			charset[s2.charAt(i)] = newVal;
		}
		
		return true;
	}
	
	/**
	 * Assumption: unicode characters.
	 * 
	 * Time: O(n); Space: O(n)
	 * 
	 * Pseudo code:
	 * permuation(s1, s2):
	 *   if length[s1] != length[s2]
	 *     return false
	 *   for i = 1 to length[s1]
	 *     hashmap[s1[i]] <- hashmap[s[i]] + 1
	 *   for i = 1 to length[s2]
	 *     hashmap[s2[i]] <- hashmap[s2[i]] - 1
	 *     if hashmap[s2[i]] < 0
	 *       return false
	 *   return true
	 */
	public boolean permutation_unicode(String s1, String s2) {
		int lengthS1 = s1.length();
		int lengthS2 = s2.length();
		
		if (lengthS1 != lengthS2) {
			return false;
		}
		
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>(); 
		
		for (int i = 0; i < lengthS1; i++) {
			Integer value = hashMap.get(s1.charAt(i));
			int oldVal = value == null ? 0 : value.intValue();
			hashMap.put(s1.charAt(i), oldVal + 1);
		}
		
		for (int i = 0; i < lengthS2; i++) {
			Integer value = hashMap.get(s2.charAt(i));
			int oldVal = value == null ? 0 : value.intValue();
			if (oldVal <= 0) {
				return false;
			}
			hashMap.put(s1.charAt(i), oldVal - 1);
		}

		return true;
	}

}
