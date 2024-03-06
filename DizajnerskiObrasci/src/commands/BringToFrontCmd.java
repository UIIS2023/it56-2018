package commands;

import drawing.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command{
	
	private DrawingModel model;
	private Shape shapeToSend;
	private int oldIndex;
	
	public BringToFrontCmd(DrawingModel model) {
		this.model = model;
		this.shapeToSend = model.getSelectedShapes().get(0);
		this.oldIndex = model.getShapes().indexOf(shapeToSend);
	}

	@Override
	public void execute() {
		for(int i = oldIndex; i <= model.getShapes().size() - 1; i++) {
			Shape shape = model.getShapes().get(i);
			int index = model.getShapes().indexOf(shape);
			if(shape != shapeToSend && index != 0) {
				model.getShapes().set(index - 1, shape);
			}
		}
		model.getShapes().set(model.getShapes().size() - 1, shapeToSend);
	}

	@Override
	public void unexecute() {
		for (int i = model.getShapes().size() - 1; i >= oldIndex; i--) {
			Shape shape = model.getShapes().get(i);
			int index = model.getShapes().indexOf(shape);
			if(shape != shapeToSend && index != model.getShapes().size() - 1) {
				model.getShapes().set(index + 1, shape);
			}
		}
		model.getShapes().set(oldIndex, shapeToSend);
	}

}
