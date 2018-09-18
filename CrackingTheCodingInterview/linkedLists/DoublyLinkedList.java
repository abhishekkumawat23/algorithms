package linkedLists;

public class DoublyLinkedList<E> {
	private static int size = 0;
	private Node head = null;
	private Node tail = null;

	/**
	 * Time: O(n/2) ~= O(n); Space: O(1)
	 * Time complexity is better than singly linked list as at max n/2 loop needs to be iterated.
	 * 
	 * Pseudo code:
	 * add(index, e):
	 *   if size = 0
	 *     head <- tail <- node[e]
	 *   else if index <= 1
	 *     next <- head
	 *     head <- node[e]
	 *     next[head] <- next
	 *   else if index > size
	 *     prev <- tail
	 *     tail <- node[e]
	 *     next[prev] <- tail
	 *   else
	 *     if index <= size/2
	 *       current <- head
	 *       for i = 2 to index-1
	 *         current = next[current]
	 *     else
	 *       current <- tail
	 *         for i = size-1 to index-1
	 *           current <- prev[current]
	 *     next <- next[current]
	 *     next[current] <- node[e]
	 *     next[next[current]] <- next
	 *   size <- size + 1
	 *     
	 */
	public void add(int index, E e) {
		Node node = new Node(e);
		if (size == 0) {
			head = tail = node;
		}
		else if (index <= 0) {
			Node next = head;
			head = node;
			head.next = next;
		}
		else if (index >= size) {
			Node prev = tail;
			tail = node;
			prev.next = tail;
		}
		else {
			Node current = null;
			if (index <= size/2) {
				current = head;
				for (int i = 1; i < index; i++) {
					current = current.next;
				}
			}
			else {
				current = tail;
				for (int i = size-2; i >= index-1; i--) {
					current = current.next;
				}
			}
			Node next = current.next;
			current.next = node;
			current.next.next = next;
		}
		size++;
	}
	
	/**
	 * Time: O(n/2) ~= O(n), Space: O(1)
	 * removeFirst is O(1), removeLast is O(1), removeAtIndex is O(n). removeLast in case of singly linkedList is O(n).
	 * 
	 * Pseudo code:
	 * remove(index):
	 *   if size = 1
	 *     head <- tail <- NIL
	 *   else if index <= 1
	 *     head <- next[head]
	 *   else if index >= size
	 *     tail <- prev[tail]
	 *   if index <= size/2
	 *     current <- head
	 *     for i = 2 to index - 1
	 *       current <- next[current]
	 *   else
	 *     current <- tail
	 *     for i = size - 1 to index - 1
	 *       current <- prev[current]
	 *   next[current] <- next[next[current]]
	 *   size <- size - 1
	 */
	public void remove(int index) {
		if (size == 1) {
			head = tail = null;
		}
		else if (index <= 0) {
			head = head.next;
		}
		else if (index >= size - 1) {
			tail = tail.prev;
		}
		else {
			Node current = null;
			if (index <= size/2) {
				current = head;
				for (int i = 1; i <= index-1; i++) {
					current = current.next;
				}
			}
			else {
				current = tail;
				for (int i = size - 2; i >= index - 1; i--) {
					current = current.prev;
				}
			}
			current.next = current.next.next;
		}
	}
	
	/**
	 * Time: O(n/2) ~= O(n); Space: O(1)
	 * 
	 * Pseudo code:
	 * get(index):
	 *   if index <= 1
	 *     return data[head]
	 *   if index >= size
	 *     return data[tail]
	 *   if index <= size/2
	 *     current <- head
	 *     for i = 2 to index
	 *       current <- next[current]
	 *   else
	 *     current <- tail
	 *     for i = size-1 to index
	 *       current <- prev[current]
	 *   return data[current]
	 */
	public E get(int index) {
		if (index <= 1) {
			return head.data;
		}
		if (index >= size) {
			return tail.data;
		}
		Node current = null;
		if (index <= size/2) {
			current = head;
			for (int i = 1; i <= index; i++) {
				current = current.next;
			}
		}
		else {
			current = tail;
			for (int i = size-2; i >= index; i--) {
				current = current.prev;
			}
		}
		return current.data;
	}
	
	/**
	 * Pseudo code:
	 * contains(e):
	 *   for i = 1 to size
	 *     if data[current] = e
	 *       return i
	 *     current <- next[current]
	 *   return -1
	 */
	public int contains(E e) {
		Node current = head;
		for (int i = 0; i < size; i++) {
			if (current.data == e || current.data.equals(e)) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}
	
	private class Node{
		public E data;
		public Node next;
		public Node prev;
		
		public Node(E e) {
			this.data = e;
		}
	}
}
