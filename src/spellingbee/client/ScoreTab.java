package spellingbee.client;

import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spellingbee.network.Client;

public class ScoreTab extends Tab {
	private Client client;
	private String currentScore;
	private String[] brackets;
	
	ScoreTab(Client client) {
		super("Score");
		this.client = client;
		this.brackets = this.client.sendAndWaitMessage("getBrackets").split(",");
		this.refresh();
		
		Text[] scoreNames = new Text[5];
		
		scoreNames[0] = new Text("Queen Bee");
		scoreNames[1] = new Text("Genius");
		scoreNames[2] = new Text("Amazing");
		scoreNames[3] = new Text("Good");
		scoreNames[4] = new Text("Getting Started");
		
		Text[] scores = new Text[5];
		scores[0] = new Text(this.brackets[4]);
		scores[1] = new Text(this.brackets[3]);
		scores[2] = new Text(this.brackets[2]);
		scores[3] = new Text(this.brackets[1]);
		scores[4] = new Text(this.brackets[0]);
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		
		for (int i = 0; i < 5; i++) {
			scoreNames[i].setFill(Color.GREY);
			gridPane.add(scoreNames[i], i, 0, 1, 1);
			gridPane.add(scores[i], i, 1, 1, 1);
		}
		
		Text scoreText = new Text("Current Score");
		Text score = new Text(this.currentScore);
		gridPane.add(scoreText, 5, 0, 1, 1);
		gridPane.add(score, 5, 1, 1, 1);
		
		this.setContent(gridPane);
	}
	
	public void refresh() {
		this.currentScore = this.client.sendAndWaitMessage("getScore");
	}
}
