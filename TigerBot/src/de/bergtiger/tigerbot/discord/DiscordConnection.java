package de.bergtiger.tigerbot.discord;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;

public class DiscordConnection {

	private JDA jda;
	private String token = "NDExMTM2NDUxNTMxNjM2NzM3.DV3WUg.xH72URV9THCY23W2JSEwecc3mgw";
	
	public boolean connection() {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		builder.setToken(token);
		builder.setAutoReconnect(true);
		builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
		try {
			JDA jda = builder.buildBlocking();
			this.jda = jda;
			return true;
		} catch (LoginException e) {
			System.out.println("can't login");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Interruption Error");
			e.printStackTrace();
		}
		return false;
	}
	
	public void disconnect() {
		this.jda.shutdown();
	}
	
	public JDA getJDA() {
		return this.jda;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
