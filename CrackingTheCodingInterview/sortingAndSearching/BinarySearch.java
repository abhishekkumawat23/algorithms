package sortingAndSearching;

/**
 * Search an element in already sorted array
 */
public class BinarySearch {
	
	/**
	 * Time: O(logn); Space: O(logn)
	 * 
	 * binarySearch(0, array.length-1, array, value)
	 * binarySearch(low, high, array, value):
	 *   if low > high
	 *     return -1
	 *   mid = (low + high) / 2
	 *   if value == array[mid]
	 *     return mid
	 *   else if value > array[mid]
	 *     binarySearch(mid+1, high)
	 *   else
	 *     binarySearch(low, mid-1)
	 *   
	 */
	public int binarySearch_recursive(int low, int high, int[] array, int value) {
		if (low > high) {
			return -1;
		}
		int mid = (low+high)/2;
		if (value == array[mid]) {
			return mid;
		}
		else if (value > array[mid]) {
			return binarySearch_recursive(mid+1, high, array, value);
		}
		else {
			return binarySearch_recursive(low, mid-1, array, value);
		}
	}
	
	/**
	 * Time: O(logn); Space: O(1)
	 * 
	 * binarySearch(array, value):
	 *   low = 0, high = array.length-1
	 *   while low <= high
	 *     mid = (low + high)/2
	 *     if value == array[mid]
	 *       return mid
	 *     else if value > array[mid]
	 *       low = mid+1
	 *     else
	 *       high = mid-1
	 *   return -1
	 *       
	 */
	public int binarySearch_iterative(int[] array, int value) {
		int low = 0; int high = array.length-1;
		while (low <= high) {
			int mid = (low + high/2);
			if (value == array[mid]) {
				return mid;
			}
			else if (value > array[mid]) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
		return -1;
	}
}
