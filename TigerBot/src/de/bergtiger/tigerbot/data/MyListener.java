package de.bergtiger.tigerbot.data;

public interface MyListener {
	
	/**
	 * sets the listener to working
	 */
	public void register();
	
	/**
	 * sets the listener to sleep
	 */
	public void unregister();
}
