package table_demo;

import facade.DataEngineImpl;
import facade.UIData;
import store.Order;
import store.OrderMgr;
import store.OrderedItem;
import store.OrderedItemMgr;
/*
 * 장바구니 테이블은 주문테이블에서 클릭이 변경될 때마다 달라져야 함
 * TableSelectionDemo 클래스에서 상속하여
 * 장바구니 내용을 다시 불러오는 부분을 loadData로 기능 추가함
 * 선택된 주문을 
 */
@SuppressWarnings("serial")
public class BasketTableDemo extends TableSelectionDemo {
	OrderedItemMgr basketMgr = OrderedItemMgr.getInstance();
	int orderId = 1;
    void init(DataEngineImpl<?> mgr) {
        // 첫번째 주문을 가져오게 하기 위해 주문번호를 세팅하는 부분
    	super.init(mgr);
    }
    // 부모 클래스의 테이블 데이터 로드하는 부분을 오버라이드하여
    // 장바구니의 경우 클릭된 주문의 번호를 이용하여 해당 주문의 장바구니를 가져오도록
    // 미리 설정한 후 슈퍼의 loadData를 호출한다.
	@Override
    void loadData(String kwd) {
		if (!kwd.equals("")) {
			orderId = Integer.parseInt(kwd)+1;
		}
		basketMgr.setOrder(orderId);
    	super.loadData("");
	}
}
