package treesAndGraphs;

/**
 * Question:
 * Implement complete binary tree using Node class.
 * 
 * Important points:
 * 1. For complete binary tree, we should use array as implementation as insert takes O(1) time only in it.
 * 2. If using Node class as implementation, insert will take O(n) time as we need to reach the last element where insertion has to happen.
 *    a. So while implementing Node class, we somehow can use funda of i, 2i+1, 2i+2.
 *    b. We need to store the size of tree for this.
 *    c. From tree size, we can get its binary number. Remove first digit of binary number.
 *    d. We can then iterate the binary number to find whcih direction to go to find the parent of last inserted element.
 *    e. 0 means go left. 1 means go right.
 * 3. As we are maintaing size of tree, we can simply return height as integer of log size / log 2
 */
public class CompleteBinaryTree_Node<E> {
	Node root;
	int size = 0;
	
	/**
	 * Time: O(log(n)); Space: O(1)
	 * 
	 * Important points:
	 * 1. Find parent of last inserted element using binary number funda.
	 * 2. If parent has both child filled, we get the nextparent using same binary number funda and add element as its left child.
	 * 
	 * Pseudo code:
	 * insert(e):
	 *   node = node(e)
	 *   height = int(log size /log 2)
	 *   // Handle root null scenario
	 *   if height == 0 and root == null
	 *     root = node
	 *     size++
	 *     return
	 *   parentOfLast = getElement(size >> 1)
	 *   // insert if left or right is empty
	 *   if parentOfLast.left == null
	 *     parentOfLast.left = node
	 *   else if parentOfLast.right == null
	 *     parentOfLast.right = node
	 *   // if both left and right are full, go to the next parent and add node in left child of it
	 *   else
	 *     nextParent = getElement(size >> 1 + 1)
	 *     nextParent.left = node
	 *   size++
	 * getNode(size):
	 *   current = root
	 *   height = log size / log 2
	 *   value = size & ~(1 shl height)
	 *   // Go to element
	 *   for i = height to 1
	 *     dir = (value & 1 << (i-1)) >> (i-1)
	 *     current = dir == 0 ? current.left : current.right
	 *   return current
	 *     
	 */
	public void insert(E e) {
		Node node = new Node(e);
		int height = (int)(Math.log(size)/Math.log(2));
		// Handle root null scenario
		if (height == 0 && root == null) {
			root = node;
			size++;
			return;
		}
		Node parentOfLast = getNode(size >> 1);
		// insert if left or right is empty
		if (parentOfLast.left == null) {
			parentOfLast.left = node;
		}
		else if (parentOfLast.right == null) {
			parentOfLast.right = node;
		}
		// if both left and right are full, go to the next parent and add node in left child of it
		else {
			Node nextParent = getNode(size >> 1 + 1);
			nextParent.left = node;
		}
		size++;
	}
	
	private Node getNode(int n) {
		Node current = root;
		int height = (int)(Math.log(n)/Math.log(2));
		n = n & ~(1 << height);
		// Go to element
		for (int i = height; i <= 1; i--) {
			int dir = (n & 1 << (i-1)) >> (i-1);
			current = dir == 0 ? current.left : current.right;
		}
		return current;
	}
	
	/**
	 * Time: O(n); Space: O(log(n))
	 * Both time complexity and space is high than insert as we have to find the element to be deleted recursively.
	 * 
	 * O(n) time is taken
	 * Pseudo code:
	 * delete(e):
	 *   node = getNode(root, e)
	 *   if node == null
	 *     return
	 *   parentOfLast = getElement(size >> 1)
	 *   if parentOfLast.right != null
	 *     node.data = parentOfLast.right.data
	 *     parentOfLast.right = null
	 *   else
	 *     node.data = parentOfLast.left.data
	 *     parentOfLast.left = null
	 *   size--
	 * getNode(node, e)
	 *   if node == null
	 *     return null
	 *   if node.data == e
	 *     return node
	 *   leftFoundNode = getNode(node.left, e)
	 *   return leftFoundNode == null ? getNode(node.right, e) : leftFoundNode
	 */
	public void delete(E e) {
		Node node = getNode(root, e);
		if (node == null) {
			return;
		}
		
		Node parentOfLast = getNode(size >> 1);
		if (parentOfLast.right != null) {
			node.data = parentOfLast.right.data;
			parentOfLast.right = null;
		}
		else {
			node.data = parentOfLast.left.data;
			parentOfLast.left = null;
		}
		size--;
	}
	
	private Node getNode(Node node, E e) {
		if (node == null) {
			return null;
		}
		if (node.data == e) {
			return node;
		}
		Node leftFoundNode = getNode(node.left, e);
		return leftFoundNode == null ? getNode(node.right, e) : leftFoundNode;
	}
	
	/**
	 * Time: O(log(n)); Space: O(1)
	 * 
	 * Pseudo code:
	 * delete(index)
	 *   // Delete node
	 *   node = getNode(index+1)
	 *   parentOfLast = getNode(size >> 1)
	 *   node.data = parentOfLast.right != null ? parentOfLast.right.data ? parentOfLast.left.data
	 *   if parentOfLast.right != null
	 *     parentOfLast.right = null
	 *   else
	 *     parentOfLast.left = null
	 */
	public void delete(int index) {
		Node node = getNode(index+1);
		Node parentOfLast = getNode(size >> 1);
		node.data = parentOfLast.right != null ? parentOfLast.right.data : parentOfLast.left.data;
		if (parentOfLast.right != null) {
			parentOfLast.right = null;
		}
		else {
			parentOfLast.left = null;
		}
	}
	
	/**
	 * Pseudo code:
	 * getHeight():
	 *   return int(log size/ log 2)
	 */
	public int getHeight() {
		return (int) (Math.log(size) / Math.log(2));
	}
	
	private class Node {
		public E data;
		public Node right;
		public Node left;
		
		public Node(E data) {
			this.data = data;
		}
	}
}
