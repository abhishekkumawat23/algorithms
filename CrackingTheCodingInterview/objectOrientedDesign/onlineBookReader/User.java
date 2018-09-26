package objectOrientedDesign.onlineBookReader;

public class User {
	private String name;
	private int userId;
	private int accountType;

	public String getName() {
		return name;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getAccountType() {
		return accountType;
	}
	
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
	public void renewMembership() {
		// renew membership of user.
	}

}
