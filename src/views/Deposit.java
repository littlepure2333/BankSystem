package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */

/**
 * @author ป๚ะตสฆ
 *
 */
public class Deposit extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Deposit(JFrame frame) {
		setLayout(null);
		this.setSize(600, 500);
		JPanel thisPanel = this;
		
		JLabel lblNewLabel = new JLabel("Account No");
		lblNewLabel.setBounds(115, 106, 80, 18);
		add(lblNewLabel);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(115, 187, 72, 18);
		add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(218, 103, 272, 24);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(218, 184, 272, 24);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(243, 287, 113, 27);
		add(btnDeposit);
		
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

	}
}
