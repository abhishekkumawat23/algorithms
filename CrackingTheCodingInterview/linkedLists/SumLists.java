package linkedLists;

/**
 * Question:
 * There are two numbers represented by linked lists where each node contains a single digit.
 * Write a function that adds the two numbers and returns the sum as linked list.
 * 1. Case 1: Digits are stored in reverse order i.e. 1's digit is at the head of the list.
 *    Example: 7 1 6 + 5 9 2 gives 2 1 9
 * 2. Case 2: Digits are stored in forward order
 *    Example: 6 1 7 + 2 9 5 gives 9 1 2
 */
public class SumLists {

	/**
	 * Time: O(m+n), Space:O(n)
	 * We can make space O(1) if we modify the existing lists.
	 * 
	 * Pseudo code:
	 * sumLists(head1, head2):
	 *   current1 <- head1
	 *   current2 <- head2
	 *   while current1 != NIL or current2 != NIL
	 *     current1Val <- (current1 != NIL) ? data[current1] : 0
	 *     current2Val <- (current2 != NIL) ? data[current2] : 0
	 *     sum <- current1Val + current2Val + carry
	 *     sum <- sum % 10
	 *     carry <- sum / 10
	 *     if sumHead = NIL
	 *       sumHead <- sumTail <- node[sum]
	 *     else
	 *       next[sumTail] <- node[sum]
	 *       sumTail <- next[sumTail]
	 *     current1 <- next[current1]
	 *     current2 <- next[current2]
	 *   if carry != 0
	 *     next[sumTail] <- node[carry]
	 *     sumTail <- next[sumTail]
	 *   return sumHead
	 */
	public Node sumLists_reverseOrder(Node head1, Node head2) {
		Node current1 = head1;
		Node current2 = head2;
		Node sumHead = null;
		Node sumTail = null;
		int carry = 0;
		while (current1 != null || current2 != null) {
			int current1Val = (current1 != null) ? current1.data : 0;
			int current2Val = (current2 != null) ? current2.data : 0;
			int sum = current1Val + current2Val + carry;
			sum = sum % 10;
			carry = sum / 10;
			if (sumHead == null) {
				sumHead = sumTail = new Node(sum);
			}
			else {
				sumTail.next = new Node(sum);
				sumTail = sumTail.next;
			}
			current1 = current1.next;
			current2 = current2.next;
		}
		// 456(3 digits)+789(3 digits) = 1245(four digits). This case is to accomodate 4th digit.
		if (carry != 0) {
			sumTail.next = new Node (carry);
			sumTail = sumTail.next;
		}
		return sumHead;
	}
	
	/**
	 * Time: O(m+n); Space: O(n)
	 * We can reduce this O(n) space to O(1) if we modify the input list itself.
	 * 
	 * Important:
	 * 1. In linked list, we can do things from right to left only. Whenever there is a requirement to do things from left to right, solution is to reverse the linked list.
	 * 
	 * Pseudo code:
	 * sumLists(head1, head2):
	 *   reverse(head1)
	 *   reverse(head2)
	 *   sumHead <- sumLists_reverseOrder(head1, head2)
	 *   return reverse(sumHead)
	 */
	public Node sumLists_forwardOrder(Node head1, Node head2) {
		head1 = reverse(head1);
		head2 = reverse(head2);
		Node sumHead = sumLists_reverseOrder(head1, head2);
		return reverse(sumHead);
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important:
	 * 1. In linked list, we can do things from right to left only. Whenever there is a requirement to do things from left to right, solution is to reverse the linked list.
	 * 2. We can use stack also to reverse the linked list but its space wastage. In this alog, we are reversing wihout using any space.
	 * 
	 * Pseudo code:
	 * reverse(head):
	 *   prev <- NIL
	 *   current <- head
	 *   next <- next[head]
	 *   while current != NIL
	 *     next[current] <- prev
	 *     prev <- current
	 *     current <- next
	 *     next <- next[next]
	 *   head <- prev
	 */
	private Node reverse(Node head) {
		Node prev = null;
		Node current =  head;
		Node next = head.next;
		while (current != null) {
			current.next = prev;
			prev = current;
			current = next;
			next = next.next;
		}
		head = prev;
		return head;
	}
	
	private class Node{
		public Node next;
		public int data;
		
		public Node(int data) {
			this.data = data;
		}
	}
}
