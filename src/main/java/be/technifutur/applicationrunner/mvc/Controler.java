package be.technifutur.applicationrunner.mvc;

public interface Controler<T> {

	void start();

	boolean isFinish();

	void newInput(String input);

	void stop();

	boolean hasLastSreen();

	void setModel(T model);

}
