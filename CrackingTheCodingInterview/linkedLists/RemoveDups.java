package linkedLists;

import java.util.HashSet;

/**
 * Question:
 * Remove duplicates from an unsorted linked list
 * What if temporary buffer is not allowed? 
 */
public class RemoveDups {

	/**
	 * Time: O(n); Space: O(n) worst case possible when no duplicates.
	 * 
	 * Important points:
	 * 1. We are using hashSet instead of using array or integer bits as space because data is integer and no character.
	 *    In case of character, ascii charset has 128 chars and alphabets are 26. So using arrya and integer bits 
	 *    
	 * Pseudo code:
	 * removeDups(head):
	 *   prev <- current <- head
	 *   while current != NIL
	 *     if hashSet[data[current]] = NIL
	 *       add(hashSet, data[current])
	 *       prev <- current
	 *     else
	 *       next[prev] <- next[current]
	 *     current <- next[current]  
	 */
	public void removeDups_withExtraSpace(Node head) {
		Node prev = head;
		Node current = head;
		HashSet<Integer> hashSet = new HashSet<Integer>();
		while (current != null) {
			if (!hashSet.contains(current.data)) {
				hashSet.add(current.data);
				prev = current;
			}
			else {
				prev.next = current.next;
			}
			current  = current.next;
		}
	}
	
	/**
	 * Time: O(n^2) worst case when no duplicates; Space: O(1)
	 * Increasing space complexity is better than increasing time complexity. So this approach is not preferred.
	 * 
	 * Important points:
	 * 1. As we have to remove dups, we can remove it while iterating.`
	 * 
	 * Pseudo code:
	 * removeDups(head):
	 *   current <- head
	 *   while current != NIL
	 *     prev <- current
	 *     checker <- next[current]
	 *     while checker != NIL
	 *       if data[checker] = data[current]
	 *         next[prev] <- next[checker]
	 *       else
	 *         prev <- checker
	 *       checker <- next[checker]
	 *     current <- next[current]
	 */
	public void removeDups_withoutExtraSpace(Node head) {
		Node current = head;
		while (current != null) {
			Node prev = current;
			Node runner = current.next;
			while (runner != null) {
				if (runner.data == current.data) {
					prev.next = runner.next;
				}
				else {
					prev = runner;
				}
				runner = runner.next;
			}
			current = current.next;
		}
	}
	
	private class Node{
		public Node next;
		public int data;
	}
}
