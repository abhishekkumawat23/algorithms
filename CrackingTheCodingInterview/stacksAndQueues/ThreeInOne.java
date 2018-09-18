package stacksAndQueues;

/**
 * Question:
 * Implement three stacks using a single array 
 */
public class ThreeInOne<E> {
	private final int INITIAL_CAPACITY = 16;
	private final int INCREMENT_FACTOR = 2;
	private final int NO_OF_STACKS = 3;
	private final float LOAD_FACTOR = 0.75f;
	private int[] size = new int[] { 0, 0, 0};
	private int[] capacity = new int[] {INITIAL_CAPACITY, INITIAL_CAPACITY, INITIAL_CAPACITY};
	private int[] start = new int[] {0, capacity[0], capacity[0]+capacity[1]};
	@SuppressWarnings("unchecked")
	private E[] data = (E[]) new Object[NO_OF_STACKS];

	/**
	 * Time: amortized O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. We are storing one stack at starting, one stack at middle and one stack at end of array.
	 * 2. Individual stack when reaches capacity, array will be expnaded with newCapacity. Thus small stack will have small capacity and vice versa.
	 * 
	 * Pseudo code:
	 * push(stackNum, e):
	 *   if size[stackNum] >= loadFactor*capacity[stackNum]
	 *     capacity[stackNum] <- incrementFactor*capacity[stackNum]
	 *     newStart <- [0, capacity[0], capacity[0]+capacity[1]]
	 *     newData <- create[capacity[0] + capacity[1] + capacity[2]]
	 *     for i = 1 to noOfStacks
	 *       copy(data, start[i], newData, newStart[i], size[i])
	 *     start <- newStart
	 *     data <- newData
	 *   data[start[stackNum] + size[stackNum]] <- e
	 *   size[stackNum] <- size[stackNum] + 1
	 */
	public void push(int stackNum, E e) {
		if (size[stackNum] >= LOAD_FACTOR*capacity[stackNum]) {
			capacity[stackNum] = INCREMENT_FACTOR*capacity[stackNum];
			int[] newStart = new int[] {0, capacity[0], capacity[0]+capacity[1]};
			@SuppressWarnings("unchecked")
			E[] newData = (E[]) new Object[capacity[0]+capacity[1]+capacity[2]];
			for (int i = 0;  i < NO_OF_STACKS; i++) {
				System.arraycopy(data, start[i], newData, newStart[i], size[i]);
			}
			start = newStart;
			data = newData;
		}
		data[start[stackNum]+size[stackNum]] = e;
		size[stackNum]++;
	}
	
	/**
	 * Time: O(1); Space: O(1) 
	 * 
	 * Pseudo code:
	 * pop(stackNum):
	 *   value <- data[start[stackNum]+size[stackNum]-1]
	 *   data[start[stackNum]+size[stackNum]-1] <- NIL
	 *   size[stackNum] <- size[stackNum] - 1
	 *   return value
	 */
	public E pop(int stackNum) {
		E value = data[start[stackNum]+size[stackNum]-1];
		data[start[stackNum]+size[stackNum]-1] = null;
		size[stackNum]--;
		return value;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * peek(stackNum):
	 *   return data[start[stackNum]+size[stackNum]-1]
	 */
	public E peek(int stackNum) {
		return data[start[stackNum]+size[stackNum]-1];
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * isEmpty(stackNum):
	 *   return size[stackNum] = 0
	 */
	public boolean isEmpty(int stackNum) {
		return size[stackNum] == 0;
	}
}
