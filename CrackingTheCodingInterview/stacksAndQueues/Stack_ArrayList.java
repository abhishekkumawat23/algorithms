package stacksAndQueues;

/**
 * Question:
 * Implement stack using array list.
 */
public class Stack_ArrayList<E> {
	private final float LOAD_FACTOR = 0.75f;
	private final int INCREMENT_FACTOR = 2;
	private final int INITIAL_CAPACITY = 16;
	@SuppressWarnings("unchecked")
	private E[] data = (E[]) new Object[INITIAL_CAPACITY];
	private int size = 0;

	/**
	 * Time: amortized O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. amortized time because for adding n elements we need to copy (n/2 + n/4 + .. + 1) ~= N elements. So on average 1 element copy for 1 addition.
	 * 
	 * Pseudo code:
	 * loadFactor <- 0.75
	 * incrementFactor <- 2
	 * push(e):
	 *   if size >= loadFactor*length[data]
	 *      newSize <- incrementFactor*size
	 *      newData <- create[newSize]
	 *      copy(data, newData)
	 *      data <- newData
	 *   data[size] <- e
	 *   size <- size + 1 
	 */
	public void push(E e) {
		if (size >= LOAD_FACTOR*data.length) {
			int newSize = INCREMENT_FACTOR*size;
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[newSize];
			System.arraycopy(data, 0, newData, 0, data.length);
			data = newData;
		}
		data[size] = e;
		size++;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * pop():
	 *   value <- data[size-1]
	 *   data[size-1] <- null
	 *   size <- size - 1
	 *   return value
	 */
	public E pop() {
		E value = data[size-1];
		data[size-1] = null;
		size--;
		return value;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * peek():
	 *   return data[size-1]
	 */
	public E peek() {
		return data[size-1];
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * isEmpty():
	 *   return size != 0
	 */
	public boolean isEmpty() {
		return size != 0;
	}
}
