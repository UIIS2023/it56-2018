package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import drawing.Square;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogModifySquare extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUpperLeftX;
	private JTextField txtUpperLeftY;
	private JTextField txtWidth;
	private int dialogUpperLeftX;
	private int dialogUpperLeftY;
	private int dialogWidth;
	private Color lineColor;
	private Color fillColor;
	private boolean correct = false;

	public DialogModifySquare(Square square) {
		setTitle("Modify square");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);
		JLabel lblUpperLeftX = new JLabel("Upper left X: ");
		lblUpperLeftX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtUpperLeftX = new JTextField();
		txtUpperLeftX.setColumns(10);
		txtUpperLeftX.setText("" + square.getUpperLeft().getX());
		
		JLabel lblUpperLeftY = new JLabel("Upper left Y: ");
		lblUpperLeftY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtUpperLeftY = new JTextField();
		txtUpperLeftY.setColumns(10);
		txtUpperLeftY.setText("" + square.getUpperLeft().getY());
		
		JLabel lblWidth = new JLabel("Width: ");
		lblWidth.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		txtWidth.setText("" + square.getWidth());
		
		JLabel lblLineColor = new JLabel("Line color: ");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(square.getLineColor());
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
		btnFillColor.setBackground(square.getFillColor());
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
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblUpperLeftX)
								.addComponent(lblUpperLeftY).addComponent(lblWidth).addComponent(lblLineColor)
								.addComponent(lblFillColor))
						.addGap(59)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnFillColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(txtWidth).addComponent(txtUpperLeftY).addComponent(txtUpperLeftX)
								.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap(126, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING).addComponent(lblUpperLeftX).addComponent(txtUpperLeftX,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtUpperLeftY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUpperLeftY))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblLineColor)
						.addComponent(btnLineColor))
				.addGap(18).addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(btnFillColor)
						.addComponent(lblFillColor))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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
							dialogUpperLeftX = Integer.parseInt(txtUpperLeftX.getText());
							dialogUpperLeftY = Integer.parseInt(txtUpperLeftY.getText());
							dialogWidth = Integer.parseInt(txtWidth.getText());
							if(dialogUpperLeftX <= 0 || dialogUpperLeftY <= 0 || dialogWidth <= 0) {
								JOptionPane.showMessageDialog(null, "Coordinates x, y and width must be positive");
							} else {
								//square.getUpperLeft().setX(dialogUpperLeftX);
								//square.getUpperLeft().setY(dialogUpperLeftY);
								//square.moveOn(dialogUpperLeftX, dialogUpperLeftY);
								//square.setWidth(dialogWidth);
								//square.setLineColor(btnLineColor.getBackground());
								//square.setFillColor(btnFillColor.getBackground());
								correct = true;
								dispose();
							}
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter coordinates x and y and width ");
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
	
	public int getDialogUpperLeftX() {
		return dialogUpperLeftX;
	}

	public void setDialogUpperLeftX(int dialogUpperLeftX) {
		this.dialogUpperLeftX = dialogUpperLeftX;
	}

	public int getDialogUpperLeftY() {
		return dialogUpperLeftY;
	}

	public void setDialogUpperLeftY(int dialogUpperLeftY) {
		this.dialogUpperLeftY = dialogUpperLeftY;
	}

	public int getDialogWidth() {
		return dialogWidth;
	}

	public void setDialogWidth(int dialogWidth) {
		this.dialogWidth = dialogWidth;
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
