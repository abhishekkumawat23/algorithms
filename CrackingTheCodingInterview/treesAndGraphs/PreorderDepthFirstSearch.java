package treesAndGraphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Question:
 * Implement preorder depth first search
 * 
 * Important points:
 * 1. Depth first search is used to find if a path exists between 2 nodes or not.
 * 2. Breadth first search is used to find the shortest path between 2 nodes OR to find the neighbors of a node.
 * 3. TODO: we are not implementing postorder and inorder depth first searches. We will if required.
 */
public class PreorderDepthFirstSearch {
	
	/**
	 * Time: O(n + e); Space: O(m) where m is longest path from the node to any node.
	 * n is no of connected nodes
	 * e is no if edges
	 * 
	 * Important points:
	 * 1. Wrapper of this recursive method should ensure that starting node's visited is set to true. 
	 * 
	 * Pseudo code:
	 * preorder(node):
	 *   if node == null return
	 *   print node
	 *   for adjacencyNode in node.adjacencies
	 *     if !node.visited
	 *       adjacencyNode.visited = true
	 *       preorder(adjacencyNode)
	 */
	public void preorder(Node node) {
		if (node == null) {
			return;
		}
		System.out.println(node);
		for (Node adjacencyNode: node.adjacencies) {
			if (!node.visited) {
				adjacencyNode.visited = true;
				preorder(adjacencyNode);
			}
		}
	}
	
	/**
	 * Time: O(n); Space:O(d*m) where d is degree of each node and m is longest path from node to any node.
	 * Pseudo code:
	 * preorder(node)
	 *   if node == null
	 *     return
	 *   node.visited = true
	 *   stack.push(node)
	 *   while stack is not empty
	 *     current = stack.pop
	 *     print current
	 *     for i = current.adjacencies.size-1 to 0
	 *       adjacencyNode = current.adjacencies.get(i)
	 *       if !adjacencyNode.visited
	 *         adjacencyNode.visited = true
	 *         stack.push(adjacencyNode)
	 */
	public void preorder_iterative(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		node.visited = true;
		stack.push(node);
		Node current = null;
		while (!stack.isEmpty()) {
			current = stack.pop();
			System.out.println(current);
			for (int i = current.adjacencies.size()-1; i >= 0; i--) {
				Node adjacencyNode = current.adjacencies.get(i);
				if (!adjacencyNode.visited) {
					adjacencyNode.visited = true;
					stack.push(adjacencyNode);
				}
			}
		}
	}
	
	private class Node{
		boolean visited = false;
		public List<Node> adjacencies = new ArrayList<Node>();
	}
}
