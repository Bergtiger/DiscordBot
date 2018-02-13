package de.bergtiger.tigerbot.data;

import java.util.HashMap;

public class MyListenerOverview {
	
	private HashMap<String, MyListener> listeners = new HashMap<String, MyListener>();
	
	/**
	 * initialize your listeners (add default listener)
	 */
	protected void initialize() {}
	
	/**
	 * add the listener to overview. will not automatically start!
	 * @param listener
	 */
	public void addListener(MyListener listener) {
		this.listeners.put(listener.getClass().getSimpleName(), listener);
	}
	
	/**
	 * removes a listener from overview (also sets listener to sleep)
	 * @param listener - listener you want to remove
	 * @return true when removed
	 */
	public boolean removeListener(MyListener listener) {
		MyListener args;
		if((args = this.listeners.remove(listener)) != null) {
			args.unregister();
			return true;
		}
		return false;
	}
	
	/**
	 * set all listener to working
	 */
	public void register() {
		if((this.listeners != null) && (!this.listeners.isEmpty())) {
			this.listeners.forEach((name, listener) -> {
				listener.register();
			});
		}
	}
	
	/**
	 * set all listener to sleep
	 */
	public void unregister() {
		if((this.listeners != null) && (!this.listeners.isEmpty())) {
			this.listeners.forEach((name, listener) -> {
				listener.unregister();
			});
		}
	}
}
