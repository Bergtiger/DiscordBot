package de.bergtiger.tigerbot.data.question;

import org.bukkit.Bukkit;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyChannel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public abstract class Question {
	
	protected TigerBot plugin;
	protected User user;
	protected Guild guild;
	protected MessageChannel channel;
	
	public Question(TigerBot plugin, Guild guild, MessageChannel channel, User user) {
		this.plugin = plugin;
		this.guild = guild;
		this.channel = channel;
		this.user = user;
		this.abortByTime();
	}
	
	/**
	 * channel were question was asked
	 * @return
	 */
	public MessageChannel getChannel() {
		return this.channel;
	}
	
	/**
	 * user who was asked
	 * @return
	 */
	public User getUser() {
		return this.user;
	}
	
	/**
	 * abort when question need to long to answer
	 */
	private void abortByTime() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {

			@Override
			public void run() {
				remove();
			}
			
		}, MyChannel.QUESTION_DELAY.getLong());
	}
	
	
	/**
	 * removes Question from Questioner
	 */
	protected void remove() {
		this.plugin.getQuestioner().removeQuestion(this);
	}
	
	/**
	 * @param message
	 */
	public abstract void handle(String message);
	
	/**
	 * check if message is a answer for question
	 * @param message
	 * @return true when message is a answer for question
	 */
	public abstract boolean isAnswer(String message);
}
