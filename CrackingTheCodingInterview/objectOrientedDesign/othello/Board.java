package objectOrientedDesign.othello;

import java.util.LinkedList;
import java.util.Queue;

public class Board {

	private Piece[][] pieces;
	
	public Board(Piece[][] pieces) {
		this.pieces = pieces;
	}
	
	public Position findNextValidPosition(Player player, Position position) {
		// Find if there is any valid position for the player to play.
		return null;
	}

	public void putPiece(Piece piece, Position position) {
		pieces[position.getX()][position.getY()] = piece;
		flipColorsIfRequired(position);
	}

	private void flipColorsIfRequired(Position position) {
		Queue<Position> effectedPositions = new LinkedList<Position>();
		effectedPositions.add(position);
		while (!effectedPositions.isEmpty()) {
			Position ps = effectedPositions.poll();
			int x = ps.getX();
			int y = ps.getY();
			Piece self = pieces[x][y];
			Color selfColor = self.getColor();
			Color topColor = pieces[x-1][y].getColor();
			Color bottomColor = pieces[x+1][y].getColor();
			Color leftColor = pieces[x][y-1].getColor();
			Color rightColor = pieces[x][y+1].getColor();
			
			if ((topColor != selfColor && bottomColor != selfColor) ||
				(leftColor != selfColor && rightColor != selfColor)) {
				self.flip();
			}
		}
	}

}
