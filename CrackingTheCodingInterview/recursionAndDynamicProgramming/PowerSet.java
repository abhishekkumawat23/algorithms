package recursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a method to return all subsets of a set.
 */
public class PowerSet {

	/**
	 * Time: O(2^(n+1)); Space: O(n) because of stack + O((2^n)-1) because of powerSet
	 * 
	 * Important points:
	 * 1. Set of n elements has 2^n-1 sub sets. (1+2+4+8...+2^(n-1))
	 * 2. Time complexity is summation of (2^n-1) = 2^(n+1)-2-n
	 * 
	 * getPowerSet(0, set, powerSet) // This powerSet will have one empty set in it
	 * getPowerSet(n, set, powerSet):
	 *   if n >= set.length
	 *     return
	 *   add(powerSet, set[n])
	 *   getPowerSet(n+1, set, powerSet)
	 * add(powerSet, e):
	 *   for i = 0 to powerSet.length-1
	 *     newSet = new set(powerSet.get(i))
	 *     newSet.add(e)
	 *     powerSet.add(newSet)
	 *   powerSet.add(new set(e))
	 */
	public void getPowerSet_bottomUp(int n, int[] set, List<List<Integer>> powerSet){
		if (n >= set.length) {
			return;
		}
		
		add_bottomUp(powerSet, set[n]);
		getPowerSet_bottomUp(n+1, set, powerSet);
	}
	
	private void add_bottomUp(List<List<Integer>> powerSet, int e) {
		add(powerSet, e);
		List<Integer> newSet = new ArrayList<Integer>();
		newSet.add(e);
		powerSet.add(newSet);
	}

	private void add(List<List<Integer>> powerSet, int e) {
		for (int i = 0; i < powerSet.size(); i++) {
			List<Integer> newSet = new ArrayList<Integer>(powerSet.get(i));
			newSet.add(e);
			powerSet.add(newSet);
		}
	}

	/**
	 * Time: O(2^(n+1)); Space: O(n) because of stack + O((2^n)-1) because of powerSet
	 * 
	 * Important points:
	 * 1. Time and space complexity logic is similar to topDown approach logic.
	 * 
	 * getPowerSet(set.length-1, set)
	 * getPowerSet(n, set):
	 *   if n < 0
	 *     return powerSet with one empty set in it.
	 *   powerSet = getPowerSet(n-1, set)
	 *   add(powerSet, set[n])
	 *   return powerSet
	 */
	public List<List<Integer>> getPowerSet_topDown(int n, int[] set){
		List<List<Integer>> powerSet;
		if (n < 0) {
			 powerSet = new ArrayList<List<Integer>>();
			powerSet.add(new ArrayList<Integer>());
			return powerSet;
		}
		powerSet = getPowerSet_topDown(n-1, set);
		add(powerSet, set[n]);
		return powerSet;
	}
}
