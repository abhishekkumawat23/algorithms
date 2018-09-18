package arraysAndStrings;

/**
 * Question:
 * If an element in MxN matix os zero, then make its entire row and column to zero.
 */
public class ZeroMatrix {

	/**
	 * Time: O(n^2); Space: O(1)
	 * 
	 * Important points:
	 * 1. For making this algo inplace, whenever we find 0, we make that row and column's first entry as 0.
	 *  
	 * Pseudo code:
	 * zeroMatrix(MN, m, n):
	 *   for i = 1 to m
	 *     if MN[i][1] = 0
	 *       firstColHasZero <- true
	 *   for j = 1 to n
	 *     if MN[1][j] = 0
	 *       firstRowHasZero <- true
	 *   for i = 2 to m
	 *     for j = 2 to n
	 *       if MN[i][j] = 0
	 *         MN[1][j] <- 0
	 *         MN[i][1] <- 0
	 *   for j = 1 to n
	 *     if MN[1][j] = 0
	 *       for i = 2 to m
	 *         MN[i][j] <- 0
	 *   for i = 1 to m
	 *     if MN[i][1] = 0
	 *       for j = 2 to n
	 *         MN[i][j] <- 0
	 *   if firstColHashZero <- true
	 *     for i = 1 to m
	 *       MN[i][1] <- 0
	 *   if firstRowHashZero <- true
	 *     for j = 1 to n
	 *       MN[1][j] <- 0
	 */
	public void zeroMatrix(int[][] mn) {
		int rowLength = mn.length;
		int colLength = mn[0].length;
		
		boolean firstRowHasZero = false;
		boolean firstColHasZero = false;
		
		for (int j = 0; j < colLength; j++) {
			if (mn[0][j] == 0) {
				firstRowHasZero = true;
			}
		}
		
		for (int i = 0; i < rowLength; i++) {
			if (mn[i][0] == 0) {
				firstColHasZero = true;
			}
		}
		
		for (int i = 1; i < rowLength; i++) {
			for (int j = 1; j < colLength; j++) {
				if (mn[i][j] == 0) {
					mn[0][j] = 0;
					mn[i][0] = 0;
				}
			}
		}
		
		// Nullify cols
		for (int j = 0; j < colLength; j++) {
			if (mn[0][j] == 0) {
				for (int i = 1; i < rowLength; i++) {
					mn[i][j] = 0;
				}
			}
		}
		
		// Nullify rows
		for (int i = 0; i < rowLength; i++) {
			if (mn[i][0] == 0) {
				for (int j = 1; j < colLength; j++) {
					mn[i][j] = 0;
				}
			}
		}
		
		if (firstRowHasZero) {
			for (int j = 0; j < colLength; j++) {
				mn[0][j] = 0;
			}
		}
		
		if (firstColHasZero) {
			for (int i = 0; i < rowLength; i++) {
				mn[i][0] = 0;
			}
		}
	}
}
