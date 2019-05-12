package views;

import views.Menu;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.JEditorPane;

/**
 * 
 */

/**
 * @author »úÐµÊ¦
 *
 */
public class Withdraw extends JPanel {
	private JTextField textField;

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
		
		JLabel label = new JLabel("500");
		label.setBounds(295, 135, 115, 18);
		add(label);
		
		JLabel lblYouWantTo = new JLabel("Withdraw amount");
		lblYouWantTo.setBounds(161, 195, 120, 18);
		add(lblYouWantTo);
		
		textField = new JTextField();
		textField.setBounds(295, 192, 115, 24);
		add(textField);
		textField.setColumns(10);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Menu(frame));
				frame.validate();
				frame.repaint();
			}
		});
		btnWithdraw.setBounds(240, 322, 113, 27);
		add(btnWithdraw);

	}
}
