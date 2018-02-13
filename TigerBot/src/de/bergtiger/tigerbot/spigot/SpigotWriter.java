package de.bergtiger.tigerbot.spigot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.spigot.player.MyPlayer;

public class SpigotWriter {

	private TigerBot plugin;
	private HashMap<String, Player> players = new HashMap<String, Player>();
	
	public SpigotWriter(TigerBot plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * sorts if message is a command or a chatmessage
	 * if fakeplayer doesn't exist in cach it creates a new fakeplayer
	 * @param playerName
	 * @param message
	 */
	public void writeMessage(String playerName, String message) {
		Player p = null;
		if(this.players.containsKey(playerName)) {
			p = this.players.get(playerName);
		} else {
			p = new MyPlayer().getMyPlayer(this.plugin, playerName);
			this.players.put(playerName, p);
		}
		if(p != null) {
			if(message.startsWith("/")) {
				this.sendCommand(p, message);
			} else {
				this.sendMessage(p, message);
			}
		}
	}
	
	/**
	 * sends a chatevent. when not canceld sends message to all onlineplayers
	 * @param p
	 * @param message
	 */
	private void sendMessage(Player p, String message) {
		Set<Player> players = new HashSet<Player>();
		for(Player player: Bukkit.getOnlinePlayers()) {
			players.add(player);
		}
		AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(true, p, message, players);
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) {
			return;
		}
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(String.format(event.getFormat(), event.getPlayer().getName(), event.getMessage()));
		}
	}
	
	/**
	 * Perform a command
	 * @param p
	 * @param message
	 */
	private void sendCommand(Player p, String message) {
		Bukkit.getServer().dispatchCommand(p, message.substring(1));
	}
}
