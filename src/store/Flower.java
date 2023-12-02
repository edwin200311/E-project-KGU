package store;

import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class Flower implements Manageable, UIData {
    String name; // 이름
    String language; // 꽃말
    String floweringTime; // 개화시기
    String colorCategory; // 색 분류
    String color; // 색상
    String care; // 관리법
    int cost; // 가격
    int count; // 재고

    @Override
    public void read(Scanner scan) {
        name = scan.next();
        language = scan.next();
        floweringTime = scan.next();
        colorCategory = scan.next();
        color = scan.next();
        cost = scan.nextInt();
        count = scan.nextInt();
        care = scan.nextLine();
    }

    @Override
    public void print() {
        System.out.printf("이름: %s | 꽃말: %s | 개화시기: %s | 색상: %s | 관리법: %s | 가격: %d\n",
                this.name, this.language, this.floweringTime, this.color, this.care, this.cost);
    }

    @Override
    public boolean matches(String kwd) {
        if (name.contains(kwd))
            return true;
        if (language.contains(kwd))
            return true;
        if (floweringTime.contains(kwd))
            return true;
        if (colorCategory.contains(kwd))
            return true;
        if(color.contains(kwd))
            return true;
        else
            return false;
    }

    public boolean matches(String[] kwdArr) {
        for (String kwd : kwdArr) {
            if (!matches(kwd))
                return false;
        }
        return true;
    }

    @Override
    public void set(Object[] uitexts) {
        // TODO Auto-generated method stub
    }

    @Override
    public String[] getUiTexts() {
        // TODO Auto-generated method stub
        String[] texts = new String[8];
        texts[0] = name;
        texts[1] = language;
        texts[2] = floweringTime;
        texts[3] = color;
        texts[4] = care;
        texts[5] = "" + cost;
        texts[6] = "" + count;

        return texts;
    }

    public int getcount(){
        return this.count;
    }

    public void minuscount(){
        this.count-=1;
    }

    public String getname() {
        return this.name;
    }

    public int getcost() {
        return this.cost;
    }
}
