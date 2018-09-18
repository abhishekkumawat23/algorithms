package treesAndGraphs;

/**
 * Question:
 * Find if given tree is perfect binary tree
 * 
 * Important points:
 * 1. Perfect binary tree is both complete and full.
 */
public class IsPerfectBinaryTree<E> {

	/**
	 * Time: O(n); Space: O(h)
	 * 
	 * Important points:
	 * 1. Here we are using the fact that diff between min height and max height of perfect binary tree is 0.
	 * 
	 * Pseudo code:
	 * isPerfectBinaryTree(root)
	 * isPerfectBinarytree(node):
	 *   heights = getHeights(root)
	 *   return hegiths.max - heights.min == 0
	 * getHeights(node):
	 *   if node == null
	 *     return heights(-1, -1)
	 *   heightsLeft = getHeights(node.left)
	 *   heightsRights = getHeights(node.right)
	 *   heightMin = min(heightsLeft.min, heightsRight.min) + 1
	 *   heightMax = max(heightsLeft.max, heightsRight.max) + 1
	 *   return heights(heightMax, heightMin)
	 */
	public boolean isPerfectBinaryTree_minMaxHeight(Node root) {
		Heights heights = getHeights(root);
		return heights.max - heights.min == 0;
	}
	
	private Heights getHeights(Node node) {
		if (node == null) {
			return new Heights(-1, -1);
		}
		Heights heightsLeft = getHeights(node.left);
		Heights heightsRight = getHeights(node.right);
		int heightMin = Math.min(heightsLeft.min, heightsRight.min) + 1;
		int heightMax = Math.min(heightsLeft.max, heightsRight.max) + 1;
		return new Heights(heightMax, heightMin);
	}
	
	private class Heights{
		public int min;
		public int max;
		
		public Heights(int max, int min) {
			this.max = max;
			this.min = min;
		}
	}
	
	/**
	 * Pseudo code:
	 * isPerfectBinaryTree(root):
	 *   height = getHeight(root)
	 *   count = getCount(root)
	 *   return count == pow(2, height+1)-1
	 * getHeight(node):
	 *   if node == null
	 *     return -1
	 *   return max(getHeight(node.left), getHeight(node.right)) + 1
	 * getCount(node):
	 *   if node == null
	 *     return 0
	 *   return getCount(node.left) + getCount(node.right) + 1
	 */
	public boolean isPerfectBinaryTree_noOfElements(Node root) {
		int height = getHeight(root);
		int count = getCount(root);
		return count == (int)(Math.pow(2, height+1) - 1);
	}
	
	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}
	
	private int getCount(Node node) {
		if (node == null) {
			return 0;
		}
		return getCount(node.left) + getCount(node.right) + 1;
	}
	
	private class Node{
		public Node left;
		public Node right;
	}
}
