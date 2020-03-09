package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCard {

	@Test
	public void test() {
		Card testCard = new Card(2,1);
		String testStr = testCard.toString();
		System.out.println(testStr);
		assertEquals(testStr, "2 of Clubs");
		testCard = new Card(1,2);
		testStr = testCard.toString();
		System.out.println(testStr);
		assertEquals(testStr, "Ace of Diamonds");
	}
}
