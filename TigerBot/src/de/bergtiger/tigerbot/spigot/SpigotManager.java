package de.bergtiger.tigerbot.spigot;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.spigot.listener.SpigotListenerOverview;

public class SpigotManager {
	
	private SpigotWriter spigotWriter;
	private SpigotListenerOverview spigotListenerOverview;
	
	public SpigotManager(TigerBot plugin) {
		this.spigotWriter = new SpigotWriter(plugin);
		this.spigotListenerOverview = new SpigotListenerOverview(plugin);
	}
	
	/**
	 * evrything about writing in Spigot
	 * @return
	 */
	public SpigotWriter getWriter() {
		return this.spigotWriter;
	}
	
	/**
	 * spigot Listeners
	 * @return
	 */
	public SpigotListenerOverview getListener() {
		return this.spigotListenerOverview;
	}

}
