package treesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Question:
 * Check if given tree is complete binary tree.
 * 
 * Important points:
 * 1. Complete binary tree has every level fully filled except perpahps the last level.
 * 2. In last level, nodes are filled from left to right
 * 3. By definition, for complete binary tree, the diff between path of max height and path of min height should not be greater than 1.
 * 4. If complete binary tree is put in array in breadth traversal form, then if parent index is i, then left child is 2i+1 and right child is 2i+2  
 */
public class IsCompleteBinaryTree<E> {
	
	/**
	 * Important points:
	 * 1. We are using the fact that diff between max and min height should be less than equal to 1.
	 * 
	 * Pseudo code:
	 * isCompleteBinaryTree(s):
	 *   heights = getHeights(root)
	 *   return heights.max - heights.max <= 1
	 * getHeights(node)
	 *   heightsLeft = node.left != null ? getHeights(node.left) : -1
	 *   heightsRight = node.right != null ? getHeights(node.right) : -1
	 *   heights.max = max(heightsLeft.max, heightsRight.max) + 1
	 *   heights.min = min(heightsLeft.max, heightsRight.min) + 1
	 *   return heights
	 */
	public boolean isCompleteBinaryTree(Node root) {
		Heights heights = getHeights(root);
		return heights.max - heights.min <= 1;
	}
	
	private Heights getHeights(Node node) {
		Heights heightsLeft = node.left != null ? getHeights(node.left) : new Heights(-1, -1);
		Heights heightsRight = node.right != null ? getHeights(node.right) : new Heights(-1, -1);
		int maxHeight = Math.max(heightsLeft.max, heightsRight.max) + 1;
		int minHeight = Math.min(heightsLeft.min, heightsRight.min) + 1;
		Heights heights = new Heights(maxHeight, minHeight);
		return heights;
	}
	
	/**
	 * Important points:
	 * 1. we are using the fact that when we convert the binary tree in array form. Then if parent index is i, then child index are 2i+1 and 2i+2
	 * 2. So, if we calculate the number of elements in tree and then check that whether the indexing of any element is crossing n or not.
	 * 3. Index will cross n if the tree is not complete binary tree
	 * 
	 * Pseudo code:
	 * isCompleteBinaryTree(root, 1, getCount(root))
	 * isCompleteBinaryTree(node, index, n):
	 *   if node == null
	 *     return true
	 *   if index > noOfElements
	 *     return false
	 *   return isCompleteBinaryTree(node.left, index, n) && isCompleteBinaryTree(node.right, index, n)
	 * getCount(node):
	 *   if (node == null)
	 *     return 0
	 *   return getCount(node.left) + getCount(node.right) + 1
	 *   
	 */
	public boolean isCompleteBinaryTree_index(Node root) {
		int n = getCount(root);
		return isCompleteBinaryTree(root, 1, n);
	}
	
	private boolean isCompleteBinaryTree(Node node, int index, int n) {
		if (node == null) {
			return true;
		}
		if (index > n) {
			return false;
		}
		return isCompleteBinaryTree(node.left, 2*index+1, n) && isCompleteBinaryTree(node.right, 2*index+2, n);
	}
	
	private int getCount(Node node) {
		if (node == null) {
			return 0;
		}
		return getCount(node.left) + getCount(node.right) + 1;
	}
	
	private class Heights {
		public int min;
		public int max;
		
		public Heights(int max, int min) {
			this.max = max;
			this.min = min;
		}
	}
	
	/**
	 * Time: O(n); Space: O(2^h) ~= O(n)
	 * 
	 * Pseudo code:
	 * isCompleteBinaryTree(root)
	 *   if root == null
	 *     return true
	 *   queue.add(root)
	 *   while queue is not empty
	 *     node = queue.poll
	 *     if node.left != null
	 *       if firstNullChildOccurred
	 *         return false
	 *       queue.add(node.left)
	 *     else
	 *       firstNullChildOccurred = true
	 *     if node.right != null
	 *       if firstNullChildOccurred
	 *         return false
	 *       queue.add(node.right)
	 *     else
	 *       firstNullChildOccurred = true
	 *   return true
	 */
	public boolean isCompleteBinaryTree_breadthTraversal(Node root) {
		if (root == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		boolean firstNullChildOccurred = false;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			// left node
			if (node.left != null) {
				if (firstNullChildOccurred) {
					return false;
				}
				queue.add(node.left);
			}
			else {
				firstNullChildOccurred = true;
			}
			
			// right node
			if (node.right != null) {
				if (firstNullChildOccurred) {
					return false;
				}
				queue.add(node.right);
			}
			else {
				firstNullChildOccurred = true;
			}
		}
		return true;
	}
	
	private class Node{
		public Node left;
		public Node right;
	}
}
