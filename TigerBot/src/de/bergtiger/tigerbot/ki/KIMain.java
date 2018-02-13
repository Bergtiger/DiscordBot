package de.bergtiger.tigerbot.ki;

import de.bergtiger.tigerbot.TigerBot;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class KIMain {
	
	private TigerBot plugin;
	
	public KIMain(TigerBot plugin) {
		this.plugin = plugin;
	}
	
	public void receiveMessage(MessageChannel channel, User user, String message) {
		if(this.isAllUpper(message)) {
			this.sendMessage(channel, KIString.SCREAM.getRandom());
			return;
		}
		message = message.toLowerCase();
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
	}
	
	public void sendMessage(MessageChannel channel, String message) {
		this.plugin.getDiscord().getWriter().write(channel, message);
	}
	
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
