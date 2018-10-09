package objectOrientedDesign.chatServer;

public class PrivateChat extends Conversation {
	
	public PrivateChat(User user1, User user2) {
		super();
		users.put(user1.getId(), user1);
		users.put(user2.getId(), user1);
	}
}
