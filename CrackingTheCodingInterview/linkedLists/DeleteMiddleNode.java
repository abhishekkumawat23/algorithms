package linkedLists;

/**
 * Question:
 * Delete a node in the middle of a singly linked list, given only access to that node.
 * Example: if input is a -> b -> c -> d -> e, then c is given as input. output should be a -> b -> d -> e
 */
public class DeleteMiddleNode {

	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. We can't delete the element of which pointer is given. We need to previous element to delete the element.
	 * 2. So we apply a hack. We copy the content of next element to current element and then delete the next element.
	 * 3. Thus if we have to delete an element and pointer to that is given, we can do it in O(1) time.
	 * 4. We can't delete last element as there is no element next to it. So in this case we can make that element dummy element.
	 * 
	 * Pseudo code:
	 * deleteMiddleNode(middle):
	 *   data[middle] <- data[next[middle]]
	 *   next[middle] <- next[next[middle]]
	 */
	public void deleteMiddleNode(Node middle) {
		middle.data = middle.next.data;
		middle.next = middle.next.next;
	}
	
	private class Node{
		public Node next;
		public int data;
	}
}
