package sortingAndSearching;

/**
 * You are given two sorted arrays, A and B where A has a large enough buffer at the end to hold B.
 * Write a method to merge B into A in sorted order.
 */
public class SortedMerge {

	/**
	 * Time: O(a + b); Space: O(1)
	 * 
	 * Important points:
	 * 1. Here we can start merging from last instead of start as in last we have buffer for b.
	 * 
	 * sortedMerge(a, b, lastA, lastB):
	 *   indexA = lastA - 1
	 *   indexB = lastB - 1
	 *   index = lastA + lastB - 1
	 *   while indexB >= 0
	 *     if indexA > 0 and a[indexA] > b[indexB]
	 *       a[index] = a[indexA]
	 *       indexA--
	 *     else
	 *       a[index] = b[indexB]
	 *       indexB--
	 *     index--
	 */
	public void sortedMerge(int[] a, int[] b, int lastA, int lastB) {
		int indexA = lastA - 1;
		int indexB = lastB - 1;
		int index = lastA + lastB - 1;
		while (indexB >= 0) {
			if (indexA > 0 && a[indexA] > b[indexB]) {
				a[index] = a[indexA];
				indexA--;
			}
			else {
				a[index] = b[indexB];
				indexB--;
			}
			index--;
		}
	}
}
