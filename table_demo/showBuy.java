package table_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



@SuppressWarnings("serial")
public class showBuy extends JPanel {
    public ImageIcon imageIcon;
    public JLabel[] labels = new JLabel[9]; // 배열로 변경
    public Integer Zero = 0;
    Integer WIDTH = 200;
    Integer HEIGHT = 200;
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

                // ID 입력 다이얼로그 표시
                String[] IDandAddress = showIDAndAddressInputDialog();

                // 입력받은 ID를 사용하여 원하는 작업 수행
                if (IDandAddress != null) {
                    // 여기에 입력받은 ID를 사용한 작업을 추가할 수 있습니다.
                    // 예시: 각 이미지의 구매 여부를 확인하고 구매한 경우에만 작업 수행
                    for (int i = 0; i < labels.length; i++) {
                        ImageIcon currentIcon = (ImageIcon) labels[i].getIcon();
                        if (currentIcon.getDescription().equals("구매 가능")) {
                            // 해당 이미지를 구매 완료로 변경
                            labels[i].setIcon(resize(new ImageIcon("images/구매완료.png"), 100, 100));

                            // 여기에 원하는 작업 추가
                            //GUIMain.getInstance().od.updatePurchaseStatus(i);

                            // 예시: 구매한 상품의 ID를 출력
                            System.out.println("ID " + IDandAddress + "로 " + i + "번 상품을 구매했습니다.");
                        }
                    }
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
