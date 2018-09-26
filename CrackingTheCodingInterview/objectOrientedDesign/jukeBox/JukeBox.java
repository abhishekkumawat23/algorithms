package objectOrientedDesign.jukeBox;

import java.util.List;

public class JukeBox {

	private CDPlayer cdPlayer;
	private User user;
	private List<CD> cdCollection;
	
	public Song getCurrentSong() {
		return cdPlayer.currentSong();
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void changeCd(CD cd) {
		cdPlayer.setCD(cd);
	}
}
