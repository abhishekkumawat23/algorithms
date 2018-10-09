package objectOrientedDesign.minesweeper;

import java.util.Random;

public class Board {

	private int rows;
	private int columns;
	private int nBombs;
	private Cell[][] cells;
	private int noOfUnexposedCells;
	
	public Board(int rows, int columns, int nBombs) {
		this.noOfUnexposedCells = rows*columns;
		this.rows = rows;
		this.columns = columns;
		this.nBombs = nBombs;
		this.cells = new Cell[rows][columns];
		Initialize();
	}

	private void Initialize() {
		InitializeBombCells();
		InitializeNonBombCells();
	}

	private void InitializeNonBombCells() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (cells[i][j] == null) {
					int value = getNearBombCount(i, j);
					cells[i][j] = new Cell(i, j, value, false);	
				}
			}
		}
	}

	private int getNearBombCount(int i, int j) {
		int count = 0;
		int[][] deltas = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
		for (int[] delta: deltas) {
			if (cells[delta[0]][delta[1]] != null && cells[delta[0]][delta[1]].isBomb()) {
				count++;
			}
		}
		
		return count;
	}

	private void InitializeBombCells() {
		int count = 0;
		Random random = new Random();
		while (count != nBombs) {
			int i = random.nextInt(rows);
			int j = random.nextInt(columns);
			if (cells[i][j] == null) {
				cells[i][j] = new Cell(i, j, -1, true);
				count++;
			}
		}
	}
	
	public UserPlayResult playCell(UserPlay userPlay) {
		int i = userPlay.getRow();
		int j = userPlay.getColumn();
		
		// Check if bomb
		if (cells[i][j].isBomb()) {
			return UserPlayResult.Lost;
		}
		
		// Expose cells
		if (cells[i][j].getValue() == 0) {
			expandBlankAreas(i, j);
		}
		else {
			expose(cells[i][j]);
		}
		
		// Check if player won
		if (noOfUnexposedCells == 0) {
			return UserPlayResult.Won;
		}
		
		return UserPlayResult.Successful;
	}
	
	public void toggleGuess(int i, int j) {
		cells[i][j].toggleGuess();
	}
	
	private void expandBlankAreas(int i, int j) {
		expose(cells[i][j]);
		if (cells[i][j].getValue() != 0) {
			return;
		}
		
		// Expand near by blank areas.
		int[][] deltas = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
		for (int[] delta: deltas) {
			if (!cells[delta[0]-1][delta[1]-1].isExposed()) {
				expandBlankAreas(delta[0]-1, delta[1]-1);
			}
		}
	}

	private void expose(Cell cell) {
		if (!cell.isExposed()) {
			cell.expose();
			noOfUnexposedCells--;	
		}
	}
	
	
}
