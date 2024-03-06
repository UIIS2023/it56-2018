package commands;

import drawing.Donut;

public class UpdateDonutCmd implements Command {
	
	private Donut oldState;
	private Donut newState;
	private Donut original = new Donut();
	
	public UpdateDonutCmd(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setLineColor(newState.getLineColor());
		oldState.setFillColor(newState.getFillColor());
	}

	@Override
	public void unexecute() {
		oldState.setCenter(original.getCenter());
		oldState.setRadius(original.getRadius());
		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setLineColor(original.getLineColor());
		oldState.setFillColor(original.getFillColor());
	}

}
