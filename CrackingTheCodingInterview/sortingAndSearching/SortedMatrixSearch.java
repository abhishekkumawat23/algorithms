package sortingAndSearching;

/**
 * Given an MxN matrix in which each row and each column is sorted in ascending order, write a method to find an element.
 */
public class SortedMatrixSearch {

	/**
	 * Time: O(logm*logn)
	 * 
	 * Important points:
	 * 1. We can do binary search for all rows. In that case time is O(mlogn)
	 * 2. But we can do better both rows and columns are sorted.
	 * 3. We can apply binary search in both rows and columns. Thus we can achieve O(logm*logn)
	 * 
	 * searchSortedMatrix(matrix, value):
	 *   currentRowLow = 0, currentRowHigh = matrix.length-1, currentColLow = 0, currentColHigh = matrix[0].length-1
	 *   while currentRowLow <= currentRowHigh && currentColLow <= currentColHigh
	 *     rowLow = currentRowLow, rowHigh = currentRowHigh, colLow = currentColLow, colHigh = currentColHigh
	 *     // Binary search across rows
	 *     while rowLow <= rowHigh
	 *       rowMid = (rowLow + rowHigh)/2
	 *       if value < matrix[rowMid][currentColLow]
	 *         rowHigh = rowMid-1
	 *       else if value > matrix[rowMid][currentColLow]
	 *         rowLow = rowMid + 1
	 *       else
	 *         point[0] = rowMid, point[1] = currentColLow
	 *         return point
	 *     // Binary search across columns
	 *     while colLow <= colHigh
	 *       colMid = (colLow + colHigh)/2
	 *       if value < matrix[currentRowLow][colMid]
	 *         colHigh = colMid-1
	 *       else if value > matrix[currentRowLow][colMid]
	 *         colLow = colMid + 1
	 *       else
	 *         point[0] = currentRowLow, point[1] = colMid
	 *         return point
	 *     currentRowLow++
	 *     currentRowHigh = rowLow-1
	 *     currentColLow++
	 *     currentColHigh = colLow-1
	 *   return null
	 */
	public static int[] searchSortedMatrix(int[][] matrix, int value) {
		int[] point = new int[2];
		int currentRowLow = 0; int currentRowHigh = matrix.length-1; int currentColLow = 0; int currentColHigh = matrix[0].length-1;
		while (currentRowLow <= currentRowHigh && currentColLow <= currentColHigh) {
			int rowLow = currentRowLow; int rowHigh = currentRowHigh; int colLow = currentColLow; int colHigh = currentColHigh;
			// Binary search across rows
			while (rowLow <= rowHigh) {
				int rowMid = (rowLow+rowHigh)/2;
				if (value < matrix[rowMid][currentColLow]) {
					rowHigh = rowMid - 1;
				}
				else if (value > matrix[rowMid][currentColLow]) {
					rowLow = rowMid + 1;
				}
				else {
					point[0] = rowMid; point[1] = currentColLow;
					return point;
				}
			}
			
			// Binary search across columns
			while (colLow <= colHigh) {
				int colMid = (colLow+colHigh)/2;
				if (value < matrix[currentRowLow][colMid]) {
					colHigh = colMid - 1;
				}
				else if (value > matrix[currentRowLow][colMid]) {
					colLow = colMid + 1;
				}
				else {
					point[0] = currentRowLow; point[1] = colMid;
					return point;
				}
			}
			
			currentRowLow++;
			currentRowHigh = rowLow-1;
			currentColLow++;
			currentColHigh = colLow-1;
		}
		return null;
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{15, 20, 40, 85}, {20, 35, 80, 95}, {30, 55, 95, 105}, {40, 80, 100, 120}};
		int[] point = searchSortedMatrix(matrix, 55);
		System.out.println(point[0] + " " + point[1]);
	}
}
