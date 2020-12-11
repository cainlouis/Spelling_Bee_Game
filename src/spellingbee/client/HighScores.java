package spellingbee.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HighScores {
	ArrayList<Score> scores;
	HighScores(){
		scores = new ArrayList<String>();
	}
	
	HighScores(Path p){
		
	}
	
	public void addResult(String player, String score){
		//parse score
		Score playerScore = new Score(player, score);
		scores.addAll(playerScore);
	}
	
	public void saveResults(String filepath) {
		
	}
	
	public String getTopTen() {
		
	}
	
}
