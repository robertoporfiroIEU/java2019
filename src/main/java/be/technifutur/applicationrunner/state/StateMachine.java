package be.technifutur.applicationrunner.state;

import java.util.Optional;

import be.technifutur.applicationrunner.EmptyApplication;
import be.technifutur.applicationrunner.InteractiveApplication;

public class StateMachine implements InteractiveApplication {

	private static final State finalState = StateMachine.getPseudoState();
	private State initialState = StateMachine.getPseudoState();
	private State activeState = initialState;

	private static State getPseudoState() {
		State s = new State();
		s.setApplication(EmptyApplication.getInstance());
		return s;
	}

	@Override
	public void start() {
		this.activeState = initialState;
		this.updateState();
	}

	private void updateState() {
		State next = this.getActualState();
		if (next.getApplication().isFinish() && next != finalState) {
			Optional<Transition> t = this.getActualState().getFinishTransition().stream().filter((e) -> e.isActive())
					.findFirst();
			if (t.isEmpty() || t.get().getDestination() == null) { // TODO attention transition interne
				next = finalState;
			} else {
				next = t.get().getDestination();
			}
			setActiveState(next);
		}
	}

	private void setActiveState(State destination) {
		this.activeState.getApplication().stop();
		this.activeState = destination;
		if (!isFinish()) {
			this.activeState.getApplication().start();
			updateState();
		}
	}

	@Override
	public StringBuilder getScreen() {
		return getActiveState().getApplication().getScreen();
	}

	@Override
	public boolean isFinish() {
		return this.getActiveState() == StateMachine.finalState;
	}

	@Override
	public void newInput(String input) {
		this.getActiveState().getApplication().newInput(input);
	}

	private State getActiveState() {
		this.updateState();
		return this.activeState;
	}

	@Override
	public void stop() {
		this.getActiveState().getApplication().stop();
	}

	@Override
	public boolean hasLastSreen() {
		return this.getActiveState().getApplication().hasLastSreen();
	}

	public State getInitialState() {
		return this.initialState;
	}

	public State getFinalState() {
		return finalState;
	}

	public State getActualState() {
		return this.activeState;
	}

}
