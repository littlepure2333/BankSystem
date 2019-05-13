package com.littlepure.views;

import com.littlepure.controller.Bank;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpenAccount extends JPanel {
	private JTextField nameInput;
	private JTextField addressInput;
	private JTextField DOBInput;
	private JLabel lblType;
	private JRadioButton rdbtnCurrentAccount;
	private JRadioButton rdbtnSaverAccount;
	private JRadioButton rdbtnJuniorAccount;
	private JButton btnConfirm;
	private JButton btnReturn;

	/**
	 * Create the panel.
	 */
	public OpenAccount(JFrame frame) {
		setLayout(null);
		this.setSize(600, 500);
		JPanel thisPanel = this;
		
		JLabel lblAccountNo = new JLabel("Name");
		lblAccountNo.setBounds(75, 72, 120, 29);
		add(lblAccountNo);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(75, 122, 108, 29);
		add(lblAddress);
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(75, 172, 108, 29);
		add(lblBirthday);
		
		nameInput = new JTextField();
		nameInput.setBounds(216, 72, 318, 35);
		add(nameInput);
		nameInput.setColumns(10);
		
		addressInput = new JTextField();
		addressInput.setBounds(216, 119, 318, 35);
		add(addressInput);
		addressInput.setColumns(10);
		
		DOBInput = new JTextField();
		DOBInput.setBounds(216, 169, 318, 35);
		add(DOBInput);
		DOBInput.setColumns(10);
		
		lblType = new JLabel("Type");
		lblType.setBounds(75, 259, 108, 29);
		add(lblType);
		
		rdbtnCurrentAccount = new JRadioButton("Current Account");
		rdbtnCurrentAccount.setSelected(true);
		rdbtnCurrentAccount.setBounds(216, 221, 231, 37);
		add(rdbtnCurrentAccount);
		
		rdbtnSaverAccount = new JRadioButton("Saver Account");
		rdbtnSaverAccount.setBounds(216, 255, 231, 37);
		add(rdbtnSaverAccount);
		
		rdbtnJuniorAccount = new JRadioButton("Junior Account");
		rdbtnJuniorAccount.setBounds(216, 292, 231, 37);
		add(rdbtnJuniorAccount);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameInput.getText();
				String address = addressInput.getText();
				String DOB = DOBInput.getText();
				int type;
				if(rdbtnCurrentAccount.isSelected()) {
					type = Bank.CURRENT;
				}
				else if(rdbtnJuniorAccount.isSelected()) {
					type = Bank.JUNIOR;
				}
				else {
					type = Bank.SAVER;
				}
				int result = Bank.register(name, address, DOB, type);
				if(result == Bank.REGISTER_SUCCESS) {
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new SetPIN(frame));
				}
				else if(result == Bank.INCORRECT_FORMAT) {
					JOptionPane.showMessageDialog(null,
							"Incorrect format of date! i.e. 2000-2-3");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
				}
				else if(result == Bank.NOT_JUNIOR) {
					JOptionPane.showMessageDialog(null,
							"You are not under 16. Can't open a junior account");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
				}
				else if(result == Bank.POOR_CREDIT_HISTORY) {
					JOptionPane.showMessageDialog(null,
							"You have a poor credit history. Can't open account");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
				}
				//frame.getContentPane().remove(thisPanel);
				//frame.getContentPane().add(new Menu(frame));
				frame.validate();
				frame.repaint();
			}
		});
		btnConfirm.setBounds(226, 358, 153, 37);
		add(btnConfirm);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnCurrentAccount);
		buttonGroup.add(rdbtnJuniorAccount);
		buttonGroup.add(rdbtnSaverAccount);
		
		btnReturn = new JButton("return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Menu(frame));
				frame.validate();
				frame.repaint();
			}
		});
		btnReturn.setBounds(0, 0, 115, 27);
		add(btnReturn);

	}

}
