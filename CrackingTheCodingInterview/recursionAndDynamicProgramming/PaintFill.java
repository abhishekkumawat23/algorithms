package recursionAndDynamicProgramming;

/**
 * Implement the "paint fill" function that one might see on many image editing programs.
 * That is, given a screen (represented by two-dimensional array of colors), a point, and a new color,
 * fill in the surrounding area until the color changes from the original color.
 */
public class PaintFill {

	/**
	 * Time: O(m*n) where m and n are screen dimensions 
	 * 
	 * paintFill(i, j, screen, screen[i][j], Color.red)
	 * paintFill(i, j, screen, oldColor, newColor)
	 *   if i < 0 || i >= screen.length || j < 0 || j >= screen[0].length
	 *     return
	 *   if screen[i][j] != oldColor
	 *     return
	 *   surrounding = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}
	 *   screen[i][j] = color
	 *   for point in surrounding
	 *     paintFill(point[0], point[j], screen, oldColor, newColor)
	 */
	public void paintFill(int i, int j, Color[][] screen, Color oldColor, Color newColor){
		if (i < 0 || i >= screen.length || j <0 || j < screen[0].length) {
			return;
		}
		
		if (screen[i][j] != oldColor) {
			return;
		}
		
		int[][] surrounding = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		for (int[] point: surrounding) {
			paintFill(point[0], point[1], screen, oldColor, newColor);
		}
	}
	
	private enum Color{
		Red, Black, Blue, Green, Yellow;
	}
}
