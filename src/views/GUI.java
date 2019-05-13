package views;

import com.littlepure.Bank;
import com.littlepure.CleanSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * 
 */

/**
 * @author ป๚ะตสฆ
 *
 */
public class GUI {

	private JFrame frmBankSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmBankSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		CleanSystem.clear();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBankSystem = new JFrame();
		frmBankSystem.setTitle("Bank System");
		frmBankSystem.setBounds(100, 100, 600, 500);
		frmBankSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBankSystem.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Menu menu = new Menu(frmBankSystem);
		frmBankSystem.getContentPane().add(menu, BorderLayout.CENTER);
	}

}
