package commands;

import drawing.Circle;

public class UpdateCircleCmd implements Command {
	
	private Circle oldState;
	private Circle newState;
	private Circle original = new Circle();
	
	public UpdateCircleCmd(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setLineColor(newState.getLineColor());
		oldState.setFillColor(newState.getFillColor());
		

	}
	
	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setLineColor(original.getLineColor());
		oldState.setFillColor(original.getFillColor());
	}
	
	

}
