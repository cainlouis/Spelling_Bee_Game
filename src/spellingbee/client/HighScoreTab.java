package spellingbee.client;

import java.awt.*;
import javafx.scene.control.Tab;

import spellingbee.network.Client;

public class HighScoreTab extends Tab {
	private Client client;
	
	HighScoreTab(Client client) {
		super("HighScore");
		this.client = client;
		
		
		TextField userName = new TextField();
		
		Button submit = new Button("Submit");
		
		TextArea topTen = new TextArea();

		Button refresh = new Button("Refresh");

	}
}
