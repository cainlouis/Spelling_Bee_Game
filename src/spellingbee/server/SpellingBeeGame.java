package spellingbee.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		// TODO Auto-generated method stub
		return 0;
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
