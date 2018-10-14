package sortingAndSearching;

/**
 * Implement insertion sort
 * 
 * Important points:
 * 1. Logic:
 *    a. Insert element in the already sorted left part by finding its suitale position.
 *    b. We need to right shift elements to insert element.
 * 2. This is stable sort as we are moving/swapping adjacent elements only.
 * 3. Insertion sort is better than bubble sort as instead of swaps, we are shifting.
 * 4. In selection sort, we can stop while insertion as soon as we found the insertion index. But in bubble sort, largest element is always bubbled to end.
 * 5. For nearly sorted array, both bubble and insertion takes O(n).
 * 6. Between insertion sort and selection sort, it depends:
 *    a. Insertion sort stops as soon as it finds insertion index. Selection sort doesn't. It always find min element and places it at last.
 *    b. Insertion sort stops as soon as array is sorted. Thus better for nearly sorted arrays. Selection sort takes O(n^2) time even for sorted array.
 *    c. Selection sort does only comparisons and thus number of shift/swap is less. Insertion sort does lots of shifting. Assignment is costly than comparison.
 *    d. Insertion sort is very good when insertion can be done in O(1). Example linked list.
 */
public class InsertionSort {

	/**
	 * Time: O(n^2); Space: O(1)
	 * 
	 * sort(array):
	 *   for i = 0 to array.length-1
	 *     key = array[i]
	 *     j = i-1
	 *     while j >= 0 and array[j] > key
	 *       array[j+1] = array[j]
	 *       j++
	 *     array[j+1] = key
	 *         
	 */
	public void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int key = array[i];
			int j = i-1;
			while (j >= 0 && array[j+1] > key) {
				array[j+1] = array[j];
				j++;
			}
			array[j+1] = key;
		}
	}
}
