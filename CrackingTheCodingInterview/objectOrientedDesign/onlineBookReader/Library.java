package objectOrientedDesign.onlineBookReader;

import java.util.Collection;
import java.util.HashMap;

public class Library {

	private HashMap<String, Book> books;
	
	public Book searchBook(String name) {
		return books.get(name);
	}
	
	public void addBook(Book book) {
		books.put(book.getName(), book);
	}
	
	public void removeBook(Book book) {
		books.remove(book.getName());
	}
	
	public Collection<Book> getAllBooks() {
		return books.values();
	}
}
