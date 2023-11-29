package store;

import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class OrderedItem implements Manageable, UIData {
	Order order;
	flower item;
	int howMany;
	OrderedItem(Order order, flower item, Scanner scan) {
	    howMany = scan.nextInt();
		this.item = item;
		this.order = order;
	}
	int subTotal() {
		return item.cost * howMany;
	}
	public void print() {
		System.out.format("[%s] %d원 x %d개 = %d원\n", 
				item.name, item.cost, howMany, 
				subTotal());
	}
	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return kwd.equals(order.orderId+"");
	}
	@Override
	public void set(Object[] uitexts) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String[] getUiTexts() {  // "주문번호", "사용자", "상품명", "개수", "소계"
		// TODO Auto-generated method stub
		String[] texts = new String[6];
		texts[0] = ""+order.orderId;
		texts[1] = order.user.id;
		texts[2] = item.name;
		texts[3] = ""+howMany;
		texts[5] = ""+subTotal();
		texts[4] = String.format("%d",item.cost);
		return texts;
	}
}
