package de.bergtiger.tigerbot.spigot.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.spigot.player.MyEntityPlayer1_12;
import de.bergtiger.tigerbot.spigot.player.MyPlayer;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import net.minecraft.server.v1_12_R1.WorldServer;

public class MyPlayer {
	
	/**
	 * get a costum player
	 * @param plugin
	 * @param playerName
	 * @return Player
	 */
	public Player getMyPlayer(TigerBot plugin, String playerName) {
		
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
	
	
		EntityPlayer entityPlayer = new MyEntityPlayer1_12(server, world, new GameProfile(UUID.randomUUID(), playerName), new PlayerInteractManager(world));
	
		return (Player) new MyCraftPlayer1_12(plugin, (CraftServer)Bukkit.getServer(), entityPlayer);
	}
	
}
