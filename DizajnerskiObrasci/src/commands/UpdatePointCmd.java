package commands;

import drawing.Point;

public class UpdatePointCmd implements Command { 
	
	private Point oldState;
	private Point newState;
	private Point original = new Point(); 
	
	public UpdatePointCmd(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setLineColor(newState.getLineColor());
	}

	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setLineColor(original.getLineColor());
	}

}
