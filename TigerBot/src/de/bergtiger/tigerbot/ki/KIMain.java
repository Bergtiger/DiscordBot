package de.bergtiger.tigerbot.ki;

import de.bergtiger.tigerbot.TigerBot;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class KIMain {
	
	private TigerBot plugin;
	
	public KIMain(TigerBot plugin) {
		this.plugin = plugin;
	}
	/**
	 * receive a Message and try to answer
	 * @param channel
	 * @param user
	 * @param message
	 */
	public void receiveMessage(MessageChannel channel, User user, String message) {
		if(this.isAllUpper(message)) {
			this.sendMessage(channel, KIString.SCREAM.getRandom());
			return;
		}
		message = message.toLowerCase();
		if(message.contains("hallo") || message.contains("hi") || message.contains("hello")) {
			this.sendMessage(channel, KIString.GREETING.getRandom());
		}
		//if answer good is
		if(message.contains(KIString.FEELING_GOOD.get(0)) || message.contains(KIString.FEELING_GOOD.get(1))) {
			this.sendMessage(channel, "me too");
		}
		//if man bad feels
		if(KIString.FEELING_BAD.checkAnswer(message, KIString.FEELING_BAD.get()) || KIString.ILLNESS.checkAnswer(message, KIString.ILLNESS.get())) {
			this.sendMessage(channel, KIString.GET_WELL.getRandom());
		}
		if(message.contains("joke")) {
			this.sendMessage(channel, KIString.JOKES.getRandom());
		}
		if(message.contains("how are you")) {
			long ping = this.plugin.getJDA().getPing();
			if(ping < 200) {
				this.sendMessage(channel, KIString.FEELING_GOOD.getRandom());
			} else {
				this.sendMessage(channel, KIString.FEELING_BAD.getRandom());
			}
		}
		if(message.contains("where are you")) {
			this.sendMessage(channel, KIString.NAME.getRandom());
		}
	}
	/**
	 * send Message to a chanel
	 * @param channel
	 * @param message
	 */
	public void sendMessage(MessageChannel channel, String message) {
		this.plugin.getDiscord().getWriter().write(channel, message);
	}
	/**
	 * check that Message is written all upper case
	 * @param s message
	 * @return
	 */
	private boolean isAllUpper(String s) {
	    for(char c : s.toCharArray()) {
	       if(Character.isLetter(c) && Character.isLowerCase(c)) {
	           return false;
	        }
	    }
	    return true;
	}
	
	public TigerBot getTigerBot() {
		return this.plugin;
	}
}
