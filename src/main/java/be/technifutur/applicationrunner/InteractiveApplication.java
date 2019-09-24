package be.technifutur.applicationrunner;


public interface InteractiveApplication {

	void start();

	StringBuilder getScreen();
	
	boolean isFinish();

	void newInput(String input);

	void stop();

	boolean hasLastSreen();

}