package treesAndGraphs;

import java.util.Stack;

/**
 * Implement postorder traversal.
 */
public class PostorderTraversal<E> {

	/**
	 * Time: O(n); Space: O(h)
	 * 
	 * Important points:
	 * 1. Dont get confused for space complecity. Remember its not O(n), its O(h).
	 * 
	 * Pseudo code:
	 * postorder(root)
	 * postorder(node):
	 *   if node == null
	 *     return
	 *   postorder(node.left)
	 *   postorder(node.right)
	 *   print node.data
	 */
	public void postorder(Node node) {
		if (node == null) {
			return;
		}
		postorder(node.left);
		postorder(node.right);
		System.out.println(node.data);
	}
	
	/**
	 * Time: O(n); Space: O(h)
	 * 
	 * Important points:
	 * 1. Space is O(h), as at a time we are storing nodes of a particular path of tree.
	 * 2. Postorder traversal is little tricky to implement iteratively.
	 *    a. First we are storing all left nodes.
	 *    b. Then we are peeking the stack element and checking if it has right has elements and if those right elements has already been visited or not.
	 *    c. If there are no right elements or right elements are already visited, than we pop the element and visit it.
	 *    d. If there are right elements and are not yet visited, we set current to current.right and then from step a.
	 *    e. Every time we visit an element, we store it in variable called lastVisited.
	 *    f. We know that in post order, when we are visiting the node we know that node.right element will be the last visited.
	 *    g. If lastVisited matches the node.right, this means that we have visited the right elements oterwise we have to visit.
	 *   
	 * Pseudo code:
	 * postorder(root):
	 *   if root == null
	 *     return
	 *   current = root
	 *   while stack is not empty and current != null
	 *     if current != null
	 *       stack.push(current)
	 *       current  = current.left
	 *     else
	 *       peekNode = stack.peek
	 *       if peekNode.right != null and lastNodeVisited != peekNode.right
	 *         current = peekNode.right
	 *       else
	 *         print peekNode.data
	 *         lastNodeVisited = peekNode
	 *         stack.pop // remove the current from stack we have visited this
	 */
	public void postorder_iterative_onestack(Node root) {
		if (root == null) {
			return;
		}
		
		Stack<Node> stack = new Stack<Node>();
		Node current = root;
		Node lastVisited = null;
		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			}
			else {
				Node peekNode = stack.peek();
				if (peekNode.right != null && lastVisited != peekNode.right) {
					current = peekNode.right;
				}
				else {
					System.out.println(peekNode.data);
					lastVisited = peekNode;
					stack.pop();
				}
			}
		}
	}
	
	/**
	 * Time: O(n); Space: O(h)+O(n)
	 * 
	 * Important points:
	 * 1. Reverse of postorder traversal is similar to preorder traversal with right child visited before left child.
	 * 2. Using above print, we do preorder travesal but visit right element before left element and keep storing the nodes in path1.
	 * 3. Instead of printing the node which we visited, we store it in stack2. This is because this is reversal of postorder traversal.
	 * 4. At end of traversal, we print elements from stack2. Doing this, we print correct postorder.
	 * 5. If someone asks to print reverse of postorder, we don't need stack2.
	 * 6. Space complexoty here is increased and is O(n) because we are storing all the reversed post roder elements in stack2. stack1 still takes O(h) space. 
	 * 
	 * Pseudo code:
	 * postorder(root):
	 *   if root == null
	 *     return
	 *   stack1.push(root)
	 *   while stack1 is not empty
	 *     current = stack1.pop
	 *     stack2.push(current)
	 *     if current.left != null
	 *       stack1.push(current.left)
	 *     if current.right != null
	 *       stack1.push(current.right)
	 *   for stack2 is not empty
	 *     print stack2.pop
	 */
	public void postorder_iterative_twostacks(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		stack1.push(root);
		Node current = null;
		while (!stack1.isEmpty()) {
			current = stack1.pop();
			stack2.push(current);
			if (current.left != null) {
				stack1.push(current.left);
			}
			if (current.right != null) {
				stack2.push(current.right);
			}
		}
		while(!stack2.isEmpty()) {
			System.out.println(stack2.pop().data);
		}
	}
	
	private class Node{
		public E data;
		public Node left;
		public Node right;
	}
}
