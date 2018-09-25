package objectOrientedDesign.callCenter;

public class Caller {

	public void makeCall() {
		Call call = new Call(this);
		call.connect();
		call.disconnect();
	}
}
