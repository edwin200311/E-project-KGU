package table_demo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class orderDetail extends JPanel {
    public JTextArea textArea = new JTextArea(6,20);
    public ImageIcon imageIcon;
    String labelText;
    String defaultText;
    public orderDetail(String labelText, String defaultText, ImageIcon imageIcon){
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel(labelText);
        JButton addItem = new JButton("추가");
        textArea = new JTextArea(defaultText);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500,150));
        this.imageIcon = imageIcon;

        label.setIcon(imageIcon);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);

        this.add(label, BorderLayout.EAST);
        this.add(scrollPane,BorderLayout.WEST);
        this.add(addItem,BorderLayout.PAGE_END);
        textArea.setEditable(false);
    }
}
