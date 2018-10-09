package recursionAndDynamicProgramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implement an algorithm to print all valid (i.e. properly opened and closed) combinations of n pairs of parentheses.
 * Example:
 * Input : 3
 * Output: ((())), (()()), (())(), ()(()), ()()()
 */
public class Parens {

	/**
	 * Important points:
	 * 1. After every left parentheses, add the pair. Also add one pair at the begining.
	 * 2. Problem in this approach is duplicates and thus this code is not that efficicent.
	 * 
	 * parens(n):
	 *  parensSet = new set
	 *   if n == 1
	 *     parensSet.add("()")
	 *     return parensSet
	 *   prevSet = parens(n-1)
	 *   for str in prevSet
	 *     for i = 0 to str.length
	 *       if str.charAt(i) == '('
	 *         newStr = str.substring(0, i+1) + "()" + str.substring(i+1)
	 *         parensSet.add(newStr)
	 *     parensSet.add("()" + str)
	 *   return parensSet
	 *     
	 */
	public Set<String> parens_insert(int n) {
		// Not a good efficient because of duplicates.
		HashSet<String> parensSet = new HashSet<String>();
		if (n == 1) {
			parensSet.add("()");
			return parensSet;
		}
		
		Set<String> prevSet = parens_insert(n-1);
		for (String str: prevSet) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(') {
					String newStr = str.substring(0, i+1) + "()" + str.substring(i+1);
					parensSet.add(newStr);
				}
			}
			parensSet.add("()" + str);
		}
		return parensSet;
	}
	
	/**
	 * Important points:
	 * 1. We can simply use recursion to solve this problem.
	 * 2. We will add left parentheses in recursive way and then add right parentheses in recursive way.
	 * 3. We will return as soon as any invalid parenthesis occurs.
	 * 4. This is better approach as no duplicates.
	 * 
	 * parens(leftRem, rightRem, charArr, count, list)
	 *   if rightRem < leftRem || leftRem < 0
	 *     return
	 *   if leftRem == 0 && rightRem == 0
	 *     newStr = copy(charArr)
	 *     list.add(newStr)
	 *     return
	 *   if leftRem > 0
	 *     charArr[count] = '('
	 *     parens(leftRem-1, rightRem, charArr, count+1, list)
	 *   else if rightRem > leftRem
	 *     charArr[count] = ')'
	 *     parens(leftRem, rightRem-1, charArr, count+1, list)
	 */
	public void parens_recursion(int leftRem, int rightRem, char[] charArr, int count, List<String> list) {
		if (rightRem < leftRem || leftRem < 0) {
			return;
		}
		
		if (leftRem == 0 && rightRem == 0) {
			String newStr = String.copyValueOf(charArr);
			list.add(newStr);
			return;
		}
		
		if (leftRem > 0) {
			charArr[count] = '(';
			parens_recursion(leftRem-1, rightRem, charArr, count+1, list);
		}
		else if (rightRem > 0) {
			charArr[count] = ')';
			parens_recursion(leftRem, rightRem-1, charArr, count+1, list);
		}
	}
}
