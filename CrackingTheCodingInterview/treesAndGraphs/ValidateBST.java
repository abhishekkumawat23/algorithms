package treesAndGraphs;

/**
 * Question:
 * Implement a function to check if a binary tree is a binary search tree
 */
public class ValidateBST {

	/**
	 * Time: O(n); Space: O(logn)
	 * 
	 * isBST(node):
	 *   if node == null
	 *     return true
	 *   if (node.left != null and node.left.data > node.data) or
	 *      (node.right != null and node.right.data < node.data)
	 *     return false
	 *   return isBST(node.left) and isBST(node.right) 
	 */
	public boolean isBST(Node node) {
		if (node == null) {
			return true;
		}
		if ((node.left != null && node.left.data > node.data) ||
			(node.right != null && node.right.data < node.data)) {
			return false;
		}
		return isBST(node.left) && isBST(node.right);
	}
	
	private class Node{
		public int data;
		public Node left;
		public Node right;
	}
}
