package objectOrientedDesign.minesweeper;

public class Player {

	private String name;

	public Player(String name) {
		this.name = name;
	}

	public UserPlayResult playCell(Board board) {
		UserPlay userPlay = new UserPlay(2, 3);
		return board.playCell(userPlay);
	}

}
