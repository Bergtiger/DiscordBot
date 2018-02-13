package de.bergtiger.tigerbot.spigot.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyPermission;
import de.bergtiger.tigerbot.data.MyString;
import de.bergtiger.tigerbot.data.question.UnLockQuestion;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDA.Status;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.requests.RestAction;

public class Commands implements CommandExecutor{
	
	private TigerBot plugin;
	
	public Commands(TigerBot plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(args.length >= 1) {
			switch(args[0].toLowerCase()) {
				case "status" : this.setDiscordStatus(cs, args); break;
				case "guilds" : this.showGuilds(cs); break;
				case "roles" : this.showRoles(cs, args); break;
				case "members" : this.showMembers(cs, args); break;
			
				case "roleset" : this.setDiscordRole(cs, args); break;
				case "roleremove" : this.removeDiscordRole(cs, args); break;
				
				case "unlock" : this.unLock(cs, args); break;
				case "help" : this.sendHelp(cs); break;
				default: this.sendHelp(cs);
			}
		} else {
			this.sendHelp(cs);
		}
		return true;
	}
	
	public void sendHelp(CommandSender cs) {
		
	}
	
	public void unLock(CommandSender cs, String[] args) {
		if(cs.hasPermission(MyPermission.ADMIN.get())) {
			if(args.length == 3) {
				JDA jda = this.plugin.getJDA();
				if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
					Guild guild = null;
					try {
						guild = jda.getGuildById(args[1]);
					} catch (Exception e) {
						List<Guild> guilds = jda.getGuilds();
						for (int i = 0; i < guilds.size(); i++) {
							if(guilds.get(i).getName().equalsIgnoreCase(args[1])) {
								guild = guilds.get(i);
								break;
							}
						}
					}
					if(guild != null) {
						Member member = null;
						try {
							member = guild.getMemberById(args[2]);
						} catch (Exception e) {
							List<Member> members = guild.getMembers();
							for (int i = 0; i < members.size(); i++) {
								if(members.get(i).getUser().getName().equalsIgnoreCase(args[2])) {
									member = members.get(i);
								}
							}
						}
						if(member != null) {
							RestAction<PrivateChannel> reaction = member.getUser().openPrivateChannel();
							PrivateChannel channel = reaction.complete();
							this.plugin.getDiscord().getWriter().write(channel, MyString.UNLOCK_Question.colored().replace("-member-", cs.getName()));
							this.plugin.getQuestioner().addQuestion(new UnLockQuestion(this.plugin, guild, channel, member.getUser()));
						} else {
							cs.sendMessage(MyString.NOMEMBER.colored().replace("-member-", args[2]));
						}
					} else {
						cs.sendMessage(MyString.NOGUILD.colored().replace("-guild-", args[1]));
					}
				} else {
					cs.sendMessage(MyString.NOCONNECTION.colored());
				}
			} else {
				cs.sendMessage(MyString.UNLOCK_HELP.colored());
			}
		} else {
			cs.sendMessage(MyString.NOPERMISSION.colored());
		}
	}
	
	public void showGuilds(CommandSender cs) {
		if(cs.hasPermission(MyPermission.ADMIN.get())) {
			JDA jda = this.plugin.getJDA();
			if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
				cs.sendMessage(MyString.GUILDS_HEAD.colored());
				for(Guild guild : jda.getGuilds()) {
					cs.sendMessage(MyString.GUILDS_BODY.colored().replace("-guildName-", guild.getName()).replace("-guildID-", guild.getId()));
				}
			} else {
				cs.sendMessage(MyString.NOCONNECTION.colored());
			}
		} else {
			cs.sendMessage(MyString.NOPERMISSION.colored());
		}
	}
	
	public void showRoles(CommandSender cs, String[] args) {
		if(cs.hasPermission(MyPermission.ADMIN.get())) {
			JDA jda = this.plugin.getJDA();
			if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
				if(args.length == 2) {
					Guild guild = null;
					try {
						guild = jda.getGuildById(args[1]);
					} catch (Exception e) {
						List<Guild> guilds = jda.getGuilds();
						for (int i = 0; i < guilds.size(); i++) {
							if(guilds.get(i).getName().equalsIgnoreCase(args[1])) {
								guild = guilds.get(i);
								break;
							}
						}
					}
					if(guild != null) {
						cs.sendMessage(MyString.ROLES_HEAD.colored().replace("-guild-", guild.getName()));
						for(Role role : guild.getRoles()) {
							cs.sendMessage(MyString.ROLES_BODY.colored().replace("-roleName-", role.getName()).replace("-roleID-", role.getId()));
						}
					} else {
						cs.sendMessage(MyString.NOGUILD.colored());
					}
				} else {
					cs.sendMessage(MyString.ROLES_HELP.colored());
				}
			} else {
				cs.sendMessage(MyString.NOCONNECTION.colored());
			}
		} else {
			cs.sendMessage(MyString.NOPERMISSION.colored());
		}
	}
	
	public void showMembers(CommandSender cs, String[] args) {
		if(cs.hasPermission(MyPermission.ADMIN.get())) {
			JDA jda = this.plugin.getJDA();
			if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
				if(args.length == 2) {
					Guild guild = null;
					try {
						guild = jda.getGuildById(args[1]);
					} catch (Exception e) {
						List<Guild> guilds = jda.getGuilds();
						for (int i = 0; i < guilds.size(); i++) {
							if(guilds.get(i).getName().equalsIgnoreCase(args[1])) {
								guild = guilds.get(i);
								break;
							}
						}
					}
					if(guild != null) {
						cs.sendMessage(MyString.MEMBERS_HEAD.colored().replace("-guild-", guild.getName()));
						for(Member member : guild.getMembers()) {
							cs.sendMessage(MyString.MEMBERS_BODY.colored().replace("-memberName-", member.getUser().getName()).replace("-memberID-", member.getUser().getId()));
						}
					} else {
						cs.sendMessage(MyString.NOGUILD.colored());
					}
				} else {
					cs.sendMessage(MyString.MEMBERS_HELP.colored());
				}
			} else {
				cs.sendMessage(MyString.NOCONNECTION.colored());
			}
		} else {
			cs.sendMessage(MyString.NOPERMISSION.colored());
		}
	}
	
	/**
	 * discord setrole [guild] [role] [player]
	 * @param cs - CommandSender
	 * @param args - 
	 */
	private void setDiscordRole(CommandSender cs, String[] args) {
		if(cs.hasPermission(MyPermission.ADMIN.get())) {
			JDA jda = this.plugin.getJDA();
			if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
				Guild guild = null;
				try {
					guild = jda.getGuildById(args[1]);
				} catch (Exception e) {
					List<Guild> guilds = jda.getGuilds();
					for (int i = 0; i < guilds.size(); i++) {
						if(guilds.get(i).getName().equalsIgnoreCase(args[1])) {
							guild = guilds.get(i);
							break;
						}
					}
				}
				if(guild != null) {
					Role role = null;
					try {
						role = guild.getRoleById(args[2]);
					} catch (Exception e) {
						List<Role> roles = guild.getRoles();
						for (int i = 0; i < roles.size(); i++) {
							if(roles.get(i).getName().equalsIgnoreCase(args[2])) {
								role = roles.get(i);
								break;
							}
						}
					}
					if(role != null) {
						Member member = null;
						try {
							member = guild.getMemberById(args[3]);
						} catch (Exception e) {
							List<Member> members = guild.getMembers();
							for (int i = 0; i < members.size(); i++) {
								if(members.get(i).getUser().getName().equalsIgnoreCase(args[3])) {
									member = members.get(i);
								}
							}
						}
						if(member != null) {
							List<Role> roles = member.getRoles();
							for (int i = 0; i < roles.size(); i++) {
								if(roles.get(i).getIdLong() == role.getIdLong()) {
									cs.sendMessage(MyString.ROLE_HASROLE.colored().replace("-role-", role.getName()).replace("-member-", member.getUser().getName()));
									return;
								}
							}
							if(this.plugin.getDiscord().getRole().setRole(guild, role, member)) {
								cs.sendMessage(MyString.ROLE_SET.colored().replace("-role-", role.getName()).replace("-member-", member.getUser().getName()));
							} else {
								cs.sendMessage(MyString.ROLE_ERROR.colored());
							}
						} else {
							cs.sendMessage(MyString.NOMEMBER.colored().replace("-member-", args[3]));
						}
					} else {
						cs.sendMessage(MyString.NOROLE.colored().replace("-role-", args[2]));
					}
				} else {
					cs.sendMessage(MyString.NOGUILD.colored().replace("-guild-", args[1]));
				}
			} else {
				cs.sendMessage(MyString.NOCONNECTION.colored());
			}
		} else {
			cs.sendMessage(MyString.NOPERMISSION.colored());
		}
	}
	
	private void removeDiscordRole(CommandSender cs, String[] args) {
		if(cs.hasPermission(MyPermission.ADMIN.get())) {
			JDA jda = this.plugin.getJDA();
			if((jda != null) && (jda.getStatus().equals(Status.CONNECTED))) {
				Guild guild = null;
				try {
					guild = jda.getGuildById(args[1]);
				} catch (Exception e) {
					List<Guild> guilds = jda.getGuilds();
					for (int i = 0; i < guilds.size(); i++) {
						if(guilds.get(i).getName().equalsIgnoreCase(args[1])) {
							guild = guilds.get(i);
							break;
						}
					}
				}
				if(guild != null) {
					Role role = null;
					try {
						role = guild.getRoleById(args[2]);
					} catch (Exception e) {
						List<Role> roles = guild.getRoles();
						for (int i = 0; i < roles.size(); i++) {
							if(roles.get(i).getName().equalsIgnoreCase(args[2])) {
								role = roles.get(i);
								break;
							}
						}
					}
					if(role != null) {
						Member member = null;
						try {
							member = guild.getMemberById(args[3]);
						} catch (Exception e) {
							List<Member> members = guild.getMembers();
							for (int i = 0; i < members.size(); i++) {
								if(members.get(i).getUser().getName().equalsIgnoreCase(args[3])) {
									member = members.get(i);
								}
							}
						}
						if(member != null) {
							List<Role> roles = member.getRoles();
							for (int i = 0; i < roles.size(); i++) {
								if(roles.get(i).getIdLong() == role.getIdLong()) {
									if(this.plugin.getDiscord().getRole().removeRole(guild, role, member)) {
										cs.sendMessage(MyString.ROLE_REMOVE.colored().replace("-role-", role.getName()).replace("-member-", member.getUser().getName()));
									} else {
										cs.sendMessage(MyString.ROLE_ERROR.colored());
									}
									return;
								}
							}
							cs.sendMessage(MyString.ROLE_NOROLE.colored().replace("-role-", role.getName()).replace("-member-", member.getUser().getName()));
						} else {
							cs.sendMessage(MyString.NOMEMBER.colored().replace("-member-", args[3]));
						}
					} else {
						cs.sendMessage(MyString.NOROLE.colored().replace("-role-", args[2]));
					}
				} else {
					cs.sendMessage(MyString.NOGUILD.colored().replace("-guild-", args[1]));
				}
			} else {
				cs.sendMessage(MyString.NOCONNECTION.colored());
			}
		} else {
			cs.sendMessage(MyString.NOPERMISSION.colored());
		}
	}

	private void setDiscordStatus(CommandSender cs, String[] args) {
		if(cs.hasPermission(MyPermission.ADMIN.get())) {
			JDA jda = this.plugin.getJDA();
			if(jda != null) {
				if(args.length == 2) {
					try {
						jda.getPresence().setPresence(OnlineStatus.valueOf(args[1].toUpperCase()), true);
						cs.sendMessage(MyString.STATUS_SET.colored().replace("-status-", args[1]));
					} catch (Exception e) {
						cs.sendMessage(MyString.STATUS_VALUES.colored());
					}
				} else {
					cs.sendMessage(MyString.STATUS_HELP.colored());
				}
			} else {
				cs.sendMessage(MyString.NOCONNECTION.colored());
			}
		} else {
			cs.sendMessage(MyString.NOPERMISSION.colored());
		}
	}
}
