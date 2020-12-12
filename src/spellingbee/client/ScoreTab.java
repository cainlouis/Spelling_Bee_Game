package spellingbee.client;

import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spellingbee.network.Client;

public class ScoreTab extends Tab {
	private Client client;
	private String currentScore;
	private int[] bracketsScores;
	private Text[] scoreNames;
	private Text score;
	
	ScoreTab(Client client) {
		super("Score");
		this.client = client;
		String[] brackets = this.client.sendAndWaitMessage("getBrackets").split(",");
		this.bracketsScores = new int[5];
		for (int i = 0; i < 5; i++) {
			bracketsScores[i] = Integer.parseInt(brackets[i]);
		}
		
		this.scoreNames = new Text[5];
		
		this.scoreNames[0] = new Text("Queen Bee");
		this.scoreNames[1] = new Text("Genius");
		this.scoreNames[2] = new Text("Amazing");
		this.scoreNames[3] = new Text("Good");
		this.scoreNames[4] = new Text("Getting Started");
		
		Text[] scores = new Text[5];
		scores[0] = new Text(brackets[4]);
		scores[1] = new Text(brackets[3]);
		scores[2] = new Text(brackets[2]);
		scores[3] = new Text(brackets[1]);
		scores[4] = new Text(brackets[0]);
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		
		for (int i = 0; i < 5; i++) {
			scoreNames[i].setFill(Color.GREY);
			gridPane.add(this.scoreNames[i], 0, i, 1, 1);
			gridPane.add(scores[i], 1, i, 1, 1);
		}
		
		Text scoreText = new Text("Current Score");
		this.score = new Text(this.currentScore);
		gridPane.add(scoreText, 0, 5, 1, 1);
		gridPane.add(score, 1, 5, 1, 1);
		
		this.setContent(gridPane);
		this.refresh();
	}
	
	public void refresh() {
		this.currentScore = this.client.sendAndWaitMessage("getScore");
		this.score.setText(this.currentScore);
		
		int currentScoreInt = Integer.parseInt(this.currentScore);
		for (int i = 0; i < this.bracketsScores.length; i++) {
			if (currentScoreInt > this.bracketsScores[i]) {
				this.scoreNames[i].setFill(Color.BLACK);
			}
		}
	}
}
