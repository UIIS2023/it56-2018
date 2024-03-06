package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import drawing.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogModifyRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUpperLeftX;
	private JTextField txtUpperLeftY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private int dialogUpperLeftX;
	private int dialogUpperLeftY;
	private int dialogHeight;
	private int dialogWidth;
	private Color lineColor;
	private Color fillColor;
	private boolean correct = false;
	
	public DialogModifyRectangle(Rectangle rectangle) {
		setTitle("Modify rectangle");
		setModal(true);
		setBounds(100, 100, 450, 332);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblUpperLeftX = new JLabel("Upper left X:");
		lblUpperLeftX.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtUpperLeftX = new JTextField();
		txtUpperLeftX.setColumns(10);
		txtUpperLeftX.setText("" + rectangle.getUpperLeft().getX());
		
		JLabel lblUpperLeftY = new JLabel("Upper left Y:");
		lblUpperLeftY.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtUpperLeftY = new JTextField();
		txtUpperLeftY.setColumns(10);
		txtUpperLeftY.setText("" + rectangle.getUpperLeft().getY());
		
		JLabel lblHeight = new JLabel("Height: ");
		lblHeight.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		txtHeight.setText("" + rectangle.getHeight());
		
		JLabel lblWidth = new JLabel("Width: ");
		lblWidth.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtWidth = new JTextField();
		txtWidth.setColumns(10);
		txtWidth.setText("" + rectangle.getWidth());
		
		JLabel lblLineColor = new JLabel("Line color: ");
		lblLineColor.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLineColor = new JButton("Modify color");
		btnLineColor.setBackground(rectangle.getLineColor());
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
		lblFillColor.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JButton btnFillColor = new JButton("Modify color");
		btnFillColor.setBackground(rectangle.getFillColor());
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
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblFillColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblLineColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblWidth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblHeight, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUpperLeftX, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblUpperLeftY, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(78)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnFillColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtUpperLeftY)
							.addComponent(txtUpperLeftX)
							.addComponent(txtWidth)
							.addComponent(btnLineColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUpperLeftX)
						.addComponent(txtUpperLeftX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtUpperLeftY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUpperLeftY))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblHeight))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLineColor)
						.addComponent(btnLineColor))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFillColor)
						.addComponent(btnFillColor))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
							dialogUpperLeftX = Integer.parseInt(txtUpperLeftX.getText());
							dialogUpperLeftY = Integer.parseInt(txtUpperLeftY.getText());
							dialogWidth = Integer.parseInt(txtWidth.getText());
							dialogHeight = Integer.parseInt(txtHeight.getText());
							if(dialogUpperLeftX <= 0 || dialogUpperLeftY <= 0 || dialogWidth <= 0 || dialogHeight <= 0) {
								JOptionPane.showMessageDialog(null, "Cordinates x and y, width and height must be positive");
							} else {
								//rectangle.getUpperLeft().setX(dialogUpperLeftX);
								//rectangle.getUpperLeft().setY(dialogUpperLeftY);
								//rectangle.moveOn(dialogUpperLeftX, dialogUpperLeftY);
								//rectangle.setWidth(dialogWidth);
								//rectangle.setHeight(dialogHeight);
								//rectangle.setLineColor(btnLineColor.getBackground());
								//rectangle.setFillColor(btnFillColor.getBackground());
								correct = true;
								dispose();
							}
							
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter coordinates x and y, width and height");
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

	public int getDialogHeight() {
		return dialogHeight;
	}

	public void setDialogHeight(int dialogHeight) {
		this.dialogHeight = dialogHeight;
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
