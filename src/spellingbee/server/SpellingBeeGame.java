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

public class SpellingBeeGame implements ISpellingBeeGame {
	// [0] = Center letter
	private String letters;
	private Set<String> foundWords;
	private Set<String> possibleWords;
	// Generate brackets in constructor for performance
	private int[] brackets;
	private int score;
	private char centerLet;
	
	SpellingBeeGame() {
		this.letters = createLettersCombination();
		this.possibleWords = createWordsFromFile();
		this.centerLet = getCenterLetter();
	}
	
	SpellingBeeGame(String letters) {
		this.letters = letters;
		this.possibleWords = createWordsFromFile();
		this.centerLet = getCenterLetter();
	}
	
	private String createLettersCombination() {
		Random random = new Random();
		List<String> lines = new ArrayList<String>();
		try {
			Path p = Paths.get("\\data\\letterCombinations.txt");
			lines = Files.readAllLines(p);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		String comb = lines.get(random.nextInt(lines.size()));
		return comb;
	}
	
	private Set<String> createWordsFromFile() {
		Set<String> list = new HashSet<String>();
		try {
			Path p = Paths.get("\\data\\english.txt");
			List<String> lines = Files.readAllLines(p);
			for (String s : lines) {
				list.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//TO-DO: check if the word is a word from the list of possibleWordForCombination
	public int getPointsForWord(String attempt) {
		int wordPoints = 0;
		if (attempt.contains(Character.toString(this.centerLet))) {
			if (isValid(attempt)) {
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
		}
		return 0;
	} 
	
	//Gotta change it to make sure the 7 letters are used
	//Not a panaram
	public boolean containAll(String attempt) {
		for (int i = 0; i < attempt.length(); i++) {
			if (!(letters.contains(Character.toString(attempt.charAt(i))))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getMessage(String attempt) {
		int points = getPointsForWord(attempt);

		if (points == 0) {
			// TODO: Bad reasons
			// Smaller than 4 chars =>
			// Doesn't contain center letter =>
			// Contains letters that aren't valid
			return "bad";
		}
		
		this.score += points;
		return "good";
	}

	@Override
	public String getAllLetters() {
		return this.letters;
	}

	@Override
	public char getCenterLetter() {
		Random ran = new Random();
		return this.letters.charAt(ran.nextInt(this.letters.length()));
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public int[] getBrackets() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Look through the list of word and select the words possible for the combination of letter we get.
	 * @return Set<String> containing all the possible word for the letters we get.
	 */
	private Set<String> findPossibleWordForCombination() {
		Set<String> possibleWordForCombination = new HashSet<String>();
		//Go through all the word of the file
		for (String word : this.possibleWords) {
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
	
	private boolean isValid(String attempt) {
		Set<String> possibleWordForCombination = findPossibleWordForCombination();
		for (String word : possibleWordForCombination) {
			if (attempt.equals(word)) {
				return true;
			}
		}
		return false;
	}

}
