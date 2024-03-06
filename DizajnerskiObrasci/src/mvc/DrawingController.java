package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import commands.AddShapeCmd;
import commands.BringToBackCmd;
import commands.BringToFrontCmd;
import commands.Command;
import commands.RemoveShapeCmd;
import commands.ToBackCmd;
import commands.ToFrontCmd;
import commands.UpdateCircleCmd;
import commands.UpdateDonutCmd;
import commands.UpdateHexagonAdapterCmd;
import commands.UpdateLineCmd;
import commands.UpdatePointCmd;
import commands.UpdateRectangleCmd;
import commands.UpdateSquareCmd;
import dialogs.DialogCircle;
import dialogs.DialogDelete;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
import dialogs.DialogModifyCircle;
import dialogs.DialogModifyDonut;
import dialogs.DialogModifyHexagon;
import dialogs.DialogModifyLine;
import dialogs.DialogModifyPoint;
import dialogs.DialogModifyRectangle;
import dialogs.DialogModifySquare;
import dialogs.DialogRectangle;
import dialogs.DialogSquare;
import drawing.Circle;
import drawing.Donut;
import drawing.HexagonAdapter;
import drawing.Line;
import drawing.Point;
import drawing.Rectangle;
import drawing.Shape;
import drawing.Square;
import observer.Observable;
import observer.Observer;

public class DrawingController implements Observable {

	private DrawingModel model;
	private DrawingFrame frame;
	
	private int click = 1;
	private Point startPoint;
	private boolean isCtrl;
	
	private List<Observer> observers = new ArrayList<Observer>();
	private boolean enabledModify = false;
	private boolean enabledDelete = false;
	private Shape currentSelectedShape = null;
	private List<Command> listCommands = new ArrayList<Command>();
	private List<Command> listForRedo = new ArrayList<Command>();
	private List<String> listForLoggerLines = new ArrayList<String>();
	private int loggerLineIndex = 0;
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void executeCommand(Command command) {
		listCommands.add(command);
		command.execute();
		frame.getView().repaint();
		enableUndoRedo();
	}
	
	public void drawPoint(int x, int y) {
		Point p = new Point(x, y, frame.getLineColor());
		AddShapeCmd addPoint = new AddShapeCmd(p, model);
		executeCommand(addPoint);
		frame.getDefaultLoggerList().addElement("AddShapeCmd>" + p.toString());
		listForRedo.clear();
		frame.getBtnRedo().setEnabled(false);
		click = 1;
	}
	
	public void drawLine(int x, int y) {
		if(click == 1) {
			startPoint = new Point(x, y);
			click++;
		} else {
			Point endPoint = new Point(x, y);
			Line line = new Line(startPoint, endPoint, frame.getLineColor());
			click = 1;
			AddShapeCmd addLine = new AddShapeCmd(line, model);
			executeCommand(addLine);
			frame.getDefaultLoggerList().addElement("AddShapeCmd>" + line.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		}
	}
	
	public void drawSquare(int x, int y) {
		DialogSquare dialog = new DialogSquare();
		dialog.setVisible(true);
		if(dialog.getCorrect() == true) {
			Square square = new Square(new Point(x, y), dialog.getDialogWidth(), frame.getLineColor(), frame.getFillColor());
			click = 1;
			AddShapeCmd addSquare = new AddShapeCmd(square, model);
			executeCommand(addSquare);
			frame.getDefaultLoggerList().addElement("AddShapeCmd>" + square.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		}
	}
	
	public void drawRectangle(int x, int y) {
		DialogRectangle dialog = new DialogRectangle();
		dialog.setVisible(true);
		if(dialog.getCorrect() == true) {
			Rectangle rectangle = new Rectangle(new Point(x, y),  dialog.getDialogHeight(), dialog.getDialogWidth(), frame.getLineColor(), 
					frame.getFillColor());
			click = 1;
			AddShapeCmd addRectangle = new AddShapeCmd(rectangle, model);
			executeCommand(addRectangle);
			frame.getDefaultLoggerList().addElement("AddShapeCmd>" + rectangle.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		}
	}
	
	public void drawCircle(int x, int y) {
		DialogCircle dialog = new DialogCircle();
		dialog.setVisible(true);
		if(dialog.getCorrect() == true) {
			Circle circle = new Circle(new Point(x, y), dialog.getDialogRadius(), frame.getLineColor(), frame.getFillColor());
			click = 1;
			AddShapeCmd addCircle = new AddShapeCmd(circle, model);
			executeCommand(addCircle);
			frame.getDefaultLoggerList().addElement("AddShapeCmd>" + circle.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		}
	}
	
	public void drawDonut(int x, int y) {
		DialogDonut dialog = new DialogDonut();
		dialog.setVisible(true);
		if(dialog.getCorrect() == true) {
			Donut donut = new Donut(new Point(x, y), dialog.getDialogRadius(), dialog.getDialogInnerRadius(), frame.getLineColor(), 
					frame.getFillColor());
			click = 1;
			AddShapeCmd addDonut = new AddShapeCmd(donut, model);
			executeCommand(addDonut);
			frame.getDefaultLoggerList().addElement("AddShapeCmd>" + donut.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		}
	}
	
	public void drawHexagon(int x, int y) {
		DialogHexagon dialog = new DialogHexagon();
		dialog.setVisible(true);
		if(dialog.getCorrect() == true) {
			HexagonAdapter hexagon = new HexagonAdapter(new Point(x, y), dialog.getDialogRadius(), frame.getLineColor(), frame.getFillColor());
			click = 1;
			AddShapeCmd addHexagon = new AddShapeCmd(hexagon, model);
			executeCommand(addHexagon);
			frame.getDefaultLoggerList().addElement("AddShapeCmd>" + hexagon.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		}
	}
	
	public void selectedShape(int x, int y, int modifiers) {
		boolean flag = false;
		isCtrl = (modifiers & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK;
		Collections.reverse(model.getShapes());
		for(Shape shape: model.getShapes()) {
			if(shape.contains(x, y) && !shape.isSelected()) {
				flag = true;
				if(!isCtrl) {
					updateStateOfSelectedShape(shape);
					frame.getDefaultLoggerList().addElement("SelectedShape>" + shape.toString());
					break;
				} else {
					frame.getDefaultLoggerList().addElement("SelectedShape>" + shape.toString());
					addSelectedShape(shape);
					break;
				}
				
			} else if(!isCtrl && shape.isSelected() && shape.contains(x, y)) {
				flag = true;
				frame.getDefaultLoggerList().addElement("DeselectedShape>" + shape.toString());
				shape.setSelected(false);
				model.getSelectedShapes().remove(shape);
				break;
			} else if(isCtrl && shape.isSelected() && shape.contains(x, y)) {
				flag = true;
				break;
			}
		}
		if(flag == false && !isCtrl) {
			deselectedShapes();
		}
		if(model.getSelectedShapes().size() == 1) {
			currentSelectedShape = model.getSelectedShapes().get(0);
		}
		Collections.reverse(model.getShapes());
		notifyObserver();
		frame.getView().repaint();
	}
	
	public void deselectedShapes() {
		ArrayList<Shape> tempShapes = new ArrayList<Shape>();
		for(Shape shape1: model.getSelectedShapes()) {
			if(shape1.isSelected()) {
				tempShapes.add(shape1);
			}
		}
		for(Shape shape2: tempShapes) {
			frame.getDefaultLoggerList().addElement("DeselectedShape>" + shape2.toString());
			shape2.setSelected(false);
			model.getSelectedShapes().remove(shape2);
		}
	}
	
	public void updateStateOfSelectedShape(Shape shape) {
		deselectedShapes();
		addSelectedShape(shape);
	}
	
	public void addSelectedShape(Shape shape) {
//		boolean flag = false;
//		for(Shape s : model.getSelectedShapes()) {
//			if(s.equals(shape)) {
//				flag = true;
//			}
//		}
		if(shape != null) {
			model.addSelectedShape(shape);
			shape.setSelected(true);
		}
	}

	public void deleteShape() {
			DialogDelete dialog = new DialogDelete();
			dialog.setVisible(true);
			if(dialog.isCorrect() == true) {
				frame.getDefaultLoggerList().addElement("DeleteShapeCmd>Number of deleted shapes = " + model.getSelectedShapes().size());
				RemoveShapeCmd removeShape = new RemoveShapeCmd(model);
				executeCommand(removeShape);
				listForRedo.clear();
				frame.getBtnRedo().setEnabled(false);
			}
			notifyObserver();
	}
	
	public void modify() {
		if(currentSelectedShape.toString().contains("Point")) {
			Point newPoint = null;
			DialogModifyPoint dialog = new DialogModifyPoint((Point)currentSelectedShape);
			dialog.setVisible(true);
			if(dialog.getCorrect() == true) {
				newPoint = new Point(dialog.getDialogX(), dialog.getDialogY(), dialog.getLineColor());
				UpdatePointCmd update = new UpdatePointCmd((Point)currentSelectedShape, newPoint);
				executeCommand(update);
				frame.getDefaultLoggerList().addElement("UpdateShapeCmd>" + newPoint.toString());
			}
		} else if(currentSelectedShape.toString().contains("Line")) {
			Line newLine = null;
			DialogModifyLine dialog = new DialogModifyLine((Line)currentSelectedShape);
			dialog.setVisible(true);
			if(dialog.getCorrect() == true) {
				newLine = new Line(new Point(dialog.getStartCoordinateX(), dialog.getStartCoordinateY()), 
						new Point(dialog.getEndCoordinateX(), dialog.getEndCoordinateY()), dialog.getLineColor());
				UpdateLineCmd update = new UpdateLineCmd((Line)currentSelectedShape, newLine);
				executeCommand(update);
				frame.getDefaultLoggerList().addElement("UpdateShapeCmd>" + newLine.toString());
			}
		} else if(currentSelectedShape.toString().contains("Circle")) {
			Circle newCircle = null;
			DialogModifyCircle dialog = new DialogModifyCircle((Circle)currentSelectedShape);
			dialog.setVisible(true);
			if(dialog.getCorrect() == true) {
				newCircle = new Circle(new Point(dialog.getDialogCenterX(), dialog.getDialogCenterY()), dialog.getDialogRadius(),
						dialog.getLineColor(), dialog.getFillColor());
				UpdateCircleCmd update = new UpdateCircleCmd((Circle)currentSelectedShape, newCircle);
				executeCommand(update);
				frame.getDefaultLoggerList().addElement("UpdateShapeCmd>" + newCircle.toString());
			}
		} else if(currentSelectedShape.toString().contains("Donut")) {
			System.out.println(currentSelectedShape.toString());
			Donut newDonut = null;
			DialogModifyDonut dialog = new DialogModifyDonut((Donut)currentSelectedShape);
			dialog.setVisible(true);
			if(dialog.getCorrect() == true) {
				newDonut = new Donut(new Point(dialog.getDialogCenterX(), dialog.getDialogCenterY()), dialog.getDialogRadius(), dialog.getDialogInnerRadius(), 
						dialog.getLineColor(), dialog.getFillColor());
				UpdateDonutCmd update = new UpdateDonutCmd((Donut)currentSelectedShape, newDonut);
				executeCommand(update);
				frame.getDefaultLoggerList().addElement("UpdateShapeCmd>" + newDonut.toString());
			}
		} else if(currentSelectedShape.toString().contains("Square")) {
			Square newSquare = null;
			DialogModifySquare dialog = new DialogModifySquare((Square)currentSelectedShape);
			dialog.setVisible(true);
			if(dialog.getCorrect() == true) {
				newSquare = new Square(new Point(dialog.getDialogUpperLeftX(), dialog.getDialogUpperLeftY()), dialog.getDialogWidth(), dialog.getLineColor(), 
						dialog.getFillColor());
				UpdateSquareCmd update = new UpdateSquareCmd((Square)currentSelectedShape, newSquare);
				executeCommand(update);
				frame.getDefaultLoggerList().addElement("UpdateShapeCmd>" + newSquare.toString());
			}
		} else if(currentSelectedShape.toString().contains("Rectangle")) {
			Rectangle newRectangle = null;
			DialogModifyRectangle dialog = new DialogModifyRectangle((Rectangle)currentSelectedShape);
			dialog.setVisible(true);
			if(dialog.getCorrect() == true) {
				newRectangle = new Rectangle(new Point(dialog.getDialogUpperLeftX(), dialog.getDialogUpperLeftY()), dialog.getDialogHeight(), dialog.getDialogWidth(), 
						dialog.getLineColor(), dialog.getFillColor());
				UpdateRectangleCmd update = new UpdateRectangleCmd((Rectangle)currentSelectedShape, newRectangle);
				executeCommand(update);
				frame.getDefaultLoggerList().addElement("UpdateShapeCmd>" + newRectangle.toString());
			}
		} else {
			HexagonAdapter newHexagon = null;
			DialogModifyHexagon dialog = new DialogModifyHexagon((HexagonAdapter)currentSelectedShape);
			dialog.setVisible(true);
			if(dialog.getCorrect() == true) {
				newHexagon = new HexagonAdapter(new Point(dialog.getDialogCenterX(), dialog.getDialogCenterY()), dialog.getDialogRadius(), dialog.getLineColor(), 
						dialog.getFillColor());
				UpdateHexagonAdapterCmd update = new UpdateHexagonAdapterCmd((HexagonAdapter)currentSelectedShape, newHexagon);
				executeCommand(update);
				frame.getDefaultLoggerList().addElement("UpdateShapeCmd>" + newHexagon.toString());
			}
		}
	}
	
	public void undo() {
		Command command = listCommands.get(listCommands.size() - 1);
		listCommands.remove(command);
		listForRedo.add(command);
		command.unexecute();
		frame.getDefaultLoggerList().addElement("UndoShape>" + command.toString());
		enableUndoRedo();
		frame.getView().repaint();
	}
	
	public void redo() {
		Command command = listForRedo.get(listForRedo.size() - 1);
		listForRedo.remove(command);
		listCommands.add(command);
		command.execute();
		frame.getDefaultLoggerList().addElement("RedoShape>" + command.toString());
		enableUndoRedo();
		frame.getView().repaint();
	}
	
	public void enableUndoRedo() {
		if(listCommands.isEmpty()) {
			frame.getBtnUndo().setEnabled(false);
		} else {
			frame.getBtnUndo().setEnabled(true);
		}
		
		if(listForRedo.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
		} else {
			frame.getBtnRedo().setEnabled(true);
		}
	}
	
	public void sendToFront() {
		if(model.getSelectedShapes().size() == 1 && model.getShapes().indexOf(currentSelectedShape) != model.getShapes().size() - 1) {
			ToFrontCmd command = new ToFrontCmd(model);
			executeCommand(command);
			frame.getDefaultLoggerList().addElement("SendToFrontShapeCmd>" + currentSelectedShape.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, "Only one shape can be selected or shape is already in front");
		}
	}
	
	public void sendToBack() {
		if(model.getSelectedShapes().size() == 1 && model.getShapes().indexOf(currentSelectedShape) != 0) {
			ToBackCmd command = new ToBackCmd(model);
			executeCommand(command);
			frame.getDefaultLoggerList().addElement("SendToBackShapeCmd>" + currentSelectedShape.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, "Only one shape can be selected or shape is already in back");
		}
	}
	
	public void bringToFront() {
		if(model.getSelectedShapes().size() == 1 && model.getShapes().indexOf(currentSelectedShape) != model.getShapes().size() - 1) {
			BringToFrontCmd command = new BringToFrontCmd(model);
			executeCommand(command);
			frame.getDefaultLoggerList().addElement("BringToFrontShapeCmd>" + currentSelectedShape.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, "Only one shape can be selected or shape is already in front");
		}
	}
	
	public void bringToBack() {
		if(model.getSelectedShapes().size() == 1 && model.getShapes().indexOf(currentSelectedShape) != 0) {
			BringToBackCmd command = new BringToBackCmd(model);
			executeCommand(command);
			frame.getDefaultLoggerList().addElement("BringToBackShapeCmd>" + currentSelectedShape.toString());
			listForRedo.clear();
			frame.getBtnRedo().setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, "Only one shape can be selected or shape is already in back");
		}
	}
	
	public void save(String path) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(model);
			fileOutputStream.close();
			List<Object> loggerList = Arrays.asList(frame.getDefaultLoggerList().toArray());
			PrintWriter printWriter = new PrintWriter(path + ".txt");
			for(Object line: loggerList) {
				printWriter.println(line);
			}
			printWriter.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void load(String path) {
		try {
			if(!path.contains(".txt")) {
				FileInputStream fileInputStream = new FileInputStream(path);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				model = (DrawingModel)objectInputStream.readObject();
				fileInputStream.close();
				frame.getView().setModel(model);
				frame.getView().repaint();
			} else {
				loggerLineIndex = 0;
				frame.getBtnReadLine().setEnabled(true);
				listForLoggerLines = Files.readAllLines(Paths.get(path));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeLoggerLine() {
		if(listForLoggerLines.size() == loggerLineIndex) {
			JOptionPane.showMessageDialog(null, "Dont have anymore commands");
			return;
		}
		String line = listForLoggerLines.get(loggerLineIndex);
		loggerLineIndex++;
		String[] lineData = line.split(">");
		String[] shapeData = lineData[1].split(" ");
		if(lineData[0].equals("AddShapeCmd")) {
			Shape shape = createdShape(shapeData);
			AddShapeCmd addShape = new AddShapeCmd(shape, model);
			frame.getDefaultLoggerList().addElement("AddShapeCmd>" + shape);
			executeCommand(addShape);
		} else if(lineData[0].equals("SelectedShape")) {
			Shape shape = createdShape(shapeData);
			int index = model.getShapes().indexOf(shape);
			currentSelectedShape = model.getShapes().get(index);
			addSelectedShape(currentSelectedShape);
			frame.getDefaultLoggerList().addElement("SelectedShape>" + currentSelectedShape);
		} else if (lineData[0].equals("DeselectedShape")) {
			Shape shape = createdShape(shapeData);
			int index = model.getShapes().indexOf(shape);
			currentSelectedShape = model.getShapes().get(index);
			frame.getDefaultLoggerList().addElement("DeselectedShape>" + currentSelectedShape.toString());
			currentSelectedShape.setSelected(false);
			model.getSelectedShapes().remove(currentSelectedShape);
		} else if (lineData[0].equals("UpdateShapeCmd")) {
			Shape shape = createdShape(shapeData);
			if(shape.toString().contains("Point")) {
				UpdatePointCmd update = new UpdatePointCmd((Point)currentSelectedShape, (Point)shape);
				executeCommand(update);
			} else if (shape.toString().contains("Line")) {
				UpdateLineCmd update = new UpdateLineCmd((Line)currentSelectedShape, (Line)shape);
				executeCommand(update);
			} else if (shape.toString().contains("Circle")) {
				UpdateCircleCmd update = new UpdateCircleCmd((Circle)currentSelectedShape, (Circle)shape);
				executeCommand(update);
			} else if (shape.toString().contains("Square")) {
				UpdateSquareCmd update = new UpdateSquareCmd((Square)currentSelectedShape, (Square)shape);
				executeCommand(update);
			} else if (shape.toString().contains("Rectangle")) {
				UpdateRectangleCmd update = new UpdateRectangleCmd((Rectangle)currentSelectedShape, (Rectangle)shape);
				executeCommand(update);
			} else if (shape.toString().contains("Donut")) {
				UpdateDonutCmd update = new UpdateDonutCmd((Donut)currentSelectedShape, (Donut)shape);
				executeCommand(update);
			} else if (shape.toString().contains("Hexagon")) {
				UpdateHexagonAdapterCmd update = new UpdateHexagonAdapterCmd((HexagonAdapter)currentSelectedShape, (HexagonAdapter)shape);
				executeCommand(update);
			}
			frame.getDefaultLoggerList().addElement("UpdateShapeCmd>" + shape.toString());
		} else if (lineData[0].equals("SendToFrontShapeCmd")) {
			ToFrontCmd command = new ToFrontCmd(model);
			executeCommand(command);
			frame.getDefaultLoggerList().addElement("SendToFrontShapeCmd>" + currentSelectedShape.toString());
		} else if (lineData[0].equals("SendToBackShapeCmd")) {
			ToBackCmd command = new ToBackCmd(model);
			executeCommand(command);
			frame.getDefaultLoggerList().addElement("SendToBackShapeCmd>" + currentSelectedShape.toString());
		} else if (lineData[0].equals("BringToFrontShapeCmd")) {
			BringToFrontCmd command = new BringToFrontCmd(model);
			executeCommand(command);
			frame.getDefaultLoggerList().addElement("BringToFrontShapeCmd>" + currentSelectedShape.toString());
		} else if (lineData[0].equals("BringToBackShapeCmd")) {
			BringToBackCmd command = new BringToBackCmd(model);
			executeCommand(command);
			frame.getDefaultLoggerList().addElement("BringToBacktShapeCmd>" + currentSelectedShape.toString());
		} else if (lineData[0].equals("DeleteShapeCmd")) {
			frame.getDefaultLoggerList().addElement("DeleteShapeCmd>Number of deleted shapes = " + model.getSelectedShapes().size());
			RemoveShapeCmd removeShape = new RemoveShapeCmd(model);
			executeCommand(removeShape);
		} else if (lineData[0].equals("UndoShape")) {
			undo();
		} else if (lineData[0].equals("RedoShape")) {
			redo();
		}
		frame.getView().repaint();
	}
	
	private Shape createdShape(String[] shapeData) {
		Shape shape;
		if(shapeData[0].contains("Point")) {
			shape = convertToPoint(shapeData);
		} else if(shapeData[0].equals("Line")) {
			shape = convertToLine(shapeData);
		} else if(shapeData[0].equals("Square")) {
			shape = convertToSquare(shapeData);
		} else if(shapeData[0].equals("Rectangle")) {
			shape = convertToRectangle(shapeData);
		} else if(shapeData[0].equals("Circle")) {
			shape = convertToCircle(shapeData);
		} else if(shapeData[0].equals("Donut")) {
			shape = convertToDonut(shapeData);
		} else {
			shape = convertToHexagon(shapeData);
		}
		return shape;
	}
	
	private Point convertToPoint(String[] shapeData) {
		int x = Integer.parseInt(shapeData[2]);
		int y = Integer.parseInt(shapeData[4]);
		Color lineColor = new Color(Integer.parseInt(shapeData[7]));
		Point point = new Point(x, y, lineColor);
		return point;
	}
	
	private Line convertToLine(String[] shapeData) {
		int startX = Integer.parseInt(shapeData[3]);
		int startY = Integer.parseInt(shapeData[5]);
		int endX = Integer.parseInt(shapeData[8]);
		int endY = Integer.parseInt(shapeData[10]);
		Color lineColor = new Color(Integer.parseInt(shapeData[13]));
		Line line = new Line(new Point(startX, startY), new Point(endX, endY), lineColor);
		return line;
	}
	
	private Square convertToSquare(String[] shapeData) {
		int upperLeftX = Integer.parseInt(shapeData[2]);
		int upperLeftY = Integer.parseInt(shapeData[4]);
		int width = Integer.parseInt(shapeData[6]);
		Color lineColor = new Color(Integer.parseInt(shapeData[9]));
		Color fillColor = new Color(Integer.parseInt(shapeData[12]));
		Square square = new Square(new Point(upperLeftX, upperLeftY), width, lineColor, fillColor);
		return square;
	}
	
	private Rectangle convertToRectangle(String[] shapeData) {
		int upperLeftX = Integer.parseInt(shapeData[2]);
		int upperLeftY = Integer.parseInt(shapeData[4]);
		int height = Integer.parseInt(shapeData[6]);
		int width = Integer.parseInt(shapeData[8]);
		Color lineColor = new Color(Integer.parseInt(shapeData[11]));
		Color fillColor = new Color(Integer.parseInt(shapeData[14]));
		Rectangle rectangle = new Rectangle(new Point(upperLeftX, upperLeftY), height, width, lineColor, fillColor);
		return rectangle;
	}
	
	private Circle convertToCircle(String[] shapeData) {
		int centerX = Integer.parseInt(shapeData[3]);
		int centerY = Integer.parseInt(shapeData[5]);
		int radius = Integer.parseInt(shapeData[7]);
		Color lineColor = new Color(Integer.parseInt(shapeData[10]));
		Color fillColor = new Color(Integer.parseInt(shapeData[13]));
		Circle circle = new Circle(new Point(centerX, centerY), radius, lineColor, fillColor);
		return circle;
	}
	
	private Donut convertToDonut(String[] shapeData) {
		int centerX = Integer.parseInt(shapeData[3]);
		int centerY = Integer.parseInt(shapeData[5]);
		int radius = Integer.parseInt(shapeData[7]);
		int innerRadius = Integer.parseInt(shapeData[10]);
		Color lineColor = new Color(Integer.parseInt(shapeData[13]));
		Color fillColor = new Color(Integer.parseInt(shapeData[16]));
		Donut donut = new Donut(new Point(centerX, centerY), radius, innerRadius, lineColor, fillColor);
		return donut;
	}
	
	private HexagonAdapter convertToHexagon(String[] shapeData) {
		int radius = Integer.parseInt(shapeData[2]);
		int centerX = Integer.parseInt(shapeData[4]);
		int centerY = Integer.parseInt(shapeData[6]);
		Color lineColor = new Color(Integer.parseInt(shapeData[9]));
		Color fillColor = new Color(Integer.parseInt(shapeData[12]));
		HexagonAdapter hexagon = new HexagonAdapter(new Point(centerX, centerY), radius, lineColor, fillColor);
		return hexagon;
	}
	
	public void chooseLineColor() {
		Color temp = JColorChooser.showDialog(null, "Choose color", frame.getLineColor());
		if(temp != null) {
			frame.setLineColor(temp);
			frame.getBtnLineColor().setBackground(temp);
		}
	}
	
	public void chooseFillcolor() {
		Color temp = JColorChooser.showDialog(null, "Choose color", frame.getFillColor());
		if(temp != null) {
			frame.setFillColor(temp);
			frame.getBtnFillColor().setBackground(temp);
		}
	}
	
	public void countForEnabledButtons() {
		if(model.getSelectedShapes().isEmpty()) {
			enabledModify = false;
			enabledDelete = false;
		}
		else if(model.getSelectedShapes().size() == 1) {
			enabledModify = true;
			enabledDelete = true;
		}
		else {
			enabledModify = false;
			enabledDelete = true;
		}
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObserver() {
		countForEnabledButtons();
		Iterator<Observer> it = observers.iterator();
		while(it.hasNext()) 
			it.next().update(enabledModify, enabledDelete);
	}

}
