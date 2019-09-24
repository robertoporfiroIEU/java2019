package be.technifutur.applicationrunner;

public class InteractiveRunner implements Runnable {

	private InteractiveApplication application;
	private Environnement env = new ConsoleEnvironnement();

	public Environnement getEnv() {
		return env;
	}

	public void setEnv(Environnement env) {
		if (env == null) {
			throw new NullEnvironnementException();
		}
		this.env = env;
	}

	@Override
	public void run() {
		if (this.application != null) {
			application.start();
			while (!application.isFinish()) {
				env.print(application.getScreen().toString());
				application.newInput(env.nextLine());
			}
			if (application.hasLastSreen()) {
				env.print(application.getScreen().toString());
			}
			application.stop();
		}
	}

	public InteractiveApplication getApplication() {
		return application;
	}

	public void setApplication(InteractiveApplication application) {
		this.application = application;
	}

}
