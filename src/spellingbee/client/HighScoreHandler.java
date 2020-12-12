package spellingbee.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spellingbee.network.Client;

public class HighScoreHandler implements EventHandler<ActionEvent>  {
	private String event;
	private TextField userName;
	private TextArea topTen;
	private Client client;
	private String player;
	
	public HighScoreHandler(TextArea topTen, TextField userName, String event, Client client) {
		this.topTen = topTen;
		this.event = event;
		this.userName = userName;
		this.client = client;
	}

	@Override
	public void handle(ActionEvent event) {
		
		// TODO Auto-generated method stub
		if (this.event.equals("submitHighScore")) {
			String score = client.sendAndWaitMessage("submitHighScore:" + userName.getText());
			String[] playerScore = score.split(":");
			score = playerScore[1];
			player = userName.getText();
			this.topTen.setText(topTen.getText() + player + ": " + score + "\n" );
			
		}
		
	}
	
}
