package de.bergtiger.tigerbot.data;

public enum MyPermission {

	ADMIN		("tigerdiscord.admin"),
	BROADCAST	("tigerdiscord.broadcast");
	
	private String args;
	
	MyPermission(String args) {
		this.args = args;
	}
	
	public String get(){
		return this.args;
	}
}
