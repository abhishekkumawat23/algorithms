package linkedLists;

/**
 * Question:
 * Check if linked list is palindrome.
 */
public class Palindrome {

	/**
	 * Time: O(n), Space: O(1)
	 * 
	 * Important points:
	 * 1. To find palindrome, we are first going to the middle node by runner technique. Then we are reversing the below half and then comparing it with first half.
	 * 
	 * Pseudo code:
	 * isPalindrome(head):
	 *   slow <- head
	 *   fast <- head
	 *   while fast != NIL and next[fast] != NIL
	 *     slow <- next[slow]
	 *     fast <- next[next[fast]]
	 *   reverseHead <- (fast = NIL) ? reverse[slow] : reverse[next[slow]] // fast = NIL is case of even length and other one is of odd length.
	 *   current <- head
	 *   currentReversed = reverseHead
	 *   while currentReversed != NIL
	 *     if data[currentReversed] != data[current]
	 *       return false
	 *     current <- next[current]
	 *     currentReversed <- currentnext[Reversed]
	 *   return true
	 */
	public boolean isPalindrome(Node head) {
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		// Fast == null is the scenario of even length. Other one is one of odd length.
		Node reverseHead = (fast == null) ? reverse(slow) : reverse(slow.next);
		Node current = head;
		Node currentReversed = reverseHead;
		while (currentReversed != null) {
			if (currentReversed.next != current.next) {
				return false;
			}
			current = current.next;
			currentReversed = currentReversed.next;
		}
		return true;
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Pseudo code:
	 * reverse(head):
	 *   prev <- NIL
	 *   current <- head
	 *   next <- head.next
	 *   while current != NIL
	 *     next[current] <- prev
	 *     prev <- current
	 *     current <- next
	 *     next <- next[next]
	 *   head <- prev
	 *   return head
	 */
	private Node reverse(Node head) {
		Node prev = null;
		Node current = null;
		Node next = null;
		
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
	}
}
