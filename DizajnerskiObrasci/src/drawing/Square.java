package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Square extends SurfaceShape implements Movable, Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	protected Point upperLeft;
	protected int width;

	public Square() {

	}

	public Square(Square s) {
		this(s.upperLeft, s.width);
		setLineColor(s.getLineColor());
		setFillColor(s.getFillColor());
	}

	public Square(Point upperLeft, int width) {
		this.upperLeft = upperLeft;
		this.width = width;
	}

	public Square(Point upperLeft, int width, Color lineColor) {
		this(upperLeft, width);
		setLineColor(lineColor);
	}

	public Square(Point upperLeft, int width, Color lineColor, Color fillColor) {
		this(upperLeft, width);
		setLineColor(lineColor);
		setFillColor(fillColor);
	}

	@Override
	public void moveOn(int newX, int newY) {
		upperLeft.moveOn(newX, newY);

	}

	@Override
	public void moveBy(int newX, int newY) {
		upperLeft.moveBy(newX, newY);

	}

	@Override
	public double area() {
		return width * width;

	}

	public Line diagonal() {
		int ddx = upperLeft.getX() + width;
		Point upperRight = new Point(ddx, upperLeft.getY());

		int ddy = upperLeft.getY() + width;
		Point downLeft = new Point(upperLeft.getX(), ddy);

		Line diagonal = new Line(upperRight, downLeft);

		return diagonal;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(upperLeft.getX() + 1, upperLeft.getY() + 1, width - 1, width - 1);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, width);
		if (this.isSelected()) {
			this.selected(g);
		}
		fill(g);
	}

	public void selected(Graphics g) {
		upperLeft.selected(g);
		this.diagonal().getStartPoint().selected(g);
		this.diagonal().getEndPoint().selected(g);
		Point downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + width);
		downRight.selected(g);

		Line line1 = new Line(upperLeft, this.diagonal().getStartPoint());
		line1.middlePoint().selected(g);
		Line line2 = new Line(this.diagonal().getEndPoint(), downRight);
		line2.middlePoint().selected(g);
		Line line3 = new Line(upperLeft, this.diagonal().getEndPoint());
		line3.middlePoint().selected(g);
		Line line4 = new Line(this.diagonal().getStartPoint(), downRight);
		line4.middlePoint().selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		if (x <= upperLeft.getX() + width && x > upperLeft.getX() && y <= upperLeft.getY() + width
				&& y > upperLeft.getY())
			return true;
		else
			return false;
	}
	
	@Override
	public Square clone() {
		Square s = new Square();
		s.setUpperLeft(this.getUpperLeft());
		s.setWidth(this.getWidth());
		s.setLineColor(this.getLineColor());
		s.setFillColor(this.getFillColor());
		return s;
	}

	public String toString() {
		return "Square x: " + upperLeft.getX() + " y: " + upperLeft.getY() + " width: " + width + 
				" line color: " +getLineColor().getRGB() + " fill color: " +getFillColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square s = (Square) obj;
			if (this.upperLeft.equals(s.getUpperLeft()) && this.width == s.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Point getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
