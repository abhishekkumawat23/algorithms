package arraysAndStrings;

/**
 * Question:
 * Determine if a string has all unique characters.
 * What if additional data structures can't be used.
 */
public class IsUnique {

	/**
	 * Assumption: Ascii characters.
	 * 
	 * Time: O(n) for n < charsetLength else O(1); Space: O(1) with const space of charsetLength.
	 * 
	 * Important points:
	 * 1. As ascii charset is limited to only 128 characters i.e. 7 bit; we can create a const array of 128 length to determine if the string is unique or not.
	 * 
	 * Pseudo code:
	 * isStringUnique(S):
	 *   charsetLength <- 128
	 *   if length[S] > charsetLength
	 *     return false
	 *   for i = 1 to length[S]
	 *     if charset[S[i]] = true
	 *       return false
	 *     charset[S[i]] <- true
	 *   return true
	 */
	public boolean isStringUnique_ascii(String str) {
		int charsetLength = 128;
		
		if (str.length() > charsetLength) {
			return false;
		}
		
		boolean[] charset = new boolean[charsetLength];
		
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (charset[val]) {
				return false;
			}
			charset[val] = true;
		}
		
		return true;
	}
	
	/**
	 * Assumption: unicode characters.
	 * 
	 * Time: O(n) for n < charlength else O(1); Space: O(n) for n < charlength else O(1)
	 * 
	 * As we are adding characters which are good hashes as no duplicates possible.
	 * If this was a bad hash function, then O(1+2+..+n)=O(n^2) will be time complexity as `get` will take time.
	 * 
	 * Important points:
	 * 1. In case of unicode characters, charset length is very high so we can't use array of charset length as its space expensive.
	 * 2. There are 1,112,064 unicode characters. Encoding for unicode characters like utf-8 or utf-16 can support way more than current unicode characters.
	 *  
	 * Pseudo code:
	 * isStringUnique(S):
	 *   charsetLength <- 1,112,064
	 *   if length[S] > charsetLength
	 *     return false
	 *   for i = 1 to length[S]
	 *     if contains(hashset, S[i]) == true
	 *       return false
	 *     add(hashset, S[i]])
	 *   return true
	 */
	public boolean isStringUnique_unicode(String str) {
		int charLength = 1112064;
		if (str.length() > charLength) {
			return false;
		}
		
		HashSet hashSet = new HashSet();
		for (int i = 0; i < str.length(); i++) {
			char val = str.charAt(i);
			if (hashSet.contains(val)) {
				return false;
			}
			hashSet.add(val);
		}
		return true;
	}
	
	/**
	 * Assumptions:
	 * Only alphabets are present
	 * 
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. As we need inplace solution and as alphabets are only 26, we can use a int rather than a array.
	 * 2. In Java, int has 32 bits. So we can store the bit number corresponding to alphabet value (as 26 < 32).
	 * 3. 1 << 4 means 00010000. 1 << 1 means 00000010
	 * 
	 * Pseudo code:
	 * isStringUnique(S):
	 *   noOfAlphabets <- 26
	 *   if length[S] > 26
	 *     return false
	 *   bitInt <- 0
	 *   for i = 1 to length[S]
	 *     val <- S[i] - A
	 *     if bitInt & 1 << val != 0
	 *       return false
	 *     bitInt <- bitInt | 1 << val
	 *   return true
	 */
	public boolean isStringUnique_onlyAlphabets_inplace(String str) {
		int noOfAlphabets = 26;
		if (str.length() > noOfAlphabets) {
			return false;
		}
		
		int bitInt = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'A';
			if ((bitInt & (1 << val)) > 0) {
				return false;
			}
			bitInt = bitInt | (1 << val);
		}
		return true;
	}
}
