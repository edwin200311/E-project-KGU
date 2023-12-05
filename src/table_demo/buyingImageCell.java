package table_demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import facade.UIData;

public class buyingImageCell extends JPanel implements MouseListener {

	ImageIcon imgIcon;
	String name;
	String price;
	UIData item;
	String[] userStrings;

	buyingImageCell(UIData item) {
		this.item = item;
		setup();
	}

	void setup() {
		setLayout(new BorderLayout());
		userStrings = item.getUiTexts();
		imgIcon = new ImageIcon("images/" + userStrings[0] + ".png");
		Image scaleImage = imgIcon.getImage().getScaledInstance(100,110, Image.SCALE_DEFAULT);
		ImageIcon imgResized = new ImageIcon(scaleImage);
		add(new JLabel(imgResized), BorderLayout.CENTER);
		String itemLabelStr = String.format("<html><body><h3>%s <br/>가격: %s원", userStrings[0], userStrings[5]);
		JLabel itemLabel = new JLabel(itemLabelStr);
		itemLabel.setPreferredSize(new Dimension(150, 80));
		itemLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		itemLabel.setForeground(Color.black);
		itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(itemLabel, BorderLayout.PAGE_END);
		addMouseListener(this);
	}

	void addImage() {
		String name = item.getUiTexts()[0];
		String language = item.getUiTexts()[1];
		String flowering = item.getUiTexts()[2];
		String color = item.getUiTexts()[3];
		String cost = item.getUiTexts()[5];
		String care = item.getUiTexts()[4];
		String icon = null;
		ImageIcon imgicon = null;
		Image image = null;
		Integer allCost=0;

		Font font = new Font("맑은 고딕", Font.PLAIN, 14); // 원하는 폰트, 스타일, 크기 설정
				GUIMain.getInstance().od.textArea.setFont(font);
				GUIMain.getInstance().od.textArea.setText("이름: " + name + "\n\n" + "꽃말: " + language + "\n\n" + "개화시기: "
						+ flowering + "\n\n" + "색상: " + color + "\n\n" + "관리방법: " + care + "\n\n" + "가격: " + cost+"원");
				icon = "images/" + name + ".png";
				imgicon = new ImageIcon(icon);
				image = imgicon.getImage();
				GUIMain.getInstance().od.changeImg(imgicon);
				allCost += Integer.valueOf(cost);
				
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		addImage();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
