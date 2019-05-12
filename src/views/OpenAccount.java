package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpenAccount extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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
		
		JLabel lblAccountNo = new JLabel("Account No");
		lblAccountNo.setBounds(75, 72, 120, 29);
		add(lblAccountNo);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(75, 122, 108, 29);
		add(lblAddress);
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(75, 172, 108, 29);
		add(lblBirthday);
		
		textField = new JTextField();
		textField.setBounds(216, 72, 318, 35);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(216, 119, 318, 35);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(216, 169, 318, 35);
		add(textField_2);
		textField_2.setColumns(10);
		
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
				frame.getContentPane().remove(thisPanel);
				frame.getContentPane().add(new Menu(frame));
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
