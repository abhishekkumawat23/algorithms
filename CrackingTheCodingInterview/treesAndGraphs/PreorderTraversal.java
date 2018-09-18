package treesAndGraphs;

import java.util.Stack;

/**
 * Question:
 * Implement preorder traversal
 */
public class PreorderTraversal<E> {

	/**
	 * Time: O(n); Space: O(h)
	 * 
	 * Pseudo code:
	 * preorder(root)
	 * preorder(node):
	 *   if node == null
	 *     return
	 *   print node.data
	 *   preorder(node.left)
	 *   preorder(node.right)
	 */
	public void preorder(Node node) {
		if (node == null) {
			return;
		}
		System.out.println(node.data);
		preorder(node.left);
		preorder(node.right);
	}
	
	/**
	 * Time: O(n); Space: O(h)
	 * 
	 * Important points:
	 * 1. Space complexity is O(h) as we at every level down we are adding 2 elements and removing one.
	 * 2. we are removing the top element from stack, printing it and storing right and left children of popped element back in stack.
	 * 3. Rememeber to first store the right element and then left element as left element should be above as it gets printed before right.
	 * 
	 * Pseudo code:
	 * preorder(root)
	 *   if root == null
	 *     return
	 *   stack.push(root)
	 *   while stack is not empty
	 *     current = stack.pop
	 *     print current.data
	 *     if current.right != null
	 *       stack.push(current.right)
	 *     if current.left != null
	 *       stack.push(current.left)
	 *       
	 */
	public void preorder_iterative(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		Node current = null;
		while(!stack.isEmpty()) {
			current = stack.pop();
			System.out.println(current.data);
			if (current.right != null) {
				stack.push(current.right);
			}
			if (current.left != null) {
				stack.push(current.left);
			}
		}
	}
	
	private class Node{
		public E data;
		public Node left;
		public Node right;
	}
}
