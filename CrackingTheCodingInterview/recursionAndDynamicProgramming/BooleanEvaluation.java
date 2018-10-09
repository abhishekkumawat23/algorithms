package recursionAndDynamicProgramming;

import java.util.HashMap;

/**
 * Given a boolean expression consisting of the symbols 0(false), 1(true), &(AND), |(OR), and ^(XOR),
 * and a desired boolean result value `result`, implement a function to count the number of ways of parenthesizing the expression such that it evaluates to result.
 * The expression should be fully parenthesized e.g. (0)^(1) but not extraneously(e.g. (((0))^(1))
 * Example:
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&1^1|0", true) -> 10
 */
public class BooleanEvaluation {

	/**
	 * Important points
	 * 1. we are dividing into subproblems by putting parentheses around every operator.
	 * 2. Example: f(1^0|0|1) = f((1)^(0|0|1)) + f((1^0)|(0|1)) + f((1^0|0)|(1))
	 * 3. As result can be true or false, we are calculating count for result as true. Also we will count total count and reduce it from true count if result is false.
	 * 4. As there can be duplicates, we are memoizing.
	 * 5. From java 1.7, substring takes O(n) time instead of O(1), so if that's a concern to interviewer, then pass the start, end index instead of substring as method arg.
	 * 
	 * evaluateCount(exp, result, memo):
	 *   if exp.length == 0
	 *     return 0
	 *   if exp.length == 1
	 *     return (exp == "0") == result ? 1 : 0
	 *   if memo.containsKey(result+exp)
	 *     return memo.get(result+exp)
	 *   ways = 0
	 *   for i = 1 to exp.length-1
	 *     leftTrue = evaluateCount(exp.substring(0,i), true, memo)
	 *     rightTrue = evaluateCount(exp.substring(i+1), true, memo)
	 *     leftFalse = evaluateCount(exp.substring(0,i), false, memo)
	 *     rightFalse = evaluateCount(exp.substring(i+1), false, memo)
	 *     totalCount = (leftTrue+leftFalse)*(rightTrue + rightFalse)
	 *     c = exp.charAt(i)
	 *     totatlTrue = 0
	 *     if c == '|'
	 *       totalTrue = leftTrue*rightTrue + leftTrue*rightFalse + leftFalse*rightTrue
	 *     else if c == '&'
	 *       totalTrue = leftTrue*rightTrue
	 *     else if c == '^'
	 *       totalTrue = leftTrue*rightFalse + leftFalse*rightTrue
	 *     subWays = result ? totalTrue : totalCount - totalTrue
	 *     ways += subWays
	 *   memo.put(result+exp, ways)
	 *   return ways
	 *     
	 */
	public int evaluateCount(String exp, boolean result, HashMap<String, Integer> memo) {
		if (exp.length() == 0) {
			return 0;
		}
		if (exp.length() == 1) {
			return (exp == "0") == result ? 1 : 0;
		}
		if (memo.containsKey(result+exp)) {
			return memo.get(result+exp);
		}
		int ways = 0;
		for (int i = 1; i < exp.length(); i++) {
			int leftTrue = evaluateCount(exp.substring(0,i), true, memo);
			int rightTrue = evaluateCount(exp.substring(i+1), true, memo);
			int leftFalse = evaluateCount(exp.substring(0,i), false, memo);
			int rightFalse = evaluateCount(exp.substring(i+1), false, memo);
			int totalCount = (leftTrue+leftFalse)*(rightTrue + rightFalse);
			
			char c = exp.charAt(i);
			int totalTrue = 0;
			if (c == '|') {
				totalTrue = leftTrue*rightTrue + leftTrue*rightFalse + leftFalse*rightTrue;
			}
			else if (c == '&') {
				totalTrue = leftTrue*rightTrue;
			}
			else if (c == '^') {
				totalTrue = leftTrue*rightFalse + leftFalse*rightTrue;
			}
			int subWays = result ? totalTrue : totalCount - totalTrue;
			ways += subWays;
		}
		memo.put(result+exp, ways);
		return ways;
	}
}
