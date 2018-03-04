package de.bergtiger.tigerbot.ki;

import javax.annotation.CheckReturnValue;

public enum KIString {
	GET_WELL		(new String[] {"Get well",
									"wish for a better recovery",
									"Good improvement for you"
									}),
	GREETING		(new String[] {"Hello, nice to meet you",
									"Welcome :) ",
									"Hi, how are you?",
									}),
	ILLNESS			(new String[] {"sick", "headache", "ill", "stomach"}),
	JOKES			(new String[] {	"What do you call a bee that can't make up its mind? A Maybe",
									"My boyfriend told me to stop acting like a flamingo. So I had to put my foot down."}),
	FEELING_GOOD	(new String[] {	"I'm fine.",
									"I feel great."}),
	FEELING_BAD		(new String[] {	"I feel bad.",
									"I do not want to talk about it."}),
	SCREAM			(new String[] {	"Don't shoud at me.",
									"I do not reply to that.",
									"I feel bad when you tell me loud"}),
	NAME 			(new String[] { "I'm there. Do you want to say with me? :) ",
									"Sorry! I felt asleep, tell me whatever",
									"So, where are you? I'm waiting you all time :D",
									"Welcome back!"});
	
	String[] args;
	
	KIString(String[] args) {
		this.args = args;
	}
	/**
	 * check that message is equals of a String on list. 
	 * if equals, return true, 
	 * else false
	 * 
	 * @param message answer of user
	 * @param list KIString_name
	 * @return
	 */
	public boolean checkAnswer(String message, String[] list) {
		for(int i=0; i<list.length; i++) {
			if(message.contains(list[i]))
				return true;
		}
		return false;
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
