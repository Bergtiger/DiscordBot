package de.bergtiger.tigerbot.data.question;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyString;
import de.bergtiger.tigerbot.discord.DiscordWriter;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;

public class UnLockQuestion extends Question {
	
	public UnLockQuestion(TigerBot plugin, Guild guild, MessageChannel channel, User user) {
		super(plugin, guild, channel, user);
	}

	@Override
	public void handle(String message) {
		DiscordWriter writer = this.plugin.getDiscord().getWriter();
		switch (message.toLowerCase()) {
			case "yes"	: case "y"	: case "accept"	: {
					String roleName = "Spieler";
					Role role = this.plugin.getDiscord().getRole().getRole(this.guild, roleName);
					if(role != null) {
						if(!this.guild.getMember(this.user).getRoles().contains(role)) {
							if(this.plugin.getDiscord().getRole().setRole(this.guild, role, this.guild.getMember(this.user))) {
								writer.write(this.channel, MyString.QUESTION_UNLOCK_ACCEPTED.colored());
							} else {
								writer.write(this.channel, MyString.QUESTION_ERROR.colored());
							}
						} else {
							writer.write(this.channel, MyString.ROLE_HASROLE.colored().replace("-role-", roleName).replace("-member-", user.getName()));
						}
					} else {
						writer.write(this.channel, MyString.NOROLE.colored().replace("-role-", roleName));
					}
				} break;
			case "no"	: case "n"	: case "deny"	: {
					writer.write(this.channel, MyString.QUESTION_UNLOCK_DENY.colored());
				}
		}
	}

	@Override
	public boolean isAnswer(String message) {
		switch (message.toLowerCase()) {
			case "yes"	: case "y"	: case "accept"	:
			case "no"	: case "n"	: case "deny"	: return true;
		}
		return false;
	}

}
