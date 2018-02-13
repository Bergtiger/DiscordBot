package de.bergtiger.tigerbot.discord.listener;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ChatListener extends DiscordListener {

	public ChatListener(TigerBot plugin) {
		super(plugin);
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if(!e.getAuthor().isBot()) {
			/**
			 * send to spigot server
			 */
			if(e.getChannel().getId().equals(MyChannel.CONSOLE_CHANNEL.get())) {
				this.plugin.getSpigot().getWriter().writeMessage(e.getAuthor().getName() + MyChannel.DISCORD_USER.get(), e.getMessage().getContentDisplay());
				return;
			}
			/**
			 * check for question
			 */
			if(e.getChannelType().equals(ChannelType.PRIVATE)) {
				this.plugin.getQuestioner().receivedAnswer(e.getAuthor(), e.getChannel(), e.getMessage().getContentDisplay());
				this.plugin.getKI().receiveMessage(e.getChannel(), e.getAuthor(), e.getMessage().getContentDisplay());
			}
		}
	}
}
