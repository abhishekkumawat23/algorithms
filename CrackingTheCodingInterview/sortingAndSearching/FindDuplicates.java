package sortingAndSearching;

/**
 * You have an array with all the numbers from 1 to N, where N is at most 32,000.
 * The array may have duplicate entries and you do not know what N is.
 * With only 4 kilobytes of memory available, how would you print all duplicate elements in that array? 
 */
public class FindDuplicates {

	/**
	 * findDuplicates(array):
	 *   bits = new byte[4000]
	 *   for value in array:
	 *     bit = bits[value/8]
	 *     if bit & (1 << value%8) != 0
	 *       print value
	 *     else
	 *       bit |= 1 << value%8
	 */
	public void findDuplicates(int[] array){
		byte[] bits = new byte[4000];
		for (int value: array) {
			byte bit = bits[value/8];
			if ((bit & (1 << value%8)) != 0) {
				System.out.println(value);
			}
			else {
				bit |= 1 << value%8;
			}
		}
	}
}
