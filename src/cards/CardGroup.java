package cards;

import java.util.Comparator;

// a card group is a pair or trips or 4 of a kind.  size is the number of members in the group, 
// value is the face value of the members.
public class CardGroup implements Comparable<CardGroup>{
	int size = 0;
	int value = 0;
	
	public CardGroup(int value, int size) {
		this.size = size;
		this.value = value;
	}
	
	@Override
	public int compareTo(CardGroup other) {
		if (this.getSize() < other.getSize()) {
			return -1;
		} else if (this.getSize() > other.getSize()) {
			return 1;
		} else if (this.getValue() < other.getValue()) {
			return -1;
		} else if (this.getValue() > other.getValue()) {
			return 1;
		} else { 
			return 0;
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString() {
		String retStr = "CardGroup of size " + Integer.toString(size) + " of card " + value;
		return retStr;
	}
	
	public static class Comparators {
		public static Comparator<CardGroup> VALUE = new Comparator<CardGroup>() {
			@Override
			public int compare(CardGroup o1, CardGroup o2) {
				return o1.getValue() - o2.getValue();
			}
		};
	}
}
