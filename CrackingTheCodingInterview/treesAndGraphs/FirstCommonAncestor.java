package treesAndGraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Question:
 * Find first common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure
 * Note: This is not necessarily a binary search tree
 * 
 * Important points:
 * 1. With parent link:
 *    i. Node with head start in moving:
 *       a. We can go to root form both nodes and find their heights.
 *       b. Now move node eith higher depth above till both heights are equals.
 *       c. Now move both nodes together until they are equal.
 *       d. This is best when parent link is present.
 *    ii. Marking visited true:
 *       a. Visit from one node to parent. Mark ancestors as visited.
 *       b. Visit from second node to parent, if ancestor is already visited, return ancestor.
 *    iii. Maintain hashmap of ancestor
 *       a. Visit from one node to parent. Add ancestors in hashMap.
 *       b. Visit from second node to parent, if ancestor already in hashMap, return ancestor.
 *    iv. check if in path:
 *       a. from node1, go to one level up.
 *       b. Traverse from q to root and if node1 ancestor comes in path return that ancestor.
 *       c. if not, go one more level up in node1 and repeat.
 * 2. Without parent link:
 *    i. Storing the ancestors of nodes:
 *       a. Store the ancestors of node1 and node2.
 *       b. For storing ancestors, do depth first traversal and at every level add node in list. (by adding at last)
 *       c. If in the ancestor, node is not present, remove node from list (by removing last)
 *       d. Once ancestors are stored, check size of ancestors.
 *       e. Head start the list with more elements.
 *       f. Move both elements together in list untill both reaches same node.
 *    ii. Finding ancestor by depth first traversal
 *       a. As depth first traversal at a time represent a particular node at a level. We can find lot of things: like list of nodes in same level, list of ancestors of any node.
 *       b. This is best when parent link is not present.
 */
public class FirstCommonAncestor {

	/**
	 * Time: O(logn); Space: O(1)
	 * 
	 * firstCommonAncestor(root, p, q){
	 *   if p == null or q == null
	 *     return null
	 *   pDepth = findDepth(root, p)
	 *   if pDepth = -1
	 *     return null
	 *   qDepth = findDepth(root, q)
	 *   if qDepth = -1
	 *     return null
	 *   currentP = p
	 *   currentQ = q
	 *   if qDepth > pDepth
	 *     temp = qDepth
	 *     qDepth = pDepth
	 *     pDepth = temp
	 *     tempNode = currentQ
	 *     currentQ = currentP
	 *     currentP = tempNode
	 *   while pDepth != qDepth
	 *     currentP = p.parent
	 *     pDepth--;
	 *   while currentP != null
	 *     if currentP == currentQ
	 *       return currentP
	 *     currentP = currentP.parent
	 *     currentQ = currentQ.parent
	 *   return null 
	 * findDepth(root, node):
	 *   nodeDepth = 0
	 *   current = node
	 *   while current.parent != null
	 *     current = current.parent
	 *     nodeDepth++
	 *   return current == root ? nodeDepth : -1
	 */
	public Node firstCommonAncestor_withParentLink(Node root, Node p, Node q) {
		if (p == null || q == null) {
			return null;
		}
		
		int pDepth = findDepth(root, p);
		if (pDepth == -1) {
			return null;
		}
		int qDepth = findDepth(root, q);
		if (qDepth == -1) {
			return null;
		}
		
		// Moving element with more depth till both elements depth are same.
		Node currentP = p;
		Node currentQ = q;
		if (qDepth > pDepth) {
			// Swap q with p as we want p to be greater.
			int temp = qDepth;
			qDepth = pDepth;
			pDepth = temp;
			Node tempNode = currentQ;
			currentQ = currentP;
			currentP = tempNode;
		}
		
		// Moving both elements together untill we find the ancestor
		while (currentP.parent != null) {
			if (currentP == currentQ) {
				return currentP;
			}
			currentP = currentP.parent;
			currentQ = currentQ.parent;
		}
		return null;
	}
	
	private int findDepth(Node root, Node node) {
		int nodeDepth = 0;
		Node current = node;
		while (current.parent != null) {
			current = current.parent;
			nodeDepth++;
		}
		return current == root ? nodeDepth : -1;
	}
	
	/**
	 * Time: O(n); Space: O(h) where h is height of tree.
	 * 
	 * firstCommonAncestor(root, p, q):
	 *   if root == null or p == null or q == null
	 *     return null
	 *   return findCommonAncestor(root, p, q).ancestor
	 * findCommonAncestor(node, p, q):
	 *   if node == null
	 *     return result(null, false, false)
	 *   result = result(node, node == p, node == q)
	 *   if result.containsP && result.containsQ
	 *     return result
	 *   // check left subtree
	 *   resultLeft = findCommonAncestor(node.left, p, q)
	 *   if resultLeft.containsP && result.containsQ
	 *     result.containsP = true
	 *     return result
	 *   else if resultLeft.containsQ && result.containsP
	 *     result.containsQ = true
	 *     return result
	 *   else if resultLeft.containsP && resultLeft.containsQ
	 *     return resultLeft
	 *   // check right subtree
	 *   resultRight = findCommonAncestor(node.right, p, q)
	 *   if resultRight.containsP && result.containsQ
	 *     result.containsP = true
	 *     return result
	 *   else if resultRight.containsQ && result.containsP
	 *     result.containsQ = true
	 *     return result
	 *   else if resultRight.containsP && resultRight.containsQ
	 *     return resultRight
	 *   // Compare left and right subtree
	 *   if (resultLeft.containsP && resultRight.containsQ) || (resultRight.containsP && resultLeft.containsQ)
	 *     result.containsP = true
	 *     result.containsQ = true
	 *     return result
	 *   return result
	 */
	public Node firstCommonAncestor_withoutParentLink(Node root, Node p, Node q) {
		if (root == null || p == null || q == null) {
			return null;
		}
		
		return findCommonAncestor(root, p, q).ancestor;
	}
	
	private Result findCommonAncestor(Node node, Node p, Node q) {
		if (node == null) {
			return new Result(null, false, false);
		}
		
		Result result = new Result(node, node == p, node == q);
		if (result.containsP && result.containsQ) {
			return result;
		}
		
		// Check in left subtree
		Result resultLeft = findCommonAncestor(node.left, p, q);
		if (resultLeft.containsP && result.containsQ) {
			result.containsP = true;
			return result;
		}
		else if (resultLeft.containsQ && result.containsP) {
			result.containsQ = true;
			return result;
		}
		else if (resultLeft.containsP && resultLeft.containsQ) {
			return resultLeft; 
		}
		
		// Check in right subtree
		Result resultRight = findCommonAncestor(node.right, p, q);
		if (resultRight.containsP && result.containsQ) {
			result.containsP = true;
			return result;
		}
		else if (resultRight.containsQ && result.containsP) {
			result.containsQ = true;
			return result;
		}
		else if (resultRight.containsP && resultRight.containsQ) {
			return resultRight; 
		}
		
		// Compare left and right subtree
		if ((resultLeft.containsP && resultRight.containsQ) ||
			(resultRight.containsP && resultLeft.containsQ)) {
			result.containsP = true;
			result.containsQ = true;
			return result;
		}
		
		return result;
		
	}
	
	private class Result{
		public Node ancestor;
		public boolean containsP;
		public boolean containsQ;
		
		public Result(Node ancestor, boolean containsP, boolean containsQ) {
			this.ancestor = ancestor;
			this.containsP = containsP;
			this.containsQ = containsQ;
		}
	}
	
	private class Node{
		public Node left;
		public Node right;
		public Node parent;
	}
}
