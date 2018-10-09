package recursionAndDynamicProgramming;

import java.util.*;

/**
 * You have a stack of n boxes, with widths wi, heights hi, and depths di.
 * The boxes cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly larger than the box above it in width, height and depth.
 * Implement a method to compute the height of the maxHeight possible stack.
 * The height of a stack is the sum of heights of each box.
 */
public class StackOfBoxes {

	/**
	 * Important points:
	 * 1. Sorting by height so that atleast by one dimension values are correct. This will reduce the recursion drastically and is a optimization.
	 * 2. Logic: place(n) = place(1st at bottom, place(n-1) + place(2nd at bottom, place(n-2)) + ....
	 * 3. Just because of sorting optimization, we don't have to look list backwards and thus list need not be altered in recursion. We can simply use index pointer for list.
	 * 4. As we are calculating many subproblems again and again, we are using memoization.
	 * 
	 * createStack(boxes):
	 *   sort(boxes) // sort by height in descending order
	 *   return createStack(-1, boxes, new memo) 
	 * createStack(bottomIndex, boxes, memo):
	 *   if bottomIndex > 0  && bottomIndex < boxes.size && memo[bottomIndex] >= 0
	 *     return memo[bottomIndex]
	 *   maxHeight = 0
	 *   bottom = bottomIndex != -1 ? boxes.get(bottomIndex) : null
	 *   for j = botomIndex+1 to boxes.length-1
	 *     if checkValid(boxes.get(j), bottom)
	 *       height = createStack(j+1, boxes, memo)
	 *       maxHeight = max(height, maxHeight)
	 *   maxHeight += (bottom != null ? bottom.height : 0)
	 *   memo[bottomIndex] = maxHeight
	 *   return maxHeight
	 * checkValid(small, big):
	 *   return (big == null) || (small.width < big.width && small.depth < big.depth)
	 * sort(boxes):
	 *   Collections.sort(boxes, new BoxComparator) // We will use manual implementation once we read sorting chapter.
	 * BoxComparator implements Comparator<Box>
	 *   compare(Box x, Box y):
	 *     return y.height - x.height
	 *   
	 */
	public int createStack_memoization(List<Box> boxes) {
		Collections.sort(boxes, new BoxComparator());
		return createStack_memoization(-1, boxes, new int[boxes.size()]);
	}
	
	private int createStack_memoization(int bottomIndex, List<Box> boxes, int[] memo) {
		if (bottomIndex > 0  && bottomIndex < boxes.size() && memo[bottomIndex] >= 0) {
			return memo[bottomIndex];
		}
		
		int maxHeight = 0;
		Box bottom = bottomIndex != -1 ? boxes.get(bottomIndex) : null;
		for (int j = bottomIndex + 1; j < boxes.size(); j++) {
			if (checkValid(boxes.get(j), bottom)) {
				int height = createStack_memoization(j+1, boxes, memo);
				maxHeight = Math.max(height, maxHeight);
			}
		}
		maxHeight += (bottom != null) ? bottom.height : 0;
		return maxHeight;
	}

	private boolean checkValid(Box small, Box big) {
		return (big == null) || (small.width < big.width && small.depth < big.depth);
	}

	private class Box{
		public int width;
		public int height;
		public int depth;
	}
	
	private class BoxComparator implements Comparator<Box>{
		@Override
		public int compare(Box x, Box y) {
			return y.height = x.height; // We want descending order.
		}
	}
	
	/**
	 * Important points:
	 * 1. Here at each point we are going with binary tree with one side where take the box in stack and other side where we don't take the box in stack.
	 * 
	 * createStack(boxes)
	 *   Collections.sort(boxes) // sort by height in descending order
	 *   return createStack(null 0, boxes, new int[boxes.size])
	 * createStack(bottom, offset, boxes, memo):
	 *   if offset >= boxes.size
	 *     return 0
	 *   newBottom = boxes.get(offset)
	 *   heightWithBottom = 0
	 *   if checkValid(newBottom, bottom)
	 *     if memo[offset] == 0
	 *       memo[offset] = createStack(newBottom, offset+1, boxes)
	 *       memo[offset] += newBottom.height
	 *     heightWithBottom = memo[offset]
	 *   heightWithoutBottom = createStack(bottom, offset+1, boxes)
	 *   return max(heightWithBottom, heightWithoutBottom)
	 */
	public int createStack_decision_recursion(List<Box> boxes) {
		Collections.sort(boxes, new BoxComparator());
		return createStack_decision_recursion(null, 0, boxes, new int[boxes.size()]);
	}

	private int createStack_decision_recursion(Box bottom, int offset, List<Box> boxes, int[] memo) {
		if (offset >= boxes.size()) {
			return 0;
		}
		
		Box newBottom = boxes.get(offset);
		int heightWithBottom = 0;
		if (checkValid(newBottom, bottom)) {
			if (memo[offset] == 0) {
				memo[offset] += createStack_decision_recursion(newBottom, offset+1, boxes, memo);
				memo[offset] += newBottom.height;
			}
			heightWithBottom = memo[offset];
		}
		int heightWithoutBottom = createStack_decision_recursion(bottom, offset+1, boxes, memo);
		return Math.max(heightWithBottom, heightWithoutBottom);
	}
}


