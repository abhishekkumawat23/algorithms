package recursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Write an algorithm to print all ways of arranging eight queens on a 8x8 chess board so that none of them share the same row, column or diagonal.
 * In this case, "diagonal" means all diagonals, not just the two that bisect the board. 
 */
public class EightQueens {

	/**
	 * Important points: 
	 * 1. We don't need to maintain entire chess board positions. Only columns array is enough as queen will be at position only in a row.
	 * 
	 * // columns represent queen column position for each row index
	 * // Initialize columns with -1
	 * queens(0, columns)
	 * queens(row, columns, results)
	 *   if row >= columns.length
	 *     results.add(columns.clone)
	 *     return
	 *   for col = 0 to columns.length-1
	 *     if checkValid(row, col, columns)
	 *       columns[row] = col
	 *       queens(row+1, columns, results) 
	 * checkValid(row1, col1, columns):
	 *   // Check cols and diagonals till now
	 *   for row2 = 0 to row1-1
	 *     col2 = columns[row2]
	 *     if col2 == col1 // For column
	 *       return false
	 *     if row1-row2 == Math.abs(col1-col2) // For diagonal
	 *       return false  
	 *   return true
	 *     
	 */
	public void queens() {
		int[] columns = new int[8];
		List<int[]> results = new ArrayList<int[]>();
		queens_recursive(0, columns, results);
	}

	private void queens_recursive(int row, int[] columns, List<int[]> results) {
		if (row >= columns.length) {
			results.add(columns.clone());
			return;
		}
		
		for (int col = 0; col < columns.length; col++) {
			if (checkValid(row, col, columns)) {
				columns[row] = col;
				queens_recursive(row+1, columns, results);
			}
		}
	}
	
	private boolean checkValid(int row1, int col1, int[] columns) {
		// Check cols and diagonals till now.
		for (int row2 = 0; row2 < row1; row2++) {
			int col2 = columns[row2];
			// Check col
			if (col2 == col1) {
				return false;
			}
			// Check diagonal
			if (row1-row2 == Math.abs(col1-col2)) {
				return false;
			}
		}
		return true;
	}
}
