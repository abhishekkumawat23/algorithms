package treesAndGraphs;

/**
 * Question:
 * Write basic binary search tree
 * 
 * Important points:
 * 1. Each node of binary search tree has zero to 2 nodes.
 * 2. For every node, all left descendents < node < all right descendents
 * 3. Equal elements can be stored either on left or right. If stored on right, it will be stable sort on in-order traversal. 
 */
public class BinarySearchTree<E extends Comparable<E>> {
	Node root;
	
	/**
	 * Time: O(n); Space: O(n)
	 * 
	 * Important points:
	 * 1. All operations like insert, delete, search, min, max in BST takes O(h) time where h is height of tree.
	 * 2. In this case, as binary tree is not balanced, height can go till n. So time taken can be O(n)
	 * 2. As function is recursive and tree is not balanced, insert can take O(n) space.
	 * 3. Algo:
	 *    a. If element is less than current node, then this means now problem is reduced to insert element in left subtree.
	 *    b. If element is greater or equal than current node, then this means problem is reduced to insert element in right subtree.
	 *    c. If current node is null, then this means we need to insert the element there.
	 *    d. As current node can change after insertion, we are returning the node in each insertion call.
	 * 4. Iterative version is always better than recursive version as iterative one doesn't take any additional space.
	 * 5. In this case, while recursion:
	 *    a. we are reducing problem to one subproblem only. i.e. we are either going left or right but not both.
	 *    b. In addition, once the problem is reduced to smaller problem, smaller problem doesn't need to pass any result back to its bigger problem.
	 *    c. This type of recusrion is very simple to convert into iterative.
	 *    d. While converting to iterative, recursive calls is converted to for/while loop.
	 *    e. We set the loop next call to `problem = smaller problem` every time based on conditions.
	 *
	 * Pseudo code:
	 * insert(e):
	 *   root <- insert(root, e)
	 * insert(node, e)
	 *   if node = NIL
	 *     return node[e]
	 *   if e < data[node]
	 *     left[node] <- insert(left[node], e)
	 *   else
	 *     right[node] <- insert(right[node], e)
	 *   return node
	 */
	public void insert_recursive(E e) {
		root = insert_recursive(root, e);
	}
	
	private Node insert_recursive(Node node, E e) {
		if (node == null) {
			return new Node(e);
		}
		if (e.compareTo(node.data) < 0) {
			node.left = insert_recursive(node.left, e);
		}
		else {
			node.right = insert_recursive(node.right, e);
		}
		return node;
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. Iterative doesn't additional space as recursive function. So it is always better than recursive.
	 * 
	 * Pseudo code:
	 * insert(e):
	 *   if root = NIL
	 *     root <- node[e]
	 *     return
	 *   parent <- root
	 *   current <- root
	 *   while current != NIL
	 *     parent <- current
	 *     current <- (e < data[current]) ? left[current] : right[current]
	 *   if e < data[parent]
	 *     left[parent] <- node[e]
	 *   else
	 *     right[parent] <- node[e]
	 */
	public void insert_iterative(E e) {
		if (root == null) {
			root = new Node(e);
			return;
		}
		Node parent = root;
		Node current = root;
		while (current != null) {
			parent = current;
			current = (e.compareTo(current.data) < 0) ? current.left : current.right;
		}
		if (e.compareTo(parent.data) < 0) {
			parent.left = new Node(e);
		}
		else {
			parent.right = new Node(e);
		}
	}
	
	/**
	 * Time: O(n); Space: O(n)
	 * 
	 * Important points:
	 * 1. Time O(n) becuase unbalanced.
	 * 2. Space (n) because recusrion + unbalanced.
	 * 3. In this recursion:
	 *    a. We are reducing problem into one subproblem only.
	 *    b. But here we are returning the result of subproblem to bigger problem.
	 *    c. This returned result doesn't need to be processed at every single subproblem level. Every subproblem just pass this result to the parent result and parent result can check this return.
	 *    d. In case of recursion, we are processing the return result at every subproblem level, but in actual its not required at every subproblem level.
	 *    c. This type of recursion is also very easy to convert to iterative form.
	 *    d. Every loop reduces by setting `problem = sub problem`.
	 *    e. Any output which is returned by smaller problem can be updated in one single variable and result var can be modified after every loop. 
	 * 
	 * Pseudo code:
	 * search(e):
	 *   search(root, e)
	 * search(node, e):
	 *   if node = NIL
	 *     return false
	 *   else if data[node] = e
	 *     return true
	 *   else if e < data[node]
	 *     return search(left[node], e)
	 *   else
	 *     return search(right[node], e)
	 */
	public boolean search_recursive(E e) {
		return search_recursive(root, e);
	}
	
	private boolean search_recursive(Node node, E e) {
		if (node == null) {
			return false;
		}
		else if (e == node.data) {
			return true;
		}
		else if (e.compareTo(node.data) < 0) {
			return search_recursive(node.left, e);
		}
		else {
			return search_recursive(node.right, e);
		}
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1 Iterative better than recursive as no extra space.
	 * 2. As this tree is not balanced, time taken is O(n). In case it was balanced, we could have done it in O(log(n)) time.
	 * 
	 * Pseudo code:
	 * search(e):
	 *   current <- root
	 *   while current != NIL
	 *     if e = data[current]
	 *       return true
	 *     else if e < data[current]
	 *       current <- left[current]
	 *     else
	 *       current <- right[current]
	 *   return false
	 */
	public boolean search_iterative(E e) {
		Node current = root;
		while (current != null) {
			if (e == current.data) {
				return true;
			}
			else if (e.compareTo(current.data) < 0) {
				current = current.left;
			}
			else {
				current = current.right;
			}
		}
		return false;
	}
	
	/**
	 * Time: O(n); Space: O(n)
	 * 
	 * Important points:
	 * 1. Deletion is usually tricky in trees and graphs.
	 * 2. A node which needs to be deleted can be of following types:
	 *    a. Has no children: In this case, we can remove the element easily.
	 *    b. Has one children only i.e. either left subtree or right subtress
	 *       In this case, if left children is there, then we remove the node by making node = node.left
	 *    c. Has 2 children: In this case:
	 *       i. we find either the minimum in right tree or maximum in left tree and replace this node with the node to be removed so as to maintain the BST conditions.
	 *       iii. If we choise min of right subtree, then now we delete the min element of right subtree.
	 * 3. Min of right subtree will be the leftmost element in right subtree. Similarly max of left subtree will be the rightmost element in left subtree.
	 * 
	 * Pseudo code:
	 * delete(e):
	 *   root <- delete(root, e)
	 * delete(node, e):
	 *   if node = NIL
	 *     return NIL
	 *   else if e < data[node]
	 *     left[node] <- delete(left[node], e)
	 *   else if e > data[node]
	 *     right[node] <- delete(right[node], e)
	 *   else
	 *     if left[node] = NIL and right[node] = NIL
	 *       node <- NIL
	 *     else if left[node] = NIL
	 *       node <- right[node]
	 *     else if right[node] = NIL
	 *       node <- left[node]
	 *     else
	 *       temp <- findMin(right[node])
	 *       data[node] <- data[temp]
	 *       right[node] <- delete(right[node], data[temp])
	 *   return node
	 * findMin(node):
	 *   current <- node
	 *   while current != NIL and left[current] != NIL
	 *     current <- left[current]
	 *   return current
	 */
	public void delete_recursive(E e) {
		root = delete_recursive(root, e);
	}
	
	private Node delete_recursive(Node node, E e) {
		if (node == null) {
			return null;
		}
		else if (e.compareTo(node.data) < 0) {
			node.left = delete_recursive(node.left, e);
		}
		else if (e.compareTo(node.data) > 0) {
			node.right = delete_recursive(node.right, e);
		}
		else {
			// Case 1: No children
			if (node.left == null && node.right == null) {
				node = null;
			}
			// Case 2: Only one children
			else if (node.left == null) {
				node = node.right;
			}
			else if (node.right == null) {
				node = node.left;
			}
			// Case 3: Both children
			else {
				Node temp = findMin(node.right);
				node.data = temp.data;
				node.right = delete_recursive(node.right, temp.data);
			}
		}
		return node;
	}
	
	private Node findMin(Node node) {
		Node current = node;
		while (current != null && current.left != null) {
			current = current.left;
		}
		return current;
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Pseudo code:
	 * delete(e):
	 *   if root = NIL
	 *     return
	 *   parent <- root
	 *   current <- root
	 *   while current != NIL
	 *     if e < data[current]
	 *       parent <- current
	 *       current <- left[current]
	 *     else if e > data[current]
	 *       parent <- current
	 *       current <- right[current]
	 *     else
	 *       if left[current] = NIL
	 *         if e < data[parent]
	 *           left[parent] <- right[current]
	 *         else
	 *           right[parent] <- right[current]
	 *         break
	 *       else if right[current] = NIL
	 *         if e < data[parent]
	 *           left[parent] <- left[current]
	 *         else
	 *           right[parent] <- left[current]
	 *         break
	 *       else
	 *         min <- findMin(right[current])
	 *         data[current] <- data[min]
	 *         e <- data[min]
	 *         parent <- current
	 *         current <- current.right
	 * findMin(node):
	 *   current <- node
	 *   while current != NIL and left[current] != NIL
	 *     current <- left[current]
	 *   return current
	 */
	public void delete_iterative(E e) {
		if (root == null) {
			return;
		}
		Node current = root;
		Node parent = root;
		while (current != null) {
			if (e.compareTo(current.data) < 0) {
				parent = current;
				current = current.left;
			}
			else if (e.compareTo(current.data) > 0) {
				parent = current;
				current = current.right;
			}
			else {
				// Case 1 & 2: No children and one children
				if (current.left == null) {
					if (e.compareTo(parent.data) < 0) {
						parent.left = current.right;
					}
					else {
						parent.right = current.right;
					}
					break;
				}
				else if (current.right == null) {
					if (e.compareTo(parent.data) < 0) {
						parent.left = current.left;
					}
					else {
						parent.right = current.left;
					}
					break;
				}
				// Case 3: Two children
				else {
					Node min = findMin(current.right); // Find min of right subtree.
					current.data = min.data;
					e = min.data;
					parent = current;
					current = current.right;
				}
			}
		}
	}
	
	/**
	 * Time: O(n); Space: O(n)
	 * 
	 * Pseudo code:
	 * min():
	 *   min(root)
	 * min(node):
	 *   if left[node] = NIL
	 *     return data[node]
	 *   return min(left[node])
	 */
	public E min_recursive() {
		return min_recursive(root);
	}
	
	private E min_recursive(Node node) {
		if (node.left == null) {
			return node.data;
		}
		return min_recursive(node.left);
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Pseudo code:
	 * min():
	 *   current <- root
	 *   while left[current] != NIL
	 *     current <- left[current]
	 *   return data[current]
	 */
	public E min_iterative() {
		Node current = root;
		while (current.left != null) {
			current = current.left;
		}
		return current.data;
	}

	private class Node{
		public E data;
		public Node left;
		public Node right;
		
		public Node(E data) {
			this.data = data;
		}
	}
}
