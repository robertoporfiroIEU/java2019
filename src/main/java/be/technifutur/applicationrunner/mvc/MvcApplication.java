package be.technifutur.applicationrunner.mvc;

import be.technifutur.applicationrunner.InteractiveApplication;

public class MvcApplication<T> implements InteractiveApplication {

	private Controler<T> ctrl;
	private Vue<T> vue;
	private T model;

	@Override
	public void start() {
		this.ctrl.setModel(this.model);
		this.vue.setModel(this.model);
		this.ctrl.start();
		this.vue.start();

	}

	public Controler<T> getCtrl() {
		return ctrl;
	}

	public void setCtrl(Controler<T> ctrl) {
		this.ctrl = ctrl;
	}

	public Vue<T> getVue() {
		return vue;
	}

	public void setVue(Vue<T> vue) {
		this.vue = vue;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	@Override
	public StringBuilder getScreen() {
		return this.vue.getScreen();
	}

	@Override
	public boolean isFinish() {
		return this.ctrl.isFinish();
	}

	@Override
	public void newInput(String input) {
		this.ctrl.newInput(input);
	}

	@Override
	public void stop() {
		this.ctrl.stop();
		this.vue.stop();

	}

	@Override
	public boolean hasLastSreen() {
		return this.ctrl.hasLastSreen();
	}

}
