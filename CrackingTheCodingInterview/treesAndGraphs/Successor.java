package treesAndGraphs;

/**
 * Question:
 * Write an algorithm to find the `next` node (i.e. in-order successor) of a given node in a binary search tree.
 * You may assume that each node has a link to its parent.
 */
public class Successor {

	/**
	 * Time: O(2logn); Space: O(logn)
	 * 
	 * successor(node):
	 *   if node == null
	 *     return null
	 *   if node.right != null
	 *     return getLeftMost(node.right)
	 *   while node.parent.right == node
	 *     node = node.parent
	 *   return node.parent
	 * getLeftMost(node)
	 *   while node.left != null
	 *     node = node.left
	 *   return node
	 */
	public Node successor(Node node) {
		if (node == null) {
			return null;
		}
		if (node.right != null && node.parent.right != node) {
			return getLeftMost(node.right);
		}
		while (node.parent != null && node.parent.right == node) {
			node = node.parent;
		}
		return node.parent;
	}
	
	private Node getLeftMost(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	private class Node{
		public Node left;
		public Node right;
		public Node parent;
	}
}
