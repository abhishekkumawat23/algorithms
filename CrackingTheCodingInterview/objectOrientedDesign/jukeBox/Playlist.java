package objectOrientedDesign.jukeBox;

import java.util.LinkedList;
import java.util.Queue;

public class Playlist {
	
	Queue<Song> songsQueue = new LinkedList<Song>();

	public Song poll() {
		return songsQueue.poll();
	}

	public boolean isEmpty() {
		return songsQueue.isEmpty();
	}
	
	public Song getNextSong() {
		return songsQueue.peek();
	}
	
	public void addSong(Song song) {
		songsQueue.add(song);
	}

}
