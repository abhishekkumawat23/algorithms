package linkedLists;

/**
 * Question:
 * Partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x.
 * If x is contained in the list, the values of x only need to be after the elements less than x.
 */
public class Partition {

	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. If the alogrithm is stable, it respects the insertion order for elements.
	 * 2. In this algo, as we are adding the elements at head, and thus inversing the insertion order. 
	 * 
	 * Pseudo code:
	 * partition(head, x):
	 *   prev <- head
	 *   current <- next[head]
	 *   while current != NIL
	 *     if data[current] < x
	 *       next[prev] <- next[current]
	 *       temp <- head
	 *       head <- current
	 *       next[head] <- temp
	 *     else
	 *       prev <- current
	 *     current <- next[current]
	 */
	public void partition_withoutStable(Node head, int x) {
		Node prev = head;
		Node current = head.next;
		while (current != null) {
			if (current.data < x) {
				prev.next = current.next;
				Node temp = head;
				head = current;
				head.next = temp;
			}
			else {
				prev = current;
			}
			current = current.next;
		}
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. Here we are creating two linked lists one is before and one is after.
	 * 2. Space is still O(1) as we are just changing links but not actually using additional space.
	 * 3. In case there are lot of shiftings in linked list, its better to make multiple lists out of single list and then later merge it. Its efficient both time wise and space wise as we are only changing links.
	 * 
	 * Pseudo code:
	 * partition(head, x):
	 *   current <- head
	 *   while current != NIL
	 *     if data[current] < x
	 *       if beforeTail = NIL
	 *         beforeHead <- beforeTail <- current
	 *       else
	 *         next[beforeTail] <- current
	 *         beforeTail <- next[beforeTail]
	 *     else
	 *       if afterTail = NIL
	 *         afterHead <- afterTail <- current
	 *       else
	 *         next[afterTail] <- current
	 *         afterTail <- next[afterTail]
	 *     current <- next[current]
	 *   
	 *   if beforeTail = NIL
	 *     return afterHead
	 *   next[before] <- afterHead
	 *   return beforeHead
	 *         
	 */
	public Node partition_withStable(Node head, int x) {
		Node current = head;
		Node beforeHead = null;
		Node afterHead = null;
		Node beforeTail = null;
		Node afterTail = null;
		
		while (current != null) {
			// Create before list
			if (current.data < x) {
				if (beforeTail == null) {
					beforeTail = beforeHead = current;
				}
				else {
					beforeTail.next = current;
					beforeTail = beforeTail.next;
				}
			}
			//Create after list
			else {
				if (afterTail == null) {
					afterTail = afterHead = current;
				}
				else {
					afterTail.next = current;
					afterTail = afterTail.next;
				}
			}
			current = current.next;
		}
		
		if (beforeHead == null) {
			return afterHead;
		}
		
		// Merging before and after lists.
		beforeTail.next = afterHead;
		return beforeHead;
	}
	
	private class Node{
		public Node next;
		public int data;
	}
}
