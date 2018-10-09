package objectOrientedDesign.chatServer;

public class AddRequest {

	private User from;
	private RequestStatus requestStatus;
	private User to;

	public AddRequest(User from, User to, RequestStatus requestStatus) {
		this.from = from;
		this.to = to;
		this.requestStatus = requestStatus;
	}
	
	public User getFrom() {
		return from;
	}
	
	public User getTo() {
		return to;
	}

	public void setStatus(RequestStatus status) {
		this.requestStatus = status;
	}

	public RequestStatus getStatus() {
		return requestStatus;
	}
}
