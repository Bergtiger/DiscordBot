package de.bergtiger.tigerbot.discord;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.discord.listener.DiscordListenerOverview;

public class DiscordManager {
	
	private DiscordWriter discordWriter;
	private DiscordRole discordRole;
	private DiscordConnection discordConnection;
	private DiscordListenerOverview discordListenerOverview;
	
	public DiscordManager(TigerBot plugin) {
		this.discordConnection = new DiscordConnection();
		this.discordWriter = new DiscordWriter(plugin);
		this.discordRole = new DiscordRole(plugin);
		this.discordListenerOverview = new DiscordListenerOverview(plugin);
	}
	
	/**
	 * evrything about writing in discord
	 * @return DiscordWriter
	 */
	public DiscordWriter getWriter() {
		return this.discordWriter;
	}
	
	/**
	 * evrything about roles from discord
	 * @return DiscordRole
	 */
	public DiscordRole getRole() {
		return this.discordRole;
	}
	
	/**
	 * connection to discord
	 * @return DiscordConnection
	 */
	public DiscordConnection getConnection() {
		return this.discordConnection;
	}
	
	/**
	 * discord listeners
	 * @return Listeners
	 */
	public DiscordListenerOverview getListener() {
		return this.discordListenerOverview;
	}
}
