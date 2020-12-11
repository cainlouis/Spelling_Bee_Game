package spellingbee.client;

import java.awt.*;
import javafx.scene.control.Tab;
import javafx.scene.control.*;
import spellingbee.network.Client;

public class HighScoreTab extends Tab {
	private Client client;
	private HighScoreHandler event;
	
	HighScoreTab(Client client) {
		super("HighScore");
		this.client = client;
		
		
		TextField userName = new TextField();
		
		Button submit = new Button("Submit");
		event = new HighScoreHandler("submit", client);
		//submit.setOnAction(event);
		
		TextArea topTen = new TextArea();

		Button refresh = new Button("Refresh");

	}
}
