package treesAndGraphs;

import java.util.Random;

/**
 * Question:
 * You are implementing a binary tree class from scratch.
 * This class in addition to insert, find and delete has a method getRandomNode() which returns a random node from the tree.
 * All nodes should be equally likely to be chosen.
 * Design and implement an algorithm for getRandomNode and explain how you would implement the rest of the methods.
 */
public class RandomNode {
	Node root;

	/**
	 * Time: O(h); Space; O(h)
	 * 
	 * Important points:
	 * 1. As nothing is given about binary tree, we will add small elements in left and large elements in right.
	 * 
	 * insert(node, data):
	 *   if node == null
	 *     node = node(data)
	 *     node.size = 1
	 *   else if data < node.data
	 *     node.left = insert(node.left, data)
	 *   else
	 *     node.right = insert(node.right, data)
	 *   sizeLeft = node.left != null ? node.left.size : 0
	 *   sizeRight = node.right != null ? node.right.size : 0
	 *   node.size = sizeLeft + sizeRight + 1
	 *   return node
	 */
	public Node insert(Node node, int data) {
		if (node == null) {
			node = new Node(data);
			node.size = 1;
		}
		else if (data < node.data) {
			node.left = insert(node.left, data);
		}
		else {
			node.right = insert(node.right, data);
		}
		int sizeLeft = node.left != null ? node.left.size : 0;
		int sizeRight = node.right != null ? node.right.size : 0;
		node.size = sizeLeft + sizeRight + 1;
		return node;
	}
	
	/** 
	 * insert(data):
	 *   node = node(data)
	 *   node.size = 1
	 *   if root == null
	 *     root = node
	 *     return
	 *   parent = root
	 *   current = root
	 *   while current != null
	 *     parent = current
	 *     current.size++
	 *     current = data < current.data ? current.left : current.right
	 *   if data < stack.peek.data
	 *     parent.left = node(data)
	 *   else
	 *     parent.right = node(data)
	 *   
	 */
	public void insert_iter(int data) {
		Node node = new Node(data);
		node.size = 1;
		if (root == null) {
			root = node;
			return;
		}
		// Find where to insert node
		Node parent = root;
		Node current = root;
		while (current != null) {
			parent = current;
			current.size++;
			current = data < current.data ? current.left : current.right;
		}
		// Insert node
		if (data < parent.data) {
			parent.left = node; 
		}
		else {
			parent.right = node;
		}
	}
	
	/**
	 * Time: O(h); Space: O(h)
	 * 
	 * find(node, data):
	 *   if node == null
	 *     return false
	 *   if node.data == data
	 *     return true
	 *   return find(node.left, data) || find(node.right, data)
	 */
	public boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}
		if (node.data == data) {
			return true;
		}
		return find(node.left, data) || find(node.right, data);
	}
	
	/**
	 * Time: O(h); Space: O(1)
	 * 
	 * find(data):
	 *   current = root
	 *   while current != null
	 *     if current.data == data
	 *       return true
	 *     current = data < current.data ? current.left : current.right;
	 *   return false
	 */
	public boolean find_iter(int data) {
		Node current = root;
		while (current != null) {
			if (current.data == data) {
				return true;
			}
			current = (data < current.data) ? current.left : current.right;
		}
		return false;
	}
	
	/**
	 * Time: O(h); Space: O(h)
	 * 
	 * delete(node, data):
	 *   if node == null
	 *     return null
	 *   if data < node.data
	 *     node.left = delete(node.left, data)
	 *   else if data > node.data
	 *     node.right = delete(node.right, data)
	 *   else
	 *     if node.left == null
	 *       node = node.right
	 *     else if node.right == null
	 *       node = node.left
	 *     else
	 *       rightMin = findMin(node.right)
	 *       node.data = rightMin.data
	 *       node.right = delete(node.right, rightMin.data)
	 *   sizeLeft = node.left != null ? node.left.size : 0
	 *   sizeRight = node.right != null ? node.right.size : 0
	 *   node.size = sizeLeft + sizeRight + 1
	 *   return node
	 * findMin(node):
	 *   current = node
	 *   while current.left != null
	 *     current = current.left
	 *   return current
	 */
	public Node delete(Node node, int data) {
		if (node == null) {
			return null;
		}
		if (data < node.data) {
			node.left = delete(node.left, data);
		}
		else if (data > node.data) {
			node.right = delete(node.right, data);
		}
		else {
			if (node.left == null) {
				node = node.right;
			}
			else if (node.right == null) {
				node = node.left;
			}
			else {
				Node rightMin = findMin(node.right);
				node.data = rightMin.data;
				node.right = delete(node.right, rightMin.data);
			}
		}
		int sizeLeft = node.left != null ? node.left.size : 0;
		int sizeRight = node.right != null ? node.right.size : 0;
		node.size = sizeLeft + sizeRight + 1;
		return node;
	}
	
	private Node findMin(Node node) {
		Node current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}
	
	/**
	 * Time: O(h); Space: O(1)
	 * 
	 * delete(data):
	 *   if root.data == data
	 *     root = null
	 *   parent = root
	 *   current = root
	 *   // Find node to delete
	 *   while current != null
	 *     if data != current.data
	 *       parent = current
	 *       current.size--
	 *       current = data < curent.data ? current.left : current.right
	 *     else if current.left != null and current.right != null
	 *       parent = current
	 *       current.size--
	 *       // Update current with right's min value
	 *       min = findMin(current.right)
	 *       current.data = min.data
	 *       data = min.data
	 *       current = current.right
	 *     else
	 *       break
	 *   // If data not found, simply return
	 *   if current == null
	 *     return
	 *   // Delete node
	 *   if current.data == data
	 *     node = current.left != null ? current.left : current.right;
	 *     if data < parent.data
	 *       parent.left = node
	 *     else
	 *       parent.right = node
	 */
	public void delete_iter(int data) {
		if (root.data == data) {
			root = null;
		}
		Node parent = root;
		Node current = root;
		// Find node to delete
		while (current != null) {
			if (data != current.data) {
				parent = current;
				current.size--;
				current = data < current.data ? current.left : current.right;
			}
			else if (current.left != null && current.right != null) {
				parent = current;
				current.size--;
				Node min = findMin(current.right);
				current.data = min.data;
				data = min.data;
				current = current.right;
			}
			else {
				break;
			}
		}
		// If data not found, simply return
		if (current == null) {
			return;
		}
		// Delete node
		if (current.data == data) {
			Node node = current.left != null ? current.left : current.right;
			if (data < parent.data) {
				parent.left = node;
			}
			else {
				parent.right = node;
			}
		}
	}
	
	/**
	 * Time: O(h); Space: O(h)
	 * 
	 * getRandomNode():
	 *   randomIndex = random(0, root.size)
	 *   getRandomNode(root, randomIndex)
	 * getRandomNode(node, randomIndex):
	 *   leftSize = node.left != null ? node.left.size : 0
	 *   if randomIndex < leftSize
	 *     return getRandomNode(node.left, randomIndex)
	 *   else if randomIndex == leftSize
	 *     return node
	 *   else
	 *     return getRandomNode(node.right, randomIndex - (leftSize + 1))
	 */
	public Node getRandomNode() {
		Random random = new Random();
		int randomIndex = random.nextInt(root.size);
		return getRandomNode(root, randomIndex);
	}
	
	private Node getRandomNode(Node node, int randomIndex) {
		int leftSize = node.left != null ? node.left.size : 0;
		if (randomIndex < leftSize) {
			return getRandomNode(node.left, randomIndex);
		}
		else if (randomIndex == leftSize) {
			return node;
		}
		else {
			return getRandomNode(node.right, randomIndex - (leftSize + 1));
		}
	}
	
	/**
	 * Time: O(h); Space:(1)
	 * 
	 * getRandomNode():
	 *   randomIndex = random(0, root.size)
	 *   current = root
	 *   while randomIndex > 0
	 *     if randomIndex < current.left.size
	 *       current = current.left
	 *     else if randomIndex == current.left.size
	 *       return current
	 *     else
	 *       current = current.right
	 *       randomIndex = randomIndex - (current.left.size + 1)
	 *   return null
	 */
	public Node getRandomNode_iter() {
		Random random = new Random();
		int randomIndex = random.nextInt(root.size);
		Node current = root;
		while (randomIndex > 0) {
			int leftSize = current.left != null ? current.left.size : 0;
			if (randomIndex < leftSize) {
				current = current.left;
			}
			else if (randomIndex == leftSize) {
				return current;
			}
			else {
				current = current.right;
				randomIndex = randomIndex - (leftSize + 1);
			}
		}
		return null;
	}
	
	private class Node{
		public int data;
		public Node left;
		public Node right;
		public int size;
		
		public Node(int data) {
			this.data = data;
		}
	}
}
