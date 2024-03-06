package commands;

import drawing.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command{
	
	private DrawingModel model;
	private Shape shapeToSend;
	private Shape shapeToChange;
	private int oldIndex;
	private int newIndex;
	
	public ToBackCmd(DrawingModel model) {
		this.model = model;
		this.shapeToSend = model.getSelectedShapes().get(0);
		this.oldIndex = model.getShapes().indexOf(shapeToSend);
		this.newIndex = oldIndex - 1;
		this.shapeToChange = model.getShapes().get(newIndex);
	}

	@Override
	public void execute() {
		model.getShapes().set(newIndex, shapeToSend);
		model.getShapes().set(oldIndex, shapeToChange);
	}

	@Override
	public void unexecute() {
		model.getShapes().set(oldIndex, shapeToSend);
		model.getShapes().set(newIndex, shapeToChange);
	}

}
