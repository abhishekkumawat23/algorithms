package stacksAndQueues;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Question:
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Thus, in real life, we would likely start a new stack when the previous stack exceeds some threshold.
 * 1. Implement a data structure StackOfPlates that mimics this.
 *    SetOfPlates should be composed of several stacks and should create a new stack once exceeds capacity.
 *    push and pop methods should behave identically to a single stack (that is pop() should return the same values a it would if there was just a single stack)
 * 2. Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
 */
public class StackOfPlates<E> {
	private static final int THRESHOLD = 128;
	private ArrayList<Stack<E>> setOfStacks = new ArrayList<Stack<E>>();
	
	/**
	 * Time: amortized O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * threshold <- 128
	 * push(e):
	 *   currentStack <- last[setOfStacks]
	 *   if size[currentStack] >= threshold
	 *     stack <- create()
	 *     add(setOfStacks, stack)
	 *     currentStack <- stack
	 *   push(currentStack, node[e])
	 */
	public void push(E e) {
		Stack<E> currentStack = setOfStacks.get(setOfStacks.size()-1);
		if (currentStack.size() >= THRESHOLD) {
			Stack<E> stack = new Stack<E>();
			setOfStacks.add(stack);
			currentStack = stack;
		}
		currentStack.push(e);
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * pop(e):
	 *   currentStack <- last[setOfStacks]
	 *   if size[currentStack] = 0
	 *     remove(setOfStacks, size[setofStacks]-1)
	 *     currentStack <- setOfStacks[size[setOfStacks]-1]
	 *   return pop[currentStack]
	 */
	public E pop(E e) {
		Stack<E> currentStack = setOfStacks.get(setOfStacks.size()-1);
		if (currentStack.size() == 0) {
			setOfStacks.remove(setOfStacks.size()-1);
			currentStack = setOfStacks.get(setOfStacks.size()-1);
		}
		return currentStack.pop();
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Important points:
	 * 1. Here intreviewer can ask to maintain the threshold of each stack.
	 *    In that case we need to remove plate from bottom of next stack and place it at top of first stack and repeat this process consecutively for each stack till we reach at last stack.
	 * 2. As remove plate from bottom is O(n) is stack is implemented using arraylist or singly linked list. We have to use doubly linked list.
	 * 3. Even after using doubly linked list, time complexity will be O(n) because of left shifting plates.
	 * 
	 * Pseudo code:
	 * popAt(stackIndex):
	 *   targetStack <- setOfStacks[stackIndex]
	 *   if size[targetStack] = 0
	 *     remove(setOfStacks, stackIndex)
	 *     return NIL
	 *   return pop[targetStack]
	 */
	public E popAt(int stackIndex) {
		Stack<E> targetStack = setOfStacks.get(stackIndex);
		if (targetStack.size() == 0) {
			setOfStacks.remove(stackIndex);
			return null;
		}
		return targetStack.pop();
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * peek(e):
	 *   currentStack <- last[setOfStacks]
	 *   return peek[currentStack]
	 */
	public E peek(E e) {
		Stack<E> currentStack = setOfStacks.get(setOfStacks.size()-1);
		return currentStack.peek();
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * isEmpty():
	 *   return size[setOfStacks] = 0 or size[setOfStacks[1]] = 0
	 */
	public boolean isEmpty() {
		return setOfStacks.size() == 0 || setOfStacks.get(1).size() == 0;
	}
}
