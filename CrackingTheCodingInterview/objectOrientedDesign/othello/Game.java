package objectOrientedDesign.othello;

public class Game {

	private static Game instance;
	private Board board;
	private int whiteScore;
	private int blackScore;
	private Player whitePlayer;
	private Player blackPlayer;
	
	// Plays game and returns the winner.
	public Player play() {
		boolean ableToPlayPiece = whitePlayer.playPiece();
		Player current = whitePlayer;
		while (ableToPlayPiece) {
			current = flipPlayer(current);
			ableToPlayPiece = current.playPiece();
		}
		return winner();
	}
	
	public int getWhiteScore() {
		return whiteScore;
	}
	
	public int getBlackScore() {
		return blackScore;
	}
	
	public Player getWhitePlayer() {
		return whitePlayer;
	}
	
	public Player getBlackPlayer() {
		return blackPlayer;
	}
	
	public Player winner() {
		return whiteScore >= blackScore ? whitePlayer : blackPlayer;
	}
	
	private Player flipPlayer(Player current) {
		return current == whitePlayer ? blackPlayer : whitePlayer;
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public Board getBoard() {
		return board;
	}
}
