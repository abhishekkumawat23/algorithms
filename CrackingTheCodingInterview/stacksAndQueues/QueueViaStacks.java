package stacksAndQueues;

import java.util.Stack;

/**
 * Question:
 * Implement queue using two stacks.
 *
 */
public class QueueViaStacks<E> {
	Stack<E> stack1 = new Stack<E>();
	Stack<E> stack2 = new Stack<E>();
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * enqueue(e):
	 *   push(stack1, e)
	 */
	public void enqueue(E e) {
		stack1.push(e);
	}
	
	/**
	 * Time: amortized O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. If push happens for n elements and then first time pop happens, first pop will take O(n) time but subsequent pop will take O(1) time.
	 * 
	 * Pseudo code:
	 * dequeue():
	 *   if size[stack1] = 0 and size[stack2] = 0
	 *     return NIL
	 *   if size[stack2] = 0
	 *     while size[stack1] != 0
	 *       push(stack2, pop[stack1])
	 *   return pop[stack2]
	 */
	public E dequeue() {
		if (stack1.size() == 0 && stack2.size() == 0) {
			return null;
		}
		if (stack2.size() == 0) {
			while (stack1.size() != 0) {
				stack2.push(stack1.pop());
			}
		}
		
		return stack2.pop();
	}
	
	/**
	 * Time: amortized O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * peek():
	 *   if size[stack1] = 0 and size[stack2] = 0
	 *     return NIL
	 *   if size[stack2] = 0
	 *     while size[stack1] != 0
	 *       push(stack2, pop[stack1])
	 *   return peek[stack2]
	 */
	public E peek() {
		if (stack1.size() == 0 && stack2.size() == 0) {
			return null;
		}
		if (stack2.size() == 0) {
			while (stack1.size() != 0) {
				stack2.push(stack1.pop());
			}
		}
		
		return stack2.peek();
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * isEmpty():
	 *   return size[stack1] = 0 and size[stack2] = 0
	 */
	public boolean isEmpty() {
		return stack1.size() == 0 && stack2.size() == 0;
	}
}
