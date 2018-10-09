package objectOrientedDesign.chatServer;

public class Message {

	private String content;
	private User from;
	
	public Message(String msg, User from) {
		this.content = msg;
		this.from = from;
	}
}
