package be.technifutur.applicationrunner;

public class EmptyApplication implements InteractiveApplication {

	private static InteractiveApplication instance;

	private EmptyApplication() {
	}

	public static InteractiveApplication getInstance() {
		if (instance == null) {
			instance = new EmptyApplication();
		}
		return instance;
	}

	@Override
	public void start() {}

	@Override
	public StringBuilder getScreen() {
		return new StringBuilder();
	}

	@Override
	public boolean isFinish() {
		return true;
	}

	@Override
	public void newInput(String input) {}

	@Override
	public void stop() {}

	@Override
	public boolean hasLastSreen() {
		return false;
	}

}
