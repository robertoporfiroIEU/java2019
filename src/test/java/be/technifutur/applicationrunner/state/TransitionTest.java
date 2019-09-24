package be.technifutur.applicationrunner.state;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

class TransitionTest {

	private String t;
	@Test
	void test() {
		Transition transition = new Transition(()->true, ()-> {t="toto";},null);
		
		assertTrue(transition.isActive());
		assertNull(t);
		transition.run();
		assertEquals("toto", t);
	}

}
