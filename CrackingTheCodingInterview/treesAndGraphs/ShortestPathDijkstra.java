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
 * Find shortest path using dijkstra search - For directed/undirected and weighted graph
 * 
 * Important points:
 * 1. 
 */
public class ShortestPathDijkstra {
	Map<String, Node> adjList = new HashMap<String, Node>();
	
	/**
	 * Time: O(e*timeForUpdate + n*(timeForInsert+timeForDeleteMin))
	 * 
	 * Important points:
	 * 1. Priority queue can be implementest using array or heap.
	 * 2. In case of array, Time: O(e + n^2). This is assuming we know the index of node being updated.
	 * 3. In case of heap, Time: (elogn + nlogn). This is assuming we know the index of node being updated.
	 * 4. Knowing the index of node being updated is difficult. So we either:
	 *    a. take O(n) time to find node to update
	 *    b. Add duplicate elements in priority queues instead of updating it.
	 *    c. Use balanced bianry search tree (not peffectly balanced binary tree are not that efficient in compare to heap)
	 * 5. For sparse graph, e is n or 0.
	 * 6. For dense graph, e is n^2. 
	 * 
	 * Pseudo code:
	 * shortestPath(from, to):
	 *   fromNode = adjList.get(from)
	 *   toNode = adjList.get(to)
	 *   fromNode.distance = 0 // Rest all nodes distance is integer max value
	 *   processNextPrior.add(fromNode)
	 *   previous[fromNode] = null
	 *   addedInQueue.add(fromNode)
	 *   while processNextPrior is not empty
	 *     current = processNextPrior.poll // Get min value
	 *     visited.add(current)
	 *     if current == toNode
	 *       break;
	 *     for edge in current.edges
	 *       if !visited[edge.toNode]
	 *         if edge.weight + current.distance < edge.toNode.distance
	 *           edge.toNode.distance = edge.weight + current.distance
	 *           addedInQueue.add(edge.toNode)
	 *           processNextPrior.updateOrInsert(edge.toNode) // There is no update op in priority queue or heap.
	 *           previous[edge.toNode] = current
	 *         if !addedInQueue(edge.toNode)
	 *           addedInQueue.add(edge.toNode)
	 *           processNextPrior.add(edge.toNode)
	 *   if current != toNode
	 *     return null
	 *   previousNode = toNode
	 *   while previousNode != null
	 *     shortestPath.addFirst(previousNode)
	 *     previousNode = previous[previousNode]
	 *   return shortestPath
	 */
	public List<Node> shortestPath(String from, String to){
		Node fromNode = adjList.get(from);
		Node toNode = adjList.get(to);
		fromNode.distance = 0;
		
		Queue<Node> processNextPrior = new LinkedList<Node>();
		Map<String, Node> previous = new HashMap<String, Node>();
		HashSet<String> addedInQueue = new HashSet<String>();
		HashSet<String> visited = new HashSet<String>();
		
		processNextPrior.add(fromNode);
		previous.put(fromNode.name, null);
		addedInQueue.add(fromNode.name);
		
		Node current = null;
		while (!processNextPrior.isEmpty()) {
			current = processNextPrior.poll();
			visited.add(current.name);
			if (current == toNode) {
				break;
			}
			for (Edge edge: current.edges) {
				if (!visited.contains(edge.toNode.name)) {
					if (edge.weight + current.distance < edge.toNode.distance) {
						edge.toNode.distance = edge.weight + current.distance;
						addedInQueue.add(edge.toNode.name);
						processNextPrior.add(edge.toNode); // Here we inserting instead of updateOrInsert and thus having duplicates. We are ok with this as for update/delete we need to find the node first.
					}
					if (!addedInQueue.contains(edge.toNode.name)) {
						addedInQueue.add(edge.toNode.name);
						processNextPrior.add(edge.toNode);
					}
				}
			}
		}
		if (current != toNode) {
			return null;
		}
		Node previousNode = toNode;
		LinkedList<Node> shortestPath = new LinkedList<Node>();
		while (previousNode != null) {
			shortestPath.addFirst(previousNode);
			previousNode = previous.get(previousNode.name);
		}
		return shortestPath;
	}
	
	private class Node{
		public String name;
		public int distance = Integer.MAX_VALUE;
		public ArrayList<Edge> edges = new ArrayList<Edge>();
	}
	
	private class Edge{
		public int weight;
		public Node toNode;
	}
}
