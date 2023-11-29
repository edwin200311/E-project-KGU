package table_demo;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import store.FlowerMgr;

public class firstDetailDialog extends DetailDialog {
    String[] itemDetails;
    JLabel details[] = new JLabel[18];
    String flowerlist[]= new String[15];
    firstDetailDialog(String[] texts) {
        super(texts);
    }
    @Override
    void setup(){

        setTitle("첫 화면");
        JPanel fpane = new JPanel(new BorderLayout());
        JPanel flpane = new JPanel(new GridLayout(3, 5));
        for(int i=0;i<15;i++){
            flowerlist[i]="1";
        }
    }


}
