package store;

import java.util.Scanner;

import mgr.Factory;
import table_demo.GUIMain;

public class Store {
	private static Store store = null;
	public static Store getInstance() {
		if (store == null)
			store = new Store();
		return store;
	}
	Scanner scan = new Scanner(System.in);
	
	public void run() {
		FlowerMgr.getInstance().readAll("flower.txt", new Factory<Flower>() {
			public Flower create() {
				return new Flower();
			}
		});
		UserMgr.getInstance().readAll("users.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
		OrderMgr.getInstance().readAll("order.txt", new Factory<Order>() {
			public Order create() {
				return new Order();
			}
		});
	}
	
	public static void main(String args[]) {
		Store store = Store.getInstance();
		store.run();
		GUIMain.startGUI();
	}
}
