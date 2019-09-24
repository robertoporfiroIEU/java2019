package be.technifutur.applicationrunner.state;

import java.util.function.BooleanSupplier;

public class Transition {

	private BooleanSupplier condition;
	private Runnable action;
	private State destination;

	public boolean isActive() {
		return condition == null ? true : condition.getAsBoolean();
	}

	public void run() {
		if (action != null)
			action.run();
	}

	public State getDestination() {
		return this.destination;
	}

	public Transition() {
		// TODO Auto-generated constructor stub
	}

	public Transition(BooleanSupplier condition, Runnable action, State destination) {
		super();
		this.condition = condition;
		this.action = action;
		this.destination = destination;
	}

}
