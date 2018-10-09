package objectOrientedDesign.othello;

public class Player {
	
	private Color color;

	// Returns false if unable to play any piece.
	public boolean playPiece() {
		Board board = Game.getInstance().getBoard();
		Position position = board.findNextValidPosition(this, null);
		if (position == null) {
			return false;
		}
		
		while (!isSatisfied(position)) {
			position = board.findNextValidPosition(this, position);
			if (position == null) {
				return false;
			}
		}
		Piece piece = new Piece();
		piece.setColor(color);
		board.putPiece(piece, position);
		return true;
	}

	private boolean isSatisfied(Position position) {
		// Analyze if satisfied with the given position or not.
		return true;
	}

}
