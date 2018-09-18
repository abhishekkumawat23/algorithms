package treesAndGraphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Question:
 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
 */
public class RouteBetweenNodes {

	/**
	 * Time: O(n + e); Space: O(m) where m is longest path from start to any node.
	 * 
	 * Important points:
	 * 1. Wrapper of this method should ensure that starting node visited is set to true.
	 * 
	 * search(start, end):
	 *   if start == end
	 *     return true
	 *   visited[start] = true
	 *   return searchHelper(start, end, visited)
	 * searchHelper(start, end):
	 *   if start == null
	 *     return false
	 *   for adjacencyNode in start.getAdjacencies
	 *     if !visited[adjacencyNode]
	 *       if adjacencyNode == end
	 *         return true
	 *       visited[adjacencyNode] = true
	 *       found = search(adjacencyNode, end, visited)
	 *       if found
	 *         return true
	 *   return false
	 *     
	 */
	public boolean search_dfs(Node start, Node end){
		if (start == end) {
			return true;
		}
		HashSet<Node> visited = new HashSet<Node>();
		return searchHelper_dfs(start, end, visited);
	}
	
	private boolean searchHelper_dfs(Node start, Node end, HashSet<Node> visited) {
		if (start == null) {
			return false;
		}
		for (Node adjacencyNode: start.getAdjacencies()) {
			boolean found = false;
			if (!visited.contains(adjacencyNode)) {
				if (adjacencyNode == end) {
					return true;
				}
				visited.add(adjacencyNode);
				found = searchHelper_dfs(adjacencyNode, end, visited);
				if (found) return true;
			}
		}
		return false;
	}
	
	/**
	 * Time: O(n + e); Space: O(n) for visited + O(k*m) for stack where k is atmost adjacencies per node and m is longest distance from start to any node. 
	 * 
	 * Pseudo code:
	 * search(start, end):
	 *   if start == end
	 *     return true
	 *   visited[start] = true
	 *   stack.push(start)
	 *   while stack is not empty
	 *     current = stack.pop
	 *     for adjacencyNode in current.getAdjacencies
	 *       if adjacencyNode != null and !visited[adjacencyNode]
	 *         if adjacencyNode == end
	 *           return true
	 *         visited[adjacencyNode] = true
	 *         stack.push(adjacencyNode)
	 *   return false
	 */
	public boolean search_dfs_iter(Node start, Node end) {
		if (start == end) {
			return true;
		}
		HashSet<Node> visited = new HashSet<Node>();
		Stack<Node> stack = new Stack<Node>();
		stack.push(start);
		Node current = null;
		while (!stack.isEmpty()) {
			current = stack.pop();
			for (Node adjacencyNode: current.getAdjacencies()) {
				if (adjacencyNode != null && !visited.contains(adjacencyNode)) {
					if (adjacencyNode == end) {
						return true;
					}
					visited.add(adjacencyNode);
					stack.push(adjacencyNode);
				}
			}
		}
		return false;
	}
	
	/**
	 * Time: (n + e); Space: O(n) as queue might have all nodes at one go in queue
	 * 
	 * Important points:
	 * 1. As this problem can be done using both BFS and DFS, we must ak interviewer whether there is any relation in graph.
	 * 2. Example if the end is some neighbor of start, then BFS makes more sense as it will find it quickly.
	 * 
	 * search(start, end):
	 *   if start == end
	 *     return true
	 *   queue.add(start)
	 *   start.visited = true
	 *   while queue is not empty
	 *     current = queue.poll
	 *     for adjacencyNode in current.getAdjacencies
	 *       if adjacencyNode != null and !visited[adjacencyNode]
	 *         if adjacencyNode == end
	 *           return true
	 *         visited[adjacencyNode] = true
	 *         queue.add(adjacencyNode)
	 *   return false
	 */
	public boolean search_bfs(Node start, Node end) {
		if (start == end) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		HashSet<Node> visited = new HashSet<Node>();
		Node current = null;
		queue.add(start);
		while (!queue.isEmpty()) {
			current = queue.poll();
			for (Node adjacencyNode: current.getAdjacencies()) {
				if (adjacencyNode != null && !visited.contains(adjacencyNode)) {
					if (adjacencyNode ==  end) {
						return true;
					}
					visited.add(adjacencyNode);
					queue.add(adjacencyNode);
				}
			}
		}
		return false;
	}

	private class Node{
		private List<Node> adjacencies = new ArrayList<Node>();
		
		public List<Node> getAdjacencies(){
			return adjacencies;
		}
	}
}
