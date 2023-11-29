package table_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;



@SuppressWarnings("serial")
public class showBuy extends JPanel {
    public ImageIcon imageIcon;
    public JLabel[] labels = new JLabel[9]; // 배열로 변경
    public Integer Zero = 0;
    Integer WIDTH = 200;
    Integer HEIGHT = 200;
    LocalDateTime curDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public showBuy(String[] imagePaths) {
        this.setLayout(new BorderLayout());

        JPanel west = new JPanel(new GridLayout(3, 3, 0, 0));
        JButton buyItem = new JButton("구매");
        JButton resetItem = new JButton("초기화");

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(resize(new ImageIcon(imagePaths[i]), WIDTH,HEIGHT));
            west.add(labels[i]);
        }

        this.add(west, BorderLayout.CENTER);
        this.add(resetItem, BorderLayout.NORTH);
        this.add(buyItem, BorderLayout.PAGE_END);

        resetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 초기 이미지로 모든 레이블 초기화
                for (int i = 0; i < labels.length; i++) {
                    labels[i].setIcon(resize(new ImageIcon("images/None.png"),WIDTH,HEIGHT));
                    GUIMain.getInstance().od.reset(Zero);
                }
            }
        });
        buyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // buyItem 버튼을 눌렀을 때의 동작을 여기에 추가
                String test = GUIMain.getInstance().od.getorderList();
                System.out.println(test);
                // ID 입력 다이얼로그 표시
                String[] IDandAddress = showIDAndAddressInputDialog();

                if(IDandAddress!=null){
                    String formattedDate = curDateTime.format(formatter);
                    StringBuilder result = new StringBuilder();
                    result.append(IDandAddress[0]).append(" ").append(IDandAddress[1]).append(" ").append(formattedDate).append(" ").append(test);
                    String Rresult = result.toString().trim();
                    System.out.println(Rresult);
                }
            }
        });

    }

    // ID와 주소 입력 다이얼로그 표시하는 메서드
    private String[] showIDAndAddressInputDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField idField = new JTextField(10);
        JTextField addressField = new JTextField(10);

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("주소:"));
        panel.add(addressField);

        int result = JOptionPane.showConfirmDialog(null, panel, "ID와 주소 입력", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String enteredID = idField.getText();
            String enteredAddress = addressField.getText();

            if (!enteredID.isEmpty() && !enteredAddress.isEmpty()) {
                return new String[]{enteredID, enteredAddress};
            } else {
                JOptionPane.showMessageDialog(null, "ID와 주소를 모두 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    public ImageIcon resize(ImageIcon originalIcon, int width, int height) {
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }
}
