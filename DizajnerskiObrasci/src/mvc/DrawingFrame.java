package mvc;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import observer.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.List;

public class DrawingFrame extends JFrame implements Observer{

	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	private JToggleButton tglbtnPoint, tglbtnLine, tglbtnSquare, tglbtnRectangle, tglbtnCircle, tglbtnDonut, tglbtnHexagon, tglbtnSelect;
	private JButton btnLineColor, btnFillColor, btnModify, btnDelete, btnUndo, btnRedo, btnFront, btnBack, btnBringFront, btnBringBack, btnSave, btnLoad;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel contentPane;
	
	private Color lineColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private JPanel loggerPanel;
	private JList loggerList;
	private DefaultListModel<String> defaultLoggerList = new DefaultListModel<String>();
	private JButton btnReadLine;
	
	public DrawingFrame() {
		setTitle("Kristina Nikolic IT56-2018");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tglbtnPoint = new JToggleButton("Point");
		tglbtnLine = new JToggleButton("Line");
		tglbtnSquare = new JToggleButton("Square");
		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnCircle = new JToggleButton("Circle");
		tglbtnDonut = new JToggleButton("Donut");
		tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnSelect = new JToggleButton("Select");
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});
		btnModify.setEnabled(false);
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteShape();
			}
		});
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnUndo.setEnabled(false);
		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		btnRedo.setEnabled(false);
		btnLineColor = new JButton("Line color");
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chooseLineColor();
			}
		});
		btnFillColor = new JButton("Fill color");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chooseFillcolor();
			}
		});
		btnFront = new JButton("Front");
		btnFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.sendToFront();
			}
		});
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.sendToBack();
			}
		});
		btnBringFront = new JButton("Bring front");
		btnBringFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		btnBringBack = new JButton("Bring back");
		btnBringBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
	    JFileChooser fileChooser = new JFileChooser();
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int approver = fileChooser.showSaveDialog(view);
				if(approver == JFileChooser.APPROVE_OPTION) {
					controller.save(fileChooser.getSelectedFile().getPath());
				}
			}
		});
	    btnLoad = new JButton("Load");
	    btnLoad.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int approver = fileChooser.showOpenDialog(view);
	    		if(approver == JFileChooser.APPROVE_OPTION) {
	    			controller.load(fileChooser.getSelectedFile().getPath());
	    		}
	    	}
	    });
	    
	    btnLineColor.setBackground(lineColor);
	    btnFillColor.setBackground(fillColor);
	    
	    view.setBackground(Color.WHITE);
	    
	    view.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if(tglbtnPoint.isSelected()) {
	    			controller.drawPoint(e.getX(), e.getY());
	    			
	    		} else if(tglbtnLine.isSelected()) {
	    			controller.drawLine(e.getX(), e.getY());
	    			
	    		} else if(tglbtnSquare.isSelected()) {
	    			controller.drawSquare(e.getX(), e.getY());
	    			
	    		} else if(tglbtnRectangle.isSelected()) {
	    			controller.drawRectangle(e.getX(), e.getY());
	    			
	    		} else if(tglbtnCircle.isSelected()) {
	    			controller.drawCircle(e.getX(), e.getY());
	    			
	    		} else if(tglbtnDonut.isSelected()) {
	    			controller.drawDonut(e.getX(), e.getY());
	    			
	    		} else if(tglbtnHexagon.isSelected()) {
	    			controller.drawHexagon(e.getX(), e.getY());
	    			
	    		} else if(tglbtnSelect.isSelected()) {
	    			controller.selectedShape(e.getX(), e.getY(), e.getModifiers());
	    		}
	    	}
	    });
	    
	    
	    buttonGroup.add(tglbtnCircle);
	    buttonGroup.add(tglbtnPoint);
	    buttonGroup.add(tglbtnLine);
	    buttonGroup.add(tglbtnSquare);
	    buttonGroup.add(tglbtnRectangle);
	    buttonGroup.add(tglbtnDonut);
	    buttonGroup.add(tglbtnHexagon);
	    buttonGroup.add(tglbtnSelect);
		
		loggerPanel = new JPanel();
		
		btnReadLine = new JButton("Read Line");
		btnReadLine.setEnabled(false);
		btnReadLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.executeLoggerLine();
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnSquare, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnHexagon, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(tglbtnSelect, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUndo, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRedo, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFillColor, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFront, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBringFront, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(loggerPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
								.addComponent(view, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnBringBack, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSave, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
								.addComponent(btnLoad, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
							.addGap(11))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnReadLine, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addComponent(view, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(48)
									.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnBringBack, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBringFront, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnFront, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnLineColor, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(tglbtnSquare, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
								.addComponent(btnLoad, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tglbtnDonut, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tglbtnHexagon, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
								.addComponent(btnReadLine, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
							.addGap(8))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(loggerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_loggerPanel = new GroupLayout(loggerPanel);
		gl_loggerPanel.setHorizontalGroup(
			gl_loggerPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
		);
		gl_loggerPanel.setVerticalGroup(
			gl_loggerPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
		);
		
		loggerList = new JList();
		scrollPane.setViewportView(loggerList);
		loggerPanel.setLayout(gl_loggerPanel);
		contentPane.setLayout(gl_contentPane);
		loggerList.setModel(defaultLoggerList);
	}
	

	@Override
	public void update(boolean enabledModify, boolean enabledDelete) {
		btnModify.setEnabled(enabledModify);
		btnDelete.setEnabled(enabledDelete);
	} 
	
	public DefaultListModel<String> getDefaultLoggerList() {
		return defaultLoggerList;
	}
	
	public DrawingView getView() {
		return view;
	}
	
	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public JButton getBtnLineColor() {
		return btnLineColor;
	}

	public void setBtnLineColor(JButton btnLineColor) {
		this.btnLineColor = btnLineColor;
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public void setBtnFillColor(JButton btnFillColor) {
		this.btnFillColor = btnFillColor;
	}

	public JButton getBtnReadLine() {
		return btnReadLine;
	}

	public void setBtnReadLine(JButton btnReadLine) {
		this.btnReadLine = btnReadLine;
	}
}
