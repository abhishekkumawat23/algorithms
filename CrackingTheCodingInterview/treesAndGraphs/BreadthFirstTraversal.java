package treesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Question:
 * Implement breadth first traversal of binary tree.
 */
public class BreadthFirstTraversal<E> {
	
	/**
	 * Time: O(n); Space: O(2^h) ~= O((n+1)/2) ~= O(n)
	 * 
	 * Important points:
	 * 1. Inorder, preorder, postorder are all examples of depth first traversal. In this question, we are implementing breadth first traversal.
	 * 2. Queue stores elements of a particular level at a time. So max no. of elements will be when last level elements (2^h) are stored in it.
	 * 
	 * Pseudo code:
	 * breadthFirst(root):
	 *   if root == null
	 *     return
	 *   queue.add(root)
	 *   while queue is not empty
	 *     current = queue.poll;
	 *     print node.data
	 *     if current.left != null
	 *       queue.add(current.left)
	 *     if current.right != null
	 *       queue.add(current.right)
	 */
	public void breadthFirst(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Node current = null;
		while(!queue.isEmpty()) {
			current = queue.poll();
			System.out.println(current.data);
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
	}
	
	/**
	 * Time: O(n); Space: O(2^h) for queue + O(h) for recursion
	 * 
	 * Important points:
	 * 1. Stack always deals with depth and queue always deals with breadth.
	 * 2. While traversal we are going breadth wise for a level. Once that level is traversed, we are going one level deep.
	 * 3. So only the level changes thing can be done recursively. Traversing within level had to be done using queue.
	 * 4. Queue when stores last level takes max space which is O(2^h). Recusrion stack is till no. of levels so its O(h).
	 * 
	 * Pseudo code:
	 * breadthFirst(queue.add(root))
	 * breadthFirst(queue):
	 *   queueSize = queue.size
	 *   for i = 1 to queueSize
	 *     current = queue.poll
	 *     print node.data
	 *     if current.left != null
	 *       queue.add(current.left)
	 *     if current.right != null
	 *       queue.add(current.right)
	 */
	public void breadthFirst_recursive(Queue<Node> queue) {
		int queueSize = queue.size();
		Node current = null;
		for (int i = 1; i <= queueSize; i++) {
			current = queue.poll();
			System.out.println(current.data);
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
		}
	}

	private class Node{
		public E data;
		public Node left;
		public Node right;
	}
}
