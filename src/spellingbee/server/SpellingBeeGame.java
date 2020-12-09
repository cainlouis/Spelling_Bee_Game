package spellingbee.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
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
	
	SpellingBeeGame() {
		Random random = new Random();
		Set<String> letterCombinations = createWordsFromFile("\\data\\letterCombinations.txt");
		int target = random.nextInt(letterCombinations.size());
		int i = 0;
		for (String letters : letterCombinations) {
			if (i == target) {
				this.letters = letters;
				break;
			}
			i++;
		}
		// TODO: For performance purposes make possibleWords only actual possible words
		this.possibleWords = createWordsFromFile("\\data\\english.txt");
	}
	
	SpellingBeeGame(String letters) {
		this.letters = letters;
		// TODO: For performance purposes make possibleWords only actual possible words
		this.possibleWords = createWordsFromFile("\\data\\english.txt");
	}
	
	private Set<String> createWordsFromFile(String path) {
		Set<String> list = new HashSet<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				list.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int getPointsForWord(String attempt) {
		// If the attempt is smaller than 4 letters return 0;
		if (attempt.length() < 4 ) {
			return 0;
		}
		
		// If it doesn't contain center letter, return 0
		if (!attempt.contains("" + getCenterLetter())) {
			return 0;
		}
		
		int score = 0;
		for (int i = 0; i < attempt.length(); i++) {
			if (this.letters.contains("" + attempt.charAt(i))) {
				score++;
			} else {
				// If letters does not contain a letter in the attempt just return 0
				return 0;
			}
		}
		score = (score <= 4) ? 1 : score;
		
		// If attempt length is smaller than 7 it cannot be a panagram, therefore, return score
		if (attempt.length() < 7) {
			return score;
		}
		
		char[] sortedAttempt = attempt.toCharArray();
		char[] sortedLetters = this.letters.toCharArray();
		Arrays.sort(sortedAttempt);
		Arrays.sort(sortedLetters);
		// TODO: Add clause if attempt uses all the letters but also has extra (Ex: some doubles)
		// String -> Remove duplicates -> char[]
		// This will currently work with attempts that have exactly the same 7 letters
		if (sortedAttempt.equals(sortedLetters)) {
			score += 7;
		}
		
		return score;
	}
	
	/*
	  public int getPointsForWord(String attempt) {
		int wordPoints = 0;
		if (attempt.contains(Character.toString(getCenterLetter()))) {
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
	
	public boolean containAll(String attempt) {
		for (int i = 0; i < attempt.length(); i++) {
			if (!(letters.contains(Character.toString(attempt.charAt(i))))) {
				return false;
			}
		}
		return true;
	} */

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
		return this.letters.charAt(0);
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
				if (word.contains(Character.toString(getCenterLetter()))) {
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

}
