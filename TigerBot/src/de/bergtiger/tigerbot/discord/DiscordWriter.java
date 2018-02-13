package de.bergtiger.tigerbot.discord;

import java.util.Calendar;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDA.Status;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class DiscordWriter {

	private TigerBot plugin;
	private String timeFormat = "[HH:MM:SS dd.mm.yyyy] ";
	
	public DiscordWriter(TigerBot plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * set new time format
	 * @param format
	 */
	public void setTimeFormat(String format) {
		this.timeFormat = format;
	}
	
	/**
	 * @param channel - MessageChannel you want to write
	 * @param message - message to write
	 */
	public void write(MessageChannel channel, String message) {
		JDA jda = this.plugin.getJDA();
		if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
			if(channel != null) {
				MessageAction reaction = channel.sendMessage(this.removeColorCodes(message));
				reaction.complete();
			}
		}
	}
	
	/**
	 * @param channel - MessageChannel you want to write
	 * @param message - message to write
	 * @param user - name of user
	 * @param useTime - time prefix
	 */
	public void write(MessageChannel channel, String message, String user, boolean useTime) {
		message = user + ": " + message;
		if(useTime) message = this.getTime(timeFormat) + message;
		this.write(channel, message);
	}
	
	/**
	 * @param channel
	 * @param message
	 * @param user
	 * @param useTime
	 */
	public void write(MyChannel channel, String message, String user, boolean useTime) {
		this.write(this.getChannel(channel.get()), message, user, useTime);
	}
	
	/**
	 * if you have the channelID this will get the channel
	 * @param channelID
	 * @return
	 */
	private MessageChannel getChannel(String channelID) {
		JDA jda = this.plugin.getJDA();
		if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
			return jda.getTextChannelById(channelID);
		}
		return null;
	}
	
	/**
	 * build time prefix
	 * @param format - format to create string
	 * @return
	 */
	private String getTime(String format) {
		Calendar calendar = Calendar.getInstance();
		return format.replace("HH", Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)))
					 .replace("MM", Integer.toString(calendar.get(Calendar.MINUTE)))
					 .replace("SS", Integer.toString(calendar.get(Calendar.SECOND)))
					 .replace("dd", Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)))
					 .replace("mm", Integer.toString(calendar.get(Calendar.MONTH) + 1))
					 .replace("yyyy", Integer.toString(calendar.get(Calendar.YEAR)));
	}
	
	/**
	 * discord has no chatcolorcodes, removing these codes
	 * @param message
	 * @return
	 */
	private String removeColorCodes(String message) {
		return message.replaceAll("§0", "")
					  .replaceAll("§1", "")
					  .replaceAll("§2", "")
					  .replaceAll("§3", "")
					  .replaceAll("§4", "")
					  .replaceAll("§5", "")
					  .replaceAll("§6", "")
					  .replaceAll("§7", "")
					  .replaceAll("§8", "")
					  .replaceAll("§9", "")
					  .replaceAll("§a", "")
					  .replaceAll("§b", "")
					  .replaceAll("§c", "")
					  .replaceAll("§d", "")
					  .replaceAll("§e", "")
					  .replaceAll("§f", "")
					  .replaceAll("§k", "")
					  .replaceAll("§l", "")
					  .replaceAll("§m", "")
					  .replaceAll("§n", "")
					  .replaceAll("§o", "");
	}
}
