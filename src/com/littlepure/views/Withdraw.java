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
public class Withdraw extends JPanel {
	private JTextField amountInput;

	/**
	 * Create the panel.
	 */
	public Withdraw(JFrame frame) {
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
		
		JLabel lblYourBalanceIs = new JLabel("Your balance is");
		lblYourBalanceIs.setBounds(161, 135, 120, 18);
		add(lblYourBalanceIs);


		JLabel label = new JLabel();
		label.setBounds(295, 135, 115, 18);
		add(label);
		
		JLabel lblYouWantTo = new JLabel("Withdraw amount");
		lblYouWantTo.setBounds(161, 195, 120, 18);
		add(lblYouWantTo);
		
		amountInput = new JTextField();
		amountInput.setBounds(295, 192, 115, 24);
		add(amountInput);
		amountInput.setColumns(10);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 如果被停用了
				if(Bank.isSuspended()) {
					JOptionPane.showMessageDialog(null,
							"Your account has been suspended!");
					frame.getContentPane().remove(thisPanel);
					frame.getContentPane().add(new Menu(frame));
				}
				else {
					double amount = Double.parseDouble(amountInput.getText());
					int result = Bank.withdraw(amount);
					if(result == SaverAccount.WITHDRAW_SUCCESS) {
						JOptionPane.showMessageDialog(null,
								"Withdraw success!");
						frame.getContentPane().remove(thisPanel);
						frame.getContentPane().add(new Menu(frame));
					}
					else if(result == SaverAccount.EXCEED_OVERDRAFT_LIMIT) {
						JOptionPane.showMessageDialog(null,
								"Can't withdraw due to exceeding overdraft limit!");
						frame.getContentPane().remove(thisPanel);
						frame.getContentPane().add(new Menu(frame));
					}
					// 如果还没到noticeday
					else if(result == SaverAccount.WITHDRAWAL_IS_NOT_ALLOWED) {
						JOptionPane.showMessageDialog(null,
								"Withdraw is not allowed until " + Bank.getNoticeDate() + "!");
						frame.getContentPane().remove(thisPanel);
						frame.getContentPane().add(new Menu(frame));
					}
					// 否则还没申请notice，让用户申请
					else {
						JOptionPane.showMessageDialog(null,
								"You should apply a notice first because of saver account");
						frame.getContentPane().remove(thisPanel);
						frame.getContentPane().add(new Notice(frame));
					}
				}
				frame.validate();
				frame.repaint();
			}
		});
		btnWithdraw.setBounds(240, 322, 113, 27);
		add(btnWithdraw);

		JButton btnNewButton = new JButton("Check balance");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double balance = Bank.getBalance();
				label.setText(Double.toString(balance));
			}
		});
		btnNewButton.setBounds(409, 131, 137, 27);
		add(btnNewButton);

	}
}
