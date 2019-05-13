package com.littlepure.views;

import com.littlepure.controller.Bank;
import com.littlepure.models.SaverAccount;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */

/**
 * @author 机械师
 *
 */
public class Notice extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Notice(JFrame frame) {
		setLayout(null);
		this.setSize(600, 500);
		JPanel thisPanel = this;
		
		JLabel lblNewLabel = new JLabel("When do you want to withdraw?");
		lblNewLabel.setBounds(184, 167, 232, 18);
		add(lblNewLabel);
		
		JLabel lblApplyANotice = new JLabel("Apply a notice");
		lblApplyANotice.setBounds(244, 89, 112, 18);
		add(lblApplyANotice);
		
		textField = new JTextField();
		textField.setBounds(257, 256, 86, 24);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDays = new JLabel("days");
		lblDays.setBounds(360, 259, 40, 18);
		add(lblDays);
		
		JLabel lblAfter = new JLabel("After");
		lblAfter.setBounds(195, 259, 40, 18);
		add(lblAfter);
		
		JLabel lblminimumDays = new JLabel("(Minimum " + Integer.toString(SaverAccount.MINIMUM_PERIOD_OF_NOTICE) + " days)");
		lblminimumDays.setBounds(236, 198, 128, 18);
		add(lblminimumDays);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int noticeDaysInput = Integer.parseInt(textField.getText());
				// 如果符合最小日期
				if(Bank.applyNotice(noticeDaysInput)) {
					JOptionPane.showMessageDialog(null,
							"Apply notice success!");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
				}
				else {
					JOptionPane.showMessageDialog(null,
							"You must apply at least after "
									+ SaverAccount.MINIMUM_PERIOD_OF_NOTICE
									+ " days!");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
				}
				frame.validate();
				frame.repaint();
			}
		});
		btnApply.setBounds(243, 350, 113, 27);
		add(btnApply);
		
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
