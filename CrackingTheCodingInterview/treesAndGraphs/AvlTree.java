package treesAndGraphs;

import java.util.Stack;

/**
 * Question:
 * Implement self-balancing AVL tree.
 * 
 * Important points:
 * 1. AVL tree is self balancing binary search tree which has following balancing conditions:
 *    a. Difference betweeen heights of left and right subtrees cannot be more than one for all nodes.
 * 2. Due to this self balancing, height of tree is factor of log(n).
 * 3. As in BST, all operations like insert, delete, search, min, max takes O(height) time, we achieve all of them in O(log(n)) time because of this re-balancing.
 * 4. Min possible height of avl tree is log(n). This is when right and left subtree of all nodes has same height.
 * 5. Max possible height of avl tree is 1.44log(n). This is when right and left subtree of all nodes have diff of 1.
 * 6. Fibonacci tree is formed in case avl tree is of max possible height.
 * 7. We are not implementing search, min and max as thery are similar to BST. Balancing condition doesn't effect the implementation. Only change is that time complexity for these now is o(log(n)) due to balancing.
 * 8. Red black insertion and deletion is very complicated. So we are not covering that in implementation.
 */
public class AvlTree<E extends Comparable<E>> {
	Node root;
	
	/**
	 * Time: O(log(n)); Space: O(log(n))
	 * 
	 * Important points:
	 * 1. Every node local height is also saved in node variable as its required in calculations.
	 * 2. First we do normal BST insertion.
	 * 3. After insertion the AVL balance property can change i.e diff between left and right subtree at any node should not be greater than 1.
	 * 4. The imbalance can only happen in ancestor chain where the node is inserted.
	 * 5. To balance it again, we look the ancestor chain from the inserted element and find the node which is unbalanced.
	 * 6. Lets say z is unbalanced node, y is its child and x is its grandchild. These elements can be in ancestor chain in following ways:
	 *    a. left left case: z.left = y; y.left = x. In this case, rightRotate the z. It will make y.right = z; y.left = x;
	 *    b. right right case: z.right = y; y.right = x. In this case, leftRotate the z. It will make y.left = z; y.right = x;
	 *    c. left right case: z.left = y; y.right = x; In this case, leftRotate the y and then rightRotate the z. It will make x.left = y; x.right = z;
	 *    d. right left case: z.right = y; y.left = x; In this case, rightRotate the y and then leftRotate the z. It will make x.left = z; x.right = y;
	 * 7. These rotations are in such a ways that they follow BST property and AVL balanced property.
	 * 8. Height of z before balancing and after balancing doesn't change. Thus only balancing one unbalanced node will make the entire tree balanced.
	 * 9. Thus balancing happens in O(1) time.
	 * 10. Time for insertion is O(log(n)) as all BST operations are O(h) and in avl tree, max `h` possible is 1.44log(n)
	 * 11. Space for insertion is O(log(n)) as recursive operation comsumes stack of O(h).
	 * 12. In this case, while recursion:
	 *     a. we are reducing problem to one subproblem only.
	 *     b. But we need to return the result from subproblem to parent problem.
	 *     c. This returned results needds to be processed at every subproblem level (check the height imbalance at every level). This makes conversion to iterative difficult.
	 *     d. In addition, whatever elements we recursed, we might need to go to them again (for balancing)
	 *     e. For converting this to iterative, every loop reduces the problem by doing `problem=subProblem`
	 *     f. We maintian a stack/list of elements we are recursing so that we can recurse them again and also process the returned result from subproblem.
	 *     g. As we are maintaining a stack in iterative case, itetative version is not better than recursive so its better to use recursive due to less code complexity.
	 * 
	 * Pseudo code:
	 * insert(e):
	 *   root <- insert(root, e)
	 * insert(node, e)
	 *   // Normal BST insertion
	 *   if node = NIL
	 *     node <- node[e]
	 *     height[node] <- 1
	 *     return node
	 *   if e < data[node]
	 *     left[node] <- insert(left[node], e)
	 *   else
	 *     right[node] <- insert(right[node], e)
	 *   // Updating heights
	 *   heightLeft <- left[node] != NIL ? height[left] ? -1
	 *   heightRight <- right[node] != NIL ? height[right] ? -1
	 *   height[node] <- max(heightLeft, heightRight) + 1
	 *   // Balancing trees
	 *   // Left Left case
	 *   if heightLeft - heightRight > 1 and e < data[left[node]]
	 *     return rightRotate(node)
	 *     // Right Right case
	 *   if heightRight - heightLeft > 1 and e > data[right[node]]
	 *     return leftRotate(node)
	 *     // Left Right case
	 *   if heightLeft - heightRight > 1 and e > data[left[node]]
	 *     left[node] <- leftRotate(left[node])
	 *     return rightRotate(node)
	 *   // Right left case
	 *   if heightRight - heightLeft > 1 and e < data[right[node]]
	 *     right[node] <- rightRotate(right[node])
	 *     return leftRotate(node)
	 *   return node
	 */
	public void insert_recursive(E e) {
		root = insert_recursive(root, e);
	}
	
	private Node insert_recursive(Node node, E e) {
		// Normal BST operation
		if (node == null) {
			node = new Node(e);
			node.height = 1;
			return node;
		}
		if (e.compareTo(node.data) < 0) {
			node.left = insert_recursive(node.left, e);
		}
		else {
			node.right = insert_recursive(node.right, e);
		}
		// Updating heights
		int heightLeft = node.left != null ? node.height : -1;
		int heightRight = node.right != null ? node.height : -1;
		node.height = Math.max(heightLeft, heightRight) + 1;
		// Balancing heights
		// Left left case
		if (heightLeft - heightRight > 1 && e.compareTo(node.left.data) < 0) {
			return rightRotate(node);
		}
		// Right right case
		if (heightRight - heightLeft > 1 && e.compareTo(node.right.data) > 0) {
			return leftRotate(node);
		}
		// Left right case
		if (heightLeft - heightRight > 1 && e.compareTo(node.left.data) > 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		// Right left case
		if (heightRight - heightLeft > 1 && e.compareTo(node.right.data) < 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * leftRotate(node):
	 *   temp <- left[right[node]]
	 *   left[right[node]] <- node
	 *   right[node] <- temp
	 *   return right[node]
	 */
	private Node leftRotate(Node node) {
		Node temp = node.right.left;
		node.right.left = node;
		node.right = temp;
		return node.right;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * rightRotate(node):
	 *   temp <- right[left[node]]
	 *   right[left[node]] <- node
	 *   left[node] <- temp
	 *   return left[node]
	 */
	private Node rightRotate(Node node) {
		Node temp = node.left.right;
		node.left.right = node;
		node.left = temp;
		return node.left;
	}
	
	/**
	 * Pseudo code:
	 * insert(e)
	 *   // Normal BST insertion + height update
	 *   if root = NIL
	 *     node <- node[e]
	 *     height[node] <- 1
	 *     root <- node
	 *     return
	 *   parent <- root
	 *   current <- root
	 *   while current != NIL
	 *     parent <- current
	 *     push(stack, current)
	 *     current <- e < data[node] ? left[current] : right[current]
	 *   node <- node[e]
	 *   height[node] <- 1
	 *   if e < data[parent]
	 *     left[parent] <- node
	 *   else
	 *     right[parent] <- node
	 *   if (e < data[parent] and parent.right != NIL) or (e >= data[parent] and parent.left != NIL)
	 *     // No balancing required as heights didn't change
	 *     return
	 *   while stack is not empty
	 *     // Updating heights
	 *     node <- pop[stack]
	 *     heightLeft <- left[node] != NIL ? height[left[node]] : -1
	 *     heightRight <- right[node] != NIL ? height[right[node]]: -1
	 *     height[node] <- max(heightLeft, heightRight) + 1
	 *     // Balancing not required
	 *     if abs(heightLeft - heightRight) <= 1
	 *       continue;
	 *     // Balancing required.
	 *     // Left left case
	 *     if heightLeft - heightRight > 1 and e < left[node]
	 *       node <- rightRotate(node)
	 *     // Right right case
	 *     else if heightRight - heightLeft > 1 and e > right[node]
	 *       node <- leftRotate(node)
	 *     // Left right case
	 *     else if heightLeft - heightRight > 1 and e > left[node]
	 *       left[node] <- leftRotate(left[node])
	 *       node <- rightRotate(node)
	 *     // Right left case
	 *     else if heightRight - heightLeft > 1 and e < right[node]
	 *       right[node] <- rightRotate(right[node])
	 *       node <- leftRotate(node)
	 *     parent <- pop[stack]
	 *     if parent <- NIL
	 *       root <- node
	 *     else if e < data[parent]
	 *       left[parent] <- node
	 *     else
	 *       right[parent] <- node
	 *     break
	 */
	public void insert_iterative(E e) {
		if (root == null) {
			Node node = new Node(e);
			node.height = 1;
			root = node;
			return;
		}
		
		// Normal BST insertion and updating heights.
		Stack<Node> stack = new Stack<Node>();
		Node parent = root;
		Node current = root;
		while (current != null) {
			parent = current;
			stack.push(current);
			current = (e.compareTo(current.data) < 0) ? current.left : current.right;
		}
		
		Node node = new Node(e);
		node.height = 1;
		if (e.compareTo(parent.data) < 0) {
			parent.left = node;
		}
		else {
			parent.right = node;
		}
		
		// No rebalancing required as no heights changed.
		if ((e.compareTo(parent.data) < 0 && parent.right != null) ||
				e.compareTo(parent.data) >= 0 && parent.left != null) {
			return;
		}
		
		// Updating heights and balancing nodes
		while (!stack.isEmpty()) {
			// Updating heights
			node = stack.pop();
			int heightLeft = node.left != null ? node.left.height : -1;
			int heightRight = node.right != null ? node.right.height : -1;
			node.height = Math.max(heightLeft, heightRight) + 1;
			
			// Skip if balancing not required.
			if (Math.abs(heightLeft-heightRight) <= 1) {
				continue;
			}
			
			// Balancing required.
			// Left left case
			// Left left case
			if (heightLeft - heightRight > 1 && e.compareTo(node.left.data) < 0) {
				node = rightRotate(node);
			}
			// Right right case
			else if (heightRight - heightLeft > 1 && e.compareTo(node.right.data) > 0) {
				node = leftRotate(node);
			}
			// Left right case
			else if (heightLeft - heightRight > 1 && e.compareTo(node.left.data) > 0) {
				node.left = leftRotate(node.left);
				node = rightRotate(node);
			}
			// Right left case
			else if (heightRight - heightLeft > 1 && e.compareTo(node.right.data) < 0) {
				node.right = rightRotate(node.right);
				node = leftRotate(node);
			}
			// Attach balanced node to its parent.
			Node parentOfUnbalanced = !stack.isEmpty() ? stack.pop() : null;
			if (parentOfUnbalanced == null) {
				root = node;
			}
			else if (e.compareTo(parentOfUnbalanced.data) < 0){
				parentOfUnbalanced.left = node;
			}
			else {
				parentOfUnbalanced.right = node;
			}
			break;
		}
	}	
	
	/**
	 * Time: O(log(n)); Space: O(logn(n)
	 * 
	 * Important points:
	 * 1. As AVL tree is balanced, height is factor of log(n). Thus deletion takes O(log(n)) time.
	 * 2. As recursive method, space taken is O(log(n))
	 * 3. Rotation in deletion is excatly same as rotaion in insertion. Only difference is that x y are not in ancestor chain. Because of this only, the rotaion code is exacly similar for insertion and deletion.
	 * 4. In this recursion:
	 *    a. we are recursing back for ancestor to find z.
	 *    b. In addition, subproblem returns a result which needs to be processed
	 *    c. To make this method iterative, we need maintain a separate stack of ancestors so that we can recurse back to ancestors and also process the result of parent result.
	 * 
	 * Pseudo code:
	 * delete(e):
	 *   root <- delete(root, e)
	 * delete(node, e):
	 *   // Normal BST deletion
	 *   if node = NIL
	 *     node <- NIL
	 *   else if e < node.data
	 *     node.left <- delete(node.left, e)
	 *   else if e > node.data
	 *     node.right <- delete(node.right, e)
	 *   else
	 *     // No children
	 *     if node.left = NIL and node.right = NIL
	 *       node <- NIL
	 *     // One chidlren
	 *     else if node.left = NIL
	 *       node <- node.right
	 *     else if node.right = NIL
	 *       node <- node.left
	 *     // Two children 
	 *     else
	 *       min <- findMin(node.right)
	 *       node.data <- min.data
	 *       delete(node.right, min)
	 *   // Updating heights
	 *   heightLeft <- node.left != NIL ? node.left.height : -1
	 *   heightRight <- node.right != NIL ? node.right.height : -1
	 *   node.height <- max(heightLeft, heightRight) + 1
	 *   // Balancing trees
	 *   // Left Left case
	 *   if heightLeft - heightRight > 1 and e < data[left[node]]
	 *     node <- rightRotate(node)
	 *     // Right Right case
	 *   if heightRight - heightLeft > 1 and e > data[right[node]]
	 *     node <- leftRotate(node)
	 *     // Left Right case
	 *   if heightLeft - heightRight > 1 and e > data[left[node]]
	 *     left[node] <- leftRotate(left[node])
	 *     node <- rightRotate(node)
	 *   // Right left case
	 *   if heightRight - heightLeft > 1 and e < data[right[node]]
	 *     right[node] <- rightRotate(right[node])
	 *     node <- leftRotate(node)
	 *   return node
	 *   findMin(node):
	 *     if node.left = NIL
	 *       return node
	 *     return findMin(node.left)
	 */
	public void delete_recursive(E e) {
		root = delete_recursive(root, e);
	}
	
	private Node delete_recursive(Node node, E e) {
		// Normal BST tree deletion
		if (node == null) {
			return null;
		}
		if (e.compareTo(node.data) < 0) {
			node.left = delete_recursive(node.left, e);
		}
		else if (e.compareTo(node.data) > 0) {
			node.right = delete_recursive(node.right, e);
		}
		else {
			// No children case
			if (node.left == null && node.right == null) {
				node = null;
			}
			// One chilrden case
			else if (node.left == null) {
				node = node.right;
			}
			else if (node.right == null) {
				node = node.left;
			}
			// Two children case
			else {
				Node min = findMin_recursive(node.right);
				node.data = min.data;
				delete_recursive(node.right, min.data);
			}
		}
		// Updating heights
		int heightLeft = node.left != null ? node.left.height : -1;
		int heightRight = node.right != null ? node.right.height : -1;
		node.height = Math.max(heightLeft, heightRight) + 1;
		// Balancing heights
		// Left left case
		if (heightLeft - heightRight > 1 && e.compareTo(node.left.data) < 0) {
			node = rightRotate(node);
		}
		// Right right case
		else if (heightRight - heightLeft > 1 && e.compareTo(node.right.data) > 0) {
			node = leftRotate(node);
		}
		// Left right case
		else if (heightLeft - heightRight > 1 && e.compareTo(node.left.data) > 0) {
			node.left = leftRotate(node.left);
			node = rightRotate(node);
		}
		// Right left case
		else if (heightRight - heightLeft > 1 && e.compareTo(node.right.data) < 0) {
			node.right = rightRotate(node.right);
			node = leftRotate(node);
		}
		return node;
	}
	
	private Node findMin_recursive(Node node) {
		Node min = node;
		while (min.left != null) {
			min = min.left;
		}
		return min;
	}

	/**
	 * Pseudo code:
	 * delete(e):
	 *   // Normal BST + updating heights
	 *   if root = NIL
	 *     return;
	 *   Node parent <- root
	 *   Node current <- root
	 *   while current  != NIL
	 *     if e < current.data
	 *       parent <- current
	 *       push(stack, current)
	 *       current <- current.left
	 *     else if e > current.data
	 *       parent <- current
	 *       push(stack, current)
	 *       current <- current.right
	 *     else
	 *       // No children
	 *       if current.left = NIL and current.right = NIL
	 *         if e < parent.data
	 *           parent.left <- NIL
	 *         else
	 *           parent.right <- NIL
	 *         break
	 *       // One children
	 *       else if current.left = NIL
	 *         if e < parent.data
	 *           parent.left <- current.right
	 *         else
	 *           parent.right <- current.right
	 *         break
	 *       else if current.right = NIL
	 *         if e < parent.data
	 *           parent.left <- current.left
	 *         else
	 *           parent.right <- current.left
	 *         break
	 *       // Two children
	 *       else
	 *         min <- findMin(current.right)
	 *         current.data <- min.data
	 *         e <- min.data
	 *         parent <- current
	 *         push(stack, current)
	 *         current <- current.right
	 *   // No rebalancing required
	 *   if (e < data[parent] and parent.right != NIL) or (e >= data[parent] and parent.left != NIL)
	 *     return
	 *   // Updating heights and balancing nodes
	 *   while stack is not empty
	 *     // Updating heights
	 *     node <- pop[stack]
	 *     heightLeft <- left[node] != NIL ? height[left[node]] : -1
	 *     heightRight <- right[node] != NIL ? height[right[node]]: -1
	 *     height[node] <- max(heightLeft, heightRight) + 1
	 *     // Balancing not required
	 *     if abs(heightLeft - heightRight) <= 1
	 *       continue;
	 *     // Balancing required.
	 *     // Left left case
	 *     if heightLeft - heightRight > 1 and e < left[node]
	 *       node <- rightRotate(node)
	 *     // Right right case
	 *     else if heightRight - heightLeft > 1 and e > right[node]
	 *       node <- leftRotate(node)
	 *     // Left right case
	 *     else if heightLeft - heightRight > 1 and e > left[node]
	 *       left[node] <- leftRotate(left[node])
	 *       node <- rightRotate(node)
	 *     // Right left case
	 *     else if heightRight - heightLeft > 1 and e < right[node]
	 *       right[node] <- rightRotate(right[node])
	 *       node <- leftRotate(node)
	 *     parent <- pop[stack]
	 *     if parent <- NIL
	 *       root <- node
	 *     else if e < data[parent]
	 *       left[parent] <- node
	 *     else
	 *       right[parent] <- node
	 *     break
	 */
	public void delete_iterative(E e) {
		if (root == null) {
			return;
		}
		Node parent = root;
		Node current = root;
		Stack<Node> stack = new Stack<Node>();
		while (current != null) {
			if (e.compareTo(current.data) < 0) {
				parent = current;
				stack.push(current);
				current = current.left;
			}
			else if (e.compareTo(current.data) > 0) {
				parent = current;
				stack.push(current);
				current = current.right;
			}
			else {
				// No children
				if (current.left == null && current.right == null) {
					if (e.compareTo(parent.data) < 0) {
						parent.left = null;
					}
					else {
						parent.right = null;
					}
					break;
				}
				// One chidlren
				if (current.left == null) {
					if (e.compareTo(parent.data) < 0) {
						parent.left = current.right;
					}
					else {
						parent.right = current.right;
					} 
					break;
				}
				if (current.right == null) {
					if (e.compareTo(parent.data) < 0) {
						parent.left = current.left;
					}
					else {
						parent.right = current.left;
					} 
					break;
				}
				// Two children
				else {
					Node min = findMin(current.right);
					current.data = min.data;
					parent = current;
					e = min.data;
					stack.push(current);
					current = current.right;
				}
			}
		}
		
		// No balancing requried
		if ((e.compareTo(parent.data) < 0 && parent.right != null) ||
				(e.compareTo(parent.data) >= 0 && parent.left != null)) {
			// No rebalancing required as no heights changed.
			return;
		}
		
		// Updating heights and balancing nodes
		while (!stack.isEmpty()) {
			// Updating heights
			Node node = stack.pop();
			int heightLeft = node.left != null ? node.left.height : -1;
			int heightRight = node.right != null ? node.right.height : -1;
			node.height = Math.max(heightLeft, heightRight) + 1;
			
			// Skip if balancing not required.
			if (Math.abs(heightLeft-heightRight) <= 1) {
				continue;
			}
			
			// Balancing required.
			// Left left case
			if (heightLeft - heightRight > 1 && e.compareTo(node.left.data) < 0) {
				node = rightRotate(node);
			}
			// Right right case
			else if (heightRight - heightLeft > 1 && e.compareTo(node.right.data) > 0) {
				node = leftRotate(node);
			}
			// Left right case
			else if (heightLeft - heightRight > 1 && e.compareTo(node.left.data) > 0) {
				node.left = leftRotate(node.left);
				node = rightRotate(node);
			}
			// Right left case
			else if (heightRight - heightLeft > 1 && e.compareTo(node.right.data) < 0) {
				node.right = rightRotate(node.right);
				node = leftRotate(node);
			}
			// Attach balanced node to its parent.
			Node parentOfUnbalanced = !stack.isEmpty() ? stack.pop() : null;
			if (parentOfUnbalanced == null) {
				root = node;
			}
			else if (e.compareTo(parentOfUnbalanced.data) < 0){
				parentOfUnbalanced.left = node;
			}
			else {
				parentOfUnbalanced.right = node;
			}
			break;
		}
	}
	
	private Node findMin(Node node) {
		Node min = node;
		while (min.left != null) {
			min = min.left;
		}
		return min;
	}
	
	private class Node{
		public E data;
		public Node left;
		public Node right;
		public int height;
		
		public Node(E data) {
			this.data = data;
		}
	}
}
