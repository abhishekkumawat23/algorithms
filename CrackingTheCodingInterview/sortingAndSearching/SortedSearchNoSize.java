package sortingAndSearching;

/**
 * You are given an array like data structure `Listy` which lacks a size method.
 * It does, however, have an elementAt(i) method that returns the element at index i in O(1) time.
 * If i is beyond the bounds of data structure it returns -1. (For this reason, the data structure only supports +ve integers)
 * Given a `Listy` which contains sorted positive integers, find the index at which an element x occurs.
 * If x occurs multiple times, you may return any index. 
 */
public class SortedSearchNoSize {
	
	/**
	 * Time: O(logn); Space: O(logn)
	 * 
	 * Important points:
	 * 1. Here we are finding the length and checking the elements in one go.
	 * 2. By the time we find upper bound of size, we also know that element is present in index/2 to index only.
	 * 3. If we haven't yet found the size but element at current lsat index is greater than value, we stop finding size as we will find the element in existing list itself.
	 * 
	 * search(listy, x):
	 *   index = 1
	 *   while listy.elementAt(index) != -1 and listy.elementAt(index) < x
	 *     index = 2*index
	 *   // Element is surely to be present in index/2 to index range as we have already checked before that.
	 *   left = index/2
	 *   right = index
	 *   while left <= right
	 *     mid = (left + right)/2
	 *     midElement = listy.elementAt(mid)
	 *     if midElement == x
	 *       return mid
	 *     else if midElement == -1 || x < array[mid]
	 *       right = mid - 1
	 *     else
	 *       left = mid + 1
	 *   return -1
	 */
	public int search(Listy listy, int x) {
		int index = 1;
		while (listy.elementAt(index) != -1 && listy.elementAt(index) < x) {
			index = 2*index;
		}
		// Element is surely to be present in index/2 to index range as we have already checked before that.
		int left = index/2;
		int right = index;
		while (left <= right) {
			int mid = (left+right)/2;
			int midElement = listy.elementAt(mid);
			if (midElement == x) {
				return mid;
			}
			else if (midElement == -1 || x < midElement) {
				right = mid - 1; 
			}
			else {
				left = mid + 1; 
			}
		}
		return -1;
	}

	private class Listy {
		private int[] array;
		
		public int elementAt(int i) {
			return (i < 0 || i >= array.length) ? -1 : array[i];
		}
	}
}
