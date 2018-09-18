package treesAndGraphs;

/**
 * Question:
 * Write basic binary tree.
 * 
 * Important points:
 * 1. Each node of binary tree has zero to two children nodes.
 */
public class BinaryTree<E> {

	Node root;
	
	private class Node{
		@SuppressWarnings("unused")
		public E data;
		@SuppressWarnings("unused")
		public Node left;
		@SuppressWarnings("unused")
		public Node right;
	}
}
