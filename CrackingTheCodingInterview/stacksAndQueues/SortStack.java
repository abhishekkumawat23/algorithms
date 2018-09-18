package stacksAndQueues;

import java.util.Stack;

/**
 * Question:
 * Sort a stack such that smallest items are on the top.
 * You can use an additional temprary stack, but any other data structure can't be used.
 */
public class SortStack<E extends Comparable<E>> {

	/**
	 * Time: O(n^2); Space: O(1)
	 * 
	 * Important points:
	 * 1. We are storing largest point at bottom as we need to sort such that smallest items are on the top.
	 * 2. If infinite stacks were allowed to sort instead of 2 stacks, we can use merge sort or quick sort with stacks as data structure:
	 *    a. Merge sort: We would create two extra stacks and divide the stack into two parts.
	 *       We would recursively sort each stack and then merge them back together in sorted order into the original stack.
	 *       It would require creation of 2 additional stacks per level of recursion.
	 *    b. Quick sort: We would create two additional stacks and divide the stack into two stacks based on a pivot element.
	 *       The two stacks would be recursively sorted, and then merged back together into the original stack.
	 *       It would also require 2 additional stacks per level of recursion.
	 * 
	 * Pseudo code:
	 * sort(stack1):
	 *   while size[stack1] != 0
	 *     current <- pop[stack1]
	 *     while size[stack2] != 0 and peek[stack2] <= current
	 *       push(stack1, pop[stack2])
	 *     push(stack2, current)
	 *   return stack2
	 */
	public Stack<E> sort(Stack<E> stack1) {
		Stack<E> stack2 = new Stack<E>();
		while (stack1.size() != 0) {
			E current = stack1.pop();
			while (stack2.size() != 0 && stack2.peek().compareTo(current) <= 0) {
				stack1.push(stack2.pop());
			}
			stack2.push(current);
		}
		return stack2;
	}
}
