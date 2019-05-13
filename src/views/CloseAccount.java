package views;

import com.littlepure.Bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 */

/**
 * @author ��еʦ
 *
 */
public class CloseAccount extends JPanel {

	/**
	 * Create the panel.
	 */
	public CloseAccount(JFrame frame) {
		setLayout(null);
		this.setSize(600, 500);
		JPanel thisPanel = this;
		
		JLabel lblNewLabel = new JLabel("Are you sure to close this account?");
		lblNewLabel.setBounds(160, 166, 280, 18);
		add(lblNewLabel);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Bank.canClose()) {
					Bank.closeAccount();
					JOptionPane.showMessageDialog(null,
							"Close account success!");
				}
				else {
					JOptionPane.showMessageDialog(null,
							"You can't close account until your balance is cleared");
				}
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Menu(frame));
				frame.validate();
				frame.repaint();
			}
		});
		btnYes.setBounds(141, 316, 113, 27);
		add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Menu(frame));
				frame.validate();
				frame.repaint();
			}
		});
		btnNo.setBounds(314, 316, 115, 27);
		add(btnNo);

	}

}
