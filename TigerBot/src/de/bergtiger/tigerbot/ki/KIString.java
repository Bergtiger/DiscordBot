package de.bergtiger.tigerbot.ki;

import javax.annotation.CheckReturnValue;

public enum KIString {

	JOKES			(new String[] {	"What do you call a bee that can't make up its mind? A Maybe",
									"My boyfriend told me to stop acting like a flamingo. So I had to put my foot down."}),
	FEELING_GOOD	(new String[] {	"I'm fine.",
									"I feel great."}),
	FEELING_BAD		(new String[] {	"I feel bad.",
									"I do not want to talk about it."}),
	SCREAM			(new String[] {	"Don't shoud at me.",
									"I do not reply to that."});
	
	String[] args;
	
	KIString(String[] args) {
		this.args = args;
	}
	
	/**
	 * get all
	 * @return String array
	 */
	public String[] get() {
		return this.args;
	}
	
	/**
	 * get one
	 * @param i
	 * @return null when i to big
	 */
	@CheckReturnValue
	public String get(int i) {
		try {
			return this.args[i];
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param i
	 * @return
	 */
	public String getMod(int i) {
		return this.args[(i % this.args.length)];
	}
	
	/**
	 * get one random
	 * @return
	 */
	public String getRandom() {
		return this.args[(int)(Math.random() * this.args.length)];
	}
}
