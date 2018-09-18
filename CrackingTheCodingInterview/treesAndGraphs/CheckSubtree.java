package treesAndGraphs;

/**
 * Question:
 * T1 and T2 are two very large binary trees, with T1 much bigger tahn T2.
 * Create an algorithm to determine if T2 is subtree of T1.
 * A tree is a subtree if T1 if there exists a node n in T1 such that the subtree of n is identical to T2.
 * That is, if you cut off the tree at node n, the two trees would be identical.
 */
public class CheckSubtree {

	/**
	 * Time: O(n); Space: O(h) for finding node + O(h') for checking identical
	 * n is size of root1 tree; h is height of root1 tree; h' is height of root2 tree.
	 * As h' is less than h. we can ignore h' space.
	 * 
	 * Important points:
	 * 1. We are asuming here that there is at max one element in T1 matching T2 root.
	 * 
	 * checkSubtree(root1, root2):
	 *   node = findNode(root1, root2.data)
	 *   if node == null
	 *     return false
	 *   return checkIdentical(node, root2)
	 * findNode(node, data):
	 *   if node == null || node.data == data
	 *     return node
	 *   leftFoundNode = findNode(node.left, data)
	 *   if leftFoundNode != null
	 *     return leftFoundNode
	 *   rightFoundNode = findNode(node.right, data)
	 *   if rightFoundNode != null
	 *     return rightFoundNode
	 *   return null
	 * checkIdentical(node1, node2)
	 *   if node1 == null and node2 == null
	 *     return true
	 *   if (node1.data != node2.data) or (node1 == null and node2 != null) or (node2 == null and node1 != null) 
	 *     return false
	 *   return checkIdentical(node1.left, node2.left) && checkIdentical(node1.right, node2.right)
	 */
	public boolean checkSubtree(Node root1, Node root2) {
		Node node = findNode(root1, root2.data);
		if (node == null) {
			return false;
		}
		return checkIdentical(node, root2);
	}
	
	private Node findNode(Node node, int data) {
		if (node == null || node.data == data) {
			return node;
		}
		Node leftFoundNode = findNode(node.left, data);
		if (leftFoundNode != null) {
			return leftFoundNode;
		}
		Node rightFoundNode = findNode(node.right, data);
		if (rightFoundNode != null) {
			return rightFoundNode;
		}
		return null;
	}
	
	private boolean checkIdentical(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		if ((node1.data == node2.data) ||
			(node1 == null && node2 != null) ||
			(node2 == null && node1 != null)) {
			return false;
		}
		return checkIdentical(node1.left, node2.left) && checkIdentical(node1.right, node2.right); 
	}
	
	private class Node{
		public int data;
		public Node left;
		public Node right;
	}
}
