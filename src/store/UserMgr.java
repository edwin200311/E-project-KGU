package store;

import facade.DataEngineImpl;

public class UserMgr extends DataEngineImpl<User> {
	private static UserMgr engine = null;
	public static UserMgr getInstance() {
		if (engine == null)
			engine = new UserMgr();
		return engine;
	}
	private UserMgr() {
		setLabels(headers);
	}
	
	private String[] headers = {"id", "point", "wait-point"};
}
