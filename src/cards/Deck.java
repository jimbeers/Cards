package cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	List<Card> cards = new  ArrayList<Card>();
	Random r = new Random();
	
	public Deck() {
		for (int x=2; x<15; x++) {
			for (int y=1; y<5; y++) {
				cards.add(new Card(x,y));
			}
		}
	}
	
	public Card get(int index) {
		return cards.get(index);
	}
	
	public void remove(Card card) {
		for (Card c: cards) {
			if (c.getValue() == card.getValue() && c.getSuit() == card.getSuit()) {
				cards.remove(c);
				break;
			}
		}
		
	}
	
	public int deckSize () {
		return cards.size();
	}

	public Card getRandomCard() {
		int min=0;
		int max=cards.size();
		int rNum = r.nextInt(max);
		Card retCard = cards.get(rNum);
		cards.remove(rNum);
		return retCard;
		
	}
	
	
//		  return Math.floor(Math.random() * (max - min + 1)) + min))
	
}
