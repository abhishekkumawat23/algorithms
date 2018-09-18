package arraysAndStrings;

public class StringBuilder {
	private static final int INITIAL_CAPACITY = 16;
	private static final float INCREMENT_FACTOR = 1.5f;
	private static final float LOAD_FACTOR = 0.75f;
	private char[] data = new char[INITIAL_CAPACITY];
	private int size = 0;

	/**
	 * Time: amortized O(m) where m is size of c; Space: O(1)
	 * 
	 * Important point:
	 * 1. StringBuilder internally uses arrayList.
	 * 
	 * Pseudo code:
	 * loadFactor <- 0.75
	 * incrementFactor <- 1.5
	 * append(S, c):
	 *   if size[S] + size[c] >= loadFactor*capacity[S]
	 *     newCapacity <- max(incrementFactor*capacity[S], size[S] + size[c])
	 *     data[S] <- create(data[S], newCapacity)
	 *   for i = 1 to size[c]
	 *     S[size[S]+i] <- c[i]
	 *   size[S] <- size[S] + size[c]
	 */
	public void append(String s) {
		char[] c = s.toCharArray();
		if (size + c.length >= LOAD_FACTOR*data.length) {
			int newCapacity = Math.max(
					(int) Math.ceil(INCREMENT_FACTOR*data.length),
					size + c.length);
			char[] newData = new char[newCapacity];
			System.arraycopy(data, 0, newData, 0, data.length);
			data = newData;
		}
		for (int i = 0; i < c.length; i++) {
			data[size+i] = c[i];
		}
		size = size + c.length;
	}
	
	/**
	 * Time: O(n) to get string from char array; Space: O(n) as new string is created.
	 * 
	 * Pseudo code:
	 * toString(S):
	 *   return string[S]
	 */
	@Override
	public String toString() {
		return new String(data);
	}
}
