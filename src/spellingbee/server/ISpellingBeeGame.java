package spellingbee.server;

public interface ISpellingBeeGame {
	/**
	 * This message should return the number of points that a given word is worth according to the Spelling Bee rules.
	 * @param attempt
	 * @return an int as points.
	 */
	public int getPointsForWord(String attempt);
	
	/**
	 * This method should check if the word attempt is a valid word or not according to the Spelling Bee rules. 
	 * It should return a message based on the reason it is rejected or a positive message (e.g. “good” or “great”) if it is a valid word.
	 * @param attempt
	 * @return a string as a response.
	 */
	public String getMessage(String attempt);
	
	/**
	 * This method should return the set of 7 letters (as a String) that the spelling bee object is storing
	 * @return a string containing the 7 letters.
	 */
	public String getAllLetters();
	
	/**
	 * This method should return the center character. That is, the character that is required to be part of every word.
	 * @return a char as the center letter
	 */
	public char getCenterLetter();
	
	/**
	 * This method should return the current score of the user
	 * @return an integer as the score of the user
	 */
	public int getScore();
	
	/**
	 * This method will be used in the gui to determine the various point categories.
	 * @return return an array containing all thresolds for the game.
	 */
	public int[] getBrackets();
}
