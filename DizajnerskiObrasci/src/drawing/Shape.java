package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color lineColor = Color.BLACK;

	public Shape() {

	}

	public Shape(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Shape(boolean selected, Color lineColor) {
		this.selected = selected;
		this.lineColor = lineColor;
	}

	public abstract void draw(Graphics g);

	public abstract boolean contains(int x, int y);

	public boolean isSelected() {
		return this.selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getLineColor() {
		return this.lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

}
