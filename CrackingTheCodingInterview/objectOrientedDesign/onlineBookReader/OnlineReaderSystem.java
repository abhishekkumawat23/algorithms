package objectOrientedDesign.onlineBookReader;

public class OnlineReaderSystem {

	private Library library;
	private User activeUser;
	private Book activeBook;
	private Display display;
	private UserManager userManager;
	
	public Library getLibrary() {
		return library;
	}
	
	public Book getActiveBook() {
		return activeBook;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public User getActiveUser() {
		return activeUser;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
}
