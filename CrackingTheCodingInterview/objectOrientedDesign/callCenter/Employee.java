package objectOrientedDesign.callCenter;

// As employee itself doesn't make much sense. We are making this class abstract. 
public abstract class Employee {

	private boolean isFree;
	protected JobTitle level;

	public void receiveCall() {
		isFree = false;
		// Recieve call.
		// If unable to handle, escalate call.
		isFree = true;
	}

	public boolean isFree() {
		return isFree;
	}

}
