package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private int dialogRadius;
	private int dialogInnerRadius;
	private boolean correct = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDonut dialog = new DialogDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDonut() {
		setBounds(100, 100, 322, 269);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblRadius = new JLabel("Radius: ");
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		JLabel lblInnerRadius = new JLabel("Inner radius: ");
		lblInnerRadius.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRadius)
						.addComponent(lblInnerRadius))
					.addGap(39)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtInnerRadius, 0, 0, Short.MAX_VALUE)
						.addComponent(txtRadius, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInnerRadius)
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(91, Short.MAX_VALUE))
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
							dialogRadius = Integer.parseInt(txtRadius.getText());
							dialogInnerRadius = Integer.parseInt(txtInnerRadius.getText());
							if(dialogRadius <= 0 || dialogInnerRadius <= 0 || dialogRadius < dialogInnerRadius || dialogRadius == dialogInnerRadius) {
								JOptionPane.showMessageDialog(null, "Radius and inner radius must be positive and inner radius must be less than radius");
							} else {
								correct = true;
								dispose();
							}
							
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter radius and inner radius");
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
	
	public boolean getCorrect() {
		return this.correct;
	}
	
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
}
