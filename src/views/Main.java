package views;

import javax.swing.*;

public class Main {

    private JPanel rootPanel;
    private JPanel aaa;
    private JPanel bbb;
    private JPanel ccc;
    private JPanel ddd;
    private JLabel info;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
