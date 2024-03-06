package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends SurfaceShape implements Movable, Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;

	public Circle() {

	}

	public Circle(Circle c) {
		this(c.center, c.radius);
		setLineColor(c.getLineColor());
		setFillColor(c.getFillColor());
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, Color lineColor) {
		this(center, radius);
		setLineColor(lineColor);
	}

	public Circle(Point center, int radius, Color lineColor, Color fillColor) {
		this(center, radius);
		setLineColor(lineColor);
		setFillColor(fillColor);
	}

	@Override
	public void moveOn(int newX, int newY) {
		center.moveOn(newX, newY);
	}

	@Override
	public void moveBy(int newX, int newY) {
		center.moveBy(newX, newY);
	}

	@Override
	public double area() {
		return radius * radius * Math.PI;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getFillColor());
		g.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, radius * 2 - 2, radius * 2 - 2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawOval(this.center.getX() - this.radius, this.center.getY() - this.radius, 2 * this.radius,
				2 * this.radius);

		if (this.isSelected()) {
			this.selected(g);
		}
		fill(g);
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
		g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
		g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
		g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
	}

	@Override
	public boolean contains(int x, int y) {
		if (center.distance(x, y) <= radius) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return "Circle center x: " + center.getX() + " y: " + center.getY() + " radius: " + radius + 
				" line color: " + getLineColor().getRGB() +" fill color: " +getFillColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public Circle clone() {
		Circle c = new Circle();
		c.setCenter(this.getCenter());
		c.setRadius(this.getRadius());
		c.setLineColor(this.getLineColor());
		c.setFillColor(this.getFillColor());
		return c;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
         this.radius = radius;
	}

}
