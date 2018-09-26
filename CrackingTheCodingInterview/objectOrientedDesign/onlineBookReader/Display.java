package objectOrientedDesign.onlineBookReader;

public class Display {

	private Book activeBook;
	private User activeUser;
	private int pageNumber;
	private Page currentPage;
	
	public void turnPageForward() {
		pageNumber += 1;
		Page page = activeBook.getPage(pageNumber);
		currentPage = page;
		refreshPage();
	}
	
	public void turnPageBackward() {
		pageNumber -= 1;
		Page page = activeBook.getPage(pageNumber);
		currentPage = page;
		refreshPage();
	}
	
	public void refreshUsername() {
		// refresh user name
	}
	
	public void refreshTitle() {
		// refresh title
	}
	
	public void refreshDetails() {
		// refresh details
	}
	
	public void refreshPage() {
		// refresh page.
	}
	
	public void setActiveUser(User user) {
		this.activeUser = user;
	}
	
	public void setActiveBook(Book book) {
		this.activeBook = book;
	}
}
