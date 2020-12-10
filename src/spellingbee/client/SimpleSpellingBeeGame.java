package spellingbee.client;

import spellingbee.server.*;

/**
 * This class is a draft class for SpellingBeeGame
 * @author Nael Louis
 *
 */
public class SimpleSpellingBeeGame implements ISpellingBeeGame {
	private char let1;
	private char let2;
	private char let3;
	private char centerLet4;
	private char let5;
	private char let6;
	private char let7;
	private int score = 0;
	
	/**
	 * Unparameterized constructor.
	 */
	public SimpleSpellingBeeGame() {
		this.let1 = 'u';
		this.let2 = 't';
		this.let3 = 'o';
		this.centerLet4 = 'n';
		this.let5 = 'l';
		this.let6 = 'i';
		this.let7 = 's';
	}
	
	/**
	 * Check if the word is a word of the list of possible words for the combination given to the user. 
	 * Update the score.
	 * getter method for the number of points the user get for the word.
	 * @param attempt
	 * @return int
	 */
	@Override 
	public int getPointsForWord(String attempt) {
		int wordPoints = 0;
		if (attempt.contains(Character.toString(centerLet4))) {
			if (attempt.length() == 4) {
				wordPoints = 1;
				this.score += wordPoints;
				return wordPoints;
			}
			if (attempt.length() > 4) {
				wordPoints = attempt.length();
				this.score += wordPoints;
				return wordPoints;
			}
			if (containAll(attempt)) {
				wordPoints = attempt.length() + 7;
				this.score += wordPoints;
				return wordPoints;
			}
		}
		return 0;
	}
	
	/**
	 * Verifies if the 7 letters of the combination are used
	 * @param attempt
	 * @return boolean. false if the 7 letters are not used, true if they are.
	 */
	private boolean containAll(String attempt) {
		String letters = "" + let1 + let2 + let3 + centerLet4 + let5 + let6 + let7;
		String wordLet = "";
		for (int i = 0; i < attempt.length(); i++) {
			//add the character to the wordLet if it isn't already in.
			 if (!(wordLet.contains(Character.toString(attempt.charAt(i))))) {
			 	wordLet += attempt.charAt(i);
			 }
		}
		//If the seven letters are not used return false
		if (wordLet.length() < letters.length()) {
			 return false;
		 }
		return true;
	}
	
	/**
	 * Override the method getMessage from ISpellingBeeGame
	 * getter method for the message
	 * @return String
	 */
	@Override 
	public String getMessage(String attempt) {
		getPointsForWord(attempt);
		return "You won x points";
	}
	
	/**
	 * Override the method getAllLetters from ISpellingBeeGame interface
	 * @return String letters
	 */
	@Override
	public String getAllLetters() {
		String allLetters = "" + let1 + let2 + let3 + centerLet4 + let5 + let6 + let7;
		return allLetters;
	}
	
	/**
	 * Override the method getCenterLetters from ISpellingBeeGame interface
	 * getter method for centerLet
	 * @return char
	 */
	@Override 
	public char getCenterLetter() {
		return centerLet4;
	}
	
	/**
	 * Override the method getScore from ISpellingBeeGame interface
	 * getter method for score
	 * @return int
	 */
	@Override
	public int getScore() {
		return score;
	}
	
	/**
	 * Override the method getBrackets from ISpellingBeeGame interface
	 * getter method for brackets
	 * @return int array
	 */
	@Override 
	public int[] getBrackets() {
		int[] thresolds = {50, 100, 150, 180, 200};
		return thresolds;
	}
}
