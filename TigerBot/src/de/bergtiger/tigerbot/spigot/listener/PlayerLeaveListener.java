package de.bergtiger.tigerbot.spigot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;

public class PlayerLeaveListener extends SpigotListener {

	public PlayerLeaveListener(TigerBot plugin) {
		super(plugin);
	}

	/**
	 * Sends Player kick messages to Discord
	 * @param e
	 */
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		this.plugin.getDiscord().getWriter().write(MyChannel.CONSOLE_CHANNEL, e.getLeaveMessage(), e.getPlayer().getName(), true);
	}
	
	/**
	 * Sends Player quit messages to Discord
	 * @param e
	 */
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		this.plugin.getDiscord().getWriter().write(MyChannel.CONSOLE_CHANNEL, e.getQuitMessage(), e.getPlayer().getName(), true);
	}
}
