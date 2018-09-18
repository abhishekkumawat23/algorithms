package treesAndGraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Question:
 * Implement edge list graph
 */
public class EdgeListGraph {
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	
	/**
	 * Time: O(n) for checking if edge already exists; Space: O(1)
	 * 
	 * Pseudo code:
	 * addEdge(from, to):
	 *   // Check if already exists
	 *   for i = 0 to edgeList.size-1
	 *     edge = edgeList.get(i) 
	 *     if edge.from.name == from and edge.to.name == to
	 *       return
	 *   // Add edge if not exists
	 *   edgeList.add(edge(node(from), node(to)))
	 */
	public void addEdge(String from, String to) {
		// Check if already exists
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			if (edge.from.name == from && edge.to.name == to) {
				return;
			}
		}
		// Add edge if not exists
		edgeList.add(new Edge(new Node(from), new Node(to)));
	}
	
	/**
	 * Time: O(n) for removal; Space: O(1)
	 * 
	 * Pseudo code:
	 * removeEdge(from, to):
	 *   for i = 0 to edgeList.size-1
	 *     edge = edgeList.get(i) 
	 *     if edge.from.name == from and edge.to.name == to
	 *       edgeList.remove(i)
	 *       return
	 */
	public void removeEdge(String from, String to) {
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			if (edge.from.name == from && edge.to.name == to) {
				edgeList.remove(i);
				return;
			}
		}
	}
	
	/**
	 * Time: O(n); Space: O(n)
	 * 
	 * Pseudo code:
	 * removeNode(name):
	 *   for edge in edgeList
	 *     if edge.from.name != name and edge.to.name != name
	 *       result.add(edge)
	 *   return result
	 */
	public List<Edge> removeNode(String name) {
		ArrayList<Edge> result = new ArrayList<Edge>();
		for(Edge edge : edgeList) {
			if (edge.from.name != name && edge.to.name != name) {
				result.add(edge);
			}
		}
		return result;
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Pseudo code:
	 * hasEdge(from, to):
	 *   for i = 0 to edgeList.size-1
	 *     edge = edgeList.get(i) 
	 *     if edge.from.name == from and edge.to.name == to
	 *       return true;
	 *   return false
	 */
	public boolean hasEdge(String from, String to) {
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			if (edge.from.name == from && edge.to.name == to) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Pseudo code:
	 * hasNode(name):
	 *   for i = 0 to edgeList.size-1
	 *     edge = edgeList.get(i) 
	 *     if edge.from.name == name or edge.to.name == name
	 *       return true;
	 *   return false
	 */
	public boolean hasNode(String name) {
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			if (edge.from.name == name || edge.to.name == name) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Time: O(n); Space: O(n) to store the adjacent nodes
	 * 
	 * Pseudo code:
	 * getAdjacentNodes(name):
	 *   for i = 0 to edgeList.size-1
	 *     edge = edgeList.get(i) 
	 *     if edge.from.name == name
	 *       adjacentNodes.add(edge.to)
	 *   return adjacentNodes
	 */
	public List<Node> getAdjanentNodes(String name) {
		List<Node> adjacentNodes = new ArrayList<Node>();
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			if (edge.from.name == name) {
				adjacentNodes.add(edge.to);
			}
		}
		return adjacentNodes;
	}
	
	/**
	 * Time: O(n); Space: O(n) to store the edges.
	 * 
	 * Pseudo code:
	 * getEdges(name):
	 *   for i = 0 to edgeList.size-1
	 *     edge = edgeList.get(i) 
	 *     if edge.from.name == name
	 *       edges.add(edge)
	 *   return edges
	 */
	public List<Edge> getEdges(String name){
		List<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			if (edge.from.name == name) {
				edges.add(edge);
			}
		}
		return edges;
	}
	
	private class Edge{
		public Node from;
		public Node to;
		
		public Edge(Node from, Node to) {
			this.from = from;
			this.to = to;
		}
	}
	
	private class Node {
		public String name;
		
		public Node(String name) {
			this.name = name;
		}
	}
}
