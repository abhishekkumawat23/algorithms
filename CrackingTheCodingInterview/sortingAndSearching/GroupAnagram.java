package sortingAndSearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Write a method to sort an array of strings so that all the anagrams are next to each other.
 */
public class GroupAnagram {

	/**
	 * Time: O(nklogk) where k is number of chars in a string; Space: O(n) 
	 * 
	 * groupAnagram(array):
	 *   // Put anagrams in hashMap with key as sorted string.
	 *   hashMap = new HashMap<string, list<string>>
	 *   for str in array
	 *     key = sortChars(str)
	 *     if hashMap.containsKey(key)
	 *       hashMap.get(key).add(str)
	 *     else
	 *       list = new list
	 *       list.add(str)
	 *       hashMap.put(key, list)
	 *   // Put back anagrams in array
	 *   index = 0
	 *   for valueList in hashMap.values()
	 *     for str in valueList
	 *       array[index] = str
	 *       index++
	 * sortChars(str):
	 *   content = str.toCharArray
	 *   Arrays.sort(content)
	 *   return new String(content)
	 */
	public void groupAnagram(String[] array) {
		// Put anagrams in hashMap with key as sorted string.
		HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
		for (String str: array) {
			String key = sortChars(str);
			if (hashMap.containsKey(key)) {
				hashMap.get(key).add(str);
			}
			else {
				List<String> list = new ArrayList<String>();
				list.add(str);
				hashMap.put(key, list);
			}
		}
		// Put back anagrams in array
		int index = 0;
		for (List<String> valueList: hashMap.values()) {
			for (String str: valueList) {
				array[index] = str;
				index++;
			}
		}
	}

	private String sortChars(String str) {
		char[] content = str.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
}
