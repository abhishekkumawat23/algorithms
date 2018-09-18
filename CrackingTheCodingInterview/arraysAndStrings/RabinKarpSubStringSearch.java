package arraysAndStrings;

public class RabinKarpSubStringSearch {
	private static final int BASE = 128; // WE can use 31 also.

	/**
	 * Time: O(m) for calculating substring hash + O(n-m)*O(m) for comparing each substring matching hashCode.
	 * So, Time: O(m+n-m) = O(n) considering good hash function.
	 * So, Time: O(m(n-m)) considering bad hash function.
	 * m is substring length, n is string length
	 * 
	 * Assumption: All chars are ASCII and thus having 128 as base in Rabin fingerprint rolling hash function.
	 * 
	 * Important points:
	 * 1. using power of base helps in creating different hash values for `aef` and `fea` even though they have same chars. 
	 * 1. In case non-ascii, we need to change the base to max value of that character set otherwise collisions can happen.
	 * 2. For large charset like utf 8, we can't use number like 1,112,064 as base as it will cause integer overflow. So in that case we can use some smaller number.
	 * 3. When used number smaller than 1,112,064 for utf 8, chances of collisions will occur and thus time complexity will increase.
	 * 4. Java uses 31 as base(it has UTF-16 encoding) for calculating string's hash in string hashCode function.
	 * 
	 * Pseudo code:
	 * base <- 128
	 * find(S, Sub):
	 *   for i = 1 to length[Sub]
	 *     hash <- hash*base + Sub[i]
	 *     currenthash <- currenthash*base + S[i]
	 *   processLoop: for i = 1 to length[S]-length[Sub]
	 *     if currentHash != hash
	 *       currentHash <- (currentHash - S[i]*power(base, length[Sub]-1))*base + S[i+length[Sub]]
	 *       continue
	 *     else
	 *       for j = 1 to length[Sub]
	 *         if Sub[j] != S[i+j]
	 *           continue processLoop
	 *       return i
	 *   return -1
	 */
	public int find(char[] str, char[] subStr) {
		int hash = 0;
		int currentHash = 0;
		for (int i = 0; i <subStr.length; i++) {
			hash = hash*BASE + subStr[i];
			currentHash = currentHash*BASE + str[i];
		}
		
		processLoop: for (int i = 0; i < str.length-subStr.length; i++) {
			if (currentHash != hash) {
				currentHash = (int)(currentHash - str[i]*Math.pow(BASE, subStr.length-1))*BASE + str[i+subStr.length];
				continue;
			}
			else {
				for (int j = 0; j < subStr.length; j++) {
					if (subStr[j] != str[i+j]) {
						continue processLoop;
					}
				}
				return i;
			}
		}
		return -1;
	}
}
