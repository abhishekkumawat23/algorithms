package treesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Question:
 * You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the first project is dependent on the second project).
 * All of a project's dependencies must be built before the project is. Find a build order that will allow the projects to be built.
 * If there is no valid build order, return an error.
 * Example:
 * Input:
 *   projects: a, b, c, d, e, f
 *   dependencies: (d, a), (b, f), (d, b), (a, f), (c, d)
 * Output: f, e, a, b, d, c
 */
public class BuildOrder {
	Map<String, Node> adjList = new HashMap<String, Node>();

	/**
	 * Time: O(n + e) for creating graph + O(n + e) for processing all edges of all nodes; Space: O(n+e) for storing in graph + O(n) for queue when all projects are independent
	 * 
	 * buildOrder(projects, dependencies):
	 *   for project in projects
	 *     adjList.put(project, node(project))
	 *   for dependencyPair in dependencies
	 *     adjList.get(dependencyPair[1]).adjacencies.add(adjList.get(dependencyPair[0]))
	 *     inboundCount[adjList.get(dependencyPair[0])]++
	 *   updateProcessNextQueue(processNext, inboundCount)
	 *   while processNext is not empty
	 *     projectNode = processNext.poll
	 *     buildOrder.add(projectNode.name)
	 *     for adjacencyProject in projectNode.adjacencies
	 *       inboundCount[adjacencyProject]--
	 *     updateProcessNextQueue(processNext, inboundCount)
	 *   return buildOrder
	 * updateProcessNextQueue(processNext, inboundCount):
	 *   for entry in inboundCount
	 *     if entry.value == 0
	 *       processNext.add(entry.key)
	 */
	public List<String> buildOrder(String[] projects, String[][] dependencies){
		HashMap<String, Integer> inboundCount = new HashMap<String, Integer>();
		Queue<Node> processNext = new LinkedList<Node>();
		
		for (String project : projects) {
			adjList.put(project, new Node(project));
		}
		for (String[] dependencyPair: dependencies) {
			adjList.get(dependencyPair[1]).adjacencies.add(adjList.get(dependencyPair[0]));
			inboundCount.put(adjList.get(dependencyPair[0]).name, inboundCount.get(adjList.get(dependencyPair[0]).name) + 1);
		}
		updateProcessNextQueue(processNext, inboundCount);
		Node projectNode = null;
		List<String> buildOrder = new ArrayList<String>();
		while (!processNext.isEmpty()) {
			projectNode = processNext.poll();
			buildOrder.add(projectNode.name);
			for (Node adjacencyProject: projectNode.adjacencies) {
				inboundCount.get(adjacencyProject.name);
			}
			updateProcessNextQueue(processNext, inboundCount);
		}
		return buildOrder;
	}
	
	private void updateProcessNextQueue(Queue<Node> processNext, HashMap<String, Integer> inboundCount){
		for (Map.Entry<String, Integer> entry: inboundCount.entrySet()) {
			if (entry.getValue() == 0) {
				processNext.add(adjList.get(entry.getKey()));
			}
		}
	}
	
	private class Node {
		public String name;
		public List<Node> adjacencies = new ArrayList<Node>();
		
		public Node(String name) {
			this.name = name;
		}
	}
}
