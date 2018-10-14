package sortingAndSearching;

/**
 * In an array of integers, a "peak" is an element which is greater than or equal to the adjacent integers
 * and a "valley" is an element which is less than or equal to the adjacent integers.
 * For example, in the array {5,8,6,2,3,4,6}, {8,6} are peaks and {5,2} are valleys.
 * Given an array of integers, sort the array into an alternating sequence of peaks and valleys.
 * Example:
 * Input: {5,3,1,2,3}
 * Output: {5,1,3,2,3}
 */
public class PeaksAndValleys {
 
	/**
	 * Time: O(nlogn)
	 * 
	 * Important points:
	 * 1. Sort the array.
	 * 2. Swap i and i+1 and move ahead by 2 elements.
	 * 3. Example 1 2 3 4 5 6 => 2 1 4 3 6 5
	 * 4. There is a better approach which doesn't require sorting.
	 * 
	 * peaksAndValleys(array):
	 *   for i = 0 to array.length-2
	 *     swap(i, i+1, array)
	 * swap(i, j, array):
	 *   temp = array[i]
	 *   array[i] = array[j]
	 *   array[j] = temp
	 */
	public void peaksAndValleys_sorting(int[] array) {
		for (int i = 0; i < array.length-1; i += 2) {
			swap(i, i+1, array);
		}
	}

	private void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/**
	 * Time: O(n)
	 * 
	 * Important points:
	 * 1. With three adjacent elements, we are moving largest in center.
	 * 
	 * peaksAndValleys(array):
	 *   for i = 0 to array.length-3, increment by 2
	 *     // Find max
	 *     largest = i+1
	 *     if array[i] > array[largest]
	 *       largest = i
	 *     if array[i+2] > array[largest]
	 *       largest = i+2
	 *     // Swap max to middle
	 *     swap(i+1, largest)
	 */
	public void peaksAndValleys_unsorted(int[] array) {
		for (int i = 0; i < array.length-2; i += 2) {
			// Find max
			int largest = i + 1;
			if (array[i] > array[largest]) {
				largest = i;
			}
			if (array[i+2] > array[largest]) {
				largest = i + 2;
			}
			// Swap max to middle
			swap(i+1, largest, array);
		}
	}
}
