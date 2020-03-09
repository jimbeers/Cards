package cards;

import java.util.ArrayList;
import java.util.List;

public class Evaluator2 {
	Card cardA1;
	Card cardA2;
	Card cardB1;
	Card cardB2;
	int tests;
	Deck d = null;
	private long handAcnt = 0;
	private long handBcnt = 0;
	private long tie = 0;
	private long totTests = 0;

	public Evaluator2(Card a1, Card a2, int interations) {
		cardA1 = a1;
		cardA2 = a2;
		
		// create a list with all pairs
		List<CardPair> pairsToTest = createPairsList();
		System.out.println(pairsToTest);
		pairsToTest.size();
		// iterate through each set, get results
		for (CardPair p: pairsToTest ) {
			Evaluator e = new Evaluator(a1,a2,p.getCard1(),p.getCard2(),interations);
			//System.out.println(e);
			handAcnt += e.getHandAcnt();
			handBcnt += e.getHandBcnt();
			tie += e.getTie();
			totTests += e.getTests();
		}
		//print results
		double r1 = handAcnt;
		double r2 = handBcnt;
		r1 = (r1*100)/totTests;
		r2 = (r2*100)/totTests;
		System.out.println("A = " + r1 + " B = "+ r2);
	}

	private List<CardPair> createPairsList() {
		List<CardPair> retList = new ArrayList<CardPair>();
		Card c1;
		Card c2;
		Deck d = new Deck();
		// remove our cards from the deck
		d.remove(cardA1);
		d.remove(cardA2);
		int dSize = d.deckSize();
		for (int x=0; x<dSize; x++) {
			c1 = d.get(x);
			for (int y=x+1; y<dSize; y++) {
				c2 = d.get(y);
				CardPair thispair = new CardPair(c1,c2);
				retList.add(thispair);
				System.out.println(thispair);
			}
			
		}
		
		return retList;
		
	}

	public String toString() {
		return "Results, a get " + Integer.toString(Math.round(handAcnt*100/tests)) + " b get "+ Integer.toString(Math.round(handBcnt*100/tests)) + 
				" ties get " + Integer.toString(Math.round(tie*100/tests));
	}

	public void interate() {
		for (int c=0; c<tests; c++) {
			runTest();
		}
	}

	private void runTest() {
		List<Card> aCards = new ArrayList<Card>();
		List<Card> bCards = new ArrayList<Card>();
		aCards.add(cardA1);
		aCards.add(cardA2);
		bCards.add(cardB1);
		bCards.add(cardB2);
		d = new Deck();
		d.remove(cardA1);
		d.remove(cardA2);
		d.remove(cardB1);
		d.remove(cardB2);
		// get 5 random cards, add to each group
		for (int x=0; x<5; x++) {
			Card newCard = d.getRandomCard();
			aCards.add(newCard);
			bCards.add(newCard);			
		}
		HandValue handA = new HandValue(aCards);
		HandValue handB = new HandValue(bCards);
		if (handA.getValue() > handB.getValue()) {
			handAcnt++;
		} else if (handB.getValue() > handA.getValue()) {
			handBcnt++;
		} else {
			tie++;
		}

	}
	
	public static void main(String[] args) {
		Evaluator2 v = new Evaluator2(new Card(12,1), new Card(12,2), 1000); //Q clubs, K clubs
		
		}
	
	private class CardPair {
		private Card card1 = null;
		private Card card2 = null;
		CardPair(Card c1, Card c2) {
			setCard1(c1);
			setCard2(c2);			
		}
		public Card getCard1() {
			return card1;
		}
		public void setCard1(Card card1) {
			this.card1 = card1;
		}
		public Card getCard2() {
			return card2;
		}
		public void setCard2(Card card2) {
			this.card2 = card2;
		}	
		
		public String toString() {
			return card1.toString() + "  " + card2.toString();
		}
	}
}
