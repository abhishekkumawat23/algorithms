package objectOrientedDesign.onlineBookReader;

import java.util.HashMap;

public class UserManager {
	HashMap<String, User> users = new HashMap<String, User>();
	
	public void addUser(User user) {
		users.put(user.getName(), user);
	}
	
	public void removeUser(String name) {
		users.remove(name);
	}
	
	public void findUser(String name) {
		users.get(name);
	}
}
