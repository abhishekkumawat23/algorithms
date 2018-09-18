package treesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Question:
 * Implement topological sort.
 * 
 * Important points:
 * 1. A topological sort is way of ordering the list of nodes such that if (a,b) is an edge in the graph, then a will appear before b in the list.
 * 2. If there are cycles in graph, topological sort doesn't make any sense.
 * 3. If graph is not directed, topological sort doesn't make any sense.
 * 4. In this sort:
 *    a. we are using an inbound count map. We are saving each node inbound count in that map.
 *    b. Now we store those nodes in processNext queue which has inbound count as 0 i..e they are root node.
 *    c. For each node in processNext, we decrease the inbound count of nodes which are adjacent to this node.
 *    d. Store the node from processNext in result
 *    e. Fill the processNext queue again with nodes which has inbound count as 0.
 *    f. When processNext is empty, check if result size is equal to adjList size. If not, graph has cycles or was undirected.
 */
public class TopologicalSort {
    List<Node> adjList = new ArrayList<Node>(); 
	
    /**
     * Time: O(n + e); Space: O(n) for queue when all nodes are root nodes + O(n) for inboundCount
     * 
     * Pseudo code:
     * topologicalSort():
     *   setInboundCount()
     *   updateProcessNextQueue()
     *   while processNext is not empty
     *     node = processNext.poll
     *     decreaseInboundCount(node)
     *     result.add(node)
     *     updateProcessNextQueue()
     *   return result.size == adjList.size ? result : null 
     * updateProcessNextQueue()
     *   for entry in inboundCount
     *     if entry.value == 0
     *       processNext.add(entry.key)
     * decreaseInboundCount(node)
     *   // we have to decrease inbound count present because of node
     *   for adjacencyNode in node.adjacencies
     *     inboundCount.put(adjacencyNode, inboundCount.get(adjacencyNode) - 1)
     * setInboundCount():
     *   for node in adjList
     *     inboundCount.put(node, 0)
     *     for adjacencyNode in node.adjacencies
     *       inboundCount.put(adjacencyNode, inboundCount.get(adjacencyNode) + 1)
     */
	public List<Node> topologicalSort() {
		List<Node> result = new ArrayList<Node>();
		Map<Node, Integer> inboundCount = new HashMap<Node, Integer>();
		Queue<Node> processNext = new LinkedList<Node>();
	    setInboundCount(inboundCount);
	    updateProcessNextQueue(processNext, inboundCount);
	    while (!processNext.isEmpty()) {
	    	Node node = processNext.poll();
	    	decreaseInboundCount(node, inboundCount);
	    	result.add(node);
	    	updateProcessNextQueue(processNext, inboundCount);
	    }
	    return result.size() == adjList.size() ? result : null;
	}
	
	private void decreaseInboundCount(Node node, Map<Node, Integer> inboundCount) {
		for (Node adjacencyNode: node.adjacencies) {
			inboundCount.put(adjacencyNode, inboundCount.get(adjacencyNode) - 1);
		}
	}
	
	private void updateProcessNextQueue(Queue<Node> processNext, Map<Node, Integer> inboundCount) {
		for (Map.Entry<Node, Integer> entry: inboundCount.entrySet()) {
			if (entry.getValue() == 0) {
				processNext.add(entry.getKey());
			}
		}
	}
	
	private void setInboundCount(Map<Node, Integer> inboundCount) {
		for (Node node: adjList) {
			inboundCount.put(node, 0);
			for (Node adjacencyNode: node.adjacencies) {
				inboundCount.put(adjacencyNode, inboundCount.get(adjacencyNode) + 1);
			}
		}
	}
	
	private class Node{
		public String name;
		public List<Node> adjacencies = new ArrayList<Node>();
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}
	}
}
