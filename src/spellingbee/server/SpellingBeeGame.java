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

	@Override
	public String getMessage(String attempt) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getBrackets() {
		// TODO Auto-generated method stub
		return null;
	}

}
