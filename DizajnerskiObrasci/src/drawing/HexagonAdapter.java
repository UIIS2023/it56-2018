package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape implements Movable, Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;

	public HexagonAdapter() {

	}
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public HexagonAdapter(Point p, int radius, Color lineColor, Color fillColor) {
		hexagon = new Hexagon(p.getX(), p.getY(), radius);
		hexagon.setBorderColor(lineColor);
		hexagon.setAreaColor(fillColor);
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		return;
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}
	
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void moveOn(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveBy(int x, int y) {
		hexagon.setX(hexagon.getX()+x);
		hexagon.setY(hexagon.getY()+y);
	}
	
	@Override
	public HexagonAdapter clone() {
		HexagonAdapter h = new HexagonAdapter(new Point(getX(), getY()), getR(), getLineColor(), getFillColor());
		return h;
	}
	
	public String toString() {
		return "Hexagon radius: " +hexagon.getR() + " x: " +hexagon.getX() + " y: " +hexagon.getY() + 
				" line color: " +getLineColor().getRGB() +
				" fill color: " +getFillColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter h = (HexagonAdapter) obj;
			if (this.getX() == h.getX() && this.getY() == h.getY() && this.getR() == h.getR()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getX() {
		return hexagon.getX();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public Color getLineColor() {
		return hexagon.getBorderColor();
	}
	
	public void setLineColor(Color lineColor) {
		hexagon.setBorderColor(lineColor);
	}
	
	public Color getFillColor() {
		return hexagon.getAreaColor();
	}
	
	public void setFillColor(Color fillColor) {
		hexagon.setAreaColor(fillColor);
	}

}
