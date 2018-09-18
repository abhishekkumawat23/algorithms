package treesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Question:
 * Implement adjancey list based directed graph.
 * 
 * Important points:
 * 1. Here we are implementing directed graph.
 * 2. We are storing map of nodes instead of list of nodes as we need faster lookup.
 * 3. Adjancies of a node is stored as treeset instead of list for faster lookup.
 * 4. If adjnacies is a list, then insertion takes O(1) time but search takes O(logn).
 * 5. But when adjancies is a balanced tree like treeset, then insertion is O(logn) and search is O(logn). Remember time will be logn only when node implements comparable. otherwise its O(n).
 * 6. Treeset uses red black tree internally.
 */
public class AdjacencyListGraph {
	private Map<String, Node> adjList = new HashMap<String, Node>();

	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * addNode(name):
	 *   if !adjList.containsKey(name)
	 *     adjList.put(name, node(name))
	 */
	public void addNode(String name) {
		if (!adjList.containsKey(name)){
			adjList.put(name, new Node(name));	
		}
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * removeNode(name)
	 *   if adjList.containsKey(name)
	 *     adjList.remove(name)
	 */
	public void removeNode(String name) {
		if (adjList.containsKey(name)){
			adjList.remove(name);
		}
	}
	
	/**
	 * Time: O(log(d)) for finding edge in treeset; Space: O(1)
	 * d is degree of from node. i.e. no. of edges.
	 * 
	 * Important points:
	 * 1. Here as Node is comparable, so time taken is O(logd)
	 * 2. If node is not comparable, then time will be O(d)
	 * 3. max value of d is n-1 where n is no. of nodes.
	 * 
	 * Pseudo code:
	 * addEdge(from, to):
	 *   if !adjList.containsKey(from)
	 *     addNode(from)
	 *   if !adjList.containsKey(to)
	 *     addNode(to)
	 *   fromNode = adjList.get(from)
	 *   toNode = adjList.get(to)
	 *   if !fromNode.adjancies.contains(toNode)
	 *     fromNode.adjancies.add(toNode)
	 */
	public void addEdge(String from, String to) {
		if (!adjList.containsKey(from)) {
			addNode(from);
		}
		if (!adjList.containsKey(to)) {
			addNode(to);
		}
		Node fromNode = adjList.get(from);
		Node toNode = adjList.get(to);
		if (!fromNode.adjacencies.contains(toNode)) {
			fromNode.adjacencies.add(toNode);
		}
	}
	
	/**
	 * Time: O(logd); Space: O(1)
	 * 
	 * Important points:
	 * 1. O(logd) becuase node is comparable in treeset. If not, time will be O(d).
	 * 2. max value of d is n-1
	 * 
	 * Pseudo code:
	 * removeEdge(from, to)
	 *   if !adjList.containsKey(from) or !adjList.containsKey(from)
	 *     return
	 *   fromNode = adjList.get(from)
	 *   toNode = adjList.get(to)
	 *   fromNode.adjancies.remove(toNode)
	 */
	public void removeEdge(String from, String to) {
		if (!adjList.containsKey(from) || !adjList.containsKey(from)) {
			return;
		}
		Node fromNode = adjList.get(from);
		Node toNode = adjList.get(to);
		fromNode.adjacencies.remove(toNode);
	}
	
	/**
	 * Time: O(logd); Space: O(1)
	 * 
	 * Important points:
	 * 1. O(logd) becuase node is comparable in treeset. If not, time will be O(d)
	 * 
	 * Pseudo code:
	 * hasEdge(from, to)
	 *   if !adjList.containsKey(from) or !adjList.containsKey(from)
	 *     return false
	 *   fromNode = adjList.get(from)
	 *   toNode = adjList.get(to)
	 *   return fromNode.adjancies.contains(toNode)
	 */
	public boolean hasEdge (String from, String to) {
		if (!adjList.containsKey(from) || !adjList.containsKey(from)) {
			return false;
		}
		Node fromNode = adjList.get(from);
		Node toNode = adjList.get(to);
		return fromNode.adjacencies.contains(toNode);
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * hasNode(name):
	 *   return adjList.containsKey(name)
	 */
	public boolean hasNode(String name) {
		return adjList.containsKey(name);
	}
	
	/**
	 * Time: O(d); Space: O(d) for result + O(logd) for traversal
	 * d is degree of node
	 * 
	 * Important points:
	 * 1. Here we are doing tree traversal which takes O(logd) space (both recursive and iterative depth traversal takes logd space) 
	 * 
	 * getAdjacentNodes(name)
	 *   if !adjList.containsKey(name)
	 *     return null
	 *   node = adjList.get(name)
	 *   for adjacentNode in node.adjancies
	 *     adjacentNodes.add(adjacentNode)
	 *   return adjacentNodes
	 */
	public List<Node> getAdjacentNodes(String name){
		if (!adjList.containsKey(name)) {
			return null;
		}
		List<Node> result = new ArrayList<Node>();
		Node node = adjList.get(name);
		for (Node adjacentNode : node.adjacencies) {
			result.add(adjacentNode);
		}
		return result;
	}
	
	private class Node implements Comparable<Node> {
		public String name;
		public TreeSet<Node> adjacencies;
		
		public Node(String name) {
			this.name = name;
		}

		@Override
		public int compareTo(Node node2) {
			return this.name.compareTo(node2.name);
		}
	}
}
