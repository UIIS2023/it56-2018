package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class SurfaceShape extends Shape implements Serializable {

	private static final long serialVersionUID = 1L;
	private Color fillColor = Color.WHITE;

	public SurfaceShape() {

	}

	public SurfaceShape(boolean selected, Color lineColor, Color fillColor) {
		super(selected, lineColor);
		this.fillColor = fillColor;
	}

	public abstract double area();

	public abstract void fill(Graphics g);

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

}
