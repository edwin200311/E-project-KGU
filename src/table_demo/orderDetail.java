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
public class orderDetail extends JPanel {
    public JTextArea textArea = new JTextArea(6, 20);
    public ImageIcon imageIcon;
    String labelText;
    String defaultText;
    public JLabel imageLabel;
    public Integer index = 0;
    public int imageCount = 0; // 현재 이미지 개수
    private static final int MAX_IMAGES = 9;
    public String inlistName;
    public String inlistNum = "0";
    public String[][] orderlist = new String[9][2];
    public Integer inlistIndex = 0;

    public orderDetail(String labelText, String defaultText, ImageIcon imageIcon) {
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel(labelText);
        JButton addItem = new JButton("추가");
        textArea = new JTextArea(defaultText);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        this.imageIcon = imageIcon;

        imageLabel = new JLabel(imageIcon);
        // label.setIcon(imageIcon);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);

        this.add(label, BorderLayout.EAST);
        this.add(scrollPane, BorderLayout.WEST);
        this.add(addItem, BorderLayout.NORTH);
        this.add(imageLabel, BorderLayout.CENTER);
        textArea.setEditable(false);

        addItem.addActionListener(new ActionListener() {
            ImageIcon imgicon = null;
            Image image = null;
            String icon = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (imageCount < MAX_IMAGES) {
                    String text = GUIMain.getInstance().od.textArea.getText();
                    String extractedName = extractValue(text, "이름: (.*?)\\n");
                    icon = "images/" + extractedName + ".png";
                    imgicon = new ImageIcon(icon);
                    imgicon = resize(imgicon, 100, 100);
                    image = imgicon.getImage();
                    GUIMain.getInstance().sb.labels[index]
                            .setIcon(resize(new ImageIcon("images/" + extractedName + ".png"),
                                    GUIMain.getInstance().sb.WIDTH, GUIMain.getInstance().sb.HEIGHT));

                    boolean found = false;
                    for (int i = 0; i <= inlistIndex; i++) {
                        if (orderlist[i][0] != null && orderlist[i][0].equals(extractedName)) {
                            // 이미 리스트에 존재하는 꽃이면 개수를 증가시킴
                            int count = Integer.parseInt(orderlist[i][1]);
                            orderlist[i][1] = String.valueOf(count + 1);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        // 리스트에 존재하지 않는 꽃이면 새로 추가
                        inlistIndex++;
                        orderlist[inlistIndex][0] = extractedName;
                        orderlist[inlistIndex][1] = "1";
                    }
                    index++;
                    imageCount++;
                } else {
                    // 이미지 개수가 최대치에 도달한 경우 메시지 표시
                    JOptionPane.showMessageDialog(orderDetail.this, "꽃은 최대 9개까지 추가할 수 있습니다.",
                            "Warnings", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public ImageIcon resize(ImageIcon originalIcon, int width, int height) {
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }

    void changeImg(ImageIcon imgIcon) {
        this.imageIcon = imgIcon;
        imageLabel.setIcon(imgIcon);
    }

    public void reset(Integer Zero) {
        this.index = Zero;
        this.imageCount = Zero;
        this.inlistIndex = Zero;
        for (int i = 0; i < this.orderlist.length; i++) {
            // 각 행에 해당하는 배열도 초기화
            this.orderlist[i][0] = null;
            this.orderlist[i][1] = null;
        }
    }

    public String getorderList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < imageCount; i++) {
            if (orderlist[i][0] != null) {
                String flowerName = orderlist[i][0];
                int count = Integer.parseInt(orderlist[i][1]);
                result.append(flowerName).append(" ").append(count).append(" ");

            }
        }
        result.append("0");
        return result.toString().trim();
    }

    public String extractValue(String text, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);

        if (matcher.find()) {
            return matcher.group(1); // 첫 번째 괄호 안의 그룹을 가져옴
        } else {
            return null; // 매칭되는 부분이 없을 경우
        }
    }

}
