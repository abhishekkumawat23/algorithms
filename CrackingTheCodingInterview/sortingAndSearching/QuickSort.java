package sortingAndSearching;

import java.util.Random;

/**
 * Implement quick sort
 * 
 * Important points:
 * 1. We get mid value and then all values lesser than that value are moved to left and all values greater than it are moved to right.
 * 2. In doing so, index of mid value can move anywhere and is not guaranteed to be in the mid or nearby.
 * 3. Now we do same in subarray from low to mid and mid+1 to high.
 * 4. As mid index can be anywhere, our tree formed because of sub problem break can be skewed i.e. instead of logn height we can get n height.
 * 5. Example: 1 2 3 7 5 10 4 8, mid is 7. Mid value is 7 and index is 3. On moving smaller to left and greater to right we get 1 2 3 4 5 10 7 8 and index of 7 now is 6 which is bad.
 * 6. To solve problem in nlogn, we need that after partitioning, index is somewhere in mid.
 * 7. As this is not guaranteed, instead of picking mid value in starting, we can pick random value.
 * 8. Thus skewedness will get normalized and thus chances of getting skewed tree will be less.
 * 9. So in worst case, quick search is O(n^2) and in average case, quick search its O(nlogn)
 * 10. Quick search is better than merge sort in average cases because
 *    a. It doesn't use additional memory (merge sort needs O(n) memory for helper array). Only memory it uses if os recusrion
 *    b. In merge sort, we need to copy entire array in helper array which is not efficient.
 *    c. Because of these reasons, quick sort comes out to be efficient than merge sort in average cases.
 * 11. Quick sort is not stable as it randomly swaps elements to partition.
 * 12. So prefer merge sort for reference types over quick sort because of stability. For primitive types, prefer quick sort. 
 */
public class QuickSort {

	/**
	 * Time: Average: O(nlogn), Worst: O(n^2); Space: O(logn)
	 * 1 4 7 12 5 8 19 8 20 21 > 
	 * 
	 * Important points:
	 * 1. Main problem in quick sort is how to partition it.
	 * 2. We first get a random pivot.
	 * 3. If we do low++, high-- and keep on swapping, then we don't know what to do when pivot itself comes. We can't pass from pivot as once we pass it, swapping will be corrupted.
	 * 4. Important:
	 *    a. So, once we get pivot, we swap it with righmost element and then start with low and high-1. In this we do left++ and right--.
	 *    b. Once swapping is done, we can replace back pivot with left index which will not effect ordering as left will pointing to first element of right partition.
	 *    c. We can handle duplicate elements as well that where to put them, in left list or right list.
	 * 
	 * sort(0, array.length-1, array)
	 * sort(low, high, array):
	 *   if low > high
	 *     return
	 *   pivot = partition(low, high, array)
	 *   sort(low, pivot, array)
	 *   sort(high, pivot+1, array)
	 * partition(low, high, array):
	 *   pivotIndex = random(low, high+1)
	 *   pivot = array[pivotIndex]
	 *   // Swapping pivot with high
	 *   last = high
	 *   swap(pivotIndex, last)
	 *   // Start swapping from low and high-1
	 *   high--
	 *   while low <= high
	 *     while array[low] <= pivot // equality as we want all duplicates to be on left side.
	 *       low++
	 *     while array[high] > pivot
	 *       high--
	 *     if low <= high
	 *       swap(low, high)
	 *       low++, high--
	 *   // Swap back the pivot with left
	 *   swap(low, last)
	 *   return low // Low will be pointing to the pivot
	 */
	public void sort(int[] array){
		sort(0, array.length-1, array);
	}

	private void sort(int low, int high, int[] array) {
		if (low > high) {
			return;
		}
		int pivotIndex = partition(low, high, array);
		sort(low, pivotIndex, array);
		sort(pivotIndex+1, high, array);
	}

	private int partition(int low, int high, int[] array) {
		int pivotIndex = (new Random().nextInt(high-low+1)) + low;
		int pivot = array[pivotIndex];
		// Swapping pivot with high
		int last = high;
		swap(pivotIndex, last, array);
		// Start swapping from low and high-1
		high--;
		while (low <= high) {
			while (array[low] <= pivot) { // equality as we want all duplicates to be on left side.
				low++;
			}
			while(array[high] > pivot) {
				high--;
			}
			if (low <= high) {
				swap(low, high, array);
				low++; high--;
			}
		}
		// Swap back the pivot with left
		swap(low, last, array);
		return low; // Low will be pointing to the pivot.
	}
	
	private void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
