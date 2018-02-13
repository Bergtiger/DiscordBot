package de.bergtiger.tigerbot;

import org.bukkit.plugin.java.JavaPlugin;

import de.bergtiger.tigerbot.discord.DiscordManager;
import de.bergtiger.tigerbot.spigot.SpigotManager;
import net.dv8tion.jda.core.JDA;

public class TigerBot extends JavaPlugin {
	
	private DiscordManager discordManager;
	private SpigotManager spigotManager;
	
	@Override
	public void onEnable() {
		this.discordManager = new DiscordManager(this);
		this.spigotManager = new SpigotManager(this);
		
		if(this.getDiscord().getConnection().connection()) {
			this.getDiscord().getListener().register();
			this.getSpigot().getListener().register();
		}
	}
	
	@Override
	public void onDisable() {
		this.getSpigot().getListener().unregister();
		this.getDiscord().getListener().unregister();
		this.getDiscord().getConnection().disconnect();
	}
	
	public JDA getJDA() {
		return this.getDiscord().getConnection().getJDA();
	}
	
	public DiscordManager getDiscord() {
		return this.discordManager;
	}
	
	public SpigotManager getSpigot() {
		return this.spigotManager;
	}
}
