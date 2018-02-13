package de.bergtiger.tigerbot.spigot.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyListener;

public class SpigotListener implements MyListener, Listener {

	protected TigerBot plugin;
	protected boolean active = false;
	
	public SpigotListener(TigerBot plugin) {
		this.plugin = plugin;
	}

	@Override
	public void register() {
		if(!this.active){
			Bukkit.getPluginManager().registerEvents(this, this.plugin);
			this.plugin.getLogger().info("register Event: " + this.getClass().getSimpleName());
			this.active = true;
		} else {
			this.plugin.getLogger().info("is registered: " + this.getClass().getSimpleName());
		}
	}

	@Override
	public void unregister() {
		if(this.active) {
			HandlerList.unregisterAll(this);
			this.plugin.getLogger().info("unregister Event: " + this.getClass().getSimpleName());
			this.active = false;
		}
	}

}
