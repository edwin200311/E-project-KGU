package store;

import java.util.List;

import facade.DataEngineImpl;
import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class UserMgr extends DataEngineImpl<user> {
	private static UserMgr engine = null;
	private UserMgr() {
		setLabels(headers);
	}
	public static UserMgr getInstance() {
		if (engine == null)
			engine = new UserMgr();
		return engine;
	}
	private String[] headers = {"id", "point", "wait-point"};
}
