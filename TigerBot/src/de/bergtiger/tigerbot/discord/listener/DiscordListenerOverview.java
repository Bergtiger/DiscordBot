package de.bergtiger.tigerbot.discord.listener;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyListenerOverview;

public class DiscordListenerOverview extends MyListenerOverview{

	private TigerBot plugin;
	
	public DiscordListenerOverview(TigerBot plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void initialize() {
		this.addListener(new ChatListener(this.plugin));
	}
}
