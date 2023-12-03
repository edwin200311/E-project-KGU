package store;

import java.util.List;

import facade.DataEngineImpl;
import facade.UIData;

public class FlowerMgr extends DataEngineImpl<Flower> {
	private static FlowerMgr engine = null;
	private List<Flower> flowers;
	public static FlowerMgr getInstance() {
		if (engine == null)
			engine = new FlowerMgr();
		return engine;
	}
	private FlowerMgr() {
		setLabels(headers);
	}
	
	private String[] headers = {"이름", "꽃말", "개화시기", "색", "관리법", "가격", "재고"};

	public int getFlowerPrice(int index) {
        Flower flower = flowers.get(index);
        if (flower != null) {
            return flower.getcost();
        }
        return 0; // or some default value
    }

}
