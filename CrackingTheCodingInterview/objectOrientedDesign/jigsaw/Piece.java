package objectOrientedDesign.jigsaw;

public class Piece {

	private Edge top;
	private Edge bottom;
	private Edge left;
	private Edge right;
	private String info;
	
	public boolean isCorner() {
		int countFlats = countFlats();
		return countFlats >= 2;
	}
	
	public boolean isBorder() {
		int countFlats = countFlats();
		return countFlats >= 1;
	}
	
	private int countFlats() {
		int countFlats = 0;
		if (top.isFlat()) {
			countFlats++;
		}
		if (bottom.isFlat()) {
			countFlats++;
		}
		if (left.isFlat()) {
			countFlats++;
		}
		if (right.isFlat()) {
			countFlats++;
		}
		return countFlats;
	}
	public Edge getTop() {
		return top;
	}
	public Edge getLeft() {
		return left;
	}
	
	public Edge getBottom() {
		return bottom;
	}
	
	public Edge getRight() {
		return right;
	}
	
	public String getInfo() {
		return info;
	}

	// Rotates piece clockwise
	public void rotate() {
		Edge temp = top;
		top = left;
		left = bottom;
		bottom = right;
		right = temp;
	}

	public String getNextPieceInfo() {
		return null;
	}
	
	public boolean fitsWith(Piece piece) {
		// returns true if piece correctly fits with piece.
		return false;
	}
}
