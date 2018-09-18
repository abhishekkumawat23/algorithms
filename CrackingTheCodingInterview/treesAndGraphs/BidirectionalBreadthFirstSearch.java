package treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Question:
 * Implemenet bidirectional search - assuming undirected graph
 * 
 * Important points:
 * 1. Bidirectional search is used to find shortest path between two nodes.
 * 2. Bidirectional search is an advanced version of breadth first search.
 * 3. If distacne between two nodes is d and every node has atmost k adjacent nodes, then:
 *    a. breadth first search takes O(k^d) time.
 *    b. bidirectional search takes O(k^(d/2)) time.
 * 4. Bidirectional makes much sense for undirected graph only as we can do backward searching.
 * 5. In case of directed graph, the shorted path from src to dest can be different than path from dest to src.
 * 5. In bidirectional search, we start breadth first search from both nodes and we meet at middle point (only when undirected graph)
 * 6. For directed graph, we can reverse the graph and then apply one search on original and one on reverse.
 * 7. Here, in this implementation we are only finding whether the path exists or not. we are not actually returning the shortest path. We will do that as well in coming algorithms.
 */
public class BidirectionalBreadthFirstSearch {
	
	/**
	 * Time: O(k^(d/2)) where k is  atmost number of adjancies and d is distance between from and to
	 *  
	 * Pseudo code:
	 * bidirectional(from, to):
	 *   from.visitedA = true
	 *   queueA.add(from)
	 *   to.visitedB = true
	 *   queueB.add(to)
	 *   while !queueA.isEmpty || !queueB.isEmpty
	 *     a = !queueA.isEmpty ? queueA.poll : null
	 *     b = !queueB.isEmpty ? queueB.poll : null
	 *     if a != null and a.visitedB == true
	 *       return true
	 *     else if b != null and b.visitedA == true
	 *       return true
	 *     else if a != null
	 *       addAdjacenciesInQueue(queueA, a, true)
	 *     else if b != null
	 *       addAdjacenciesInQueue(queueB, b, false)
	 * addAdjacenciesInQueue(queue, node, isQueueA):
	 *   for adjacencyNode in node.adjacencies
	 *     visited = isQueueA ? adjacencyNode.visitedA : adjacencyNode.visitedB
	 *     if !visited
	 *       if isQueueA
	 *         adjacencyNode.visitedA = true
	 *       else
	 *         adjacencyNode.visitedB = true
	 *       queue.add(adjacencyNode)     
	 */
	public boolean bidirectional(Node from, Node to) {
		Queue<Node> queueA = new LinkedList<Node>();
		Queue<Node> queueB = new LinkedList<Node>();
	  	from.visitedA = true;
	  	queueA.add(from);
	  	to.visitedB = true;
	  	queueB.add(to);
	  	while (!queueA.isEmpty() || !queueB.isEmpty()) {
	  		Node a = !queueA.isEmpty() ? queueA.poll() : null;
	  		Node b = !queueB.isEmpty() ? queueB.poll() : null;
	  		if (a != null && a.visitedB) {
	  			return true;
	  		}
	  		else if (b != null && b.visitedA) {
	  			return true;
	  		}
	  		else if (a != null){
	  			addAdjacenciesInQueue(queueA, a, true);
	  		}
	  		else if (b != null) {
	  			addAdjacenciesInQueue(queueB, b, false);
	  		}
	  	}
	  	return false;
	}
	
	private void addAdjacenciesInQueue(Queue<Node> queue, Node node, boolean isQueueA) {
		for (Node adjacencyNode: node.adjacencies) {
			boolean visited = isQueueA ? adjacencyNode.visitedA : adjacencyNode.visitedB;
			if (!visited){
				if (isQueueA) adjacencyNode.visitedA = true;
				else adjacencyNode.visitedB = true;
				queue.add(adjacencyNode);
			}
		}
	}
	
	private class Node{
		public boolean visitedA;
		public boolean visitedB;
		public ArrayList<Node> adjacencies = new ArrayList<Node>();
	}
}
