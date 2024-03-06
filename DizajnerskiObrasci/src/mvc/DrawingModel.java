package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import drawing.Shape;

public class DrawingModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	protected ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void add(Shape s) {
		shapes.add(s);
	}
	
	public void remove(Shape s) {
		shapes.remove(s);
	}
	
	public Shape get(int index) {
		return shapes.get(index);
	}
	
	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}
	
	public void addSelectedShape(Shape s) {
		selectedShapes.add(s);
	}
	
	public void removeSelectedShape(Shape s) {
		selectedShapes.remove(s);
	}

}
