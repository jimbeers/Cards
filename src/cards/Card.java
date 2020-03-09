package cards;

public class Card implements Comparable<Card> {
	public int value = -1;
	public int suit = -1;

	public Card(int value, int suit) {
		this.value = value;
		this.suit = suit;
	}

	public String toString() {
		String retStr = " of ";
		if ((value != -1) && (suit != -1)) {
			if (value > 1 && value < 11) {
				retStr = Integer.toString(value) + retStr;
			} else {
				switch (value) {
				case 1:
					retStr = "Ace"  + retStr;
					break;
				case 11:
					retStr = "Jack" + retStr;
					break;
				case 12:
					retStr = "Queen" + retStr;
					break;
				case 13:
					retStr = "King" + retStr;
					break;
				case 14:
					retStr = "Ace" + retStr;
					break;

				}
			}
			// now do the suit
			if (suit>0 && suit<5) {
				switch (suit) {
				case 1:
					retStr += "Clubs";
					break;
				case 2:
					retStr += "Diamonds";
					break;
				case 3:
					retStr += "Hearts";
					break;
				case 4:
					retStr += "Spades";
					break;

				}
			}

			
		}
		return retStr;
	}

	public int getSuit() {
		return suit;
	}

	@Override
	public int compareTo(Card other) {
		if (this.getValue() < other.getValue()) {
			return -1;
		} else if (this.getValue() > other.getValue()) {
			return 1;
		} else if (this.getSuit() < other .getSuit()) {
			return -1;
		} else if (this.getSuit() > other .getSuit()) {
			return 1;
		} else { 
			return 0;
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
