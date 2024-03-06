package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import drawing.Line;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogModifyLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;
	private int startCoordinateX;
	private int startCoordinateY;
	private int endCoordinateX;
	private int endCoordinateY;
	private Color lineColor;
	private boolean correct = false;

	public DialogModifyLine(Line line) {
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblStartPointX = new JLabel("Start point X:");
		lblStartPointX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtStartPointX = new JTextField();
		txtStartPointX.setColumns(10);
		txtStartPointX.setText("" + line.getStartPoint().getX());
		
		JLabel lblStartPointY = new JLabel("Start point Y:");
		lblStartPointY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtStartPointY = new JTextField();
		txtStartPointY.setColumns(10);
		txtStartPointY.setText("" + line.getStartPoint().getY());
		
		JLabel lblEndPointX = new JLabel("End point X:");
		lblEndPointX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtEndPointX = new JTextField();
		txtEndPointX.setColumns(10);
		txtEndPointX.setText("" + line.getEndPoint().getX());
		
		JLabel lblEndPointY = new JLabel("End point Y:");
		lblEndPointY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtEndPointY = new JTextField();
		txtEndPointY.setColumns(10);
		txtEndPointY.setText("" + line.getEndPoint().getY());
		
		JLabel lblLineColor = new JLabel("Line color: ");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(line.getLineColor());
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose coloe", btnLineColor.getBackground());
				if(temp != null) {
					btnLineColor.setBackground(temp);
					lineColor = btnLineColor.getBackground();
				}
			}
		});
		
		lineColor = btnLineColor.getBackground();
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblEndPointY)
								.addComponent(lblEndPointX).addComponent(lblStartPointY).addComponent(lblStartPointX)
								.addComponent(lblLineColor))
						.addGap(64)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false).addComponent(txtEndPointY)
								.addComponent(txtEndPointX).addComponent(txtStartPointY).addComponent(txtStartPointX)
								.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap(120, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblStartPointX)
								.addComponent(txtStartPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblStartPointY)
								.addComponent(txtStartPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblEndPointX)
								.addComponent(txtEndPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblEndPointY)
								.addComponent(txtEndPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18).addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLineColor).addComponent(btnLineColor))
						.addContainerGap(19, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						startCoordinateX = Integer.parseInt(txtStartPointX.getText());
						startCoordinateY = Integer.parseInt(txtStartPointY.getText());
						endCoordinateX = Integer.parseInt(txtEndPointX.getText());
						endCoordinateY = Integer.parseInt(txtEndPointY.getText());
						if(startCoordinateX <= 0 || startCoordinateY <= 0 || endCoordinateX <=0 || endCoordinateY <= 0 ) {
							JOptionPane.showMessageDialog(null, "Coordinates of start and end point must be positive");
						}
						else {
							//line.getStartPoint().setX(startCoordinateX);
							//line.getStartPoint().setY(startCoordinateY);
							//line.getEndPoint().setX(endCoordinateX);
							//line.getEndPoint().setY(endCoordinateY);
							//line.setLineColor(btnLineColor.getBackground());
							correct = true;
							dispose();
						}
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter coordinates of start and end point");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public int getStartCoordinateX() {
		return startCoordinateX;
	}


	public void setStartCoordinateX(int startCoordinateX) {
		this.startCoordinateX = startCoordinateX;
	}


	public int getStartCoordinateY() {
		return startCoordinateY;
	}


	public void setStartCoordinateY(int startCoordinateY) {
		this.startCoordinateY = startCoordinateY;
	}


	public int getEndCoordinateX() {
		return endCoordinateX;
	}


	public void setEndCoordinateX(int endCoordinateX) {
		this.endCoordinateX = endCoordinateX;
	}


	public int getEndCoordinateY() {
		return endCoordinateY;
	}


	public void setEndCoordinateY(int endCoordinateY) {
		this.endCoordinateY = endCoordinateY;
	}


	public Color getLineColor() {
		return lineColor;
	}


	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}


	public boolean getCorrect() {
		return correct;
	}


	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
