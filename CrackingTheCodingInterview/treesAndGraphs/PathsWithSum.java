package treesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Question:
 * You are given a binary tree in which each node contains an integer value (which might be positive or negative)
 * Design an algorithm to count the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards.
 */
public class PathsWithSum {
	private Node root;

	/**
	 * Time: O(n+ n-1 + n-2 + .. + 1) ~= O(n^2); Space: O(n)
	 * 
	 * Important points:
	 * 1. Here when we try to break the problem in sub problem, we get that listOfSum(n) = prepend(n.data, listOfSum(n.left)) + prepend(n.data, listOfSum(n.right)) + n.data
	 * 2. Time taken is O(n^2). We can optimize this code. Check alternative implementation for optimized algo.
	 * 
	 * countPathsWithSum(sum):
	 *   {listOfSum, count} = getListOfSum(root, sum)
	 *   return count
	 * getListOfSum(node, sum):
	 *   if node == null
	 *     return {empty list, 0}
	 *   {leftListOfSum, leftCount} = getListOfSum(node.left, sum)
	 *   {rightListOfSum, rightCount} = getListOfSum(node.right, sum)
	 *   for i = 0 to leftListOfSum.size-1
	 *     leftListOfSum[i] += node.data
	 *   for i = 0 to rightListOfSum.size-1
	 *     rightListOfSum[i] += node.data
	 *   listOfSum.addAll(leftListOfSum)
	 *   listOfSum.addAll(rightListOfSum)
	 *   listOfSum.add(node.data)
	 *   count = leftCount + rightCount
	 *   for value in listOfSum
	 *     if value == sum
	 *       count++
	 *   return {listOfSum, count}
	 */
	public int countPathsWithSum(int sum) {
		Result result = getListOfSum(root, sum);
		return result.count;
	}
	
	private Result getListOfSum(Node node, int sum) {
		if (node == null) {
			Result result = new Result(new ArrayList<Integer>(), 0);
		}
		Result leftResult = getListOfSum(node.left, sum);
		Result rightResult = getListOfSum(node.left, sum);
		for (int i = 0; i < leftResult.listOfSum.size(); i++) {
			leftResult.listOfSum.set(i, leftResult.listOfSum.get(i) + node.data);
		}
		for (int i = 0; i < rightResult.listOfSum.size(); i++) {
			rightResult.listOfSum.set(i, rightResult.listOfSum.get(i) + node.data);
		}
		List<Integer> listOfSum = new ArrayList<Integer>();
		listOfSum.addAll(leftResult.listOfSum);
		listOfSum.addAll(rightResult.listOfSum);
		listOfSum.add(node.data);
		int count = leftResult.count + rightResult.count;
		for (int value: listOfSum) {
			if (value == sum) {
				count++;
			}
		}
		return new Result(listOfSum, count);
	}
	
	/**
	 * Time: O(n); Space: O(n) for hashMap and O(logn) for recursion
	 * 
	 * Important points:
	 * 1. We can optimize the algorithm. We can break the problem into subproblem differently.
	 * 2. If elements of array are 10 5 1 2 -1 -1 7 1 2, then create a store in hashMap running count i.e 10 15 16 18 17 16 23 24 26
	 * 3. So by time we are at element 7, hashMap contains 10,1 15,1 16,2 18,1 17,1
	 * 4. Check for each element that if there is any entry in hashMap with key as element-targetSum. Value of such entry is the number of possibilities of getting target sum.
	 * 5. In algo, we are first adding the root node in hashMap. So we are going top down approach instead of traditional bottom up approach of recursion.
	 * 6. As this is top down, we are adding the current element in hashMap and deleting it again at end of method.
	 * 7. In bottom up approach, parent is dependent on result from child. But in top down approach, chidlren is dependent on parent info/result.
	 * 
	 * countPathsWithSum(node, targetSum, runningSum, pathCountMap):
	 *   if node == null
	 *     return 0
	 *   runningSum += node.data
	 *   runningSumCount = pathCountMap.containsKey(runningSum) ? pathCountMap.get(runningSum) + 1 : 1;
	 *   pathCountMap.put(runningSum, runningSumCount)
	 *   totalPaths = pathCountMap.containsKey(runningSum-targetSum) ? pathCountMap.get(runningSum-targetSum) : 0
	 *   totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCountMap)
	 *   totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCountMap)
	 *   pathCountMap.put(runningSum, runningSumCount - 1)
	 *   return totalPaths
	 */
	public int countPathsWithSum_optimized(Node node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCountMap) {
		if (node == null) {
			return 0;
		}
		runningSum += node.data;
		int runningSumCount = pathCountMap.containsKey(runningSum) ? pathCountMap.get(runningSum) + 1 : 1;
		pathCountMap.put(runningSum, runningSumCount); // Increase
		int totalPaths = pathCountMap.containsKey(runningSum - targetSum) ? pathCountMap.get(runningSum-targetSum) : 0;
		totalPaths += countPathsWithSum_optimized(node.left, targetSum, runningSum, pathCountMap);
		totalPaths += countPathsWithSum_optimized(node.right, targetSum, runningSum, pathCountMap);
		pathCountMap.put(runningSum, runningSumCount-1); // Decrease as its top down approach
		return totalPaths;
	}
	
	private class Result{
		public List<Integer> listOfSum;
		public int count;
		
		public Result(List<Integer> listOfSum, int count) {
			this.listOfSum = listOfSum;
			this.count = count;
		}
	}
	
	private class Node{
		public int data;
		public Node left;
		public Node right;
	}
}
