package treesAndGraphs;

import java.util.Queue;
import java.util.Stack;

/**
 * Question: Given a tree, find its height.
 * 
 * Important points:
 * 1. In case, binary tree is complete binary tree and we are representing it via array, we can easily find height via using i and 2i+1 funda.
 * 2. Even for node representation, for complete and perfect binary tree, getting height is O(log(n)) as we just need to do current = current.left 
 */
public class BinaryTreeHeight<E> {

	/**
	 * Time: O(n); Spac	e: O(log(n))
	 * 
	 * Pseudo code:
	 * height(node):
	 *   if node == null
	 *     return -1
	 *   heightLeft = node.left != null ? height(node.left) : 0
	 *   heightRight = node.right != null ? height(node.right) : 0
	 *   return max(heightLeft, heightRight) + 1
	 */
	public int height(Node node) {
		if (node == null) {
			return -1;
		}
		int heightLeft = node.left != null ? height(node.left) : 0;
		int heightRight = node.right != null ? height(node.right) : 0;
		return Math.max(heightLeft, heightRight) + 1;
	}
	
	/**
	 * Time: O(n); Space: O(log(n))
	 * 
	 * Pseudo code:
	 * mexHeight(root):
	 *   if root == null
	 *     return -1
	 *   height = -1;
	 *   current = root;
	 *   while current != null or stack is not empty
	 *     if current != null
	 *       stack.push(current)
	 *       current = current.left
	 *     else
	 *       height = max(height, stack.size - 1)
	 *       current = stack.pop
	 *       current = current.right
	 *   return height
	 *       
	 */
	public int height_iterative(Node root) {
		if (root == null) {
			return -1;
		}
		int height = -1;
		Node current = root;
		Stack<Node> stack = new Stack<Node>();
		while (current != null || !stack.isEmpty()) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			}
			else {
				height = Math.max(height, stack.size() - 1);
				current = stack.pop();
				current = current.right;
			}
		}
		return height;
	}
	
	/**
	 * Time: O(n); Space: O(2^h) ~= O(n)
	 * 
	 * Pseudo code:
	 * height(root):
	 *   if root == null
	 *     return -1 
	 *   height = -1
	 *   queue.add(current)
	 *   while queue is not empty
	 *     height = height + 1
	 *     size = queue.size
	 *     for i = 1 to size
	 *       node = queue.poll
	 *       if node.left != null
	 *         queue.add(node.left)
	 *       if node.right != null
	 *         queue.add(node.right)
	 *   return height   
	 */
	public int height_breadthTraversal(Node root) {
		if (root == null) {
			return -1;
		}
		int height = -1;
		Queue<Node> queue = new java.util.LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			height = height + 1;
			int size = queue.size();
			for (int i = 1; i <= size; i++) {
				Node node = queue.poll();
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}
		return height;
	}
	
	private class Node{
		public Node left;
		public Node right;
	}
}
