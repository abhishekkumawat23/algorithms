package linkedLists;

/**
 * Question:
 * Arrange a1->a2->...->an->b1->b2->...->bn to a1->b1->a2->b2->...->an->bn
 */
public class RearrangeLinkedList {

	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. We are using runner technique here. In this technique:
	 *    a. Either both pointers run with different speeds, or
	 *    b. Both pointers start from different locations.
	 * 
	 * Pseudo code:
	 * rearrange(head):
	 *   p1 <- p2 <- head
	 *   while p1 != NIL
	 *     p1 <- next[next[p1]
	 *     p2 <- next[p2]
	 *   p1 <- head
	 *   while next[p2] != NIL
	 *     p1Next <- next[p1]
	 *     p2Next <- next[p2]
	 *     next[p1] <- p2
	 *     next[p2] <- p1Next
	 *     p1 <- p1Next
	 *     p2 <- p2Next
	 *   next[p1] <- p2
	 */
	public void rearrange(Node head) {
		Node p1 = head;
		Node p2 = head;
		while (p1 != null) {
			p1 = p1.next.next;
			p2 = p2.next;
		}
		
		p1 = head;
		while (p2.next != null) {
			Node p1Next = p1.next;
			Node p2Next = p2.next;
			p1.next = p2;
			p2.next = p1Next;
			p1 = p1Next;
			p2 = p2Next;
		}
		p1.next = p2;
	}
	
	private class Node{
		public Node next;
	}
}
