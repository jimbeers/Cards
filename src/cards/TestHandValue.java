package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestHandValue {
	@Test
	public void testTopCard() {
		System.out.println("test top card");
		int x = (((((((14*15) + 13)*15) + 9)*15)+7)*15)+6;
		List<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card(9,1));
		cards2.add(new Card(7,2));
		cards2.add(new Card(3,1));
		cards2.add(new Card(1,2));
		cards2.add(new Card(6,3));
		cards2.add(new Card(4,1));
		cards2.add(new Card(13,1));
		// 13 9 7 6 4 
		HandValue v = new HandValue(cards2);
		System.out.println("X = " + x);
		assertEquals(x,v.getValue());
	}
	
	@Test
	public void testStraight() {
		System.out.println("test Straight");
		List<Card> cards2 = new ArrayList<Card>();
		List<Card> cards1 = new ArrayList<Card>();
		cards1.add(new Card(9,1));
		cards1.add(new Card(11,2));
		cards1.add(new Card(4,1));
		cards1.add(new Card(10,2));
		cards1.add(new Card(12,3));
		cards1.add(new Card(1,1));
		cards1.add(new Card(13,1));
		
		HandValue v1 = new HandValue(cards1);
		cards2.add(new Card(5,1));
		cards2.add(new Card(11,2));
		cards2.add(new Card(4,1));
		cards2.add(new Card(1,2));
		cards2.add(new Card(2,3));
		cards2.add(new Card(1,1));
		cards2.add(new Card(3,1));
		
		HandValue v2 = new HandValue(cards2);
		assertEquals(4000014,v1.getValue());
		assertEquals(4000005,v2.getValue());
	}
	
	@Test
	public void testFullHouse() {
		System.out.println("test top card");
		List<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card(9,3));
		cards2.add(new Card(9,2));
		cards2.add(new Card(9,1));
		cards2.add(new Card(13,2));
		cards2.add(new Card(13,3));
		cards2.add(new Card(8,1));
		cards2.add(new Card(13,1));
		
		HandValue v = new HandValue(cards2);
		int x=6000000+(13*15)+9;
		assertEquals(x,v.getValue());
	}
	
	@Test
	public void testFlush() {
		System.out.println("test flush");
		List<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card(9,1));
		cards2.add(new Card(4,1));
		cards2.add(new Card(7,1));
		cards2.add(new Card(13,2));
		cards2.add(new Card(2,1));
		cards2.add(new Card(8,1));
		cards2.add(new Card(13,1));
		
		HandValue v = new HandValue(cards2);
		int x=5000000+(((((((13*15)+9)*15)+8)*15)+7)*15)+4; //5690409
		
		assertEquals(x,v.getValue());
	}
	
	@Test
	public void testTwoPair() {
		System.out.println("test Two Pair");
		List<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card(9,1));
		cards2.add(new Card(4,1));
		cards2.add(new Card(4,2));
		cards2.add(new Card(8,2));
		cards2.add(new Card(10,4));
		cards2.add(new Card(8,3));
		cards2.add(new Card(11,2));
		int x=2000000+(((8*15)+4)*15)+11; //2001871
		HandValue v = new HandValue(cards2);
		assertEquals(x,v.getValue()); //2001869
	}
	@Test
	public void testOnePair() {
		System.out.println("test One Pair");
		List<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card(9,1));
		cards2.add(new Card(4,1));
		cards2.add(new Card(5,2));
		cards2.add(new Card(8,2));
		cards2.add(new Card(10,3));
		cards2.add(new Card(8,3));
		cards2.add(new Card(11,4));
		
		HandValue v = new HandValue(cards2);
		int x=1000000+(((((8*15)+11)*15)+10)*15)+9; //1029634
		assertEquals(x,v.getValue());  //1027984
	}
	
	@Test
	public void testFourOfAKind() {
		System.out.println("test 4 of a kind");
		List<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card(9,1));
		cards2.add(new Card(9,3));
		cards2.add(new Card(9,2));
		cards2.add(new Card(9,4));
		cards2.add(new Card(10,3));
		cards2.add(new Card(8,3));
		cards2.add(new Card(11,4));
		
		HandValue v = new HandValue(cards2);
		int x=7000000+(9*15)+11; //
		System.out.println(x);
		assertEquals(x,v.getValue());  
	}
	@Test
	public void testStrFlush() {
		System.out.println("test straight flush");
		List<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card(9,4));
		cards2.add(new Card(9,3));
		cards2.add(new Card(12,4));
		cards2.add(new Card(7,3));
		cards2.add(new Card(10,4));
		cards2.add(new Card(8,4));
		cards2.add(new Card(11,4));
		
		HandValue v = new HandValue(cards2);
		int x=8000000+12; //
		System.out.println(x);
		assertEquals(x,v.getValue());  
	}

}
