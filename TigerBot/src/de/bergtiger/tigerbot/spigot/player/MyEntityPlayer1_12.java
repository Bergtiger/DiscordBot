package de.bergtiger.tigerbot.spigot.player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import net.minecraft.server.v1_12_R1.WorldServer;

/**
 * @author Bergtiger
 * class need to override sendMessage and permission
 * class is a fake player to perform messages and commands from discord
 *
 */
public class MyEntityPlayer1_12 extends EntityPlayer {

	public MyEntityPlayer1_12(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
		super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
	}

	@Override
	public void sendMessage(IChatBaseComponent iChatBaseComponent) {
		//TODO
	}
}
