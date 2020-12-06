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
		return 50;
	}
	
	@Override 
	public String getMessage(String attempt) {
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
		return 5;
	}
	
	@Override 
	public int[] getBrackets() {
		int[] thresolds = {50, 100, 150, 180, 200};
		return thresolds;
	}
}
