package treesAndGraphs;

/**
 * Question:
 * Find if given tree is full binary tree or not.
 * 
 * Important points:
 * 1. Full binary tree has either 0 or 2 nodes.
 */
public class IsFullBinaryTree {

	/**
	 * Time; O(n); Space: O(h)
	 * 
	 * Pseudo code:
	 * isFullBinaryTree(node):
	 *   if node == null
	 *     return true
	 *   if (node.left == null and node.right != null) or (node.left != null and node.right != null)
	 *     return false
	 *   return isFullBinaryTree(node.left) and isFullBinaryTree(node.right)
	 */
	public boolean isFullBinaryTree(Node node) {
		if (node == null) {
			return true;
		}
		
		if ((node.left == null && node.right != null) ||
		    (node.right == null && node.left != null)) {
			return false;
		}
		
		if (node.right == null && node.left != null) {
			return false;
		}
		return isFullBinaryTree(node.left) && isFullBinaryTree(node.right);
	}
	
	private class Node{
		public Node left;
		public Node right;
	}
}
