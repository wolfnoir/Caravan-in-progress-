import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {

	// each suit/face card corresponds to a number. god help me if i don't remember
	// this
	public final static int SPADES = 0, HEARTS = 1, DIAMONDS = 2, CLUBS = 3;

	// ace is definitely the value of 1 but the others we just don't know until i
	// code this huh
	public final static int ACE = 1, JACK = 11, QUEEN = 12, KING = 13;

	private final int suit; // spades, hearts, diamonds, or clubs. no default instantiation
	private final int value; // from 1-11. no default instantiation
	
	private Image cardImg;
	private ImageView cardImgView;
	
	// make a new card
	public Card(int newValue, int newSuit) {
		this.value = newValue;
		this.suit = newSuit;
		cardImg = new Image("images/" + this.toString() + ".jpg");
		cardImgView = new ImageView(cardImg);		
	}

	public Image getCardImg() {
		return cardImg;
	}
	
	public ImageView getCardImgView() {
		return cardImgView;
	}
	
	// get suit of card (in number code)
	public int getSuit() {
		return this.suit;
	}

	// get value of card (in number code)
	public int getValue() {
		return this.value;
	}
	
	public int compareSuit(Card otherCard) {
		if(this.getSuit() == otherCard.getSuit())
			return 0;
		else
			return -1;
	}
	
	public int compareValue(Card otherCard) {
		if(this.getValue() == otherCard.getValue())
			return 0;
		else
			return -1;
	}

	// get suit in string form
	public String getSuitString() {
		switch (suit) {
		case SPADES:
			return "Spades";
		case HEARTS:
			return "Hearts";
		case DIAMONDS:
			return "Diamonds";
		case CLUBS:
			return "Clubs";
		default:
			return "??";
		}
	}

	// get value in string form
	// haha can you imagine trying to do this whole thing with only if statements
	// because i sure didn't spend 20 minutes trying to do that before remembering
	// switch cases existed
	public String getValueString() {
		switch (value) {
		case 1:
			return "Ace";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		default:
			return "??";
		}
	}

	// check to see if this card is a face card. idk if this is actually useful but
	// it's worth having maybe
	public boolean isFaceCard() {
		if (this.getValue() >= 11 && this.getValue() < 13)
			return true;
		return false;
	}

	// prints out the full name of the card
	public String toString() {
		return getValueString() + " of " + getSuitString();
	}

	public static void main(String args[]) {
		Card a = new Card(12, 1);
		System.out.println(a.toString());
		System.out.println(a.isFaceCard());
	}
}
