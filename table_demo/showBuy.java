package table_demo;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class showBuy extends JLabel{
    public ImageIcon imageIcon;
    public JLabel imageLabel;
    public String[] imagePaths = {
        "images/None.png",
        "images/None.png",
        "images/None.png",
        "images/None.png",
        "images/None.png",
        "images/None.png",
        "images/None.png",
        "images/None.png",
        "images/None.png",
    };
    public showBuy() {
        this.setLayout(new BorderLayout());
    
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 0, 0));
        ImageIcon[] imageIcons = new ImageIcon[9];
        JButton buyItem = new JButton("구매");
    
        for (int i = 0; i < 9; i++) {
            imageIcons[i] = new ImageIcon(imagePaths[i]);
            System.out.println("Image path at index " + i + ": " + imagePaths[i]);
            if (imageIcons[i].getImage() == null) {
                System.out.println("Error loading image at index " + i);
            }
            imageIcons[i] = resize(imageIcons[i], 100, 100);
        }
    
        for (int i = 0; i < 9; i++) {
            JLabel label = new JLabel(imageIcons[i]);
            gridPanel.add(label);
        }
    
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(buyItem, BorderLayout.PAGE_END);
        this.revalidate();
        this.repaint();
    }
    public void setImages(String[] imagePaths, String imagePath,Integer index){
        this.imagePaths[index]=imagePath;
    }
    public ImageIcon resize(ImageIcon originalIcon, int width, int height) {
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }
}
    
        

