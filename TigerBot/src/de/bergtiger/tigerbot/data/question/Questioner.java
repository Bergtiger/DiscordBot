package de.bergtiger.tigerbot.data.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Questioner {

	/**
	 * List of Questions
	 */
	private HashMap<Long, List<Question>> questions = new HashMap<Long, List<Question>>();

	/**
	 * check if message was answer to question
	 * @param user - user who send messahe
	 * @param channel - channel where message was send
	 * @param message
	 * @return true when message was answer
	 */
	public boolean receivedAnswer(User user, MessageChannel channel, String message) {
		if((this.questions != null) && (!this.questions.isEmpty())) {
			if(this.questions.containsKey(user.getIdLong())) {
				List<Question> questions = this.questions.get(user.getIdLong());
				for(Question question : questions) {
					if(question.getChannel().getIdLong() == channel.getIdLong()) {
						if(question.isAnswer(message)) {
							question.handle(message);
							question.remove();
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @param question - question you want add
	 */
	public void addQuestion(Question question) {
		if((this.questions != null) && (!this.questions.isEmpty())) {
			List<Question> questions = null;
			if(this.questions.containsKey(question.getUser().getIdLong())) {
				questions = this.questions.get(question.getUser().getIdLong());
			} else {
				questions = new ArrayList<Question>();
			}
			questions.add(question);
			this.questions.put(question.getUser().getIdLong(), questions);
		}
	}
	
	/**
	 * @param question - question you want remove
	 * @return true when removed
	 */
	public boolean removeQuestion(Question question) {
		if((this.questions != null) && (!this.questions.isEmpty())) {
			if(this.questions.containsKey(question.getUser().getIdLong())) {
				List<Question> questions = this.questions.get(question.getUser().getIdLong());
				if((questions != null) && (!questions.isEmpty())) {
					questions.remove(question);
					return true;
				}
				if(questions.size() > 0) {
					this.questions.put(question.getUser().getIdLong(), questions);
				} else {
					this.questions.remove(question.getUser().getIdLong());
				}
			}
		}
		return false;
	}
}
