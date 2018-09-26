package objectOrientedDesign.jukeBox;

public class CDPlayer {
	private Playlist playlist;
	private CD cd;
	
	private Song currentSong;
	private boolean keepPlaying;

	public Song currentSong() {
		return currentSong;
	}

	public void setCD(CD cd) {
		stopPlaying();
		cd = cd;
		startPlaying();
	}
	
	private void stopPlaying() {
		currentSong = null;
		keepPlaying = false;
		// Stop playing
	}

	public void startPlaying() {
		while (!playlist.isEmpty() && keepPlaying) {
			Song song = playlist.poll();
			playSong(song);	
		}
	}

	private void playSong(Song song) {
		currentSong = song;
		// Start playing song.
	}
	
	public Playlist getPlaylist() {
		return playlist;
	}

}
