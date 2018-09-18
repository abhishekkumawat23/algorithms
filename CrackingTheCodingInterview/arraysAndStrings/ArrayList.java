package arraysAndStrings;

public class ArrayList<E> {
	private int size = 0;
	private static final int INITIAL_CAPACITY = 16;
	private static float LOAD_FACTOR = 0.75f;
	private static float INCREMENT_FACTOR = 1.5f;
	
	@SuppressWarnings("unchecked")
	private E[] data = (E[]) new Object[INITIAL_CAPACITY];

	/**
	 * Time: amortized O(n), Space: O(1).
	 * O(n) because of right shift operation.
	 * For addLast time is amortized O(1). For addFirst its worst i.e. O(n)
	 * 
	 * Important points:
	 * 1. ArrayList are bad in addFirst, bad in add at random index, good in addLast. LinkedList is good in addFirst, addLast but bad in add at index.
	 * 2. Amortized time complexity because elements are copied n/2 + n/4 + 4 + 2 + 1 times for adding n elements which sums up to < n.
	 *    Thus o(n) time for adding n elements, so amortized O(1) time for adding 1 element. This is the scenario of addLast.
	 * 2. In data structure like HashMap, increment factor is 2 (instead of 1.5) because:
	 *    a. In hashMap new array creation is expensive operation as apart from copying re-indexing is also required.
	 *    b. In arrayList, its less as more increment factor means unnecessary memory wastage.
	 * 
	 * Pseudo code:
	 * loadFactor <- 0.75
	 * incrementFactor <- 1.5
	 * add(A, index, e):
	 * 	 if size[A] >= capacity[A]*loadFactor
	 * 	   data[A] <- create(data[A], incrementFactor*capacity[A])
	 *   for i = size[A] to index
	 *     A[i+1] <- A[i] 
	 * 	 A[index] <- e
	 *   size[A] <- size[A] + 1
	 */
	public void add (int index, E e) {
		if (size >= data.length*LOAD_FACTOR) {
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[(int)Math.ceil(INCREMENT_FACTOR*data.length)];
			System.arraycopy(data, 0, newData, 0, data.length);
			data = newData;
		}
		
		for (int i = size-1; i >= index; i--) {
			data[i+1] = data[i];
		}
		
		data[index] = e;
		size++;
	}
	
	/**
	 * Time: O(n), Space: O(1)
	 * O(n) can happen when index is zero. In that case we need to left shift all the elements.
	 * 
	 * Important points:
	 * 1. ArrayList is good at removeLast, bad at removeFirst, bad at remove at random index. LinkedList is good at removeLast, good at removeFirst, bad at random index.
	 * 
	 * Pseudo code:
	 * remove(A, index):
	 *   A[i] <- NIL
	 *   for i = index to size[A]-1
	 *     A[i] <- A[i+1]
	 *   size[A] <- size[A] - 1
	 *   
	 */
	public void remove(int index) {
		data[index] = null;
		for (int i = index; i < size-1; i++) {
			data[i] = data[i+1];
		}
		size--;
	}
	
	/**
	 * Time: O(1), Space: O(1)
	 * 
	 * Important point:
	 * 1. ArrayList is good at access at any random index. ArrayLinkedList is bad at access at any random index.
	 * 
	 * Pseudo code:
	 * get(A, index):
	 *   return A[index]
	 */
	public E get(int index) {
		return data[index];
	}
	
	/**
	 * Time: O(n), Space: O(1)
	 * 
	 * Pseudo code:
	 * contains(A, e):
	 *   for i <- 1 to size[A]
	 *     if A[i] = e
	 *       return i
	 *   return -1 
	 */
	public int contains(E e) {
		for (int i = 0; i < size; i++) {
			if (data[i] == e || data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
}
