package table_demo;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class showCost extends JPanel {
    private int totalPrice = 0;
    JLabel cost;

    public showCost(String[] imagePaths) {
        setup(imagePaths);
    }

    void setup(String[] imagePaths) {
        Font font = new Font(TOOL_TIP_TEXT_KEY, ALLBITS, 32);
        
        cost = new JLabel("총 비용: " + totalPrice + "원");
        cost.setFont(font);
        add(cost);
    }

    public void updateCost(int flowerPrice) {
        totalPrice += flowerPrice;
        cost.setText("총 비용: " + totalPrice + "원");
    }
    public void resetCost(){
        this.totalPrice=0;
        cost.setText("총 비용: " + totalPrice + "원");
    }
}
