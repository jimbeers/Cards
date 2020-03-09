package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDeck {


	@Test
	public void testDeck() {
		Deck d = new Deck();
		Card c = d.get(1);		
		d.remove(new Card(5,1));
		d.remove(new Card(6,1));
		
		assertEquals(50, d.deckSize());
		
		d.remove(new Card(5,2));
		assertEquals(49, d.deckSize());
	}

}
