package treesAndGraphs;

/**
 * Question: Write basic tree data structure
 * 
 * Important points:
 * 1. Tree has a root node which can have zero or more children.
 * 2. Every children can again have zero or more children.
 */
public class Tree<E> {

	Node root;
	
	private class Node {
		@SuppressWarnings("unused")
		public E data;
		@SuppressWarnings("unused")
		public Node[] children;
	}
}
