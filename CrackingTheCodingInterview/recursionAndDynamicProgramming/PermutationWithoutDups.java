package recursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method to compute all permutations of a string of unique characters.
 */
public class PermutationWithoutDups {

	/**
	 * Time: O(n!) as there are n permutations; Space:O(n!)
	 * 
	 * Important points:
	 * 1. Logic: f(n) = addAtAll(nth element, f(n-1)) where f(n) is list of permutations of string of length n
	 * 2. This logic is dervied from recursion thinking way. There is one more way which is PnC way.
	 * 
	 * permutation(str):
	 *   if str == null
	 *     return null
	 *   permutations = new list of strings
	 *   if str.length == 0
	 *     return list with one entry of ""
	 *   first = str.charAt(0)
	 *   words = permutation(str.substring(1))
	 *   for word in words
	 *     for i = 0 to word.length
	 *       newStr = word.substring(0, i) + first + word.substring(i)
	 *       permutaions.add(newStr)
	 *   return permutations
	 */
	public List<String> permutation_recursion(String str){
		if (str == null) {
			return null;
		}
		List<String> permutations = new ArrayList<String>();
		if (str.length() == 0) {
			permutations.add("");
			return permutations;
		}
		
		char first = str.charAt(0);
		List<String> words = permutation_recursion(str.substring(1));
		for (String word: words) {
			for (int i = 0; i <= word.length(); i++) {
				String newStr = word.substring(0,  i) + first + word.substring(i);
				permutations.add(newStr);
			}
		}
		return permutations;
	}
	
	/**
	 * Time: O(n!); Space: O(n!)
	 * 
	 * Important points:
	 * 1. Logic is derived from PnC (permutation and combination).
	 * 2. Logic: f(c1,c2,c3) = add(c1, f(c2,c3)) + add(c2, f(c1, c3)) + add(c3, f(c1, c2))
	 * 3. This approach is better as it can solved permutaion with dups problem very easily.
	 *    
	 * permutation(str):
	 *   if str == null
	 *     return null
	 *   if str.length == 0
	 *     return new list with entry of ""
	 *   for i = 0 to str.length-1
	 *     words = permutation(str.substring(0, i) + str.substring(i+1))
	 *     for word in words
	 *       permutations.add(str.charAt(i) + word)
	 *   return permutations;
	 *     
	 */
	public List<String> permutation_PnC(String str){
		if (str == null) {
			return null;
		}
		
		List<String> permutations = new ArrayList<String>();
		if (str.length() == 0) {
			permutations.add("");
			return permutations;
		}
		
		for (int i = 0; i < str.length(); i++) {
			List<String> words = permutation_PnC(str.substring(0, i) + str.substring(i+1));
			for (String word: words) {
				permutations.add(str.charAt(i) + word);
			}
		}
		
		return permutations;
	}
}
