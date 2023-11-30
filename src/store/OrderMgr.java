package store;

import facade.DataEngineImpl;

public class OrderMgr extends DataEngineImpl<Order> {
	private static OrderMgr engine = null;

	private OrderMgr() {
		setLabels(headers);
	}

	public static OrderMgr getInstance() {
		if (engine == null)
			engine = new OrderMgr();
		return engine;
	}

	private String[] headers = { "주문번호", "고객아이디", "날짜", "주소", "연락처", "포인트" };
}
