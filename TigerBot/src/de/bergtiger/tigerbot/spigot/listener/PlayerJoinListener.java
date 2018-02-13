package de.bergtiger.tigerbot.spigot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;

public class PlayerJoinListener extends SpigotListener {

	public PlayerJoinListener(TigerBot plugin) {
		super(plugin);
	}

	/**
	 * Sends Player join message to Discord
	 * @param e
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		this.plugin.getDiscord().getWriter().write(MyChannel.CONSOLE_CHANNEL, e.getJoinMessage(), e.getPlayer().getName(), true);
	}
}
