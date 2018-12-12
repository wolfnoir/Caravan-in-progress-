public class Deck {

	protected Card[] deck;
	private int cardsUsed;

	public Deck() { // makes a new deck of 52 cards, unshuffled
		deck = new Card[52];

		// goes until until cardcount = 52
		int cardCount = 0;
		// gives the card a suit
		for (int suit = 0; suit <= 3; suit++) {
			// gives the card a value
			for (int value = 1; value <= 13; value++) {
				deck[cardCount] = new Card(value, suit);
				cardCount++;
			}
		}
		cardsUsed = 0;
	}

	public void shuffle() { // self explanatory
		for (int i = 51; i > 0; i--) {
			int rand = (int) (Math.random() * (i + 1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		cardsUsed = 0;
	}

	public String printDeck() { // prints the whole deck of cards in a string
		String deckString = "";
		for (int i = 0; i < 51; i++) {
			deckString += deck[i].toString();
			deckString += ", ";
		}
		return deckString;
	}

	public int cardsLeft() {
		return 52 - cardsUsed;
	}

	public Card dealCard() { //returns the card used
		if (cardsUsed == 52) { //if you've already used 52 cards, you can't use them again!
			System.out.print("out of cards");
			return null;
		}
		cardsUsed++;
		return deck[cardsUsed - 1]; //keeps using the next card in line until you run out of cards
	}

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		System.out.print(deck.printDeck());
	}
}
