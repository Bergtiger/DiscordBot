package de.bergtiger.tigerbot.spigot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerCommandEvent;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;

public class ServerCommandListener extends SpigotListener {

	public ServerCommandListener(TigerBot plugin) {
		super(plugin);
	}
	
	/**
	 * Sends all Console commands to Discord
	 * @param e
	 */
	@EventHandler
	public void onCommand(ServerCommandEvent e) {
		if(!e.isCancelled()) {
			this.plugin.getDiscord().getWriter().write(MyChannel.CONSOLE_CHANNEL, e.getCommand(), MyChannel.CONSOLE_USER.get(), true);
		}
	}
}
