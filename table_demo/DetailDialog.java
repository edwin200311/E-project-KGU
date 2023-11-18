package table_demo;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class DetailDialog extends javax.swing.JDialog {
	String[] itemDetails;
	JLabel details[] = new JLabel[8];
	DetailDialog(String[] texts) {
		super(GUIMain.mainFrame);
		itemDetails = texts;
	}
	void setup() {
		setTitle("상품상세보기");
		JPanel pane = new JPanel(new BorderLayout());
		JPanel lpane = new JPanel(new GridLayout(6, 1));
		details[0] = new JLabel("이름: " + itemDetails[0]);
		details[1] = new JLabel("꽃말: " + itemDetails[1]);
		details[2] = new JLabel("피는시기: " + itemDetails[2]);
		details[3] = new JLabel("색상: " + itemDetails[3]);
		details[4] = new JLabel("관리방법: " + itemDetails[4]);
		details[5] = new JLabel("가격: " + itemDetails[5]);
        
		String flwclassify = ("images/"+itemDetails[0]+".png");
		ImageIcon flowertestIcon = new ImageIcon(flwclassify);
		JLabel photo = new JLabel(flowertestIcon);
		photo.setOpaque(true);  // JLabel은 기본이 배경 투명
		photo.setPreferredSize(new Dimension(270, 270));
		photo.setBackground(Color.YELLOW);
		setTitle(itemDetails[0]);

		lpane.add(details[0]);
		lpane.add(details[1]);
		lpane.add(details[2]);
		lpane.add(details[3]);
		lpane.add(details[4]);
		lpane.add(details[5]);
		pane.add(lpane, BorderLayout.CENTER);
		pane.add(photo, BorderLayout.LINE_END);
		this.setMinimumSize(new Dimension(400, 150));  // 대화상자 크기 설정
		setContentPane(pane);
	}
}
