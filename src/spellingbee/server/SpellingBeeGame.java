package spellingbee.server;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * This class is the true spellingBee game where the answer of the user is verified and where the other class get
 * their information such as the score of the user, the message as a reply for the word and the brackets for the game.
 * @author Jose Carlos Betancourt, Sergio Arturo Segrega, Nael Louis
 *
 * Sergio: I really enjoyed this project! It was fairly challenging and I found debugging with teammates sorta fun!
 */
public class SpellingBeeGame implements ISpellingBeeGame {
	private String letters;
	private Set<String> foundWords = new HashSet<String>();
	private Set<String> possibleWords;
	// Generate brackets in constructor for performance
	private int[] brackets;
	private int score;
	private char centerLet;
	
	/**
	 * Unparameterized constructor that call method to initialize the field letters, possibleWords, and
	 * centerLet
	 */
	public SpellingBeeGame() {
		this.letters = createLettersCombination();
		this.centerLet = setCenterLetter();
		this.possibleWords = findPossibleWordForCombination(createWordsFromFile());
		this.foundWords.add("");
		this.brackets = createBrackets();
	}
	
	/**
	 * Overloaded constructor that uses a predetermined 7 letters for the game instead of generating it randomly
	 * 
	 * @param letters
	 */
	public SpellingBeeGame(String letters) {
		this.letters = letters;
		this.centerLet = setCenterLetter();
		this.possibleWords = findPossibleWordForCombination(createWordsFromFile());
		this.foundWords.add("");
		this.brackets = createBrackets();
	}
	
	/**
	 * Goes through the file of combination and randomly assign one to a String
	 * @return String comb
	 */
	private String createLettersCombination() {
		Random random = new Random();
		List<String> lines = new ArrayList<String>();
		try {
			Path p = Paths.get("letterCombinations.txt");
			lines = Files.readAllLines(p);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		String comb = lines.get(random.nextInt(lines.size()));
		return comb;
	}
	
	/**
	 * Goes through the English text file and put them into a list then put them into a hashSet.
	 * @return HashSet list.
	 */
	private Set<String> createWordsFromFile() {
		Set<String> list = new HashSet<String>();
		try {
			Path p = Paths.get("english.txt");
			List<String> lines = Files.readAllLines(p);
			for (String s : lines) {
				list.add(s.toLowerCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Check if the word is a word of the list of possible words for the combination given to the user. 
	 * Update the score.
	 * getter method for the number of points the user get for the word.
	 * @param attempt
	 * @return int
	 */
	public int getPointsForWord(String attempt) {
		int wordPoints = 0;
		if (attempt.contains(Character.toString(this.centerLet))) {
			if (isNotAlreadyFound(attempt)) {
				if (isValid(attempt)) {
					if (containAll(attempt)) {
						wordPoints = attempt.length() + 7;
						return wordPoints;
					}
					if (attempt.length() == 4) {
						wordPoints = 1;
						return wordPoints;
					}
					if (attempt.length() > 4) {
						wordPoints = attempt.length();
						return wordPoints;
					}
				}
			}
		}
		return wordPoints;
	} 
	
	/**
	 * Verifies if the 7 letters of the combination are used
	 * @param attempt
	 * @return boolean. false if the 7 letters are not used, true if they are.
	 */
	private boolean containAll(String attempt) {
		String wordLet = "";
		for (int i = 0; i < attempt.length(); i++) {
			//add the character to the wordLet if it isn't already in.
			 if (!(wordLet.contains(Character.toString(attempt.charAt(i))))) {
			 	wordLet += attempt.charAt(i);
			 }
		}
		//If the seven letters are not used return false
		if (wordLet.length() < this.letters.length()) {
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
		int points = getPointsForWord(attempt);
		this.score += points;
		if (points == 0) {
			if (attempt.length() < 4) {
				return "not bigger than 3";
			}
			if (!(attempt.contains(Character.toString(this.centerLet)))) {
				return "Need center letter";
			}
			return "Not valid";
		}
		this.foundWords.add(attempt);
		return "Perfect!";
	}
	
	/**
	 * Override the method getAllLetters from ISpellingBeeGame interface
	 * @return String letters
	 */
	@Override
	public String getAllLetters() {
		return this.letters;
	}
	
	/**
	 * Override the method getCenterLetters from ISpellingBeeGame interface
	 * getter method for centerLet
	 * @return char
	 */
	@Override
	public char getCenterLetter() {
		return this.centerLet;
	}
	
	/**
	 * setter method for centerLet
	 * @return char
	 */
	private char setCenterLetter() {
		Random ran = new Random();
		return this.letters.charAt(ran.nextInt(this.letters.length()));
	}
	
	/**
	 * Override the method getScore from ISpellingBeeGame interface
	 * getter method for score
	 * @return int
	 */
	@Override
	public int getScore() {
		return this.score;
	}
	
	/**
	 * createBrackets() generates the bracket scores at the construction of the object,
	 * this is a performance function that serves to not have to generate brackets on each getBracket() call.
	 * @return int array
	 */
	public int[] createBrackets() {
		int[] brackets = new int[5];
		int total = 0;
		for (String word : this.possibleWords) {
			total += getPointsForWord(word);
		}
		brackets[0] = (int)Math.round(total * 0.25);
		brackets[1] = (int)Math.round(total * 0.5);
		brackets[2] = (int)Math.round(total * 0.75);
		brackets[3] = (int)Math.round(total * 0.9);
		brackets[4] = total;
		
		return brackets;
	}
	
	/**
	 * Override the method getBrackets from ISpellingBeeGame interface
	 * getter method for brackets
	 * @return int array
	 */
	@Override
	public int[] getBrackets() {
		return this.brackets;
	}
	
	/**
	 * Look through the list of word and select the words possible for the combination of letter we get.
	 * @return Set<String> containing all the possible word for the letters we get.
	 */
	private Set<String> findPossibleWordForCombination(Set<String> words) {
		Set<String> possibleWordForCombination = new HashSet<String>();
		//Go through all the word of the file
		for (String word : words) {
			//if the word contain the center letter and its length is greater than 4.
			//The words containing less than four letters give 0 point so no point in adding them.
			if (word.length() >= 4) {
				if (word.contains(Character.toString(this.centerLet))) {
					int tracker = 0;
					//add to the tracker if our string of letters contain a character from the word 
					for (int i = 0; i < word.length(); i++) {
						if (this.letters.contains(Character.toString(word.charAt(i)))) {
						tracker++;
						}
					}
					//if letters contain all the characters of the word, add the word to the possibleCombination
					if (word.length() == tracker) {
						possibleWordForCombination.add(word);
					}
				}
			}
		}
		return possibleWordForCombination;
	}
	
	/**
	 * Goes through the file of possible combination with the letters given to the user.
	 * @param attempt. The word entered by the user.
	 * @return a boolean. True if the word the user entered is a word of the list, false if not.
	 */
	private boolean isValid(String attempt) {
		for (String word : this.possibleWords) {
			if (attempt.equals(word)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Goes through the foundWords arraylist and return false if the attempts is equal to a word
	 * inside the list, return true if the word has not already been found.
	 * @param attempt
	 * @return boolean
	 */
	private boolean isNotAlreadyFound(String attempt) {
		for (String s : this.foundWords) {
			if (attempt.equals(s)) {
				return false;
			}
		}
		return true;
	}

}
