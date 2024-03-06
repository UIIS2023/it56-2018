package mvc;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import drawing.Shape;
import java.awt.Color;
import java.awt.SystemColor;

public class DrawingView extends JPanel {

	private DrawingModel model = new DrawingModel();

	public DrawingView() {

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}
	
	public void setModel(DrawingModel model) {
		this.model = model;
	}

}
