package objectOrientedDesign.jigsaw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Puzzle {

	private LinkedList<Piece> pieces;
	private Piece[][] solution; // Segregate 
	
	private ArrayList<LinkedList<Piece>> segregate() {
		ArrayList<LinkedList<Piece>> segregatedPieces = new ArrayList<LinkedList<Piece>>();
		LinkedList<Piece> cornerPieces = new LinkedList<Piece>();
		LinkedList<Piece> borderPieces = new LinkedList<Piece>();
		LinkedList<Piece> insidePieces = new LinkedList<Piece>();
		
		for (Piece piece: pieces) {
			if (piece.isCorner()) {
				cornerPieces.add(piece);
			}
			else if (piece.isBorder()) {
				borderPieces.add(piece);
			}
			else {
				insidePieces.add(piece);
			}
		}
		
		segregatedPieces.add(cornerPieces);
		segregatedPieces.add(borderPieces);
		segregatedPieces.add(insidePieces);
		return segregatedPieces;
	}
	
	public void solve() {
		ArrayList<LinkedList<Piece>> segregatedPieces = segregate();
		LinkedList<Piece> cornerPieces = segregatedPieces.get(0);
		LinkedList<Piece> borderPieces = segregatedPieces.get(1);
		LinkedList<Piece> insidePieces = segregatedPieces.get(2);
		
		// Set first corner piece
		Piece firstCornerPiece = cornerPieces.remove(0);		
		while (firstCornerPiece.getTop().isFlat() && firstCornerPiece.getLeft().isFlat()) {
			firstCornerPiece.rotate();
		}
		
		// Solve borders
		solveColumn(0, borderPieces);
		solveRow(0, borderPieces);
		solveRow(solution.length-1, borderPieces);
		solveColumn(solution[0].length-1, borderPieces);
		
		// Solve inside pieces
		for (int i = 1; i < solution.length-1; i++) {
			for (int j = 1; j < solution[0].length-1; j++) {
				solvePosition(i, j, insidePieces);
			}
		}
	}
	
	private void solvePosition(int i, int j, LinkedList<Piece> pieces) {
		String nextPieceInfo = solution[i-1][j].getNextPieceInfo();
		Piece nextPiece = popPiece(nextPieceInfo, pieces);
		fitPiece(solution[i-1][j], nextPiece);
	}

	private void solveRow(int i, LinkedList<Piece> pieces) {
		for (int j = 1; j <= solution[0].length; j++) {
			solvePosition(i, j, pieces);
		}
	}
	
	private void solveColumn(int j, LinkedList<Piece> pieces) {
		for (int i = 1; i <= solution.length; i++) {
			solvePosition(i, j, pieces);
		}
	}
	
	private void fitPiece(Piece piece, Piece nextPiece) {
		if (piece.fitsWith(nextPiece)) {
			return;
		}
		
		piece.rotate();
		if (piece.fitsWith(nextPiece)) {
			return;
		}
		
		piece.rotate();
		if (piece.fitsWith(nextPiece)) {
			return;
		}
		
		piece.rotate();
	}

	public Piece popPiece(String pieceInfo, LinkedList<Piece> pieces) {
		Piece result = null;
		for (Piece piece: pieces) {
			if (piece.getInfo().equals(pieceInfo)) {
				result = piece;
				break;
			}
		}
		pieces.remove(result);
		return result;
	}
}
