package be.technifutur.applicationrunner.mvc;

public interface Vue<T> {

	void start();

	StringBuilder getScreen();

	void stop();

	void setModel(T model);

}
