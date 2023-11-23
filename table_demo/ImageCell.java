package table_demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import facade.UIData;

public class ImageCell extends JPanel implements MouseListener {
	ImageIcon imgIcon;
	String name;
	String price;
	UIData item;
	String[] userStrings;
	ImageCell(UIData item) {
		this.item = item;
		setup();
	}
	void setup() {
		setLayout(new BorderLayout());
		userStrings = item.getUiTexts();
		imgIcon = new ImageIcon(userStrings[3]);
		Image scaleImage = imgIcon.getImage().getScaledInstance(150, 140,Image.SCALE_DEFAULT);
		ImageIcon imgResized = new ImageIcon(scaleImage);
		add(new JLabel(imgResized), BorderLayout.CENTER);
		String itemLabelStr = String.format("<html><body><h2>%s %s<br/>가격: %s원", userStrings[0], userStrings[1], userStrings[2]);
		JLabel itemLabel = new JLabel(itemLabelStr);
		itemLabel.setPreferredSize(new Dimension(150, 80));
		itemLabel.setFont(new Font("굴림", Font.PLAIN, 13));
		itemLabel.setForeground(Color.black);
		itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(itemLabel, BorderLayout.PAGE_END);
		addMouseListener(this);
	}
    void showDetail() {
    	DetailDialog dlg = new DetailDialog(userStrings);
    	dlg.setup();
    	dlg.pack();
    	dlg.setVisible(true);
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
       	showDetail();		
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
