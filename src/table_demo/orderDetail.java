package table_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import facade.UIData;
import store.FlowerMgr;

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
    UIData item;

    void getitem(UIData item) {
        this.item = item;
    }

    public orderDetail(String labelText, String defaultText, ImageIcon imageIcon) {
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel(labelText);
        JButton addItem = new JButton("추가");
        textArea = new JTextArea(defaultText);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(250, 50));
        this.imageIcon = imageIcon;

        imageLabel = new JLabel(imageIcon);
        // label.setIcon(imageIcon);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.RIGHT);

        // this.add(label, BorderLayout.EAST);
        this.add(scrollPane, BorderLayout.WEST);
        this.add(addItem, BorderLayout.NORTH);
        this.add(imageLabel, BorderLayout.EAST);
        textArea.setEditable(false);

        addItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon imgicon = null;
                Image image = null;
                String icon = null;
                if (imageCount < MAX_IMAGES) {
                    String text = GUIMain.getInstance().od.textArea.getText();
                    String extractedName = extractValue(text, "이름: (.*?)\\n");

                    UIData selectedFlower = findFlowerByName(extractedName);
                    if (selectedFlower != null) {
                        String cost = selectedFlower.getUiTexts()[5];
                        GUIMain.getInstance().sc.updateCost(Integer.valueOf(cost));
                    } else {
                        System.err.println("Flower not found: " + extractedName);
                    }
                    icon = "images/" + extractedName + ".png";
                    imgicon = new ImageIcon(icon);
                    imgicon = resize(imgicon, 100, 100);
                    image = imgicon.getImage();
                    if (GUIMain.getInstance().sb != null && GUIMain.getInstance().sb.labels != null
                            && index < GUIMain.getInstance().sb.labels.length) {
                        GUIMain.getInstance().sb.labels[index]
                                .setIcon(resize(new ImageIcon("images/" + extractedName + ".png"),
                                        GUIMain.getInstance().sb.WIDTH, GUIMain.getInstance().sb.HEIGHT));
                    } else {
                        System.err.println("오류발생!");
                    }
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
        GUIMain.getInstance().sc.resetCost();
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

    private UIData findFlowerByName(String flowerName) {
        List<?> itemlist = FlowerMgr.getInstance().search("");
        for (Object o : itemlist) {
            UIData flower = (UIData) o;
            if (flower.getUiTexts()[0].equals(flowerName)) {
                return flower;
            }
        }
        return null;
    }
}
