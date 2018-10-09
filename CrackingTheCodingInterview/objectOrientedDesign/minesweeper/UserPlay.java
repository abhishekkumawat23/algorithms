package objectOrientedDesign.minesweeper;

public class UserPlay {

	private int row;
	private int column;

	public UserPlay(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}

}
