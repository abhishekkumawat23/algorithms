package objectOrientedDesign.fileSystem;

public abstract class Entry {

	private String name;
	private int size;
	private Directory parent;
	private long createdTime;
	private long lastUpdatedTime;
	private long lastAccessedTime;
	
	public Entry(String name, Directory parent) {
		this.name = name;
		this.parent = parent;
		this.createdTime = System.currentTimeMillis();
		this.lastUpdatedTime = System.currentTimeMillis();
		this.lastAccessedTime = System.currentTimeMillis();
		parent.addEntry(this);
	}
	
	// Size for entry doesn't make sense unless we know the entry is file or directory.
	public abstract int size();
	
	public Directory getParent() {
		return parent;
	}
	
	public long getCreatedTime() {
		return createdTime;
	}
	
	public long getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	
	public long getLastAccessedTime() {
		return lastAccessedTime;
	}
	
	public boolean delete() {
		if (parent == null) {
			return false;
		}
		return parent.deleteEntry(this);
	}
	
	public String getFullPath() {
		if (parent == null) {
			return name;
		}
		return parent.getFullPath() + "/" + name;
	}
	
	public void changeName(String name) {
		this.name = name;
	}
}
