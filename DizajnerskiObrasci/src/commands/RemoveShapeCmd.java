package commands;

import java.util.ArrayList;
import java.util.List;

import drawing.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command{
	
	private List<Shape> shapes;
	private List<Shape> allShape;
	private DrawingModel model;
	
	public RemoveShapeCmd(DrawingModel model) {
		this.model = model;
		this.shapes = new ArrayList<Shape>(model.getSelectedShapes());
		this.allShape = new ArrayList<Shape>(model.getShapes());
	}

	@Override
	public void execute() {
		model.getShapes().removeAll(shapes);
		model.getSelectedShapes().removeAll(shapes);
	}

	@Override
	public void unexecute() {
		model.getShapes().clear();
		model.getShapes().addAll(allShape);
		model.getSelectedShapes().addAll(shapes);
		//model.getShapes().addAll(shapes);
		//model.getSelectedShapes().addAll(shapes);
	}

}
