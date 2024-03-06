package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import drawing.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogModifyPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private int dialogX;
	private int dialogY;
	private Color lineColor;
	private boolean correct = false;

	public DialogModifyPoint(Point point) {
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCoordinateX = new JLabel("Coordinate X:");
		lblCoordinateX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCoordinateX = new JTextField();
		txtCoordinateX.setColumns(10);
		txtCoordinateX.setText("" + point.getX());
		
		JLabel lblCoordinateY = new JLabel("Coordinate Y:");
		lblCoordinateY.setFont(new Font("Dialog", Font.BOLD, 16));
		
		txtCoordinateY = new JTextField();
		txtCoordinateY.setColumns(10);
		txtCoordinateY.setText("" + point.getY());
		
		JLabel lblLineColor = new JLabel("Line color:");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(point.getLineColor());
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose color", btnLineColor.getBackground());
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
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblCoordinateX)
								.addComponent(lblCoordinateY).addComponent(lblLineColor))
						.addGap(41)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(btnLineColor)
								.addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(141, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(41)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtCoordinateX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCoordinateX))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblCoordinateY)
								.addComponent(txtCoordinateY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18).addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLineColor).addComponent(btnLineColor))
						.addContainerGap(71, Short.MAX_VALUE)));
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
							dialogX = Integer.parseInt(txtCoordinateX.getText());
							dialogY = Integer.parseInt(txtCoordinateY.getText());
							if(dialogX <=0 || dialogY<= 0) {
								JOptionPane.showMessageDialog(null, "Coordinates x and y must be positive");
							} else {
								//point.setX(dialogX);
								//point.setY(dialogY);
								//point.moveOn(dialogX, dialogY);
								//point.setLineColor(btnLineColor.getBackground());
								correct = true;
								dispose();
							}
							
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter coordinates");
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
	
	public int getDialogX() {
		return dialogX;
	}

	public void setDialogX(int dialogX) {
		this.dialogX = dialogX;
	}

	public int getDialogY() {
		return dialogY;
	}

	public void setDialogY(int dialogY) {
		this.dialogY = dialogY;
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
