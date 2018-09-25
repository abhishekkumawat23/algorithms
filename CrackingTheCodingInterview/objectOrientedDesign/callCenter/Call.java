package objectOrientedDesign.callCenter;

public class Call {

	private Caller caller;
	private Employee handler;
	
	public Call(Caller caller) {
		this.caller = caller;
	}

	public void connect() {
		CallHandlerProgram.getInstance().dispatchCall(this);
	}

	public void disconnect() {
		this.caller = null;
		this.handler = null;
		// Destroy object.
	}
	
	public Caller getCaller() {
		return caller;
	}
	
	public Employee getHandler() {
		return handler;
	}

	public void setHandler(Employee handler) {
		this.handler = handler;
	}
}
