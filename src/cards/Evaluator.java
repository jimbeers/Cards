package cards;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {
	Card cardA1;
	Card cardA2;
	Card cardB1;
	Card cardB2;
	int tests;
	Deck d = null;
	private int handAcnt = 0;
	private int handBcnt = 0;
	private int tie = 0;

	public Evaluator(Card a1, Card a2, Card b1, Card b2, int interations) {
		cardA1 = a1;
		cardA2 = a2;
		cardB1 = b1;
		cardB2 = b2;
		tests = interations;
		interate();
		
	}

	public String toString() {
		return "Results, " + Integer.toString(Math.round(handAcnt*100/tests)) + " "+ Integer.toString(Math.round(handBcnt*100/tests)) + 
				" ties " + Integer.toString(Math.round(tie*100/tests)) +"  cards " + cardA1 + " " + cardA2 + 
				" hand b " + cardB1 + " " + cardB2;
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
	
	public int getTests() {
		return tests;
	}

	public void setTests(int tests) {
		this.tests = tests;
	}

	public int getHandAcnt() {
		return handAcnt;
	}

	public void setHandAcnt(int handAcnt) {
		this.handAcnt = handAcnt;
	}

	public int getHandBcnt() {
		return handBcnt;
	}

	public void setHandBcnt(int handBcnt) {
		this.handBcnt = handBcnt;
	}

	public int getTie() {
		return tie;
	}

	public void setTie(int tie) {
		this.tie = tie;
	}

	public static void main(String[] args) {
		Evaluator v = new Evaluator(new Card(5,1), new Card(5,2), new Card(3,2), new Card(2,2), 100000);
		System.out.println(v);
		}
}
