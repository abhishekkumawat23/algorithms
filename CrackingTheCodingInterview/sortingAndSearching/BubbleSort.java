package sortingAndSearching;

/**
 * Implement bubble sort
 * 
 * Important points:
 * 1. Logic:
 *    a. In bubble sort, we bubble up largest element to last in first pass.
 *    a. In second pass, we bubble up second largest element to second last and so on.
 *    c. If no swap happens in a pass, we break the loop as array already sorted.
 * 2. Useful only when array is nearly sorted and also elements out of place are nearby to its positions.
 * 3. Lot of swaps in bubble sort, so not that good.
 * 4. Its stable sort becuase it swaps only adjacent elements and thus insertion order of same elements is maintained.
 */
public class BubbleSort {

	/**
	 * Time: O(n^2); Space: O(1)
	 * 
	 * sort(array):
	 *   for i = array.length-1 to 0
	 *     swapHappened = false
	 *     for j = 0 to i-1
	 *       if array[j] > array[j+1]
	 *         swap(j, j+1, array)
	 *         swapHappened = true
	 *     if !swapHappened
	 *       break
	 */
	public void sort(int[] array) {
		for (int i = array.length-1; i >= 0; i--) {
			boolean swapHappened = false;
			for (int j = 0; j <= i-1; j++) {
				if (array[j] > array[j+1]) {
					swap(j, j+1, array);
					swapHappened = true;
				}
			}
			if (!swapHappened) {
				break;
			}
		}
	}
	
	private void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
