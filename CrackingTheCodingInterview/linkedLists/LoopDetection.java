package linkedLists;

/**
 * Question:
 * Given a circular linked list, implment an alogrithm that returns the node at the begining of the loop.
 * Input: A B C D E C should return C
 */
public class LoopDetection {

	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. Lets say non-circular length is k and circular length is c.
	 * 2. When slow pointer reaches k i.e. starting of circular loop. By that time fast walks 2k in total. i.e. it walks extra k in circular.
	 * 3. So fast is c - k behind slow when slow is at circular starting point.
	 * 4. With every move, fast closes the gap with slow by 1. So by time c - k gap is covered, fast will move 2(c-k) and slow will move c-k.
	 * 5. At this point both slow and fast collidies, so its called colliding point. As slow travelled c-k from starting point, both slow and fast are k distance behind fromcircular starting point.
	 * 6. Now we move the fast pointer to head and move both pointer at same pace. So they both will reach k distance to reach at starting point and will collide there.
	 * 7. If c is less than k. Then also everything is same but in calculations we will say, k%c, c-k%c etc etc.
	 * 
	 * Pseudo code:
	 * findLoopBegining(head):
	 *   p1 <- head
	 *   p2 <- head
	 *   while p1 != NIL and next[p1] != NIL
	 *     if p1 = p2
	 *       break
	 *     p1 <- next[next[p1]]
	 *     p2 <- next[p2]
	 *   if p1 = NIL or next[p1] = NIL
	 *     return NIL
	 *   p1 <- head
	 *   while p1 != p2
	 *     p1 <- next[p1]
	 *     p2 <- next[p2]
	 *   return p1
	 */
	public Node findLoopBegining(Node head) {
		Node p1 = head;
		Node p2 = head;
		// Finding collision point.
		while (p1 != null && p1.next != null) {
			if (p1 == p2) {
				break;
			}
			p1 = p1.next.next;
			p2 = p2.next;
		}
		// Case of list not being circular.
		if (p1 == null || p1.next == null) {
			return null;
		}
		// Finding circle starting point. 
		p1 = head;
		while (p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}
	
	private class Node{
		public Node next;
	}
}
