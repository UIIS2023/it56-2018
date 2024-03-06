package commands;

import drawing.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command{
	
	private DrawingModel model;
	private Shape shapeToSend;
	private int oldIndex;
	
	public BringToBackCmd(DrawingModel model) {
		this.model = model;
		this.shapeToSend = model.getSelectedShapes().get(0);
		this.oldIndex = model.getShapes().indexOf(shapeToSend);
	}

	@Override
	public void execute() {
		for (int i = oldIndex; i >= 0; i--) {
			Shape shape = model.getShapes().get(i);
			int index = model.getShapes().indexOf(shape);
			if(shape != shapeToSend && index != model.getShapes().size() - 1) {
				model.getShapes().set(index + 1, shape);
			}
		}
		model.getShapes().set(0, shapeToSend);
	}

	@Override
	public void unexecute() {
		for(int i = 0; i <= oldIndex; i++) {
			Shape shape = model.getShapes().get(i);
			int index = model.getShapes().indexOf(shape);
			if(shape != shapeToSend && index != 0) {
				model.getShapes().set(index - 1, shape);
			}
		}
		model.getShapes().set(oldIndex, shapeToSend);
	}

}
