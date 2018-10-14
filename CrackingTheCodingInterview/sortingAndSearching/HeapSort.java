package sortingAndSearching;

/**
 * Implement heap sort
 * 
 * Important points:
 * 1. Heap sort and quick sort is competitive.
 * 2. Quick sort is still better than heap because even if array is sorted, heap do 100% swapping but quick sort doesn't do any swapping.
 * 3. Heap sort is not a stable sort.
 */
public class HeapSort {

	/**
	 * Time: O(2nlongn) where nlogn for insertion and nlogn for deletion; Space: O(1)
	 * 
	 * heapSort(array):
	 *   heapify(array)
	 *   for last = array.length-1 to 0
	 *     max = extractMaxFromHeap(array, last)
	 *     array[last] = max
	 * extractMaxFromHeap(array, last):
	 *   max = array[0]
	 *   array[0] = array[last]
	 *   heapifyBubbleDown(array, last)
	 *   return max
	 * heapifyBubbleDown(array, last):
	 *   i = 0
	 *   while i < last
	 *     left = 2*i+1, right = 2*i+2
	 *     large = array[left] > array[right] ? left : right
	 *     if array[i] < array[large]
	 *       temp = array[i]
	 *       array[i] = array[large]
	 *       array[large] = temp
	 *       i = large
	 *     else
	 *       break
	 * heapify(array):
	 *   for i = 0 to array.length-1
	 *     heapifyBubbleUp(array, i)
	 * heapifyBubbleUp(array, last):
	 *   i = last
	 *   while i > 0
	 *     parent = (i % 2 == 0) ? (i-2)/2 ? (i-1)/2
	 *     if array[i] > array[parent]
	 *       temp = array[parent]
	 *       array[parent] = array[i]
	 *       array[i] = temp
	 *       i = parent
	 *     else
	 *       break
	 */
	public void heapSort(int[] array) {
		heapify(array);
		for (int last = array.length-1; last >=0; last++) {
			int max = extractMinFromHeap(array, last);
			array[last] = max;
		}
	}

	private int extractMinFromHeap(int[] array, int last) {
		int max = array[0];
		array[0] = array[last];
		heapifyBubbleDown(array, last);
		return max;
	}

	private void heapifyBubbleDown(int[] array, int last) {
		int i = 0;
		while (i < last) {
			int left = 2*i + 1;
			int right = 2*i + 2;
			int large = array[left] > array[right] ? left : right; 
			if (array[i] < array[large]) {
				int temp = array[i];
				array[i] = array[large];
				array[large] = temp;
				i = large;
			}
			else {
				break;
			}
		}
	}

	private void heapify(int[] array) {
		for (int i = 0; i < array.length; i++) {
			heapifyBubbleUp(array, i);
		}
	}

	private void heapifyBubbleUp(int[] array, int last) {
		int i = last;
		while (i > 0) {
			int parent = (i % 2 == 0) ? (i-2)/2 : (i-1)/2;
			if (array[i] > array[parent]) {
				int temp = array[parent];
				array[parent] = array[i];
				array[i] = temp;
				i = parent;
			}
			else {
				break;
			}
		}
	}
	
	
}
