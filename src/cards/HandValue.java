package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * hand value, given a number of card, calculates the best 5 card hand, 
 * Values,
 * 
 * Straight flush = 8000000       + levelsOf13(high card) 
 * 4 of a kind = 7000000 + levelsOf13(group, kicker)
 * full house = 6000000 + levelsOf13(group, pair)
 * flush 5000000 + levelsOf13(h1,h2,h3,h4,h5)
 * straight 4000000 + levelsOf13(high card)
 * 3 of a kind = 3000000 + levelsOf13(group, kicker1,kicker2)
 * 2 pair = 2000000 + levelsOf13(hi pair, low pair, kicker)
 * pair = 1000000 + levelsOf13(pair, kicker1,kicker2,kicker3)
 * hicard = levelsOf13(h1,h2,h3,h4,h5)
 * or ((((c1*13+c2) *13 + c3)*13 + c4)*13 + c5)
 * ex. 12,10,8,6,4 = 366136
 * 12,11,8,6,4 = 368333
 * 
 * levelsOf13 max = 399655;
 */
public class HandValue {
	int outputValue = 0;
	int flushValue = 0;
	
	int sCnt;
	int cCnt;
	int hCnt;
	int dCnt;
	private int maxSuit = 0;
	int maxSuitCnt = 0;
	int straightTopCard = 0;
	int topTrips = 0;
	private int topFour = 0;
	List<CardGroup> cardGroups = new ArrayList<CardGroup>();
	List<Card> cards = new ArrayList<Card>();
	
	public HandValue(List<Card> inputCards) {
		int secondOP = 0;
		redoAces(inputCards);
		suitCnt(cards);
		Collections.sort(cards, Collections.reverseOrder());
		findGroups(cards);
		straightTopCard = findStraights(cards);
		if (maxSuit != 0) { // flush suit, MUST DO THIS FIRST IN CASE OF A STRAIGNT FLUSH
			int cnt = 5; // we need only top 5 cards
			List<Card> flushCards = new ArrayList<Card>();
			for (Card card: cards) {
				if (card.getSuit() == maxSuit) {
					flushCards.add(card);
				}
			}
			Collections.sort(flushCards, Collections.reverseOrder());
			int topStraightFlush = findStraights(flushCards);
			if (topStraightFlush != 0) {//we have a straight flush
				outputValue = 8000000 + topStraightFlush;
			} else { // just a flush
				int flushValue15 = 0;
				for (Card card: flushCards) {
					if (cnt-- > 0) {  // use the top five only
						flushValue15 = flushValue15 * 15 + card.getValue();
					}
				}
				flushValue = 5000000 + flushValue15;	
			}	
		}
		
		if (outputValue == 0 && cardGroups.size() > 0) {
			CardGroup topCardgroup = cardGroups.get(0);
			//		CardGroup secondCardGroup = cardGroups.get(1);
			if (topCardgroup.getSize() == 4) { // four of a kind, remove these 4, and get the next highest card.
				topFour = topCardgroup.getValue();
				for (Card card: cards) {
					if (card.getValue() != topFour) {  //this is our kicker
						outputValue = 7000000 + levelsOf15(topFour,card.getValue());
						break;
					}
				}
			}
			if (outputValue == 0) { // continue, look for full house
				if (topCardgroup.getSize() == 3) { //three of a kind or full house.
					topTrips = topCardgroup.getValue();
					if (cardGroups.size() > 1) { //full house
						List<Card>copyCards = new ArrayList<Card>(cards);
						cardGroups.remove(0);  //
						Collections.sort(cardGroups, CardGroup.Comparators.VALUE);
						// highest should be the second group. 
						outputValue = 6000000 + levelsOf15(topTrips,cardGroups.get(0).getValue());
					} else { // trips, need to find the next two highest cards
						List<Card>copyCards = new ArrayList<Card>(cards);
						for (Card card: cards) {
							if (card.getValue() == topTrips) {
								copyCards.remove(card);  // remove all the trips cards
							}
						}
						Collections.sort(copyCards);
						Card c1 = copyCards.get(0);
						Card c2 = copyCards.get(1);
						secondOP = 3000000 + levelsOf15(topTrips, c1.getValue(), c2.getValue());
					}
					//CardGroup secondCardGroup = cardGroups.get(1);
				}
			}
			if (outputValue == 0  && secondOP == 0) { // look for 1 pair or 2 pair.
				int topPair = topCardgroup.getValue();
				if (cardGroups.size() == 1) { // one pair, get the rest of the cards, order, get to three
					List<Card>copyCards = new ArrayList<Card>(cards);
					for (Card card: cards) {
						if (card.getValue() == topPair) {
							copyCards.remove(card);  // remove all the trips cards
						}
					}
					Collections.sort(copyCards, Collections.reverseOrder());
					Card c1 = copyCards.get(0);
					Card c2 = copyCards.get(1);
					Card c3 = copyCards.get(2);
					secondOP = 1000000 + levelsOf15(topPair, c1.getValue(), c2.getValue(), c3.getValue());
				} else { // more then one pair, so two pair
					cardGroups.remove(0);  // remove the top group
					Collections.sort(cardGroups, CardGroup.Comparators.VALUE); // resort, but by value, not size
					int secondPair = cardGroups.get(0).getValue();  // it is the new top value
					List<Card>copyCards = new ArrayList<Card>(cards);
					for (Card card: cards) {
						if (card.getValue() == topPair) {
							copyCards.remove(card);  // remove all the trips cards
						}
						if (card.getValue() == secondPair) {
							copyCards.remove(card);
						}
					}
					Collections.sort(copyCards, Collections.reverseOrder());
					Card c1 = copyCards.get(0);
					secondOP = 2000000 + levelsOf15(topPair, secondPair, c1.getValue());

				}
			}
		}
		
		//now flushes,		
		if (outputValue == 0) { // still 0
			if (flushValue > 0) {
				outputValue = flushValue;
			}
		}

		// now str. 
		if (outputValue == 0) {
			if (straightTopCard != 0) {
				outputValue = 4000000 + straightTopCard;
			}
		}

		// now trips, two pair, one pair, all already calculated, in secondOP
		if (outputValue == 0) {
			outputValue = secondOP;
		}

		// now no pair, just take the top 5 cards.
		if (outputValue == 0) {
			int result = 0;
			for (int x=0; x<5; x++) {
				result = result * 15 + cards.get(x).getValue(); 
			}
			outputValue = result;
		}
		//System.out.println(outputValue);
	}







	private void redoAces(List<Card> cList) {
		for(Card card: cList) {
			if (card.getValue() == 1) {
				card.setValue(14);
			}
			cards.add(card);
		}
		
	}







	private void findGroups(List<Card> cards) {
		int currCard = 0;
		int currCnt = 0;
		int lastCardValue = 0;
		for (Card card: cards) {
			lastCardValue = card.getValue();
			if (currCard == lastCardValue) {
				currCnt++;
			} else {
				if (currCnt > 0) {
					addToGroups(currCard, currCnt+1);
				}
				// now reset currCnt and currCard
				currCnt = 0;
				currCard = lastCardValue;
			}
		}
		// check if the last one is in a group
		if (currCnt > 0) {
			addToGroups(currCard, currCnt+1);
		}



		Collections.sort(cardGroups, Collections.reverseOrder());
	}

	private void addToGroups(int currCard, int currCnt) {
		cardGroups.add(new CardGroup(currCard, currCnt));
	}

	private void suitCnt(List<Card> cards) {
		for (Card card: cards) {
			switch (card.getSuit()) {
			case 1:
				cCnt++;
				break;
			case 2:
				dCnt++;
				break;
			case 3:
				hCnt++;
				break;
			case 4:
				sCnt++;
				break;
			}
		}

		// max suit means there is a flush,
		// limited to 9 cards, so only 1 flush allowed.
		if (cCnt > 4) {
			maxSuit = 1;
		} else if (dCnt > 4) {
			maxSuit = 2;
		} else if (hCnt > 4) {
			maxSuit = 3;
		} else if (sCnt > 4) {
			maxSuit = 4;
		} else  {
			maxSuit = 0;
		}

	}

	private int findStraights(List<Card> cards) {
		List<Card> copyCards = new ArrayList<Card>();
		int retStrTopCard = 0;
		int currStrSize = 1;
		int curStrCrd = 0;
		int curMaxStr = 99;
		// first we are going to tack a 1 on for every 14, since Ace are high and low.
		for (Card card: cards) {
			if (card.getValue() == 14) {
				Card newCard = new Card(1,card.getSuit());
				copyCards.add(newCard);
				copyCards.add(card);
			} else {
				copyCards.add(card);
			}
		}
		Collections.sort(copyCards,Collections.reverseOrder());
		for (Card card: copyCards) {
			// we are looking for 5 cards in a row.
			if (card.getValue() == curStrCrd) {
				//same value so skip
			} else if (card.getValue()+1 == curStrCrd) {
				currStrSize++;
				curStrCrd = card.getValue();
			} else {
				// streak broke, where are we,
				if (currStrSize > 4) {// we have a winner
					retStrTopCard = curMaxStr;
				} else {
					curMaxStr = card.getValue();
					currStrSize = 1;
					curStrCrd = card.getValue();
				}

			}
		}

		// check if the last card produced the streak.
		if (currStrSize > 4) {// we have a winner
			retStrTopCard = curMaxStr;
		} 
		return retStrTopCard;
	}

	public static int levelsOf15(int... value) {
		int retValue = 0;
		for (int c:value) {
			retValue = 15*retValue+c;
		}
		return retValue;
	}

	public int getValue() {
		return outputValue;
	}
}
