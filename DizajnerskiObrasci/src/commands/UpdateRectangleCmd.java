package commands;

import drawing.Rectangle;

public class UpdateRectangleCmd implements Command{
	
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original = new Rectangle();
	
	public UpdateRectangleCmd(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.setUpperLeft(newState.getUpperLeft());
		oldState.setWidth(newState.getWidth());
		oldState.setHeight(newState.getHeight());
		oldState.setLineColor(newState.getLineColor());
		oldState.setFillColor(newState.getFillColor());
	}

	@Override
	public void unexecute() {
		oldState.setUpperLeft(original.getUpperLeft());
		oldState.setWidth(original.getWidth());
		oldState.setHeight(original.getHeight());
		oldState.setLineColor(original.getLineColor());
		oldState.setFillColor(original.getFillColor());
	}

}
