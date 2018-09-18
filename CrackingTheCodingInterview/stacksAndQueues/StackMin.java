package stacksAndQueues;

/**
 * Question:
 * Design a stack which in addition to push and pop, has a function min which returns the minimum element? All should be in O(1)
 */
public class StackMin<E extends Comparable<E>> {
	private Node head;

	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. This solution is not efficient as we are wasting memory in storing duplicate min values.
	 * 2. Better solution is to:
	 *    a. maintain a separate stack called min which stores the min nodes.
	 *    a. Whenever a new min comes while pushing, we push that new node in min stack also.
	 *    b. When pop happens, then we check that popped node is the one which is min node. If yes, pop from min stack as well.
	 * 
	 * Pseudo code:
	 * push(e):
	 *   min <- (head != NIL and min[head] < e) ? min[head] : e
	 *   node <- node(e, min)
	 *   next[node] <- head
	 *   head <- node
	 */
	public void push(E e) {
		E min = (head != null && head.min.compareTo(e) < 0) ?
				head.min : e;
		Node node = new Node(e, min);
		node.next = head;
		head = node;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * pop():
	 *   if head = NIL
	 *     return NIL
	 *   value <- data[head]
	 *   head <- next[head]
	 *   return value
	 */
	public E pop() {
		if (head == null) {
			return null;
		}
		E value = head.data;
		head = head.next;
		return value;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * min():
	 *   return min[head]
	 */
	public E min() {
		return head.min;
	}
	
	private class Node{
		private Node next;
		private E min; // TO find min in O(1), we are storing min for every node, thus increasing the space by O(n).
		private E data;
		
		public Node(E data, E min) {
			this.data = data;
			this.min = min;
		}
	}
}
