package treesAndGraphs;

/**
 * Question:
 * Find if given tree is binary search tree or not.
 * 
 * Important points:
 * 1. All descendents of left is less than all descendents of right for all nodes.
 */
public class IsBinarySearchTree {

	/**
	 * Pseudo code:
	 * isBinarySearchTree(node):
	 *   if node == null
	 *     return true
	 *   if node.left != null and node.left.data > node.data
	 *     return false
	 *   if node.right != null and node.right.data < node.data
	 *     return false
	 *   return isBinarySearchTree(node.left) and isBinarySearchTree(node.right)
	 */
	public boolean isBinarySearchTree(Node node) {
		if (node == null) {
			return true;
		}
		
		if (node.left != null) {
			return node.left.data < node.data;
		}
		
		if (node.right != null) {
			return node.right.data >= node.data;
		}
		return isBinarySearchTree(node.left) && isBinarySearchTree(node.right);
	}
	
	private class Node{
		public int data;
		public Node left;
		public Node right;
	}
}
