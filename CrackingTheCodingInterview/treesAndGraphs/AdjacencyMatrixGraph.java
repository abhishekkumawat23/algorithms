package treesAndGraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Question:
 * Implement adjacency matrix based directed graph.
 * 
 * Important points:
 * 1. We are implementing directed graph here.
 * 2. In case of undirected graph, matrix is symmetric as both i to j and j to i edges exist.
 * 3. This matrix representation of graph is very space comsuming.
 * 4. Here we are taking name as input. So for finding the node from name in list takes O(n).
 * 5. If we take index as input, then almost all operations will be O(1) except insert and remove node.
 * 6. If we dont have to store nodes but only integers. Then no need to creates nodes list. Simply use adjMatrix with index as nodes and value as edge presence or edge weights.
 */
public class AdjacencyMatrixGraph {
	List<Node> nodes = new ArrayList<Node>();
	List<ArrayList<Boolean>> adjMatrix = new ArrayList<ArrayList<Boolean>>();

	/**
	 * Time: O(n); Space:O(1)
	 * O(n) for finding node if already exists. Also O(n) for adding in adjancey matrix.
	 * 
	 * Pseudo code:
	 * addNode(name):
	 *   for node in nodes
	 *     if node.name == name
	 *       return;
	 *   nodes.add(node(name))
	 *   adjancies = new arrayList
	 *   adjMatrix.add(adjancies)
	 *   for i = 1 to adjMatrix.size
	 *     adjancies.add(false) 
	 */
	public void addNode(String name) {
		// Find if already exists. O(n)
		for (Node node : nodes) {
			if (node.name == name) {
				return;
			}
		}
		// Add in nodes
		nodes.add(new Node(name));
		// Add in adjancey matrix
		ArrayList<Boolean> adjacencies = new ArrayList<Boolean>();
		adjMatrix.add(adjacencies);
		for (int i = 1; i <= adjacencies.size(); i++) {
			adjacencies.add(false);
		}
	}
	
	/**
	 * Time: O(n); Space:O(1)
	 * O(n) for finding node if already exists. Also O(n) for removing from adjancey matrix.
	 * 
	 * Pseudo code:
	 * removeNode(name):
	 *   index = -1
	 *   for i = 0 to nodes.size-1
	 *     if nodes.get(i).name == name
	 *       index = i;
	 *   if index == -1
	 *     return
	 *   nodes.remove(index)
	 *   adjMatrix.remove(index)
	 */
	public void removeNode(String name) {
		int index = -1;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).name == name) {
				index = i;
			}
		}
		if (index == -1) {
			return;
		}
		nodes.remove(index);
		adjMatrix.remove(index);
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Pseudo code:
	 * hasNode(name):
	 *   for node in nodes
	 *     if node.name == name
	 *       return true;
	 *   return false
	 */
	public boolean hasNode(String name) {
		for (Node node : nodes) {
			if (node.name == name) {
				return true;
			}
		}
		return false;
	}
	
	public int getNodeIndex(String name) {
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i); 
			if (node.name == name) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Time: O(n) to find if node exists. O(1) to add edge; Space: O(1)
	 * 
	 * Pseudo code:
	 * addEdge(from, to):
	 *   fromIndex = getNodeIndex(from)
	 *   toIndex = getNodeIndex(to)
	 *   if (fromIndex == -1 or toIndex == -1)
	 *     return
	 *   adjMatrix.get(fromIndex).set(toIndex, true)
	 */
	public void addEdge(String from, String to) {
		int fromIndex = getNodeIndex(from);
		int toIndex = getNodeIndex(to);
		if (fromIndex == -1 || toIndex == -1) {
			return;
		}
		adjMatrix.get(fromIndex).set(toIndex, true);
	}
	
	/**
	 * Time: O(n) to find if node exists. O(1) to remove edge; Space: O(1)
	 * 
	 * Pseudo code:
	 * removeEdge(from, to):
	 *   fromIndex = getNodeIndex(from)
	 *   toIndex = getNodeIndex(to)
	 *   if (fromIndex == -1 or toIndex == -1)
	 *     return
	 *   adjMatrix.get(fromIndex).set(toIndex, false)
	 */
	public void removeEdge(String from, String to) {
		int fromIndex = getNodeIndex(from);
		int toIndex = getNodeIndex(to);
		if (fromIndex == -1 || toIndex == -1) {
			return;
		}
		adjMatrix.get(fromIndex).set(toIndex, false);
	}
	
	/**
	 * Time: O(n) to find if node exists. O(1) to get edge; Space: O(1)
	 * 
	 * Pseudo code:
	 * hasEdge(from, to):
	 *   fromIndex = getNodeIndex(from)
	 *   toIndex = getNodeIndex(to)
	 *   if (fromIndex == -1 or toIndex == -1)
	 *     return false
	 *   return adjMatrix.get(fromIndex).get(toIndex)
	 */
	public boolean hasEdge(String from, String to) {
		int fromIndex = getNodeIndex(from);
		int toIndex = getNodeIndex(to);
		if (fromIndex == -1 || toIndex == -1) {
			return false;
		}
		return adjMatrix.get(fromIndex).get(toIndex);
	}
	
	/**
	 * Time: O(n) to find node. O(d) to get adjacent nodes; Space: O(d) for storing adjacent nodes.
	 * Pseudo code:
	 * getAdjacentNodes(name):
	 *   index = getNodeIndex(name)
	 *   for i = 0 to adjMatrix.size-1
	 *     if adjMatrix.get(index).get(i) == true
	 *       result.add(nodes.get(i))
	 *   return result
	 */
	public List<Node> getAdjacentNodes(String name){
		List<Node> result = new ArrayList<Node>();
		int index = getNodeIndex(name);
		for (int i = 0; i < adjMatrix.size(); i++) {
			if (adjMatrix.get(index).get(i)) {
				result.add(nodes.get(i));
			}
		}
		return result;
	}
	
	private class Node {
		public String name;
		
		public Node(String name) {
			this.name = name;
		}
	}
}
