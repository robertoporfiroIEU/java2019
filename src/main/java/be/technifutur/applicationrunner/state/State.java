package be.technifutur.applicationrunner.state;

import java.util.ArrayList;
import java.util.Collection;

import be.technifutur.applicationrunner.InteractiveApplication;


public class State{

	private InteractiveApplication application;
	private ArrayList<Transition> finishList = new ArrayList<Transition>();

	public void setApplication(InteractiveApplication application) {
		this.application = application;

	}

	public InteractiveApplication getApplication() {
		return this.application;
	}

	public void addFinishTransition(Transition transition) {
		this.finishList.add(transition);
	}

	public Collection<Transition> getFinishTransition() {
		return finishList;
	}

}
