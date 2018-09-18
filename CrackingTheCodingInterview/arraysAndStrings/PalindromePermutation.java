package arraysAndStrings;

import java.util.HashMap;

/**
 * Question:
 * Check if the given string is permutaion of a palindrome.
 * Palindrome is a word/phrase that is same forwards or backwards.
 */
public class PalindromePermutation {

	/**
	 * Assumptions: Ascii characters
	 * 
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. At max one odd number is allowed if string is a permutaion of palindrome.
	 * 
	 * Pseudo code:
	 * isPermutationOfPalindrome(S):
	 *   charsetLength <- 128
	 *   for i = 1 to length[S]
	 *     charset[S[i]] <- charSet[S[i]] + 1
	 *   containsOdd <- false
	 *   for i = 1 to length[charset]
	 *     if charset[i] % 2 != 0
	 *       if containsOdd = true
	 *         return false
	 *       containsOdd <- true
	 *   return true
	 */
	public boolean isPermutationOfPalindrome_ascii(String str) {
		int[] charset = new int[128];
		for (int i = 0; i < str.length(); i++) {
			charset[str.charAt(i)] += 1;
		}
		
		boolean containsOdd = false;
		for (int i = 0; i < charset.length; i++) {
			if (charset[i] % 2 != 0) {
				if (containsOdd) {
					return false;
				}
				containsOdd = true;
			}
		}
		
		return true;
	}
	
	/**
	 * Assumptions: unicode characters.
	 * 
	 * Time: O(n); Space: O(n)
	 * 
	 * Pseduo code:
	 * isPermutationOfPalindrome(S):
	 *   for i = 1 to length[S]
	 *     hashMap[S[i]] <- hashMap[S[i]] + 1
	 *   for i = 1 to length[hashMap]
	 *     if hashMap[i] % 2 != 0
	 *       if containsOdd = true
	 *         return false
	 *       containsOdd <- true
	 *   return true
	 */
	public boolean isPermutationOfPalindrome_unicode(String str) {
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < str.length(); i++) {
			int charKey = str.charAt(i);
			Integer oldVal = hashMap.get(charKey);
			int oldIntVal = oldVal != null ? oldVal.intValue() : 0;
			
			hashMap.put((int)str.charAt(i), oldIntVal + 1);
		}
		
		boolean containsOdd = false;
		for (Integer value: hashMap.values()) {
			int intValue = value != null ? value.intValue() : 0;
			if (intValue % 2 != 0) {
				if (containsOdd) {
					return false;
				}
				containsOdd = true;
			}
		}
		
		return true;
	}
	
	/**
	 * Assumptions: only upper alphabets
	 * 
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. We just need to know that string contains at most one odd count character. Rest all should have even count.
	 * 2. As there are only alphabets (26) which is less than what int can hold (32 bits). We can store the even odd thing in int.
	 * 3. Every bit of 32 bit represents a character evenness or oddness.
	 * 4. If existing bit is even, then it depends on mask bit whether finally it will be even or odd.
	 * 5. If existing bit is odd, then mask bit even gives odd and mask bit odd given even. 
	 * 6. How to check if integer has at most one bit with value 1? If x & (x-1) == 0, then it means x has exactly one bit.
	 * 7. Example: 16 (10000) has one bit, 15 is 01111. So 10000 & 01111 = 0.
	 * 8. So if the question is:
	 *   a. Find if integer has exactly one bit
	 *   b. Find if integer is power of 2.
	 *   In both scenario answer is x & (x-1) == 0 or not.
	 * 
	 * Pseudo code:
	 * isPermutationOfPalindrome(S):
	 *   checker <- 0
	 *   for i = 1 to length[S]
	 *     value <- S[i] - 'A'
	 *     mask <- 1 << value
	 *     if checker & mask = 0
	 *       checker <- checker | mask
	 *     else
	 *       checker <- checker & ~mask
	 *   return checker = 0 or (checker & (checker-1) = 0)
	 */
	public boolean isPermutationOfPalindrome_alphabets(String str) {
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'A';
			int mask = 1 << val;
			
			checker = (checker & mask) == 0 ?
					checker | mask :
					checker & ~mask;
		}

		// Checking if checker has atmost one bit
		return checker == 0 || (checker & (checker-1)) == 0;
	}
}
