package com.littlepure.views;

import com.littlepure.controller.Bank;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */

/**
 * @author 机械师
 *
 */
public class LogIn extends JPanel {
	private JTextField accNoInput;
	private JTextField PINInput;

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
		
		accNoInput = new JTextField();
		accNoInput.setBounds(223, 167, 267, 24);
		add(accNoInput);
		accNoInput.setColumns(10);
		
		PINInput = new JTextField();
		PINInput.setBounds(223, 251, 267, 24);
		add(PINInput);
		PINInput.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long accNo = Long.parseLong(accNoInput.getText());
				int PIN = Integer.parseInt(PINInput.getText());
				// 如果登陆成功
				if(Bank.logIn(accNo, PIN)) {
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(nextPanel);
				}
				else {
					JOptionPane.showMessageDialog(null,
							"Wrong account or PIN!");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
				}
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
