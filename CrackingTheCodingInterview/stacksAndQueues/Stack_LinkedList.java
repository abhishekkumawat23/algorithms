package stacksAndQueues;

/**
 * Question:
 * Implement stack using linked list.
 */
public class Stack_LinkedList<E> {
	private Node head;
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. To get const time for push and pop, we will push and pop from head
	 * 2. Linkedlist takes more memory than ArrayList. So we should prefer arrayList over linkedlist.
	 * 3. Only problem in arrayList is amortized time. So for few of the elements, addition will take time because of array copy.
	 * 
	 * Pseudo code:
	 * push(e):
	 *   if head = NIL
	 *     head <- node[e]
	 *   else
	 *     node <- node[e]
	 *     next[node] <- head
	 *     head <- node
	 */
	public void push(E e) {
		if (head == null) {
			head = new Node(e);
		}
		else {
			Node node = new Node(e);
			node.next = head;
			head = node;
		}
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * pop():
	 *   if head = NIL
	 *     return NIL
	 *   node <- head
	 *   head <- next[head]
	 *   return node
	 */
	public E pop() {
		if (head == null) {
			return null;
		}
		Node node = head;
		head = head.next;
		return node.data;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * peek():
	 *   return head
	 */
	public E peek() {
		return head.data;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * isEmpty():
	 *   return head = NIL
	 */
	public boolean isEmpty() {
		return head == null;
	}
	
	private class Node{
		public Node next;
		public E data;
		
		public Node(E data) {
			this.data = data;
		}
	}
}
