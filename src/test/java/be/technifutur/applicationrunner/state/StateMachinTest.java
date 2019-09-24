package be.technifutur.applicationrunner.state;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.Collection;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import be.technifutur.applicationrunner.EmptyApplication;
import be.technifutur.applicationrunner.InteractiveApplication;

class StateMachinTest {

	@Test
	void testGetInitialState() {
		StateMachine machine = new StateMachine();
		InteractiveApplication i = machine;
		State init = machine.getInitialState();
		assertNotNull(init);
		assertSame(EmptyApplication.getInstance(), init.getApplication());		
	}
	
	@Test
	void testFinalState() {
		StateMachine machine = new StateMachine();
		State fin = machine.getFinalState();
		assertNotNull(fin);
		assertSame(EmptyApplication.getInstance(), fin.getApplication());		
	}
	
	@Test
	void testInitialState() {
		StateMachine machine = new StateMachine();
		State active = machine.getActualState();
		assertSame(machine.getInitialState(), active);		
	}
	
	@Test
	void testStartEmptyMachine() {
		StateMachine machine = new StateMachine();
		machine.start();
		State active = machine.getActualState();
		assertSame(machine.getFinalState(), active);		
	}
	
	@Test
	void testStartOneStateMachine() {
		StateMachine machine = new StateMachine();
		Collection<Transition> trList = new ArrayList<Transition>();
		Transition t2 = EasyMock.createMock(Transition.class);
		EasyMock.expect(t2.isActive()).andReturn(true);
		EasyMock.expect(t2.getDestination()).andReturn(machine.getFinalState()).anyTimes();
		
		State s = EasyMock.createMock(State.class);
		EasyMock.expect(s.getApplication()).andReturn(EmptyApplication.getInstance()).anyTimes();
		EasyMock.expect(s.getFinishTransition()).andReturn(trList).anyTimes();
		
		Transition t1 = EasyMock.createMock(Transition.class);
		EasyMock.expect(t1.isActive()).andReturn(true);
		EasyMock.expect(t1.getDestination()).andReturn(s).anyTimes();
		
		EasyMock.replay(t2,s,t1);
		machine.getInitialState().addFinishTransition(t1);
		trList.add(t2);
		machine.start();
		State active = machine.getActualState();
		assertSame(machine.getFinalState(), active);
		
	}
	
}
