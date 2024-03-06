package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Line extends Shape implements Movable, Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, Color lineColor) {
		super(lineColor);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.getEndPoint().getY());

		if (this.isSelected()) {
			this.selected(g);
		}
	}

	public void selected(Graphics g) {
		startPoint.selected(g);
		this.middlePoint().selected(g);
		endPoint.selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		double startDistance = startPoint.distance(x, y);
		double endDistance = endPoint.distance(x, y);

		if (startDistance + endDistance <= this.length() + 0.05) {
			return true;
		} else {
			return false;
		}
	}

	public double length() {
		return this.startPoint.distance(this.endPoint.getX(), this.endPoint.getY());
	}

	public Point middlePoint() {
		int mx = (startPoint.getX() + endPoint.getX()) / 2;
		int my = (startPoint.getY() + endPoint.getY()) / 2;

		Point middle = new Point(mx, my);
		return middle;
	}

	@Override
	public Line clone() {
		Line l = new Line();
		l.setStartPoint(this.getStartPoint());
		l.setEndPoint(this.getEndPoint());
		l.setLineColor(this.getLineColor());
		return l;
	}

	public String toString() {
		return "Line start x: " + startPoint.getX() + " y: " + startPoint.getY() + " End x: " + endPoint.getX() + " y: "
				+ endPoint.getY() + " line color: " +getLineColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line l = (Line) obj;
			if (this.startPoint.equals(l.getStartPoint()) && this.endPoint.equals(l.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void moveOn(int x, int y) {
		int midX = (startPoint.getX() + endPoint.getX()) / 2;
		int midY = (startPoint.getY() + endPoint.getY()) / 2;
		int dx = x - midX;
		int dy = y - midY;
		this.startPoint.moveBy(dx, dy);
		this.endPoint.moveBy(dx, dy);
	}

	@Override
	public void moveBy(int dx, int dy) {
		this.startPoint.moveOn(this.startPoint.getX() + dx, this.startPoint.getY() + dy);
		this.endPoint.moveOn(this.endPoint.getX() + dx, this.endPoint.getY() + dy);
	}

	public Point getStartPoint() {
		return this.startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return this.endPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

}
