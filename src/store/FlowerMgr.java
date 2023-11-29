package store;

import facade.DataEngineImpl;

public class FlowerMgr extends DataEngineImpl<Flower> {
	private static FlowerMgr engine = null;
	public static FlowerMgr getInstance() {
		if (engine == null)
			engine = new FlowerMgr();
		return engine;
	}
	private FlowerMgr() {
		setLabels(headers);
	}
	
	private String[] headers = {"이름", "꽃말", "개화시기", "색", "관리법", "가격", "재고"};
}
