package objectOrientedDesign.onlineBookReader;

public class Book {
	
	private Page[] pages;
	private String name;
	private int id;
	
	public String getName() {
		return name;
	}
	
	public Page getPage(int pageNumber) {
		return pages[pageNumber];
	}
	
	public int getId() {
		return id;
	}

}
