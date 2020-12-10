package spellingbee.client;

import spellingbee.server.*;

public class SimpleSpellingBeeGame implements ISpellingBeeGame {
	private char let1;
	private char let2;
	private char let3;
	private char centerLet4;
	private char let5;
	private char let6;
	private char let7;
	private int score = 0;
	
	public SimpleSpellingBeeGame(char let1, char let2, char let3, char let4, char let5, char let6, char let7) {
		this.let1 = 'u';
		this.let2 = 't';
		this.let3 = 'o';
		this.centerLet4 = 'n';
		this.let5 = 'l';
		this.let6 = 'i';
		this.let7 = 's';
	}
	
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
	
	@Override 
	public String getMessage(String attempt) {
		getPointsForWord(attempt);
		return "You won x points";
	}
	
	@Override
	public String getAllLetters() {
		String allLetters = "" + let1 + let2 + let3 + centerLet4 + let5 + let6 + let7;
		return allLetters;
	}
	
	@Override 
	public char getCenterLetter() {
		return centerLet4;
	}
	
	@Override
	public int getScore() {
		return score;
	}
	
	@Override 
	public int[] getBrackets() {
		int[] thresolds = {50, 100, 150, 180, 200};
		return thresolds;
	}
}
