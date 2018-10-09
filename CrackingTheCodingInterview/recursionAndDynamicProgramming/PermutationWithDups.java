package recursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Write a method to compute all permutations of a string whose characters are not necessarily unique.
 * The list of permutation should not have duplicates
 */
public class PermutationWithDups {

	/**
	 * Time: O(n!); Space: O(n!)
	 * 
	 * Important points:
	 * 1. Logic is derived from PnC way. It is hard to think/implement this using recursive way.
	 * 2. f(c1,c1,c1,c2,c2,c3) = add(c1, f(c1,c1,c2,c2,c3)) + add(c2, f(c1,c1,c1,c2,c3)) + add(c3, f(c1,c1,c1,c2,c2))
	 * 3. We will maintain a hashmap to know the count of duplicate elements. 
	 * 4. Worst case is when all elements are distinct i.e. O(n!)
	 * 5. Best case is when all elements are duplciate i.e. O(n)
	 * 
	 * permutation(str):
	 *   map = getFrequenceyMap(str)
	 *   return permutation(map.size, map)
	 * getFrequencyMap(str)
	 *   map = new map
	 *   for ch in str
	 *     value = map.contains(ch) ? map.get(ch)+1 : 1
	 *     map.put(ch, value)
	 * permutation(count, map):
	 *   if count == 0
	 *     return list with one entry ""
	 *   for key in map.keys
	 *     originalVal = map.get(key)
	 *     if originalVal >= 0
	 *       map.put(key, originalVal-1)
	 *       words = permutation(count--, map)
	 *       map.put(key, originalVal)
	 *       for word in words
	 *         permutations.add(key+word)
	 *   return permutations
	 *     
	 */
	public List<String> permutation(String str){
		HashMap<Character, Integer> map = getFrequencyMap(str);
		return permutation(map.size(), map);
	}

	private List<String> permutation(int count, HashMap<Character, Integer> map) {
		List<String> permutations = new ArrayList<String>();
		if (count == 0) {
			permutations.add("");
			return permutations;
		}
		
		for (char key : map.keySet()) {
			int originalValue = map.get(key);
			if (originalValue >= 0) {
				map.put(key, originalValue-1);
				List<String> words = permutation(count--, map);
				map.put(key, originalValue);
				for (String word: words) {
					permutations.add(key+word);
				}
			}
		}
		return permutations;
	}

	private HashMap<Character, Integer> getFrequencyMap(String str) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int value = map.containsKey(ch) ? map.get(ch)+1 : 1;
			map.put(ch, value);
		}
		return map;
	}
}
