package be.technifutur.applicationrunner;

import be.technifutur.applicationrunner.state.State;
import be.technifutur.applicationrunner.state.StateMachine;
import be.technifutur.applicationrunner.state.Transition;

public class MainPoubelle {

	public static void main(String[] args) {
		InteractiveRunner runner = new InteractiveRunner();
		StateMachine stateMachine = new StateMachine();
		Cafe cafe = new Cafe();
		
		Choix choix = new Choix();
		choix.model = cafe;
		State state1 = new State();
		state1.setApplication(choix);
		stateMachine.getInitialState().addFinishTransition(new Transition(null, null, state1));
		
		Sucre sucre = new Sucre();
		sucre.model = cafe;
		State state2 = new State();
		state2.setApplication(sucre);
		state1.addFinishTransition(new Transition(null, null, state2));
		state2.addFinishTransition(new Transition(null, null, stateMachine.getFinalState()));
		
		
		runner.setApplication(stateMachine);
		runner.run();

	}

}

class Cafe {
	int sucre;
	boolean lait;
}

class Sucre implements InteractiveApplication{

	Cafe model;
	boolean finish;

	@Override
	public void start() {
		model.sucre = 0;
		finish = false;
	}

	@Override
	public StringBuilder getScreen() {
		return new StringBuilder("Combien de sucres voulez-vous ?");
	}

	@Override
	public boolean isFinish() {
		return finish;
	}

	@Override
	public void newInput(String input) {
		if(input.matches("[0-9]+")) {
			model.sucre = Integer.parseInt(input);
			stop();
		}
	}

	@Override
	public void stop() {
		this.finish = true;
	}

	@Override
	public boolean hasLastSreen() {
		// TODO Auto-generated method stub
		return false;
	}}
class Choix implements InteractiveApplication{

	Cafe model;
	boolean finish;
	@Override
	public void start() {
		model.lait = false;
		finish = false;
	}

	@Override
	public StringBuilder getScreen() {
		return new StringBuilder("voulez vous du lait (o/n)");
	}

	@Override
	public boolean isFinish() {
		return finish;
	}

	@Override
	public void newInput(String input) {
		if(input.matches("[on]")) {
			model.lait = "o".contentEquals(input);
			stop();
		}
	}

	@Override
	public void stop() {
		finish = true;
	}

	@Override
	public boolean hasLastSreen() {
		// TODO Auto-generated method stub
		return false;
	}
	
}