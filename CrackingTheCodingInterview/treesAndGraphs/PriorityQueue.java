package treesAndGraphs;

/**
 * Question:
 * Implement priority queue
 * 
 * Important points:
 * 1. Here we are implementing on basis of heap.
 * 2. We can implement priority queue on basis of array, linked list as well but time complexity is more in that case.
 * 3. Prioirty queue extracts values based on their priority.
 * 4. Here we are assuming that element with is smaller has greater priority. But if not we can use max heap. Default implementation of java also assumes that.
 */
public class PriorityQueue<E extends Comparable<E>> {
	BinaryMinHeap<E> heap = new BinaryMinHeap<E>();

	/**
	 * Time: O(logn); Space: O(1)
	 * 
	 * add(e):
	 *   heap.insert(e)
	 */
	public void add(E e) {
		heap.insert(e);
	}
	
	/**
	 * Time: O(logn); Space: O(1)
	 * 
	 * remove():
	 *   heap.extractMin()
	 */
	public void remove() {
		heap.extractMin();
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * peek():
	 *   heap.getMin()
	 */
	public void peek() {
		heap.getMin();
	}
}
