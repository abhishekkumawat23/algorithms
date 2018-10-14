package sortingAndSearching;

/**
 * Implement selection sort.
 * 
 * Important points:
 * 1. Logic:
 *    1. Find first minimum element and place at first position.
 *    2. Find second minimum element and place at second position and so on.
 * 2. This is not a stable sort as it swaps non-adjacent elements. Example: (4, 0), (4, 1), (1, 0) . First min element swap will make it (1, 0), (4, 1), (4, 0) and thus no longer stable.
 * 3. Better than bubble sort as number of swaps are less.
 * 4. For nearly sorted array, bubble sort is better than selection sort because in selection we will always search the list for min element. In bubble sort, we will not swap more for nearly sorted and also we will stop as soon as array is sorted.
 *    To already/nearly sorted array is passed to bubble sort, it takes O(n) time but selection sort takes O(n^2) time.
 * 5. For random list, selection sort is better than bubble sort due to less number of swaps.
 * 6. Bubble sort is swap heavy. Selection sort is comparison heavy.
 */
public class SelectionSort {

	/**
	 * Time: O(n^2); Space:O(1)
	 * 
	 * sort(array):
	 *   for i = 0 to array.length-1
	 *     minIndex = i
	 *     for j = i+1 to array.length-1
	 *       if array[j] < array[minIndex]
	 *         minIndex = j
	 *     swap(i, minIndex, array)
	 */
	public void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i+1; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			swap(i, minIndex, array);
		}
	}
	
	private void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
