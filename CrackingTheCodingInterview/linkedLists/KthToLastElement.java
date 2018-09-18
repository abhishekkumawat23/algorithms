package linkedLists;

/**
 * Question:
 * Find the kth to last element of a singly linked list.
 */
public class KthToLastElement {

	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important points:
	 * 1. kth from last is n-k+1 from start.
	 * 2. We can head start p1 pointer from kth element from beginning. Now we start p2 from head. So when p2 becomes null, we reach to kth from last.
	 * 	
	 * Pseudo code:
	 * findKthToLast(head, k):
	 *   p1 <- head
	 *   p2 <- head
	 *   for i = 2 to k
	 *     p1 <- next[p1]
	 *   while p1 != NIL
	 *     p1 <- next[p1]
	 *     p2 <- next[p2]
	 *   return data[p2]
	 */
	public int findKthToLast(Node head, int k) {
		Node p1 = head;
		Node p2 = head;
		
		for (int i = 1; i <= k; i++) {
			p1 = p1.next;
		}

		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return p2.data;
	}
	
	private class Node{
		public int data;
		public Node next;
	}
}
