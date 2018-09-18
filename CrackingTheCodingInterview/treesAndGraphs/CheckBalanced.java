package treesAndGraphs;

/**
 * Question:
 * Implement a function to check if a binary tree is balanced. 
 * For the purposes of this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
 */
public class CheckBalanced {

	/**
	 * Time: O(n); Space:(logn)
	 * checkBalanced(root):
	 *   return getHeight(root) >= -1
	 * getHeight(node):
	 *   if node == null 
	 *     return -1
	 *   leftHeight = getHeight(node.left)
	 *   if leftHeight == -2
	 *     return -2
	 *   rightHeight = getHeight(node.right)
	 *   if rightHeight == -2 or abs(leftHeight-rightHeight) > 1
	 *     return -2
	 *   return max(leftHeight, rightHeight) + 1
	 */
	public boolean checkBalanced(Node root) {
		return getHeight(root) >= -1;
	}
	
	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}
		int leftHeight = getHeight(node.left);
		if (leftHeight == -2) {
			return -2;
		}
		int rightHeight = getHeight(node.right);
		if (rightHeight == -2 || Math.abs(leftHeight-rightHeight) > 1) {
			return -2;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	private class Node{
		public Node left;
		public Node right;
	}
}
