package objectOrientedDesign.othello;

public class Piece {
	private Color color;

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void flip() {
		color = (color == Color.black) ? Color.white : Color.black;
	}

}
