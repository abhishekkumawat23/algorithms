package linkedLists;

/**
 * Question:
 * Find if two linked list intersect, return the intersecting node.
 * Intersection is based on reference and not by value
 */
public class Intersection {

	/**
	 * Time: O(m+n); Space: O(1)
	 * 
	 * Important points:
	 * 1. If the question doesn't ask intersection point, but only asks if the lists contains an intersection or not, when check if their tails match.
	 *    Because for lists to be an intersection, they must have same tail node.
	 * 1. For finding intersection, we first need to find both list lengths. Then we need to headstart the longer list to the length difference.
	 *    Then we need to move both lists together to find the intersection.
	 * 
	 * Pseudo code:
	 * findIntersection(head1, head2):
	 *   current1 <- head1
	 *   current2 <- head2
	 *   while current1.next != NIL
	 *     len1 <- len1 + 1
	 *     current1 <- next[current1]
	 *   while current2.next != NIL
	 *     len2 <- len2 + 1
	 *     current2 <- next[current2]
	 *   if current1 != current2
	 *     return null // If tails don't match, not an intersection.
	 *   first <- (len1 >= len2) ? head1 : head2;
	 *   second <- (len1 >= len2) ? head2 : head1;
	 *   lenDiff <- mod[len2-len1]
	 *   for i = 1 to lenDiff
	 *     first <- next[first]
	 *   while first != NIL and second != NIL
	 *     if first = second
	 *       return first
	 *   return NIL
	 */
	public Node findIntersection(Node head1, Node head2) {
		Node current1 = head1;
		Node current2 = head2;
		int len1 = 0;
		int len2 = 0;
		while (current1.next != null) {
			len1++;
			current1 = current1.next;
		}
		
		while(current2.next != null) {
			len2++;
			current2 = current2.next;
		}
		
		if (current1 != current2) {
			return null; // If tails don't match, lists are not an intersection.
		}
		
		Node first = (len1 >= len2) ? head1 : head2;
		Node second = (len1 >= len2) ? head2 : head1;
		int lengthDiff = Math.abs(len1-len2);
		for (int i = 0; i < lengthDiff; i++) {
			first = first.next;
		}
		while (first != null && second != null) {
			if (first == second) {
				return first;
			}
		}
		return null;
	}
	
	private class Node {
		public Node next;
	}
}
