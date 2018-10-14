package treesAndGraphs;

import java.util.ArrayList;

/**
 * Question:
 * Implement complete binary tree
 * 
 * Important points:
 * 1. Every complete binary can be represented using array or Node class.
 * 2. Every complete binary tree can be converted in array by rule that if parent index is i, then left and child index are 2i+1 and 2i+2.
 * 4. If Node class is used as implementation instead of array, then insertion in binary tree will take O(logn) time for traversal.
 * 5. For Node class implementation, to have improved insertion time, we can maintain a queue of nodes which are not yet full. This queue can have max size of 2^h
 */
public class CompleteBinaryTree<E> {

	private ArrayList<E> data = new ArrayList<E>();
	
	/**
	 * Time: amortized O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * insert(e):
	 *   data.addLast(e)
	 */
	public void insert(E e) {
		data.add(e);
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. Instead of deleting, we are deleting the last node. Last node value is replaced at the e element's index.
	 * 2. In this way, the binary tree will no change.
	 * 
	 * Pseudo code:
	 * delete(e)
	 *   last = data.getLast
	 *   index = data.get(e)
	 *   data.set(index) = last
	 *   data.removeLast
	 */
	public void delete(E e) {
		E last = data.get(data.size()-1); // O(1)
		int index = data.indexOf(e); // O(n)
		data.set(index, last); // O(1)
		data.remove(data.size()-1); // O(1)
	}
	
	/**
	 * Pseudo code:
	 * getHeight():
	 *   i = 0
	 *   height = -1
	 *   while i < data.size
	 *     height++
	 *     i = 2i + 1
	 *   return height
	 */
	public int getHeight() {
		int i = 0;
		int height = -1;
		while (i < data.size()) {
			height++;
			i = 2*i + 1;
		}
		return height;
	}
}
