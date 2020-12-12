package spellingbee.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class HighScores {
	ArrayList<Score> scores;
	public HighScores(){
		scores = new ArrayList<Score>();
	}
	
	
	public HighScores(String p){
		scores = new ArrayList<Score>();

		File scoresText = new File(p);
		Scanner scan = null;
		try {
			scan = new Scanner(scoresText);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] playerScore = line.split(";");
			String player = playerScore[0];
			int score = Integer.parseInt(playerScore[1]);
			Score newPlayer = new Score(player, score);
			scores.add(newPlayer);
			Collections.sort(scores);
		}
	}
	
	public void addResult(String player, String score){
		int points = Integer.parseInt(score);
		Score playerScore = new Score(player, points);
		scores.add(playerScore);
		Collections.sort(scores);
	}
	
	public void saveResults(String filepath){
		for (int i = 0; i < scores.size(); i++) {
			String players = scores.get(i).getPlayer();
			String score = Integer.toString(scores.get(i).getScore());
			String playerScore = players + score + "\n";
			try {
				Files.write(Paths.get(filepath), playerScore.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getTopTen() {
		File scoresText = new File("\\scores.txt");
		String lines = "";
		Scanner scan = null;
		try {
			scan = new Scanner(scoresText);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			lines += scan.nextLine();
		}
		return lines;
		
	}
	
}
