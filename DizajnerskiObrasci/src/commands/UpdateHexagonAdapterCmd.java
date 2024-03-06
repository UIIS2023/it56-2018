package commands;

import drawing.HexagonAdapter;

public class UpdateHexagonAdapterCmd implements Command {
	
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original = new HexagonAdapter();
	
	public UpdateHexagonAdapterCmd(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setR(newState.getR());
		oldState.setLineColor(newState.getLineColor());
		oldState.setFillColor(newState.getFillColor());
	}

	@Override
	public void unexecute() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setR(original.getR());
		oldState.setLineColor(original.getLineColor());
		oldState.setFillColor(original.getFillColor());
	}

}
