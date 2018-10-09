package objectOrientedDesign.chatServer;

import java.util.ArrayList;
import java.util.HashMap;

public class Conversation {
	protected ArrayList<Message> messages = new ArrayList<Message>();
	protected HashMap<Integer, User> users = new HashMap<Integer, User>();

	public void addMessage(Message message) {
		messages.add(message);
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
}
