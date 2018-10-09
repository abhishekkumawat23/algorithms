package recursionAndDynamicProgramming;

import java.util.Stack;

/**
 * In the classic problem of towers of hanoi, you have 3 towers and n disks of different sizes which can slide onto any tower.
 * The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e. each disk sits on top of an even larger one).
 * You have the following constraints:
 * 1. Only one disk can be moved at a time.
 * 2. A disk is slid off the top of one tower onto the next rod.
 * 3. A disk can only be placed on top of a larger disk.
 * Write a program to move the disks from the first tower to the last using Stacks.
 */
public class TowersOfHanoi {

	/**
	 * Important points:
	 * 1. We have to move tower from a to c.
	 * 2. Logic
	 *    a. Shift n-1 elements from a to b
	 *    b. Shift nth element from a to c
	 *    c. Shift n-1 elements from b to c
	 * 
	 * moveTowersOfHanoi(n, a, b, c):
	 *   if a.size == 0
	 *     return
	 *   moveTowersOfHanoi(n-1, a, c, b)
	 *   c.push(a.pop)
	 *   moveTowersOfHanoi(n-1, b, a, c)
	 */
	public void moveTowersOfHanoi(int n, Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
		if (n <= 0) {
			return;
		}
		moveTowersOfHanoi(n-1, a, c, b);
		c.push(a.pop());
		moveTowersOfHanoi(n-1, b, a, c);
	}
	
	public static void main(String[] args) {
		TowersOfHanoi toh = new TowersOfHanoi();
		Stack<Integer> a = new Stack<Integer>();
		Stack<Integer> b = new Stack<Integer>();
		Stack<Integer> c = new Stack<Integer>();
		a.push(10); a.push(9); a.push(8); a.push(7); a.push(6); a.push(5); a.push(4);
		toh.moveTowersOfHanoi(a.size(), a, b, c);
	}
}
