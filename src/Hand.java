import java.util.*;

public class Hand {
	private ArrayList<Card> hand = new ArrayList<>();
	private Deck deck;
	
	public Hand() { //makes a new hand of 8 cards
		deck = new Deck();
		deck.shuffle();
		for(int i = 0; i < 8; i++) {
			hand.add(deck.dealCard());
		}
	}
	
	public Hand(Deck newDeck) {
		deck = newDeck;
		for(int i = 0; i < 8; i++) {
			hand.add(deck.dealCard());
		}
	}
	
	public int drawCard() { //if card was unable to be drawn, return -1
		if(deck.cardsLeft() != 0) {
			hand.add(deck.dealCard());
			return 0;
		}
		else
			return -1;
	}
	
	public int discardCard(int i) { //discards card at index i
		if(hand.size() < i || hand.size() < 0) {
			System.out.print("error: out of index");
			return -1;
		}
		else {
			hand.remove(i);
			return 0;
		}
	}
	
	public int discardCard(Card card) { //discards card
		if(hand.contains(card)) {
			hand.remove(card);
			return 0;
		}
		else {
			System.out.print("error: card is not in hand");
			return -1;
		}
	}
	
	public Card addCardToCaravan(Card card) {
		if(hand.contains(card)) {
			hand.remove(card);
			return card;
		}
		else {
			System.out.print("error: card is not in hand");
			return null;
		}
	}
	
	public int getHandSize() {
		return hand.size();
	}
	
	public Card getCardAtIndex(int i) {
		if(hand.size() < i || hand.size() < 0) {
			System.out.print("error: out of index");
			return null;
		}
		else {
			return hand.get(i);
		}
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public String toString() {
		Object[] cardArray = hand.toArray();
		String s = "";
		for(int i = 0; i < cardArray.length; i++) {
			s += cardArray[i] + " ";
		}
		return s;
	}
	
	public static void main(String args[]) {
		Hand hand = new Hand();
		System.out.println("new hand\n" + hand.toString());
		hand.drawCard();
		System.out.println("draw new card\n" + hand.toString());
		hand.discardCard(0);
		System.out.println("discard first card\n" + hand.toString());
	}
}
