package store;

import java.util.List;

import facade.DataEngineImpl;
import facade.UIData;
import mgr.Factory;
import mgr.Manager;

public class OrderedItemMgr extends DataEngineImpl<OrderedItem> {
	private static OrderedItemMgr engine = null;
	private OrderedItemMgr() {
		setLabels(headers);
	}
	public static OrderedItemMgr getInstance() {
		if (engine == null)
			engine = new OrderedItemMgr();
		return engine;
	}
	private String[] headers = {"주문번호", "사용자", "상품명", "개수", "소계"};
	Order order;
	public void setOrder(int n) {
		order = OrderMgr.getInstance().find(""+n);
	}
	@Override
	public List<OrderedItem> search(String kwd) {
		if (kwd.equals(""))
			kwd = ""+order.orderId;
		return findAll(kwd);
	}
}
