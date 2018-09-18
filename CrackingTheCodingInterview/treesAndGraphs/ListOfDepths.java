package treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Question:
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (e.g. if you have a tree with depth d, you will have d linked lists)
 * 
 * Important points:
 * 1. Even though it looks like this problem can be solved only using BFS. But we can also solve this problem using DFS as well. Check df logic as well in this class.
 * 2. This means that if someone asks to do level wise traverse using DFS, think of approach used here.
 */
public class ListOfDepths {

	/**
	 * Time: O(n); Space: O(2^h) for last node ~= O(n(n+1)/2) + O(n) for storing result.
	 * 
	 * Important points:
	 * 1. This is a clear scenario of breadth first.
	 * 2. We can save here extra space by adding queue in final list and then creating a new queue for new level. In this way no extra space apart from result is required.
	 * 
	 * listOfDepths(root):
	 *   if root == null
	 *     return null
	 *   queue.add(root)
	 *   while queue is not empty
	 *     currentSize = queue.size
	 *     listOfDepths.add(currentList)
	 *     while i = 1 to currentSize
	 *       node = queue.poll
	 *       currentList.add(node)
	 *       queue.add(node.left)
	 *       queue.add(node.right)
	 *     return listOfDepths
	 */
	public List<List<Node>> listOfDepths(Node root){
		if (root == null) {
			return null;
		}
		Queue<Node> queue = new LinkedList<Node>();
		List<List<Node>> listOfDepths = new ArrayList<List<Node>>();
		queue.add(root);
		Node node = null;
		while (!queue.isEmpty()) {
			int currentSize = queue.size();
			List<Node> currentList = new ArrayList<Node>();
			listOfDepths.add(currentList);
			for (int i = 1; i <= currentSize; i++) {
				node = queue.poll();
				currentList.add(node);
				queue.add(node.left);
				queue.add(node.right);
			}
		}
		return null;
	}
	
	/**
	 * Time: O(n); Space:O(logn) for stack + O(n) for storing result
	 * Important points:
	 * 1. Surprisingly this problem can be solved using DFS.
	 * 2. We pass the level index. If that level is greater than lists size, this means that we need to add that list in lists as we are accessing it first time.
	 * 
	 * listOfDepths(root, lists, 0)
	 * listOfDepths(node, lists, level):
	 *   if node == null
	 *     return
	 *   if lists.size == level
	 *     currentList = new list
	 *     lists.add(currentList)
	 *   else
	 *     currentList = lists.get(level)
	 *   currentList.add(node)
	 *   listOfDepths(node.left, lists, level+1)
	 *   listOfDepths(node.right, lists, level+1)
	 */
	public void listOfDepths_preorder(Node node, List<List<Node>> lists, int level){
		if (node == null) {
			return;
		}
		List<Node> currentList = null;
		if (lists.size() == level) {
			currentList = new ArrayList<Node>();
			lists.add(currentList);
		}
		else {
			currentList = lists.get(level);
		}
		currentList.add(node);
		listOfDepths_preorder(node.left, lists, level+1);
		listOfDepths_preorder(node.right, lists, level+1);
	}
	
	private class Node{
		public Node left;
		public Node right;
	}
}
