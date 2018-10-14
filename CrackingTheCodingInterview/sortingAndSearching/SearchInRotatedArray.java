package sortingAndSearching;

/**
 * Given a sorted array of n integers that has been rotated an unknown number of times,
 * write a code to find an element in the array.
 * You may assume that the array was originally sorted in increasing order.
 * Example:
 * Input: Find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
 * Output: 8 (the index of 5 in array)
 * 
 * Important points:
 * 1. No duplicates:
 *    a. If there are no duplicates, we can find the element in rotated index in O(logn).
 *    b. We know that by whatever amount rotation happened, one half will be in normal order(i.e. increasing if original array was increasing) and second half will be in opposite order (drecreasing order if original array was increasing)
 *    c. Thus we can move to one half based on comparisons and thus we can search our element in O(logn)
 * 2. Duplicates:
 *    a. In case of duplicates, we might take O(n) time to find the element.
 *    b. In this case, both halfs comparisons of first and last elements can say elements are equal and thus we will not know in which side to go. Thus we ave to search in both directions.
 *    c. Example 1 1 1 2 1. Here mid will be 1 and both left and right part are equal in comaprison. Thus we need to search both parts. 
 */
public class SearchInRotatedArray {

	/**
	 * Time: O(logn) if unique elements and O(n) if duplicate elements; Space: O(logn)
	 * 
	 * search(0, array.length-1, array, value)
	 * search(low, high, array, value):
	 *   if low > high
	 *     return -1
	 *   mid = (low + high)/2
	 *   if array[mid] == value
	 *     return mid
	 *   if array[low] == array[mid] and array[high] != array[mid]
	 *     // Left elements will all be same.
	 *     return search(mid+1, high, array, value)
	 *   if array[high] == array[mid] and array[low] != array[mid]
	 *     // Right elements will all be same.
	 *     return search(low, mid-1, array, value)
	 *   if array[low] == mid and array[right] == mid
	 *     // Search both halves.
	 *     index = search(low, mid-1, array, value)
	 *     return index != -1 ? index : search(mid+1, high, array, value)
	 *   if array[low] < array[mid]
	 *     // This means right part is in bad order or string is not rotated
	 *     return value < array[mid] and value >= array[low] ? search(low, mid-1, array, value) : search(mid+1, high, value)  
	 *   if array[high] > array[mid]
	 *     // This means left part is in bad order or string is not rotated
	 *     return value > array[mid] and value <= array[high] ? search(mid+1, high, array, value) : search(low, mid-1, value)
	 *   return -1
	 */
	public int search(int low, int high, int[] array, int value) {
		if (low < high) {
			return -1;
		}
		
		int mid = (low + high)/2;
		if (value == array[mid]) {
			return mid;
		}
		if (array[low] == array[mid] && array[high] != array[mid]) {
			return search(mid+1, high, array, value);
		}
		if (array[high] == array[mid] && array[low] != array[mid]) {
			return search(low, mid-1, array, value);
		}
		if (array[low] == array[mid] && array[high] == array[mid]) {
			int index = search(low, mid-1, array, value);
			return index != -1 ? index : search(mid+1, high, array, value);
		}
		if (array[low] < array[mid]) {
			return value < array[mid] && value >= array[low] ?
					search(low, mid-1, array, value) :
					search(mid+1, high, array, value);
		}
		if (array[high] > array[mid]) {
			return value > array[mid] && value <= array[high] ?
					search(mid+1, high, array, value) :
					search(low, mid-1, array, value);
		}
		return -1;
	}
}
