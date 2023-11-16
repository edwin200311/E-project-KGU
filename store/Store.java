package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;
import table_demo.GUIMain;

public class Store {
	private static Store store = null;
	private Store() {}
	public static Store getInstance() {
		if (store == null)
			store = new Store();
		return store;
	}
	Scanner scan = new Scanner(System.in);
	public void run() {
		FlowerMgr.getInstance().readAll("flower.txt", new Factory<flower>() {
			public flower create() {
				return new flower();
			}
		});
//		System.out.println("\n================= 판매 상품 리스트 =================");
//		ItemMgr.getInstance().printAll();
		UserMgr.getInstance().readAll("users.txt", new Factory<user>() {
			public user create() {
				return new user();
			}
		});
		OrderMgr.getInstance().readAll("order.txt", new Factory<Order>() {
			public Order create() {
				return new Order();
			}
		});
//		System.out.println("\n================= 전체 주문 리스트 =================");
//		orderMgr.printAll();
//		System.out.println("\n=============== 사용자별 주문 리스트 =================");
//		userMgr.printAll();
	}
	public static void main(String args[]) {
		Store store = new Store();
		store.run();
	    GUIMain.startGUI();
	}
}
