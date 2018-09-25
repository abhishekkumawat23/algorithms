package bitManipulation;

/**
 * Question:
 * A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte.
 * The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows)
 * The height of the screen, of course can be derived from the length of the array and the width.
 * Implement a function that draws a horizontal line from (x1,y) to (x2,y)
 */
public class DrawLine {

	/**
	 * Time: O(x2-x1)
	 * 
	 * Important points:
	 * 1. Assuming x1 < x2
	 * 
	 * drawLine:
	 *   // Can't draw horizontal line if x2 and x1 are not on same height.
	 *   height = floor(screen.length*1.0/width)
	 *   if x1 < 0 || x2 < 0 || x1 >= width || x2 >= width || y < 0 || y >= height 
	 *     return;
	 *   yHeight = screen[floor(height*1.0/y)]
	 *   start = x1/8
	 *   end = x2/8
	 *   for i = start to end
	 *     if i == start
	 *       // In byte make bits from most significant to x1%8 as zero and others one.
	 *       screen[(yHeight-1)*width+i] |= (byte(-1) >>> (x1%8))
	 *     else if i == end
	 *       // In byte make bits from most significant to x2%9 as one and others zero
	 *       screen[(yHeight-1)*width+i] |= (byte(-1) << (x2%8))
	 *     else
	 *       // Make all bits one
	 *       screen[(yHeight-1)*width+i] |= byte(-1)
	 */
	public void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		// Can't draw horizontal line if x2 and x1 are not on same height.
		int height = (int)Math.floor(screen.length*1.0/width);
		if (x1 < 0 || x2 < 0 || x1 >= width || x2 >= width || y < 0 || y >= height) {
			return;
		}
		int yHeight = screen[(int)Math.floor(height*1.0/y)];
		int start = x1/8;
		int end = x2/8;
		for (int i = start; i <= end; i++) {
			if (i == start) {
				// In byte make bits from most significant to x1%8 as zero and others one.
				screen[(yHeight-1)*width+i] |= ((byte)-1 >>> (x1%8));
			}
			else if (i == end) {
				// In byte make bits from most significant to x2%9 as one and others zero
				screen[(yHeight-1)*width+i] |= ((byte)-1 << (x2%8));
			}
			else {
				// Make all bits one
				screen[(yHeight-1)*width+i] |= (byte)-1;
			}
		}
	}
}
