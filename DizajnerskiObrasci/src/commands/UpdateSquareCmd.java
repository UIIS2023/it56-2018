package commands;

import drawing.Square;

public class UpdateSquareCmd implements Command{
	
	private Square oldState;
	private Square newState;
	private Square original = new Square();
	
	public UpdateSquareCmd(Square oldState, Square newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		 original = oldState.clone();
		 
		 oldState.setUpperLeft(newState.getUpperLeft());
		 oldState.setWidth(newState.getWidth());
		 oldState.setLineColor(newState.getLineColor());
		 oldState.setFillColor(newState.getFillColor());
	}

	@Override
	public void unexecute() {
		 oldState.setUpperLeft(original.getUpperLeft());
		 oldState.setWidth(original.getWidth());
		 oldState.setLineColor(original.getLineColor());
		 oldState.setFillColor(original.getFillColor());
	}

}
