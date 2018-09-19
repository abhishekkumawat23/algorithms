package treesAndGraphs;

import java.util.Stack;

/**
 * Question: Implement in-order traversal.
 */
public class InorderTraversal<E> {

	/**
	 * Time: O(n); Space: O(h)
	 * 
	 * Important points:
	 * 1. Space complexity of inorder traversal is O(h) where h is height of tree. It is not O(n) because:
	 *    a. recusrion stack is first going node <- node.left until node is null which is O(h)
	 *    b. After that recursion stack is going node <- node.right until node is null which is again O(h).  
	 * 
	 * Pseudo code:
	 * inorder(root)
	 * inorder(node):
	 *   if node == null
	 *     return
	 *   inorder(node.left)
	 *   print node.data
	 *   inorder(node.right)
	 */
	public void inorder(Node node) {
		if (node == null) {
			return;
		}
		inorder(node.left);
		System.out.println(node.data);
		inorder(node.right);
	}
	
	/**
	 * Time: O(n); Space: O(h)
	 * 
	 * Important points:
	 * 1. Space complexity is O(h) because at every level down we are only storing one element in stack.
	 * 2. Stack at a time stores all elements of a particular path.
	 * 3. Here we are storing all the left nodes. Once there is nothing left on left we print the data and then move to one step right and again store all left nodes.
	 * 4. In this way we are covering left node first, then the node, and then the right.
	 * 5. Whenever current becomes null, this means we reached to end of a path. This every time, current becomes null, we can check the stack size to find height of that particular path. 
	 * 
	 * Pseudo code:
	 * inorder(root):
	 *   if root == null
	 *     return
	 *   current = root
	 *   while stack is not empty or current != null
	 *     if current != null
	 *       stack.push(current)
	 *       current = current.left
	 *     else
	 *       node = stack.pop
	 *       print node.data
	 *       current = node.right
	 */
	public void inorder_interative(Node root) {
		if (root == null) {
			return;
		}
		Node current = root;
		Stack<Node> stack = new Stack<Node>();
		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				current = current.left;	
			}
			else {
				Node node = stack.pop();
				System.out.println(node.data);
				current = node.right;
			}
		}
	}
	
	private class Node{
		public E data;
		public Node left;
		public Node right;
	}
}
