package be.technifutur.applicationrunner.state;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import be.technifutur.applicationrunner.InteractiveApplication;

class StateTest {

	@Test
	void testStateApplicationHaveAInteractiveApplication() {
		State state = new State();
		InteractiveApplication mock = EasyMock.createMock(InteractiveApplication.class);
		state.setApplication(mock);
		assertSame(mock, state.getApplication());
	}

	@Test
	void testAddFinishTransition() {
		State application = new State();
		Transition transition = new Transition();
		application.addFinishTransition(transition);
		assertTrue(application.getFinishTransition().contains(transition));
	}
}
