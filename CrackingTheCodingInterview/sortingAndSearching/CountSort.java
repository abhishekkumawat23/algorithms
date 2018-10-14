package sortingAndSearching;

/**
 * Implement count sort
 * 
 * Important points:
 * 1. If the values lies in a smaller range and number of elements are very large, its best to use count sort.
 * 2. Example list of age, list of marks etc have range from 0 to 100, list of ascii characters
 * 3. Its king of using hash of values. Example hash of 1 is 1, hash of a is 26.
 * 4. For reference type:
 *    a. If we simply store the count corresponding the a hash, our sorting will not happen as multiple objects with same hash can be present.
 *    b. So, first we store the count as we do for primitive types. Example: [2, 4, 2, 1, 6] which means first smallest element came 2 times, second smallest element came 4 times etc.
 *    c. Now we update the count sort to contain actual position of elements. Example [2, 2+4, 2+4+2, 2+4+2+1, 2+4+2+1+6] => Example[2, 6, 8, 9, 15]
 *    d. From this array value, we can get index = value-1. We will store element of a hash at that index and then decrement the value.
 *    e. We can't do inplace sorting using this. So we need additional array of space n. So Space complexity will be O(n+k) where k is range
 *    f. For this implementation, check radix sort. 
 * 5. Count sort is a stable sort.
 */
public class CountSort {

	/**
	 * Time: O(2n); Space:O(k) where k is the range
	 * 
	 * sort(array, min, max):
	 *   count = new int[max-min]
	 *   for i = 0 to array.length-1
	 *     count[min-array[min-i]]++
	 *   index = 0
	 *   for i = 0 to count.length-1
	 *     for j = 1 to count[i]
	 *       array[index] = i + min
	 *       index++ 
	 */
	public static void sort(int[] array, int min, int max) {
		// Assuming min, max > 0. We can implement count sort for negative value also.
		int[] count = new int[max-min+1];
		for (int i = 0; i < array.length; i++) {
			count[array[i]-min]++;
		}
		int index = 0;
		for (int i = 0; i < count.length; i++) {
			for (int j = 1; j <= count[i]; j++) {
				array[index] = i + min;
				index++;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = new int[] {3, 2, 1, 1, 2, 3, 3, 3, 2, 2};
		sort(array, 1, 3);
	}
}
