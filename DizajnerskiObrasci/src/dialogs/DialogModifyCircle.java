package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import drawing.Circle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogModifyCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JTextField txtRadius;
	private int dialogCenterX;
	private int dialogCenterY;
	private int dialogRadius;
	private Color lineColor;
	private Color fillColor;
	private boolean correct = false;

	public DialogModifyCircle(Circle circle) {
		setTitle("Modify circle");
		setModal(true);
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCenterX = new JLabel("Center X: ");
		lblCenterX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCenterX = new JTextField();
		txtCenterX.setColumns(10);
		txtCenterX.setText("" + circle.getCenter().getX());
		
		JLabel lblCenterY = new JLabel("Center Y: ");
		lblCenterY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCenterY = new JTextField();
		txtCenterY.setColumns(10);
		txtCenterY.setText("" + circle.getCenter().getY());
		
		JLabel lblRadius = new JLabel("Radius: ");
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		txtRadius.setText("" + circle.getRadius());
		
		JLabel lblLineColor = new JLabel("Line color: ");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(circle.getLineColor());
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose color", btnLineColor.getBackground());
				if(temp != null) {
					btnLineColor.setBackground(temp);
					lineColor = btnLineColor.getBackground();
				}
			}
			});
		
		JLabel lblFillColor = new JLabel("Fill color: ");
		lblFillColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		JButton btnFillColor = new JButton("Modify color");
		btnFillColor.setBackground(circle.getFillColor());
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   Color temp = JColorChooser.showDialog(null, "Choose color", btnFillColor.getBackground());
				   if(temp != null) {
					   btnFillColor.setBackground(temp);
					   fillColor = btnFillColor.getBackground();
				   }
				}
		});
		
		lineColor = btnLineColor.getBackground();
		fillColor = btnFillColor.getBackground();
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblCenterX)
								.addComponent(lblCenterY).addComponent(lblRadius).addComponent(lblLineColor)
								.addComponent(lblFillColor))
						.addGap(41)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnFillColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(txtRadius).addComponent(txtCenterY).addComponent(txtCenterX)
								.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap(163, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(27)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterX))
				.addGap(29)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblCenterY).addComponent(
						txtCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRadius))
				.addGap(32)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblLineColor)
						.addComponent(btnLineColor))
				.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE).addGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING).addComponent(lblFillColor).addComponent(btnFillColor))
				.addContainerGap()));
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
							dialogCenterX = Integer.parseInt(txtCenterX.getText());
							dialogCenterY = Integer.parseInt(txtCenterY.getText());
							dialogRadius = Integer.parseInt(txtRadius.getText());
							if(dialogCenterX <= 0 || dialogCenterY <= 0 || dialogRadius <= 0) {
								JOptionPane.showMessageDialog(null, "Coordinates of center and radius must be positive");
							} else {
								//circle.getCenter().setX(dialogCenterX);
								//circle.getCenter().setY(dialogCenterY);
								
								//circle.moveOn(dialogCenterX, dialogCenterY);
								//circle.setRadius(dialogRadius);;
								//circle.setLineColor(btnLineColor.getBackground());
								//circle.setFillColor(btnFillColor.getBackground());
								correct = true;
								dispose();
							}
							
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter coordinates of center and radius");
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
	
	public int getDialogCenterX() {
		return dialogCenterX;
	}

	public void setDialogCenterX(int dialogCenterX) {
		this.dialogCenterX = dialogCenterX;
	}

	public int getDialogCenterY() {
		return dialogCenterY;
	}

	public void setDialogCenterY(int dialogCenterY) {
		this.dialogCenterY = dialogCenterY;
	}

	public int getDialogRadius() {
		return dialogRadius;
	}

	public void setDialogRadius(int dialogRadius) {
		this.dialogRadius = dialogRadius;
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
	
	public boolean getCorrect() {
		return this.correct;
	}
	
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
}
