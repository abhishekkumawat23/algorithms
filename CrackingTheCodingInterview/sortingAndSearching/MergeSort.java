package sortingAndSearching;

/**
 * Implement merge sort
 * 
 * Important points:
 * 1. Avg and worst case of merge sort is O(nlogn)
 * 2. As 2-way merge is ordered merging and not random merging/shift/swap, this sort is stable sort.
 */
public class MergeSort {

	/**
	 * Time: O(nlogn); Space: O(n) for helper + O(logn) for recursion
	 * 
	 * Important points:
	 * 1. T(n) = 2T(n/2) + O(n). So, T(n) = O(nlogn).
	 * 
	 * sort(0, array.length-1, array, helper)
	 * sort(low, high, array, helper):
	 *   if low > high
	 *     return
	 *   mid = (low + high)/2
	 *   sort(low, mid, array, helper)
	 *   sort(mid+1, high, array, helper)
	 *   merge(low, mid, high, array, helper)
	 * merge(low, mid, high, array, helper)
	 *   // Copy to helper
	 *   for i = low to high
	 *     helper[i] = array[i]
	 *   // 2-way merge
	 *   current = low, helperLeft = low, helperRight = mid+1
	 *   while helperLeft <= mid and helperRight <= high
	 *    if helper[helperLeft] < helper[helperRight]
	 *      array[current] = helper[helperLeft]
	 *      helperLeft++
	 *    else
	 *      array[current] = helper[helperRight]
	 *      helperRight++
	 *    current++
	 *  // Remaining left elements. No need to copy right as they are already in array.
	 *  while helperLeft != mid
	 *    array[current] = helper[helperLeft]
	 *    current++
	 *    
	 */
	public void sort(int[] array) {
		int[] helper = new int[array.length];
		sort(0, array.length-1, array, helper);
	}

	private void sort(int low, int high, int[] array, int[] helper) {
		if (low > high) {
			return;
		}
		int mid = (low+high)/2;
		sort(low, mid, array, helper);
		sort(mid+1, high, array, helper);
		merge(low, mid, high, array, helper);
	}

	private void merge(int low, int mid, int high, int[] array, int[] helper) {
		// Copy to helper
		for(int i = low; i <= high; i++) {
			helper[i] = array[i];
		}
		// 2-way merge
		int current = low; int helperLeft = low; int helperRight = mid+1;
		while (helperLeft <= mid && helperRight <= high) {
			if (helper[helperLeft] < helper[helperRight]) {
				array[current] = helper[helperLeft];
			}
			else {
				array[current] = helper[helperRight];
			}
			current++;
		}
		// Remaining left elements. No need to copy right as they are already in array.
		while (helperLeft != mid) {
			array[current] = helper[helperLeft];
			current++;
		}
	}
}
