package objectOrientedDesign.minesweeper;

public class Cell {

	private int x;
	private int y;
	private int value;
	private boolean isBomb;
	private boolean isExposed;
	private boolean isGuess;
	
	public Cell(int x, int y, int value, boolean isBomb) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.isBomb = isBomb;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isBomb() {
		return isBomb;
	}
	
	public void expose() {
		isExposed = true;
	}
	
	public boolean isExposed() {
		return isExposed;
	}
	
	public void toggleGuess() {
		isGuess = !isGuess;
	}
	
	public boolean isGuess() {
		return isGuess;
	}
}
