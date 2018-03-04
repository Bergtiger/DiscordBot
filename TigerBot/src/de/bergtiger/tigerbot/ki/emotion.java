/**
 * 
 */
package de.bergtiger.tigerbot.ki;

import java.util.ArrayList;

/**
 * @author Minh Tam Truong
 * 
 * description of Emotion. A list of words for Emotions and a list for reactions
 *
 */
public class emotion {
	private String em;
	private ArrayList<String> emAdj;
	private ArrayList<String> react;
	/**
	 * Construction
	 * 
	 * @param em name of emotion
	 * 
	 */
	public emotion(String em) {
		this.em = em;
		this.emAdj = new ArrayList<String>();
		this.react = new ArrayList<String>();
	}
	/**
	 * set a emotional Adj in List
	 * @param adj
	 */
	public void addAdj(String adj) {
		emAdj.add(adj);
	}
	/**
	 * return a list of emotion words 
	 * @return emAdj
	 */
	public ArrayList<String> getEmAdj(){
		return emAdj;
	}
	/**
	 * set a reaction in List
	 * @param reaction
	 */
	public void addReact(String reaction) {
		react.add(reaction);
	}
	/**
	 * return a list of reactions 
	 * 
	 * @return react
	 */
	public ArrayList<String> getRections(){
		return react;
	}
}
