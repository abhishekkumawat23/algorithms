package arraysAndStrings;

/**
 * Question:
 * For NxN matrix of pixels where each pixel is of 4 bytes, write a method to rotate the matrix by 90 degrees.
 */
public class RotateMatrix {

	/**
	 * Time: O(n^2), Space: O(1)
	 * 
	 * Pseudo code:
	 * rotate(M):
	 *   topRow <- 1
	 *   downRow <- N
	 *   leftCol <- 1
	 *   rightCol <- N
	 *   while topRow < downRow and leftCol < rightCol
	 *     topColMin <- leftCol
	 *     topColMax <- rightCol - 1
	 *     downColMin <- leftCol - 1
	 *     downColMax <- rightCol
	 *     leftRowMin <- topRow - 1
	 *     leftRowmax <- downRow
	 *     rightRowMin <- topRow
	 *     rightRowMax <- downRow - 1
	 *     topCol <- topColMin
	 *     downCol <- downColMax
	 *     leftRow <- leftRowMax
	 *     rightRow <- rightRowMin
	 *     while topCol < topColMax and downCol > downColMin and leftRow > leftColMin and rightRow < rightRowMax
	 *       temp <- M[topRow][topCol]
	 *       M[topRow][topCol] <- M[leftRow][leftCol]
	 *       M[leftRow][leftCol] <- M[downRow][downCol]
	 *       M[downRow][downCol] <- M[rightRow][rightCol]
	 *       M[rightRow][rightCol] <- temp
	 *       topCol <- topCol + 1
	 *       downCol <- downCol - 1
	 *       leftRow <- leftRow - 1
	 *       rightRow <- rightRow + 1
	 *     topRow <- topRow + 1
	 *     downRow <- downRow - 1
	 *     leftCol <- leftRow + 1
	 *     rightCol <- rightRow - 1
	 */
	public static void rotate(int[][] m) {
		int length = m.length;
		int topRow = 0;
		int downRow = length-1;
		int leftCol = 0;
		int rightCol = length-1;
		
		while (topRow < downRow && leftCol < rightCol) {
			int topColMin = leftCol;
			int topColMax = rightCol - 1;
			int downColMin = leftCol - 1;
			int downColMax = rightCol;
			int leftRowMin = topRow - 1;
			int leftRowMax = downRow;
			int rightRowMin = topRow;
			int rightRowMax = downRow - 1;
			
			int topCol = topColMin;
			int downCol = downColMax;
			int leftRow = leftRowMax;
			int rightRow = rightRowMin;
			
			while (topCol <= topColMax &&
					downCol >= downColMin &&
					leftRow >= leftRowMin &&
					rightRow <= rightRowMax) {
				int temp = m[topRow][topCol];
				m[topRow][topCol] = m[leftRow][leftCol];
				m[leftRow][leftCol] = m[downRow][downCol];
				m[downRow][downCol] = m[rightRow][rightCol];
				m[rightRow][rightCol] = temp;
				
				topCol++; downCol--; leftRow--; rightRow++;
			}
			
			topRow += 1; downRow -= 1; leftCol += 1; rightCol -= 1;
		}
	}
}
