package views;

import com.littlepure.Bank;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */

/**
 * @author ป๚ะตสฆ
 *
 */
public class SetPIN extends JPanel {
	private JTextField PINInput;

	/**
	 * Create the panel.
	 */
	public SetPIN(JFrame frame) {
		setLayout(null);
		this.setSize(600, 500);
		JPanel thisPanel = this;
		
		JLabel lblSetYourPin = new JLabel("Set your PIN");
		lblSetYourPin.setBounds(252, 107, 96, 18);
		add(lblSetYourPin);
		
		PINInput = new JTextField();
		PINInput.setBounds(219, 222, 161, 24);
		add(PINInput);
		PINInput.setColumns(10);
		
		JLabel lblmustBeNumbers = new JLabel("(Must be numbers)");
		lblmustBeNumbers.setBounds(232, 138, 136, 18);
		add(lblmustBeNumbers);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int PIN = Integer.parseInt(PINInput.getText());
				Bank.setPIN(PIN);
				JOptionPane.showMessageDialog(null,
						"Register success!");
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Menu(frame));
				frame.validate();
				frame.repaint();
			}
		});
		btnConfirm.setBounds(243, 329, 113, 27);
		add(btnConfirm);

	}

}
