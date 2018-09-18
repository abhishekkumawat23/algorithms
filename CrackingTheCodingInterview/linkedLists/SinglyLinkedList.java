package linkedLists;

/**
 * Question:
 * Implement a singly linked list.
 */
public class SinglyLinkedList<E> {
	private Node head;
	private Node tail;
	private int size = 0;
	
	/**
	 * Time: O(n), Space: O(1)
	 * O(1) for addFirst and addLast, O(n) for add at random index.
	 * So, linkedList is better than arrayList for addFirst.
	 * 
	 * Important points:
	 * 1. LinkedList is better only for addFirst. addFirst is O(1) in linkedList but O(n) in arrayList.
	 * 2. Otherwise, linkedList is in general bad than arrayList. Linked access is O(n) while arrayList access is O(n)
	 * 3. ArrayList uses cache memory and thus retrieval is even faster. This is because arrayList stores elements in contiguous block of memory.
	 * 4. Currently LinkedList is wrapping the Node class. We could have directly written our data structure in one class only.
	 *    i.e. Node class could have been directly exposed as data structure and add,remove methods can be added in Node class itself.
	 *    The reason we are not doing that is because every object holding instance of this linkedList will hold the head node object.
	 *    If head is changed (i.e. someone do addFirst), multiple objects will holding stale instance of head which causes inconsistencies.
	 *    Thus wrapping Node class under another class is required to solve the problem.
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
	 *     current <- head
	 *     for i = 2 to index - 1
	 *       current <- next[current]
	 *     next <- next[current]
	 *     next[current] <- node[e]
	 *     next[next[current]] <- next
	 *   size <- size + 1
	 */
	public void add(int index, E e) {
		Node node = new Node(e);
		// Size 0 scenario
		if (size == 0) {
			head = tail = node;
		}
		// addFirst scenario
		else if (index <= 0) {
			Node next = head;
			head = node;
			head.next = next;
		}
		// addLast scenario
		else if (index >= size) {
			Node prev = tail;
			tail = node;
			prev.next = tail;
		}
		// addAtIndex scenario
		else {
			Node current = head;
			for (int i = 1; i < index; i++) {
				current.next = head;
			}
			Node next = current.next;
			current.next = node;
			current.next.next = next;
		}
		size++;
	}
	
	/**
	 * Time: O(n), Space: O(1)
	 * removeFirst is O(1), removeLast is O(n), removeAtIndex is O(n)
	 * 
	 * Important points:
	 * 1. removeLast is the scenario where doubly linked list helps.
	 * 2. doubly linked list also reduces the time complexity of add/remove to O(n/2) as we can start loop from head if index < n/2 and from tail if index > n/2.
	 * 
	 * Pseudo code:
	 * remove(index):
	 *   if size = 0
	 *     head <- tail <- NIL
	 *   else if index <= 1
	 *     head <- next[head]
	 *   else
	 *     if index >= size
	 *       index <- size
	 *     current <- head
	 *     for i = 2 to index - 1
	 *       current <- next[current]
	 *     next[current] <- next[next[current]]
	 *     if index = size
	 *       tail <- next[current]
	 *   size <- size - 1
	 */
	public void remove(int index) {
		// Size 0 scenario
		if (size == 1) {
			head = tail = null;
		}
		//removeFirst scenario
		else if (index <= 0) {
			head = head.next;
		}
		else {
			if (index >= size - 1) {
				index = size - 1;
			}
			// removeAtIndex scenario.
			Node current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			current.next = current.next.next;
			// removeAtLast scenario
			if (index == size - 1) {
				tail = current.next;
			}
		}
		size--;
	}
	
	/**
	 * Time: O(n), Space: O(1)
	 * LinkedList is bad at accessing elements. ArrayList has O(1) access but linkedList has O(n) access.
	 * 
	 * Important points:
	 * 1. If we use doubly linked list, we can access in O(n/2) time.
	 * 
	 * Pseudo code:
	 * get(index):
	 *   if index <= 1
	 *     return data[head]
	 *   if index >= size
	 *     return data[tail]
	 *   current <- head
	 *   for i = 2 to index
	 *     current <- next[current]
	 *   return data[current]
	 */
	public E get(int index) {
		if (index <= 1) {
			return head.data;
		}
		if (index >= size - 1) {
			return tail.data;
		}
		Node current = head;
		for (int i = 1; i <= index; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	/**
	 * Time: O(n); Space: O(1)
	 * 
	 * Important point:
	 * 1. Doubly linkedList will not help here. It will also take O(n).
	 * 
	 * Pseudo code:
	 * contains(e):
	 *   current <- head
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
	
	private class Node {
		public Node next;
		public E data;
		
		public Node(E e) {
			this.data = e;
		}
	}
}
