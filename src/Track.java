import java.util.*;

public class Track implements Comparable<Track> {
	static int MAXLENGTH = 7;
	
	private ArrayList<Card> track = new ArrayList<>();
	boolean isAscending, isSold;
	
	int totalValue, length, currentSuit; //how many cards are in this track?
	
	public Track() { //makes default track with no cards in track
		totalValue = 0;
		length = 0;
		isSold = false;
		
	}
	
	public Track(Card card) { //start a track with one card. cannot be done w/ face cards
		track.add(card);
		length = 1;
		isSold = false;
		totalValue = card.getValue();
		currentSuit = card.getSuit();
	}
	
	public ArrayList<Card> getTrack() {
		return track;
	}

	public Card getCardAtPos(int i) {
		if(i > track.size() || i < 0)
			return null;
		else
			return track.get(i);
	}
	
	public Card getLastCard() {
		return track.get(getLength()-1);
	}
	
	public boolean getIsAscending() {
		return isAscending;
	}
	
	public void setIsAscending(boolean b) {
		isAscending = b;
	}
	
	public boolean getIsSold() {
		return isSold;
	}
	
	public void setIsSold(boolean b) {
		isSold = b;
	}
	
	public void checkIsSold() {
		if(totalValue >= 21 || totalValue <= 26) {
			setIsSold(true);
		}
		else {
			setIsSold(false);
		}
	}
	
	public int getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(int i) {
		totalValue = i;
	}
	
	public void addTotalValue(int i) {
		totalValue += i;
	}
	
	public int getCurrentSuit() {
		return currentSuit;
	}
	
	public void setCurrentSuit(int i) {
		currentSuit = i;
	}
	
	public int getLength() {
		return length;
	}
	
	//make a new method checking if its valid to add??
	
	public int addCard(Card card) { //add NUMBER CARD to top of track. returns -1 if unsuccessful
		if(isAscending) {
			if (card.getValue() > getCardAtPos(track.size() - 1).getValue()) {
				addTotalValue(card.getValue());
				if(card.getSuit() == currentSuit) {
					isAscending = !isAscending;
				}
				this.checkIsSold();
				return 0;
			}
			else {
				return -1;
			}
		}
		else {
			if (card.getValue() < getCardAtPos(track.size() - 1).getValue()) {
				addTotalValue(card.getValue());
				if(card.getSuit() == currentSuit) {
					isAscending = !isAscending;
				}
				this.checkIsSold();
				return 0;
			}
			else {
				return -1;
			}
		}
	}
	
	/*NOTE: EDIT THIS PLEASE DON"T FORGET
	 * REMOVE AFTER EDITINGS*/
	public void addCard(int pos, Card card) { //assuming that the card can be added. must check length outside
		track.add(pos + 1, card);
		if(card.isFaceCard()) { //EDIT FACE CARD CONDITIONS
			switch(card.getValue()) {
			case 11: //EDIT THIS IN CASE ANY OTHER FACE CARDS ATTACHED
				track.remove(pos);
				track.remove(card);
				return;
			case 12:
				return;
			case 13:
				return;
			}
		}
		else {
			this.addTotalValue(card.getValue());
		}
	}
	
	public void discardTrack() {
		track.clear();
	}

	@Override
	public int compareTo(Track track2) {
		if(this.getTotalValue() == track2.getTotalValue()) {
			return 0;
		}
		else if(this.getTotalValue() < track2.getTotalValue()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
