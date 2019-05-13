package com.littlepure.views;

import com.littlepure.controller.Bank;
import com.littlepure.models.BankAccount;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */

/**
 * @author littlepure
 *
 */
public class Deposit extends JPanel {
	private JTextField accNoInput;
	private JTextField amountInput;

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
		
		accNoInput = new JTextField();
		accNoInput.setBounds(218, 103, 272, 24);
		add(accNoInput);
		accNoInput.setColumns(10);
		
		amountInput = new JTextField();
		amountInput.setBounds(218, 184, 272, 24);
		add(amountInput);
		amountInput.setColumns(10);
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long accNo = Long.parseLong(accNoInput.getText());
				double amount = Double.parseDouble(amountInput.getText());
				// 先检查这个账户有没有被冻结
				BankAccount account = Bank.findAccountByNo(accNo);
				if(account.getSuspended()) {
					JOptionPane.showMessageDialog(null,
							"Your account has been suspended!");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
					frame.validate();
					frame.repaint();
				}
				else {
					Bank.deposit(amount, true);
					JOptionPane.showMessageDialog(null,
							"Deposit success!");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
					frame.validate();
					frame.repaint();
				}


			}
		});
		btnCash.setBounds(136, 287, 113, 27);
		add(btnCash);

		JButton btnCheque = new JButton("Cheque");
		btnCheque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long accNo = Long.parseLong(accNoInput.getText());
				double amount = Double.parseDouble(amountInput.getText());
				// 先检查是否存在这个账户
				BankAccount account = Bank.findAccountByNo(accNo);
				if(account != null) {
					//如果账户已被停用
					if(account.getSuspended()) {
						JOptionPane.showMessageDialog(null,
								"Your account has been suspended!");
						frame.getContentPane().remove(thisPanel);
						frame.getContentPane().add(new Menu(frame));
						frame.validate();
						frame.repaint();
					}
					else {
						Bank.deposit(amount, false);
						JOptionPane.showMessageDialog(null,
								"Deposit success!");
						frame.getContentPane().remove(thisPanel);
						frame.getContentPane().add(new Menu(frame));
						frame.validate();
						frame.repaint();
					}
				}
				// 否则账户不存在
				else {
					JOptionPane.showMessageDialog(null,
							"The account doesn't exist");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
					frame.validate();
					frame.repaint();
				}
			}
		});
		btnCheque.setBounds(363, 287, 113, 27);
		add(btnCheque);
		
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
