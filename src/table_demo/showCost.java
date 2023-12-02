package table_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class showCost extends JPanel {
    public JTextField textField = new JTextField(7);

    public showCost(String defaultText){
        this.setLayout(new BorderLayout());

        textField = new JTextField(defaultText);
        this.add(textField,BorderLayout.CENTER);
    }
}
