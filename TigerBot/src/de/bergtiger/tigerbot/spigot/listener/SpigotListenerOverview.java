package de.bergtiger.tigerbot.spigot.listener;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyListenerOverview;

public class SpigotListenerOverview extends MyListenerOverview {

	private TigerBot plugin;
	
	public SpigotListenerOverview(TigerBot plugin) {
		this.plugin = plugin;
	}
	
	@Override
	protected void initialize() {
		this.addListener(new PlayerChatListener(this.plugin));
		this.addListener(new PlayerCommandListener(this.plugin));
		this.addListener(new PlayerJoinListener(this.plugin));
		this.addListener(new PlayerLeaveListener(this.plugin));
		this.addListener(new ServerCommandListener(this.plugin));
	}
}
