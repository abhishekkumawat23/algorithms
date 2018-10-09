package objectOrientedDesign.chatServer;

import java.util.ArrayList;

public class GroupChat extends Conversation {
	private String groupName;
	
	public GroupChat(String groupName, ArrayList<User> usersList) {
		super();
		this.groupName = groupName;
		for (User user: usersList) {
			users.put(user.getId(), user);
		}
	}
	
	public void addParticipant(User user) {
		users.put(user.getId(), user);
	}
	
	public void removeParticipant(User user) {
		users.remove(user.getId());
	}
}
