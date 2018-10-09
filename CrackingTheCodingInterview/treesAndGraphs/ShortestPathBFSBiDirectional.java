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
 * Find shortest path from s to t using bidirectional breadth first search - For undirected unweighted graph
 * 
 * Important points:
 * 1. Use Bidirectional BFS when graph is undirected and unweighted.
 * 2. Algo:
 *    a. Store `from` node in prcocessNextA and store `to` node in processNextB.
 *    b. Store `from` in previousA and `to` in previousB
 *    c. Set previousA for `from` node as null and previousB for `to` node as null. 
 *    c. Set visitedA of `from` node as true and visitedB of `to` node as true.
 *    d. Poll from queueA and queueB and name lets say currentA and currentB
 *    e. Add adjancies of currentA and currentB in respective queues if not yet visited.
 *    f. Check in visitedA and visitedB if some node is there for which both visits is true.
 *    g. Loop queueA and queueB until they are empty or we reach the destination nodes.
 *    h. If currentA is toNode or currentB is fromNode, pick shortest path from previousA and previousB
 * 3. If node has atmost k adjacent nodes and distance between from and to is d, then time to find shortest path is O(2k^(d/2)). So its better than finding shortest path using BFS as that takes O(k^d)
 */
public class ShortestPathBFSBiDirectional {
	Map<String, Node> adjList = new HashMap<String, Node>();

	/**
	 * Time: O(n + e)
	 * For sparse graph, e ~= n or e ~= 0; so time: O(n)
	 * For dense graph, e ~= n(n+1)/2,; so time: O(e)
	 * 
	 * Important points:
	 * 1. Its better than BFS becuase in BFS time is O(k^d) but in bi-directional BFS, time is 2*k^(d/2)
	 * 
	 * Pseudo code:
	 * shortestPath(from, to)
	 *   fromNode = adjList.get(from)
	 *   toNode = adjList.get(to)
	 *   processNextA.add(fromNode)
	 *   previousA.add(fromNode)
	 *   visitedA.add(fromNode)
	 *   processNextB.add(toNode)
	 *   previousB.add(toNode)
	 *   visitedB.add(toNode)
	 *   while processNextA is not empty and processNextB is not empty
	 *     currentA = processNextA.poll
	 *     currentB = processNextB.poll
	 *     for adjacencyNode in currentA.adjacencies
	 *       if visitedB[adjacencyNode] and visitedA[adjacencyNode]
	 *         intersectingNode = adjacencyNode
	 *         break
	 *       else if !visitedA[adjacencyNode]
	 *         visitedA[adjacencyNode] = true
	 *         previousA[adjacencyNode] = currentA
	 *         processNextA[adjacencyNode] = adjacencyNode
	 *     for adjacencyNode in currentB.adjacencies
	 *       if visitedB[adjacencyNode] and visitedA[adjacencyNode]
	 *         intersectingNode = adjacencyNode
	 *         break
	 *       else if !visitedB[adjacencyNode]
	 *         visitedB[adjacencyNode] = true
	 *         previousB[adjacencyNode] = currentB
	 *         processNextB[adjacencyNode] = adjacencyNode
	 *   if intersectingNode == null
	 *     return null
	 *   previousNode = intersectingNode
	 *   while previousNode != null
	 *     shortestPath.addFirst(previousNode)
	 *     previousNode = previousA[previousNode]
	 *   previousNode = previousB[intersectingNode] // Doing so as not to repeat intersection node 2 times
	 *   while previousNode != null
	 *     shortestPath.addLast(previousNode)
	 *     previousNode = previousB[previousNode]
	 *   return shortestPath
	 */
	public List<Node> shortestPath(String from, String to){
		Queue<Node> processNextA = new LinkedList<Node>();
		Map<String, Node> previousA = new HashMap<String, Node>();
		HashSet<String> visitedA = new HashSet<String>();
		Node fromNode = adjList.get(from);
		processNextA.add(fromNode);
		previousA.put(fromNode.name, null);
		visitedA.add(fromNode.name);
		
		Queue<Node> processNextB = new LinkedList<Node>();
		Map<String, Node> previousB = new HashMap<String, Node>();
		HashSet<String> visitedB = new HashSet<String>();
		Node toNode = adjList.get(to);
		processNextB.add(toNode);
		previousB.put(toNode.name, null);
		visitedB.add(toNode.name);
		
		Node intersectingNode = null;
		while (!processNextA.isEmpty() && !processNextB.isEmpty()) {
			Node currentA = processNextA.poll();
			for (Node adjacencyNode: currentA.adjacencies) {
				if (visitedA.contains(adjacencyNode.name) && visitedB.contains(adjacencyNode.name)) {
					intersectingNode = adjacencyNode;
					break;
				}
				else if(!visitedA.contains(adjacencyNode.name)) {
					visitedA.add(adjacencyNode.name);
					previousA.put(adjacencyNode.name, currentA);
					processNextA.add(adjacencyNode);
				}
			}
			
			Node currentB = processNextB.poll();
			for (Node adjacencyNode: currentB.adjacencies) {
				if (visitedA.contains(adjacencyNode.name) && visitedB.contains(adjacencyNode.name)) {
					intersectingNode = adjacencyNode;
					break;
				}
				else if(!visitedB.contains(adjacencyNode.name)) {
					visitedB.add(adjacencyNode.name);
					previousB.put(adjacencyNode.name, currentB);
					processNextB.add(adjacencyNode);
				}
			}
		}
		
		if (intersectingNode == null) {
			return null;
		}
		
		LinkedList<Node> shortestPath = new LinkedList<Node>();
		Node previousNode = intersectingNode;
		while (previousNode != null) {
			shortestPath.addFirst(previousNode);
			previousNode = previousA.get(previousNode.name);
		}
		
		previousNode = previousB.get(intersectingNode.name); // we dont want to store intersecting node 2 times in shortestPath list.
		while (previousNode != null) {
			shortestPath.addLast(previousNode);
			previousNode = previousB.get(previousNode.name);
		}
		
		return shortestPath;
	}
	
	private class Node{
		public String name;
		public ArrayList<Node> adjacencies = new ArrayList<Node>();
	}
}
