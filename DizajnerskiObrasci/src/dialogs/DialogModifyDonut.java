package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import drawing.Donut;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogModifyDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private int dialogCenterX;
	private int dialogCenterY;
	private int dialogRadius;
	private int dialogInnerRadius;
	private Color lineColor;
	private Color fillColor;
	private boolean correct = false;

	public DialogModifyDonut(Donut donut) {
		setTitle("Modify donut");
		setModal(true);
		setBounds(100, 100, 363, 357);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCenterX = new JLabel("Center X: ");
		lblCenterX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCenterX = new JTextField();
		txtCenterX.setColumns(10);
		txtCenterX.setText("" +donut.getCenter().getX());
		
		JLabel lblCenterY = new JLabel("Center Y: ");
		lblCenterY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtCenterY = new JTextField();
		txtCenterY.setColumns(10);
		txtCenterY.setText("" +donut.getCenter().getY());
		
		JLabel lblRadius = new JLabel("Radius: ");
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		txtRadius.setText("" +donut.getRadius());
		
		JLabel lblInnerRadius = new JLabel("Inner radius: ");
		lblInnerRadius.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		txtInnerRadius.setText("" +donut.getInnerRadius());
		
		JLabel lblLineColor = new JLabel("Line color: ");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(donut.getLineColor());
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
		btnFillColor.setBackground(donut.getFillColor());
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Enter color", btnFillColor.getBackground());
				if(temp != null) {
					btnFillColor.setBackground(temp);
					fillColor = btnFillColor.getBackground();
				}
			}
		});
		
		lineColor = btnLineColor.getBackground();
		fillColor = btnFillColor.getBackground();

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCenterX)
									.addComponent(lblCenterY)
									.addComponent(lblRadius))
								.addGap(46)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(txtCenterY, 0, 0, Short.MAX_VALUE)
									.addComponent(txtCenterX, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
									.addComponent(txtRadius, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblInnerRadius)
									.addComponent(lblLineColor))
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(txtInnerRadius, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
									.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnFillColor, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))))
						.addComponent(lblFillColor))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCenterX))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCenterY)
						.addComponent(txtCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblInnerRadius))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLineColor)
						.addComponent(btnLineColor))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFillColor)
						.addComponent(btnFillColor))
					.addContainerGap(26, Short.MAX_VALUE))
		);
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
							dialogInnerRadius = Integer.parseInt(txtInnerRadius.getText());
							if(dialogCenterX <= 0 || dialogCenterY <= 0 || dialogRadius <= 0 || dialogInnerRadius <= 0 || dialogRadius <= dialogInnerRadius) {
								JOptionPane.showMessageDialog(null, "Coordinates x and y, radius and inner radius must be positive");
							} else {
								//donut.getCenter().setX(dialogCenterX);
								//donut.getCenter().setY(dialogCenterY);
								//donut.moveOn(dialogCenterX, dialogCenterY);
								//donut.setRadius(dialogRadius);
								//donut.setInnerRadius(dialogInnerRadius);
								//donut.setLineColor(btnLineColor.getBackground());
								//donut.setFillColor(btnFillColor.getBackground());
								correct = true;
								dispose();
							}
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter coordinates x and y, radius and inner radius");
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

	public int getDialogInnerRadius() {
		return dialogInnerRadius;
	}

	public void setDialogInnerRadius(int dialogInnerRadius) {
		this.dialogInnerRadius = dialogInnerRadius;
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
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
}
