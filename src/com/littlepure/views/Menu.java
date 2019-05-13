package com.littlepure.views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */

/**
 * @author »úÐµÊ¦
 *
 */
public class Menu extends JPanel {

	/**
	 * Create the panel.
	 */
	public Menu(JFrame frame) {
		this.setSize(600, 500);
		setLayout(new BorderLayout(0, 0));
		JPanel thisPanel = this;
		
		JLabel label = new JLabel("Welcom to Bank System");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 28));
		add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(6, 1, 10, 10));
		
		JButton button = new JButton("Open Account");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new OpenAccount(frame));
				frame.validate();
				frame.repaint();
			}
		});
		panel.add(button);
		
		JButton button_1 = new JButton("Deposit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Deposit(frame));
				frame.validate();
				frame.repaint();
			}
		});
		panel.add(button_1);
		
		JButton button_2 = new JButton("Withdraw");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new LogIn(frame, new Withdraw(frame)));
				frame.validate();
				frame.repaint();
			}
		});
		panel.add(button_2);
		
		JButton button_3 = new JButton("Suspend Account");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new LogIn(frame, new SuspendAccount(frame)));
				frame.validate();
				frame.repaint();
			}
		});
		panel.add(button_3);
		
		JButton button_4 = new JButton("Reinstate Account");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new LogIn(frame, new ReinstateAccount(frame)));
				frame.validate();
				frame.repaint();
			}
		});
		panel.add(button_4);
		
		JButton button_5 = new JButton("Close Account");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new LogIn(frame, new CloseAccount(frame)));
				frame.validate();
				frame.repaint();
			}
		});
		panel.add(button_5);

	}

}
