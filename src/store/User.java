package store;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	String id;
	String pwd;
	int point;
	int waitPoint;
	int one;
	int two;
	ArrayList<Order> myOrderList = new ArrayList<>();
	
	public void read(Scanner scan) {  
		id = scan.next();
		pwd = scan.next();
		point = scan.nextInt();  
	}
	void addOrder(Order od) {
		myOrderList.add(od);
	    	point += od.point;
	    	waitPoint += od.point;
	}
	public boolean matches(String kwd) {
		if (kwd.length() == 0)
			return true;
		if (id.equals(kwd))
		    return true;
		for (Order od: myOrderList)
			if (od.matches(kwd)) // 주문id에만 매치
				return true;
		return false;
    }
	public void print() {
		System.out.format("[%s] (%d점) ", 
				id, point);
		System.out.println();
		for (Order od: myOrderList) {
			System.out.print("   ");
			od.print(false);
		}
		Manageable m = myOrderList.get(0);
		int n = ((Order)m).point;
//		try {
//		Order od2 = (Order)m.getClass().getDeclaredConstructor().newInstance();
//		} catch(Exception e) {
//			
//		}
	}
}
