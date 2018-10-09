package objectOrientedDesign.fileSystem;

public class File extends Entry {
	
	private String content;

	public File(String name, Directory parent) {
		super(name, parent);
	}

	@Override
	public int size() {
		return content.length();
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

}
