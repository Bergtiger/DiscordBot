package de.bergtiger.tigerbot.spigot.player;

import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;
import net.minecraft.server.v1_12_R1.EntityPlayer;

/**
 * @author Bergtiger
 * class need to override sendMessage and permission
 * class is a fake player to perform messages and commands from discord
 *
 */
public class MyCraftPlayer1_12 extends CraftPlayer {
	
	private TigerBot plugin;
	
	public MyCraftPlayer1_12(TigerBot plugin, CraftServer server, EntityPlayer entity) {
		super(server, entity);
		this.plugin = plugin;
	}
	
	@Override
	public void sendMessage(String args) {
		this.plugin.getDiscord().getWriter().write(MyChannel.CONSOLE_CHANNEL, args, this.getName(), true);
	}

	@Override
	public void sendMessage(String[] args) {
		if(args != null) {
			for(String arg : args) {
				this.sendMessage(arg);
			}
		}
	}
	
	@Override
	public boolean hasPermission(String args) {
		return true;
	}
}
