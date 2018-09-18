package treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Question:
 * Implement breadth first search
 * 
 * Important points:
 * 1. Breadth search is used to:
 *    a. find neighbors of a node OR
 *    b. finding the shortest path between two nodes. - Advanced veriant of breadth first which is bidirectional search is used to find shortest path.
 * 2. Depth search is used to find if a path exists between two nodes or not.
 * 3. Remember to use Bidirectional search instead of breadth first search to find the shortest path between two nodes (as it has less time complexity)
 */
public class BreadthFirstSearch {

	/**
	 * Time: O(n + e); Space: O(n)
	 * n is no. of connected nodes
	 * e is no. of edges
	 * 
	 * Importan points:
	 * 1. We are visting every node and from node we are visiting edges of it (non visiting edges only). So if total no of edges are e and no. of nodes are n, then time complexity is O(n+e)
	 * 2. For densely populated graph, e ~= n(n+1)/2 which is way greater than n, so time complexity is O(e)
	 * 3. For spasely populated graph e is near to n (for connected graph) and 0 for (disconnected graph). So time compelxity is O(n)
	 * 4. Here the implementation is for one node only. In a graph there can be disconnected nodes as well. So in that case, we go through all nodes fo adjList. 
	 * 
	 * Pseudo code:
	 * breadthFirst(node):
	 *   node.visited = true
	 *   queue.add(node)
	 *   while queue is not empty
	 *     current = queue.poll
	 *     print current
	 *     for adjacencyNode in current.adjacencies
	 *       if !adjacencyNode.visited
	 *         adjacencyNode.visited = true
	 *         queue.add(adjacencyNode)
	 */
	public void breadthFirst(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		node.visited = true;
		queue.add(node);
		Node current = null;
		while (!queue.isEmpty()) {
			current = queue.poll();
			System.out.println(current);
			for (Node adjacencyNode : current.adjacencies) {
				if (!adjacencyNode.visited) {
					adjacencyNode.visited = true;
					queue.add(adjacencyNode);
				}
			}
		}
	}
	
	private class Node{
		public boolean visited = false;
		public ArrayList<Node> adjacencies = new ArrayList<Node>();
	}
}
