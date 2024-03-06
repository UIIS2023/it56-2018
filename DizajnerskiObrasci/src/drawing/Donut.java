package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Donut extends Circle implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center,radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, Color lineColor) {
		this(center,radius,innerRadius);
		setLineColor(lineColor);
	}
	
	public Donut(Point center, int radius, int innerRadius, Color lineColor, Color fillColor) {
		this(center,radius,innerRadius,lineColor);
		setFillColor(fillColor);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d =(Graphics2D) g.create();
		Area donut = makeDonut();
		g2d.setColor(getFillColor());
		g2d.fill(donut);
		g2d.setColor(getLineColor());
		g2d.draw(donut);
		
		if(isSelected()) {
			super.selected(g);
		}
	}
	
	private Area makeDonut() {
		int outerRadius = super.getRadius();
		Ellipse2D outer = new Ellipse2D.Double(getCenter().getX() - outerRadius, getCenter().getY() - outerRadius, outerRadius * 2, outerRadius * 2);
		Ellipse2D inner = new Ellipse2D.Double(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, innerRadius * 2, innerRadius * 2);
		Area circle = new Area(outer);
		circle.subtract(new Area(inner));
		return circle;
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return dFromCenter > innerRadius && super.contains(x, y);
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return dFromCenter > innerRadius  && super.contains(p.getX(), p.getY());
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut d = (Donut) obj;
			if(this.getCenter().equals(d.getCenter()) &&
			        this.getRadius() == d.getRadius() &&
			        this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public Donut clone() {
		Donut d = new Donut();
		d.setCenter(this.getCenter());
		d.setRadius(this.getRadius());
		d.setInnerRadius(this.getInnerRadius());
		d.setLineColor(this.getLineColor());
		d.setFillColor(this.getFillColor());
		return d;
	}
	
	public String toString() {
		return "Donut center x: "+ this.getCenter().getX() + " y: " + this.getCenter().getY() + " radius: " + this.getRadius() +
				" inner radius: " +innerRadius + " line color: " + this.getLineColor().getRGB() + " fill color: " + this.getFillColor().getRGB();
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	

}
