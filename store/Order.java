package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class Order implements Manageable, UIData {
	int orderId;
	user user;
	String date;
	boolean payed;
	int point;
	List<OrderedItem> orderedItemList = new ArrayList<>();
	public void read(Scanner scan) {
		// 3 park 20201010 O F3124 3 F3223 1 0
		orderId = scan.nextInt();
		String userId = scan.next();
		date = scan.next();
		//payed = (scan.next().charAt(0) == 'O');
	    flower item = null;
	    String code = null;
	    while (true) {
	    	code = scan.next();
	    	if (code.contentEquals("0"))
	    		break;
			item = (flower)FlowerMgr.getInstance().find(code);
		    if (item == null) {
		    	System.out.println("해당 코드 상품 없음: " + code);
		    	scan.nextInt();
		    	continue;
		    }
		    orderedItemList.add(new OrderedItem(this, item, scan));
	    }
	    int sum = 0;
    	for (OrderedItem or: orderedItemList) {
	   		sum += or.subTotal();
		    OrderedItemMgr.getInstance().addElement(or);
    	}
    	point += sum / 1000;
    	user = UserMgr.getInstance().find("" + userId);
	    if (user == null) {
	    	System.out.println("사용자 아이디 없음: " + userId);
	    	return;
	    }
	    user.addOrder(this);
	}
	public boolean matches(String kwd) {
		if (kwd.length() == 0)
			return true;
		if (("" + orderId).equals(kwd))
		    return true;
		if (date.contentEquals(kwd))
		    return true;
		for (OrderedItem od: orderedItemList)
			if (od.item.matches(kwd))
				return true;
		if (user.id.contentEquals(kwd))
			return true;
		return false;
    }
	public boolean matches(String[] kwdArr) {
		for (String kwd: kwdArr) {
			if (!matches(kwd))
				return false;
		}
		return true;
	}
	public void print() {
		print(false);
	}
	void print(boolean bDetail) { // Order
		System.out.format("[주문아이디:%2d] 사용자: %s - 포인트: %d점 ", 
				orderId, user.id, point);
		if (!payed)
			System.out.printf(" (결재대기)");
		System.out.println();
		for (OrderedItem od: orderedItemList) {
			System.out.print("\t");
			od.print();
		}
	}

	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub
	}
	@Override
	public String[] getUiTexts() {
		// TODO Auto-generated method stub
		String[] texts = new String[5];
		texts[0] = ""+orderId;
		texts[1] = user.id;
		texts[2] = date;
		texts[3] = "O";
		if (!payed) texts[3] = "X";
		texts[4] = ""+point;
		return texts;
	}
}
