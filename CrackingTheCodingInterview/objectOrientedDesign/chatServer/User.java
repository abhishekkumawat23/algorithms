package objectOrientedDesign.chatServer;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

	private HashMap<Integer, User> contacts = new HashMap<Integer, User>();
	private HashMap<Integer, PrivateChat> privateChats = new HashMap<Integer, PrivateChat>();
	private HashMap<String, GroupChat> groupChats = new HashMap<String, GroupChat>();
	private UserStatus userStatus;
	private ArrayList<User> pendingRequests;
	private Integer id;
	private String name;
	
	public void sendAddRequest() {
		User user = null;
		UserManager.getInstance().addRequest(this, user);
	}

	public void receivedAddRequest(User user) {
		pendingRequests.add(user);
	}
	
	public void reviewPendingRequests() {
		for (User user: pendingRequests) {
			// Assuming we are accepting the user request.
			contacts.put(user.getId(), user);
			UserManager.getInstance().addRequestStatus(user, this, RequestStatus.Accepted);
		}
	}

	public Integer getId() {
		return id;
	}

	public void receivedAddRequestStatus(RequestStatus status, User to) {
		if (status == RequestStatus.Accepted) {
			contacts.put(to.getId(), to);
		}
		pendingRequests.remove(to);
	}
	
	public void sendPrivateMessage(User user, String msg) {
		if (!privateChats.containsKey(user.getId())){
			privateChats.put(user.getId(), new PrivateChat(this, user));
		}
		
		Message message = new Message(msg, this);
		privateChats.get(user.getId()).addMessage(message);
	}
	
	public void sendGroupMessage(String groupName, String msg) {
		if (!groupChats.containsKey(groupName)){
			ArrayList<User> listOfUsers = null;
			groupChats.put(groupName, new GroupChat(groupName, listOfUsers));
		}
		
		Message message = new Message(msg, this);
		groupChats.get(groupName).addMessage(message);
	}

	public String getName() {
		return name;
	}
	
	public void setUserStatus(UserStatus status) {
		this.userStatus = userStatus;
	}
}
