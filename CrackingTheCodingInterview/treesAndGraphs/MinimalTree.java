package treesAndGraphs;

/**
 * Question:
 * Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimum height.
 * 
 * Important points:
 * 1. We can get sorted list from binary search tree by in order traversal. Here question is opposite, we have to get binary search tree from sorted array.
 * 2. In in-order traversal, we first traverse left elements, then node, and then right elements.
 * 3. So here we can pick middle of array as root of tree. For left part of array, again the middle element will be left side root
 * 4. i.e. root = middle of array; root.left = middle of left sub-array; root.right = middle of right sub-array and so on.
 * 5. So if from a binary search tree of arbit height, if we have to get the minimal height, we simply have to in-order traverse and then create the minimla tree out of it. 
 */
public class MinimalTree {
	
	/**
	 * Time: O(n); Space: O(logn)
	 * minimalTree(array):
	 *   return createMinimalTree(array, 0, array.length-1)
	 * createMinimalTree(array, low, high)
	 *   if low > high
	 *     return null
	 *   mid = (low + high)/2
	 *   node = node(array[mid])
	 *   node.left = createMinimalTree(array, low, mid-1)
	 *   node.right = createMinimalTree(array, mid+1, high)
	 *   return node
	 */
	public Node minimalTree(int[] array) {
		return createMinimalTree(array, 0, array.length-1);
	}
	
	private Node createMinimalTree(int[] array, int low, int high) {
		if (low > high) {
			return null;
		}
		int mid = (low + high)/2; 
		Node node = new Node(array[mid]);
		node.left = createMinimalTree(array, low, mid-1);
		node.right = createMinimalTree(array, mid+1, high);
		return node;
	}
	
	private class Node{
		@SuppressWarnings("unused")
		public int data;
		@SuppressWarnings("unused")
		public Node left;
		@SuppressWarnings("unused")
		public Node right;
		
		public Node(int data) {
			this.data = data;
		}
	}
}
