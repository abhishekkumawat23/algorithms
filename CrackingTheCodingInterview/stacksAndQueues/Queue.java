package stacksAndQueues;

/**
 * Question: Implement Queue.
 */
public class Queue<E> {
	private Node head;
	private Node tail;

	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * enqueue(e):
	 *   if head = NIL
	 *     head <- tail <- node[e]
	 *   else
	 *     next[tail] <- node[e]
	 *     tail <- next[tail]
	 */
	public void enqueue(E e) {
		if (head == null) {
			head = tail = new Node(e);
		}
		else {
			tail.next = new Node(e);
			tail = tail.next;
		}
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * dequeue():
	 *   value <- head
	 *   if head = tail
	 *     head <- tail <- null
	 *   else
	 *     head <- next[head]
	 *   return data[value]
	 */
	public E dequeue() {
		Node value = head;
		if (head == tail) {
			head = tail = null;
		}
		else {
			head = head.next;	
		}
		return value.data;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * peek():
	 *   return data[head];
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
