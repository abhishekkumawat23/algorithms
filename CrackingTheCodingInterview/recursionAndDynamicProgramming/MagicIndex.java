package recursionAndDynamicProgramming;

/**
 * A magic index in an array A[1...n-1] is defined to be an index such that A[i] = i.
 * Given a sorted array of distinct integers, write a method to find a magic index, if one exits, in array A.
 * Follow up:
 * What if the values are not distinct.
 */
public class MagicIndex {

	/**
	 * Time: O(logn); Space: O(logn) 
	 * 
	 * findMagicIndex(low, high, array):
	 *   if low > high
	 *     return -1
	 *   mid = (low+high)/2
	 *   if array[mid] == mid
	 *     return mid
	 *   return array[mid] > mid ? findMagicIndex(low, mid-1) : findMagicIndex(mid+1, high)
	 */
	public int findMagicIndex_distinct(int low, int high, int[] array) {
		if (low > high) {
			return -1;
		}
		int mid = (low+high)/2;
		if (array[mid] == mid) {
			return mid;
		}
		return array[mid] > mid ? findMagicIndex_distinct(low, mid-1, array) : findMagicIndex_distinct(mid+1, high, array);
	}
	
	/**
	 * Time: O(2logn); Space: O(logn)
	 * 
	 * findMagicIndex(low, high, array):
	 *   if low > high
	 *     return -1
	 *   mid = (low+high)/2
	 *   if array[mid] == mid
	 *     return mid
	 *   left = findMagicIndex(low, min(array[mid], mid-1), array)
	 *   if left >= 0
	 *     return left
	 *   right = findMagicIndex(max(array[mid], mid+1), high, array)
	 *   return right
	 */
	public int findMagicIndex_notDistinct(int low, int high, int[] array) {
		if (low > high) {
			return -1;
		}
		
		int mid = (low+high)/2;
		if (array[mid] == mid) {
			return mid;
		}
		int left = findMagicIndex_notDistinct(low, Math.min(array[mid], mid-1), array);
		if (left >= 0) {
			return left;
		}
		int right = findMagicIndex_notDistinct(Math.max(array[mid], mid+1), high, array);
		return right;
	}
}
