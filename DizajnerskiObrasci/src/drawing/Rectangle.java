package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Rectangle extends Square implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private int height;

	public Rectangle() {

	}

	public Rectangle(Point upperLeft, int height, int width) {
		super(upperLeft, width);
		this.height = height;
	}

	public Rectangle(Rectangle r) {
		this(r.upperLeft, r.height, r.width);
		setLineColor(r.getLineColor());
		setFillColor(r.getFillColor());

	}

	public Rectangle(Point upperLeft, int height, int width, Color lineColor) {
		this(upperLeft, height, width);
		this.setFillColor(lineColor);
	}

	public Rectangle(Point upperLeft, int height, int width, Color lineColor, Color fillColor) {
		this(upperLeft, height, width);
		this.setLineColor(lineColor);
		this.setFillColor(fillColor);
	}

	public double area() {
		return height * getWidth();
	}

	public Line diagonal() {
		int ddx = upperLeft.getX() + getWidth();
		Point upperRight = new Point(ddx, upperLeft.getY());

		int ddy = upperLeft.getY() + height;
		Point downLeft = new Point(upperLeft.getX(), ddy);

		Line diagonal = new Line(upperRight, downLeft);
		return diagonal;
	}

	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), getWidth(), height);

		if (this.isSelected()) {
			this.selected(g);
		}
		fill(g);
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(getUpperLeft().getX() - 3, getUpperLeft().getY() - 3, 6, 6);
		g.drawRect(getUpperLeft().getX() + getWidth() - 3, getUpperLeft().getY() - 3, 6, 6);
		g.drawRect(getUpperLeft().getX() - 3, getUpperLeft().getY() + getHeight() - 3, 6, 6);
		g.drawRect(getUpperLeft().getX() + getWidth() - 3, getUpperLeft().getY() + getHeight() - 3, 6, 6);
	}

	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(upperLeft.getX() + 1, upperLeft.getY() + 1, getWidth() - 1, height - 1);
	}

	public boolean contains(int x, int y) {
		if (upperLeft.getX() <= x && x <= upperLeft.getX() + getWidth() && upperLeft.getY() <= y
				&& y <= upperLeft.getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Rectangle clone() {
		Rectangle r = new Rectangle();
		r.setUpperLeft(this.getUpperLeft());
		r.setWidth(this.getWidth());
		r.setHeight(this.getHeight());
		r.setLineColor(this.getLineColor());
		r.setFillColor(this.getFillColor());
		return r;
	}


	public String toString() {
		return "Rectangle upperLeftX: " + upperLeft.getX() + " upperLeftY: " + upperLeft.getY() + " height: " + height + " width: "
				+ width + " line color: " +getLineColor().getRGB() + " fill color: " +getFillColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			if (this.upperLeft.equals(r.getUpperLeft()) && this.height == r.getHeight() && this.width == r.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
