package treesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Question:
 * Find shortest path from s to t using breadth first search - For directed/undirected unweighted graph.
 * 
 * Important points:
 * 1. Use breadth first search for shortest path only when graph is directed/undirected and unweighted.
 * 2. Algo:
 *    a. Put `from` node in processNext queue and set its visited to true and set its previous as null.
 *    b. Remove node from processNext.
 *    c. Mark all of node's adjacencies visited true and put it in processNext and also set all adjacencie's previous as this node.
 *    d. Repeat b and c untill processNext is empty or node matches `to` node.
 *    e. If node matches `to` node, then get the shortest path using previous list. Start from `to` node previous.
 *    f. Return null if node doesn't match `to` node.
 */
public class ShortestPathBFS {
	Map<String, Node> adjList = new HashMap<String, Node>();

	/**
	 * Time: O(n + e); Space: O(n)
	 * n is no. of nodes
	 * e is no of edges
	 * 
	 * Important points:
	 * 1. For densely populated graph, e ~= n(n+1)/2 So Time: O(e)
	 * 2. For sparsely populated graph, e ~= 0 for disconnected graph and e ~= n for connected graph, so Time: O(n)
	 * 3. If node has atmost k adjacent nodes and distance between from and to is d, then Time is (k^d)
	 * 
	 * Pseudo code:
	 * shortestPath(from, to):
	 *   fromNode = adjList.get(from)
	 *   toNode = adjList.get(to)
	 *   visited[fromNode] = true
	 *   previous[fromNode] = null
	 *   processNext.add(fromNode)
	 *   while processNext is not empty and current != toNode
	 *     current = processNext.poll
	 *     for adjacencyNode in adjacencies
	 *       if !visited[adjacencyNode]
	 *         previous[adjacencyNode] = current
	 *         visited[adjacencyNode] = true
	 *         processNext.add(adjacencyNode)
	 *   if current != toNode
	 *     return null
	 *   previousNode = toNode
	 *   while previousNode != null
	 *     shortestPath.addFirst(previousNode)
	 *     previousNode = previous[previousNode]
	 *   return shortestPath
	 */
	public List<Node> shortestPath(String from, String to) {
		HashSet<String> visited = new HashSet<String>();
		Map<String, Node> previous = new HashMap<String, Node>();
		Queue<Node> processNext = new LinkedList<Node>();
		
		Node fromNode = adjList.get(from);
		Node toNode = adjList.get(to);
		visited.add(fromNode.name);
		previous.put(fromNode.name, null);
		processNext.add(fromNode);
		
		Node current = null;
		while (!processNext.isEmpty() && current != toNode) {
			current = processNext.poll();
			for (Node adjacencyNode: current.adjacencies) {
				if (!visited.contains(adjacencyNode.name)) {
					previous.put(adjacencyNode.name, current);
					visited.add(adjacencyNode.name);
					processNext.add(adjacencyNode);
				}
			}
		}
		
		if (current != toNode) {
			return null; // toNode not found.
		}
		
		LinkedList<Node> shortestPath = new LinkedList<Node>();
		Node previousNode = toNode;
		while (previousNode != null) {
			shortestPath.addFirst(previousNode);
			previousNode = previous.get(previousNode.name);
		}
		return shortestPath;
	}
	
	private class Node{
		public String name;
		public ArrayList<Node> adjacencies = new ArrayList<Node>();
	}
}
