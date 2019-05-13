package views;

import com.littlepure.Bank;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SuspendAccount extends JPanel {

	/**
	 * Create the panel.
	 */
	public SuspendAccount(JFrame frame) {
		setLayout(null);
		this.setSize(600, 500);
		JPanel thisPanel = this;
		
		JLabel lblNewLabel = new JLabel("Are you sure to suspend this account?");
		lblNewLabel.setBounds(152, 166, 296, 18);
		add(lblNewLabel);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Bank.isSuspended()) {
					JOptionPane.showMessageDialog(null,
							"You have suspended already!");
				}
				else {
					Bank.suspend();
					JOptionPane.showMessageDialog(null,
							"Suspend success!");
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
