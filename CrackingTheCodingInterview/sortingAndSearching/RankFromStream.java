package sortingAndSearching;

/**
 * Imagine you are reading in a stream of integers.
 * Periodically, you wish to be able to look up the rank of a number x(the number of values less than or equal to x).
 * Implement the data structures and algorithms to support these operations.
 * That is, implement the method track(int x), which is called when each number is generated,
 * and the method getRankOfNumber(int x), which returns the number of values less than or equal to x (not including x itself).
 * Example:
 * Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
 * getRankOfNumber(1) = 0
 * getRankOfNumber(3) = 1
 * getRankOfNumber(4) = 3
 */
public class RankFromStream {
	private Node root;
	
	/**
	 * Time: O(logn) assuming balanced tree
	 * 
	 * Important points:
	 * 1. We are skipping balancing tree here. Ideally we should balance tree.
	 * 
	 * track(x):
	 *   if root == null
	 *     root = new Node(x)
	 *     return
	 *   parent = null
	 *   current = root
	 *   while current != null
	 *     parent = current
	 *     if x > current.value
	 *       current = current.right
	 *     else if x < current.value
	 *       current.lefCount++
	 *       current = current.left
	 *     else
	 *       current.selfCount++
	 *       return
	 *   if x < parent.value
	 *     parent.leftCount++
	 *     parent.left = new Node(x)
	 *   else
	 *     parent.right = new Node(x)
	 */
	public void track(int x) {
		if (root == null) {
			root = new Node(x);
			return;
		}
		Node parent = null;
		Node current = root;
		while (current != null) {
			parent = current;
			if (x > current.value) {
				current = current.right;
			}
			else if (x < current.value) {
				current.leftCount++;
				current = current.left;
			}
			else {
				current.selfCount++;
				return;
			}
		}
		if (x < parent.value) {
			parent.leftCount++;
			parent.left = new Node(x);
		}
		else {
			parent.right = new Node(x);
		}
	}
	
	/**
	 * Time: O(logn) assuming balanced tree
	 * 
	 * getRankOfNumber(x):
	 *   current = root
	 *   rank = 0
	 *   while current != null
	 *     if x > current.value
	 *       rank += current.leftCount + current.selfCount
	 *       current = current.right
	 *     else if x < current.value
	 *       current = current.left
	 *     else
	 *       rank += current.leftCount + current.selfCount
	 *       return rank
	 *   return -1
	 */
	public int getRankOfNumber(int x) {
		Node current = root;
		int rank = 0;
		while (current != null) {
			if (x > current.value) {
				rank += current.leftCount + current.selfCount;
				current = current.right;
			}
			else if (x < current.value) {
				current = current.left;
			}
			else {
				rank += current.leftCount + current.selfCount;
				return rank;
			}
		}
		return -1;
	}
	
	private class Node{
		Node left;
		Node right;
		int value;
		int leftCount = 0;
		int selfCount = 1;
		
		public Node(int value) {
			this.value = value;
		}
	}
}
