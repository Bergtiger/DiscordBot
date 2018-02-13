package de.bergtiger.tigerbot.discord;

import java.util.List;

import de.bergtiger.tigerbot.TigerBot;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDA.Status;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction;

public class DiscordRole {

	private TigerBot plugin;
	
	public DiscordRole(TigerBot plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * adds a role to a discord user
	 * @param guild
	 * @param role
	 * @param member
	 * @return
	 */
	public boolean setRole(Guild guild, Role role, Member member) {
		JDA jda = this.plugin.getJDA();
		if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
			if((guild != null) && (role != null) && (member != null)) {
				AuditableRestAction<Void> reaction = guild.getController().addSingleRoleToMember(member, role);
				reaction.queue();
				return true;
			}
		} else {
			this.plugin.getLogger().info("not Connected");
		}
		return false;
	}
	
	/**
	 * removes a role from a discord user
	 * @param guild
	 * @param role
	 * @param member
	 * @return
	 */
	public boolean removeRole(Guild guild, Role role, Member member) {
		JDA jda = this.plugin.getJDA();
		if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
			AuditableRestAction<Void> reaction = guild.getController().removeSingleRoleFromMember(member, role);
			reaction.queue();
			return true;
		} else {
			this.plugin.getLogger().info("not Connected");
		}
		return false;
	}
	
	/**
	 * get role by ID or Name from guild
	 * @param guild - guild where the role should be
	 * @param roleName - name or id of role
	 * @return
	 */
	public Role getRole(Guild guild, String roleName) {
		if(guild != null) {
			try {
				return guild.getRoleById(roleName);
			} catch (Exception e) {
				List<Role> roles = guild.getRoles();
				for(int i = 0;  i < roles.size(); i++) {
					if(roles.get(i).getName().equalsIgnoreCase(roleName)) {
						return roles.get(i);
					}
				}
			}
		}
		return null;
	}
}
