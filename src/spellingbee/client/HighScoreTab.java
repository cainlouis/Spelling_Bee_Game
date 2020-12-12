package spellingbee.client;

import java.awt.*;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import spellingbee.network.Client;

public class HighScoreTab extends Tab {
	private Client client;
	private HighScoreHandler event;
	
	HighScoreTab(Client client) {
		super("HighScore");
		this.client = client;
		
		
		TextField userName = new TextField();
		
		Button submit = new Button("Submit");
		
		TextArea topTen = new TextArea();
		
		event = new HighScoreHandler(topTen, userName, "submitHighScore", client);
		submit.setOnAction(event);
		
		
		Button refresh = new Button("Refresh");
		
		VBox overall = new VBox();
		overall.getChildren().addAll(userName, submit, topTen, refresh);
		this.setContent(overall);

	}
}
