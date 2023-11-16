package store;

import mgr.Factory;
import java.util.List;

import facade.DataEngineImpl;
import mgr.Manager;

public class FlowerMgr extends DataEngineImpl<flower> {
	private static FlowerMgr engine = null;
	private FlowerMgr() {
		setLabels(headers);
	}
	public static FlowerMgr getInstance() {
		if (engine == null)
			engine = new FlowerMgr();
		return engine;
	}
	private String[] headers = {"name", "language", "flowering","color","care","cost"};

}
