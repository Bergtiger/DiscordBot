package de.bergtiger.tigerbot.spigot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;

public class PlayerCommandListener extends SpigotListener {

	public PlayerCommandListener(TigerBot plugin) {
		super(plugin);
	}

	
	/**
	 * Sends all Player commands to Discord
	 * @param e
	 */
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(!e.isCancelled()) {
			this.plugin.getDiscord().getWriter().write(MyChannel.CONSOLE_CHANNEL, e.getMessage(), e.getPlayer().getName(), true);
		}
	}
}
