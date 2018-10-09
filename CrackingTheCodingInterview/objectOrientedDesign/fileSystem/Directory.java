package objectOrientedDesign.fileSystem;

import java.util.ArrayList;

public class Directory extends Entry {
	
	private ArrayList<Entry> entries;
	
	public Directory(String name, Directory parent) {
		super(name, parent);
		this.entries = new ArrayList<Entry>();
	}

	public boolean deleteEntry(Entry entry) {
		return entries.remove(entry);
	}
	
	public void addEntry(Entry entry) {
		entries.add(entry);
	}
	
	public int getNumberOfFiles() {
		int numberOfFiles = 0;
		for (Entry entry: entries) {
			if (entry instanceof Directory) {
				numberOfFiles++; // Count directory also as file.
				numberOfFiles += ((Directory) entry).getNumberOfFiles();
			}
			else if (entry instanceof File) {
				numberOfFiles++;
			}
		}
		
		return numberOfFiles;
	}

	@Override
	public int size() {
		int size = 0;
		for (Entry entry: entries) {
			size += entry.size();
		}
		return size;
	}
	
	public ArrayList<Entry> getEntries(){
		return entries;
	}

}
