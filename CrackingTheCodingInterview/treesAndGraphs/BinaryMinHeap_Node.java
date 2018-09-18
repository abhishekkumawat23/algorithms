package treesAndGraphs;

/**
 * Question:
 * Implement binary min heap using nodes
 */
public class BinaryMinHeap_Node<E extends Comparable<E>> {
	Node root;
	int size = 0;
	
	/**
	 * Time: O(logn*logn); Space: O(1)
	 * 
	 * Important points:
	 * 1. O(logn) becuase nodes are singly linke and while heapify we need to bubble up to evey node. To find each parent node it takes logn.
	 * 2. we can make time O(logn) by either having a doubly linked list or maintaing a list of parents of inserted nodes. That will take space of log(n) but make time O(logn)
	 * 
	 * Pseudo code:
	 * insert(e):
	 *   node = node(e)
	 *   if root == null
	 *     root = node
	 *     size++
	 *     return 
	 *   parentOfLast = getNode(size >> 1 - 1)
	 *   parent = parentOfLast
	 *   if parentOfLast.right != null
	 *     parent = getNode((size >> 1) - 1 + 1)
	 *   if parent.left == null
	 *     parent.left = node
	 *   else
	 *     parent.right = node
	 *   size++
	 *   heapifyBubbleUp(size-1);
	 * heapifyBubbleUp(index)
	 *   n = index + 1
	 *   while index >= 0
	 *     current = getNode(n-1)
	 *     parent = getNode(n >> 1 - 1)
	 *     if parent.data > current.data
	 *       swap(parent, current)
	 *     n = n >> 1
	 * swap(node1, node2)
	 *   temp = node1.data
	 *   node1.data = node2.data
	 *   node2.data = temp
	 * getNode(index)
	 *   n = index + 1
	 *   levels = log n / log 2
	 *   n = n & ~(1 << levels)
	 *   current = root
	 *   for i = levels to 1
	 *     val = (n & (1 << i-1)) >> i-1
	 *     current = val == 0 ? current.left : current.right
	 *   return current
	 */
	public void insert(E e) {
		Node node = new Node(e);
		if (root == null) {
			root = node;
			size++;
			return;
		}
		Node parentOfLast = getNode((size >> 1) - 1);
		Node parent = parentOfLast;
		if (parentOfLast.right != null) {
			parent = getNode((size >> 1) - 1 + 1);
		}
		if (parent.left == null) {
			parent.left = node;
		}
		else {
			parent.right = node;
		}
		size++;
		heapifyBubbleUp(size-1);
	}
	
	private void heapifyBubbleUp(int index) {
		int n = index + 1;
		while (index >= 0) {
			Node current = getNode(n-1);
			Node parent = getNode((n >> 1) - 1);
			if (parent.data.compareTo(current.data) > 0) {
				swap(parent, current);
			}
			n = n >> 1;
		}
	}
	
	private void swap(Node node1, Node node2) {
		E temp = node1.data;
		node1.data = node2.data;
		node2.data = temp;
	}
	
	private Node getNode(int index) {
		int n = index + 1;
		int levels = (int)(Math.log(n)/Math.log(2));
		n = n & ~(1 << levels);
		Node current = root;
		for (int i = levels; i >= 1; i--) {
			int val = (n & (1 << i-1)) >> i-1;
			current = val == 0 ? current.left : current.right;
		}
		return current;
	}
	
	/**
	 * Time: O(logn); Space: O(1)
	 * 
	 * Pseudo code:
	 * delete(index):
	 *   if index == 0 and size == 1
	 *     root = null
	 *     size--
	 *     return
	 *   node = getNode(index)
	 *   last = getNode(size-1)
	 *   node.data = last.data
	 *   parentOfLast = getNode((size >> 1) - 1)
	 *   if parentOfLast.right != null
	 *     parentOfLast.right = null
	 *   else
	 *     parentOfLast.left = null
	 *   size--
	 *   heapifyBubbleDown(index)
	 * heapifyBubbleDown(index):
	 *   current = getNode(index)
	 *   while current != null
	 *     left = current.left
	 *     right = current.right
	 *     if (left == null and right == null) or
	 *        (right == null and left > current) or
	 *        (left > current and right > current)
	 *       return
	 *     else if (right == null and left < current) or
	 *             (left < current and right > current) or
	 *             (left < current and right < current and left < right)
	 *       swap(left, current)
	 *       current = current.left
	 *     else
	 *       swap(right, current)
	 *       current = current.right
	 */
	public void delete(int index) {
		if (index == 0 && size == 1) {
			root = null;
			size = 0;
			return;
		}
		Node node = getNode(index);
		Node last = getNode(size-1);
		node.data = last.data;
		Node parentOfLast = getNode((size >> 1) - 1);
		if (parentOfLast.right != null) {
			parentOfLast.right = null;
		}
		else {
			parentOfLast.left = null;
		}
		size--;
		heapifyBubbleDown(index);
	}
	
	private void heapifyBubbleDown(int index) {
		Node current = getNode(index);
		while (current != null) {
			Node left = current.left;
			Node right = current.right;
			if ((left == null && right == null) ||
				(right == null && left.data.compareTo(current.data) > 0) ||
				(left.data.compareTo(current.data) > 0 && right.data.compareTo(current.data) > 0)) {
				return;
			}
			else if ((right == null && left.data.compareTo(current.data) < 0)  ||
					(left.data.compareTo(current.data) < 0 && right.data.compareTo(current.data) > 0) ||
					(left.data.compareTo(current.data) < 0 && right.data.compareTo(current.data) < 0 && left.data.compareTo(right.data) < 0)){
				swap(left, current);
				current = current.left;
			}
			else {
				swap(right, current);
				current = current.right;
			}   
		}
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * getMin():
	 *   return root.data
	 */
	public E getMin() {
		return root.data;
	}
	
	/**
	 * Time: O(log(n)); Space: O(1)
	 * 
	 * Pseudo code:
	 * extractMin():
	 *   temp = root.data
	 *   delete(temp)
	 *   return temp
	 */
	public E extractMin() {
		E temp = root.data; 
		delete(0);
		return temp;
	}
	
	/**
	 * Time: O(log(n)); Space: O(1)
	 * 
	 * Pseudo code:
	 * decreaseKey(index, new):
	 *   node = getNode(index)
	 *   if new > node.data
	 *     return // new is not less than old
	 *   // Update new value
	 *   node.data = new
	 *   heapifyBubbleUp(index)
	 */
	public void decreaseKey(int index, E newVal) {
		Node node = getNode(index);
		if (newVal.compareTo(node.data) > 0) {
			return;
		}
		node.data = newVal;
		heapifyBubbleUp(index);
	}
	
	private class Node{
		public E data;
		public Node left;
		public Node right;
		
		public Node(E data) {
			this.data = data;
		}
	}
}
