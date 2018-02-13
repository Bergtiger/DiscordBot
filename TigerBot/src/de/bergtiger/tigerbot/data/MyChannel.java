package de.bergtiger.tigerbot.data;

public enum MyChannel {
	
	CONSOLE_CHANNEL	("411584164136222721"),		//channel where console output will written
	CONSOLE_USER	("Console"),				//name of your console
	DISCORD_USER	("@Discord"),				//suffix of discord users on server
	QUESTION_DELAY	(Long.toString(1000*60*10));//seconds till question will end listening (millis*seconds*minute)
	
	String args;
	
	MyChannel(String args) {
		this.args = args;
	}
	
	/**
	 * get the value of MyChannel
	 * @return String
	 */
	public String get() {
		return this.args;
	}
	
	public long getLong() {
		try {
			return Long.valueOf(args);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * set the value of MyChannel
	 * @param args - new String
	 */
	public void set(String args) {
		this.args = args;
	}
}
