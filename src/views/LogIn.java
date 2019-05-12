package views;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */

/**
 * @author »úÐµÊ¦
 *
 */
public class LogIn extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public LogIn(JFrame frame, JPanel nextPanel) {
		setLayout(null);
		this.setSize(600, 500);
		JPanel thisPanel = this;
		
		JButton button = new JButton("return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Menu(frame));
				frame.validate();
				frame.repaint();
			}
		});
		button.setBounds(0, 0, 115, 27);
		add(button);
		
		JLabel lblAccountNo = new JLabel("Account No");
		lblAccountNo.setBounds(98, 170, 80, 18);
		add(lblAccountNo);
		
		JLabel lblPin = new JLabel("PIN");
		lblPin.setBounds(98, 254, 72, 18);
		add(lblPin);
		
		textField = new JTextField();
		textField.setBounds(223, 167, 267, 24);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(223, 251, 267, 24);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(nextPanel);
				frame.validate();
				frame.repaint();
			}
		});
		btnConfirm.setBounds(243, 370, 113, 27);
		add(btnConfirm);
		
		JLabel lblNewLabel = new JLabel("Please Log In");
		lblNewLabel.setBounds(242, 57, 115, 18);
		add(lblNewLabel);

	}

}
