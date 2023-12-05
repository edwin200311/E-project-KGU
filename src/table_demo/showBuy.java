package table_demo;

import javax.swing.*;

import facade.UIData;
import store.Flower;
import store.FlowerMgr;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class showBuy extends JPanel {
    public ImageIcon imageIcon;
    public JLabel[] labels = new JLabel[9]; // 배열로 변경
    public Integer Zero = 0;
    Integer WIDTH = 170;
    Integer HEIGHT = 170;
    LocalDateTime curDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String Rresult;
    String filePath = "order.txt";
    String ufilePath = "users.txt";

    public showBuy(String[] imagePaths) {
        this.setLayout(new BorderLayout());

        JPanel west = new JPanel(new GridLayout(3, 3, 0, 0));
        JButton buyItem = new JButton("구매");
        JButton resetItem = new JButton("초기화");

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(resize(new ImageIcon(imagePaths[i]), WIDTH, HEIGHT));
            west.add(labels[i]);
        }

        this.setPreferredSize(new Dimension(500, 500));
        this.add(west, BorderLayout.CENTER);
        this.add(resetItem, BorderLayout.NORTH);
        this.add(buyItem, BorderLayout.PAGE_END);

        resetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 초기 이미지로 모든 레이블 초기화
                for (int i = 0; i < labels.length; i++) {
                    labels[i].setIcon(resize(new ImageIcon("images/None.png"), WIDTH, HEIGHT));
                    GUIMain.getInstance().od.reset(Zero);
                }
            }
        });
        buyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

                // buyItem 버튼을 눌렀을 때의 동작을 여기에 추가
                String test = GUIMain.getInstance().od.getorderList();
                // ID 입력 다이얼로그 표시
                String[] IDandAddress = showIDAndAddressInputDialog();

                if (IDandAddress != null) {
                    String formattedDate = curDateTime.format(formatter);
                    StringBuilder result = new StringBuilder();
                    result.append(IDandAddress[0]).append(" ").append(formattedDate)
                            .append(" ").append(IDandAddress[1]).append(" ").append(IDandAddress[2]).append(" ").append(test);
                    Rresult = result.toString().trim();
                    System.out.println(Rresult);

                } else {

                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    int lineNumber = countLines(filePath) + 1;
                    writer.write(String.format("%d %s", lineNumber, Rresult));
                    writer.newLine();
                } catch (IOException ex) {
                    System.err.println("오류발생 ");
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(ufilePath, true))) {
                    writer.write(String.format("%s %s 0", IDandAddress[0], IDandAddress[0]));
                    writer.newLine();
                } catch (IOException ex2) {
                    System.out.println("오류 발생");
                }

                for (int i = 0; i < labels.length; i++) {
                    labels[i].setIcon(resize(new ImageIcon("images/None.png"), WIDTH, HEIGHT));
                    GUIMain.getInstance().od.reset(Zero);
                }
            }
        });

    }

    // ID와 주소 입력 다이얼로그 표시하는 메서드 + 전화번호
    private String[] showIDAndAddressInputDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 3));
        JTextField idField = new JTextField(10);
        JTextField addressField = new JTextField(10);
        JTextField phoneField = new JTextField(10);

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("주소:"));
        panel.add(addressField);
        panel.add(new JLabel("연락처:"));
        panel.add(phoneField);

        int result = JOptionPane.showConfirmDialog(null, panel, "주문 정보 입력", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String enteredID = idField.getText();
            String enteredAddress = addressField.getText();
            String enteredPhone = phoneField.getText();

            if (!enteredID.isEmpty() && !enteredAddress.isEmpty() && !enteredPhone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "구매가 완료되었습니다.", "구매 완료!", JOptionPane.YES_OPTION);
                return new String[] { enteredID, enteredAddress, enteredPhone };
            } else {
                JOptionPane.showMessageDialog(null, "정보를 모두 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    public ImageIcon resize(ImageIcon originalIcon, int width, int height) {
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }

    public int countLines(String filePath) throws IOException {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null)
                lines++;
        }
        return lines;
    }
}
