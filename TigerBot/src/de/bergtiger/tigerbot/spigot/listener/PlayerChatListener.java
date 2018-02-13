package de.bergtiger.tigerbot.spigot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;

public class PlayerChatListener extends SpigotListener {
	
	public PlayerChatListener(TigerBot plugin) {
		super(plugin);
	}

	/**
	 * Sends all Player messages to Discord
	 * @param e
	 */
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(!e.isCancelled()) {
			if(!e.getPlayer().getName().contains(MyChannel.DISCORD_USER.get())) {
				this.plugin.getDiscord().getWriter().write(MyChannel.CONSOLE_CHANNEL, e.getMessage(), e.getPlayer().getName(), true);
			}
		}
	}
}
