package sortingAndSearching;

/**
 * Given a sorted array of strings that is interspersed with empty strings, write a method to find the location of a given string.
 * Example:
 * Input: ball, {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}
 * Output: 4 
 */
public class SparseSearch {

	/**
	 * Time: O(n) in case there are lots and lots of empty strings.
	 * 
	 * sparseSearch(array, value):
	 *   low = 0
	 *   high = array.length - 1
	 *   while low < high
	 *     mid = (low + high)/2
	 *     newMid = findNearbyNonEmpty(mid, array)
	 *     if newMid == -1
	 *       return -1
	 *     else if value < array[newMid]
	 *       high = max(mid, newMid) - 1
	 *     else if value > array[newMid]
	 *       low = min(mid, newMid) + 1
	 *     else
	 *       return newMid
	 *   return -1
	 * findNearbyNonEmpty(i, array)
	 *   left = right = i
	 *   while left >= 0 || right < array.length
	 *     if left >= 0 && array[left] != ""
	 *       return left
	 *     else if right < array.length && array[right] != ""
	 *       return right
	 *     left--
	 *     right++
	 *   return -1
	 */
	public int sparseSearch(String[] array, String value) {
		int low = 0;
		int high = array.length - 1;
		while (low < high) {
			int mid = (low + high)/2;
			int newMid = findNearbyNonEmpty(mid, array);
			if (newMid == -1) {
				return -1;
			}
			else if (value.compareTo(array[newMid]) < 0) {
				high = Math.max(mid,  newMid) - 1;
			}
			else if (value.compareTo(array[newMid]) > 0) {
				low = Math.min(mid,  newMid) + 1;
			}
			else {
				return newMid;
			}
		}
		return -1;
	}

	private int findNearbyNonEmpty(int i, String[] array) {
		int left = i; int right = i;
		while (left >= 0 || right < array.length) {
			if (left >= 0 && array[left] != "") {
				return left;
			}
			else if (right < array.length && array[right] != "") {
				return right;	
			}
			left--;
			right++;
		}
		return -1;
	}
}
