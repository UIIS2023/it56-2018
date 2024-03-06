package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Point extends Shape implements Movable, Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public Point() {

	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, Color lineColor) {
		super(lineColor);
		this.x = x;
		this.y = y;
	}

	@Override
	public void moveOn(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void moveBy(int dx, int dy) {
		this.x = this.x + dx;
		this.y = this.y + dy;
	}
	
	public double distance(int x, int y) {
		double dx = this.x - x;
		double dy = this.y - y;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
	}
	
	@Override
	public Point clone() {
		Point p = new Point();
		p.setX(this.getX());
		p.setY(this.getY());
		p.setLineColor(this.getLineColor());
		return p;
	}

	public String toString() {
		return "Point x: " + x + " y: " + y + " line color: " + getLineColor().getRGB();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			if (this.x == p.getX() && this.y == p.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawLine(x - 1, y - 1, x + 1, y + 1);
		g.drawLine(x - 1, y + 1, x + 1, y - 1);

		if (this.isSelected()) {
			this.selected(g);
		}

	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x - 3, y - 3, 6, 6);
	}

	@Override
	public boolean contains(int x, int y) {
		if (this.distance(x, y) <= 3) {
			return true;
		} else {
			return false;
		}
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
