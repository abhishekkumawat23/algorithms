package objectOrientedDesign.chatServer;

import java.util.HashMap;

public class UserManager {

	private static UserManager instance;
	private HashMap<Integer, User> usersById = new HashMap<Integer, User>();
	private HashMap<String, User> usersByName = new HashMap<String, User>();
	private HashMap<Integer, User> onlineUser = new HashMap<Integer, User>();
	
	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public void addRequest(User from, User to) {
		to.receivedAddRequest(from);
	}

	public void addRequestStatus(User from, User to, RequestStatus status) {
		// Authenticate if `to` user himself is accepting the request and noone else.
		from.receivedAddRequestStatus(status, to);
	}
	
	public void addUser(User user) {
		usersById.put(user.getId(), user);
		usersByName.put(user.getName(), user);
	}
	
	public void getUser(int id) {
		usersById.get(id);
	}
	
	public void getUser(String name) {
		usersByName.get(name);
	}
}
