package objectOrientedDesign.minesweeper;

public class Game {

	private Board board;
	private Player player;
	
	public void startGame() {
		this.board = new Board(10, 15, 20);
		this.player = new Player("Abhishek");
		UserPlayResult playResult = null;
		while (playResult != UserPlayResult.Lost || playResult != UserPlayResult.Won) {
			playResult = player.playCell(board);
		}
		System.out.println("Game result: " + playResult);
	}
}
