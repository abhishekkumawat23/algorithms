package treesAndGraphs;

import java.util.ArrayList;

/**
 * Question:
 * Implement min heap. Implement operations: insert, delete, getMin, extractMin, decreaseKey
 * 
 * Important points:
 * 1. Binary min heap has properties that:
 *    a. Node is less than both left and right child.
 *    b. Heap should be complete binary tree.
 * 2. By above properties, min heap is not a binary search tree.
 * 3. We are using array implementation here heap is a complete binary tree.
 * 4. All operations of heap can be done either in O(1) or O(log(n)) time.
 */
public class BinaryMinHeap<E extends Comparable<E>> {
	private ArrayList<E> data = new ArrayList<E>();

	/**
	 * Time: O(log(n)); Space: O(1)
	 * 
	 * Important points:
	 * 1. We are adding the new element at last and then bubbling it up if min heap property violates.
	 * 
	 * Pseudo code:
	 * insert(e)
	 *   data.addLast(e)
	 *   i = data.size
	 *   while i > 1
	 *     prev = i
	 *     i = i >> 1
	 *     if data.get(i-1) > data.get(prev-1)
	 *       temp = data.get(i-1)
	 *       data.set(i-1, data.get(prev-1))
	 *       data.set(prev-1, temp)
	 */
	public void insert(E e) {
		data.add(e);
		int i = data.size();
		int prev = i;
		while (i >= 1) {
			prev = i;
			i = i >> 1;
			if (data.get(i-1).compareTo(data.get(prev-1)) > 0) {
				E temp = data.get(i-1);
				data.set(i-1,  data.get(prev-1));
				data.set(prev-1, temp);
			}
		}
	}
	
	/**
	 * Time: O(log(n)) to bubble down; Space: O(1)
	 * 
	 * Pseudo code:
	 * delete(index):
	 *   newVal = data.getLast
	 *   data.set(index, newVal)
	 *   data.removeLast
	 *   // Move element down if required to satisfy heap property. We dont nede to check to move up as we updated the last element which is always bigger than all.
	 *   while index < data.size
	 *     val = data.get(index)
	 *     leftVal = data.get(2*index+1)
	 *     rightVal = data.get(2*index+2)
	 *     if leftVal > val and rightVal > val
	 *       break
	 *     else if (leftVal > val and rightVal < val) or
	 *             (rightVal < val and leftVal < val and rightVal < leftVal)
	 *       data.set(index, rightVal)
	 *       data.set(2*index+2, val)
	 *       index = 2*index+2
	 *     else
	 *       data.set(index, leftVal)
	 *       data.set(2*index+1, val)
	 *       index = 2*index+1
	 */
	public void delete(int index) {
		E newVal = data.get(data.size()-1);
		data.set(index,  newVal);
		data.remove(data.size()-1);
		// Move element down if required
		while (index < data.size()) {
			E val = data.get(index);
			E leftVal = data.get(2*index+1);
			E rightVal = data.get(2*index+2);
			if (leftVal.compareTo(val) > 0 && rightVal.compareTo(val) > 0) {
				break;
			}
			else if ((leftVal.compareTo(val) > 0 && rightVal.compareTo(val) < 0) ||
					(rightVal.compareTo(val) < 0 && leftVal.compareTo(val) < 0 && rightVal.compareTo(leftVal) < 0)) {
				data.set(index, rightVal);
				data.set(2*index+2, val);
				index = 2*index+2;
			}
			else {
				data.set(index, leftVal);
				data.set(2*index+1, val);
				index = 2*index+1;
			}
		}
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * getMin():
	 *   return data.get(0)
	 */
	public E getMin() {
		return data.get(0);
	}
	
	/**
	 * Time: O(log(n)); Space: O(1)
	 * 
	 * Pseudo code:
	 * extractMin():
	 *   temp = data.get(0)
	 *   delete(temp)
	 *   return temp
	 */
	public E extractMin() {
		E temp = data.get(0); 
		delete(0);
		return temp;
	}
	
	/**
	 * Time: O(log(n)) to bubble up; Space: O(1)
	 * 
	 * Pseudo code:
	 * decreaseKey(index, new):
	 *   if new > data.get(index)
	 *     return // new is not less than old
	 *  // Update new value
	 *  data.set(index, new)
	 *  // Bubble up if required
	 *  while index >= 0
	 *    parentVal = data.get(index >> 1)
	 *    if parentVal > new
	 *      data.set(index >> 1, new)
	 *      data.set(index, parentVal)
	 *    index = index >> 1
	 */
	public void decreaseKey(int index, E newVal) {
		if (newVal.compareTo(data.get(index)) > 0) {
			return;
		}
		
		// Update new value
		data.set(index, newVal);
		// Bubble up if required
		while (index >= 0) {
			E parentVal = data.get(index >> 1);
			if (parentVal.compareTo(newVal) > 0) {
				data.set(index >> 1, newVal);
				data.set(index, parentVal);
			}
			index = index >> 1;
		}
	}
}
