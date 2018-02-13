package de.bergtiger.tigerbot.data;

import org.bukkit.ChatColor;

public enum MyString {

	NOPERMISSION	("&cYou have no Permission to perform this command."),
	NOCONNECTION	("&cThere is no connection to a discord server."),
	NOGUILD			("&cThere is no such Guild &a(&e-guild-&a)"),
	NOROLE			("&cThere is no such Role &a(&e-role-&a)"),
	NOMEMBER		("&cThere is no such member &a(&e-member-&a)"),

	STATUS_SET		("&aBot status is now &e(&6-status-&e)"),
	STATUS_HELP		("&c/discord status -value-"),
	STATUS_VALUES	("&aStatus-Values&6: &eONLINE, IDLE, DO_NOT_DISTURB, INVISIBLE, OFFLINE, UNKNOWN"),
	
	GUILDS_HEAD		("&a----<(&6Discord Guilds&a)>----"),
	GUILDS_BODY		("&e  -guildName-&f: &e-guildID-"),
	
	ROLES_HEAD		("&a----<(&6Discord Roles &f(&e-guild-&f)&a)>----"),
	ROLES_BODY		("&e  -roleName-&f: &e-roleID-"),
	ROLES_HELP		("&7/discord roles [guildID]"),
	
	MEMBERS_HEAD	("&a----<(&6Discord Members &f(&e-guild-&f)&a)>----"),
	MEMBERS_BODY	("&e  -memberName-&f: &e-memberID-"),
	MEMBERS_HELP	("&7/discord members [guildID]"),
	
	ROLE_SET		("&aSet role &f(&e-role-&f) &a-member-"),
	ROLE_REMOVE		("&aRemoved role &f(&e-role-&f) &a-member-"),
	ROLE_HASROLE	("&e-member- &chas role &f(&e-role-&f)"),
	ROLE_NOROLE		("&e-member- &chas no role &f(&e-role-&f)"),
	ROLE_ERROR		("&cThere was an error."),
	
	UNLOCK			("&aAre you &e-member- &a? &etype &fyes&e/&fno"),
	
	QUESTION_ABORT	("&cit is too late."),
	QUESTION_ERROR	("&cThere was an error. Please retry."),
	QUESTION_UNLOCK_ACCEPTED	("&aYou have accepted."),
	QUESTION_UNLOCK_DENY		("&cYou have denied."),
	QUESTION_UNLOCK_ALREADY		("&cYou are already unlocked.");
	
	private String args;
	
	MyString(String args){
		this.args = args;
	}
	
	public String get(){
		return this.args;
	}
	
	public void set(String args){
		this.args = args;
	}
	
	public String colored(){
		return colories(this.get());
	}
	
	public static String colories(String args){
		return ChatColor.translateAlternateColorCodes('&', args);
	}
}
