//EDIT THIS LATER. TWO OPPOSING TRACKS AGAINST EACH OTHER
public class Caravan {
	private Track track1, track2;
	
	public Caravan() {
		track1 = new Track();
		track2 = new Track();
	}

	public Track getTrack1() {
		return track1;
	}
	
	public Track getTrack2() {
		return track2;
	}

	public void setTrack1(Track track) {
		track1 = track;
	}
	
	public void setTrack2(Track track) {
		track2 = track;
	}

}
