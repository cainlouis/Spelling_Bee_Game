package spellingbee.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import spellingbee.network.Client;

public class HighScoreHandler implements EventHandler<ActionEvent>  {
	private String event;
	private TextField userInput;
	private Client client;
	
	public HighScoreHandler(String event, Client client) {
		this.event = event;
	}

	@Override
	public void handle(ActionEvent event) {
		
		// TODO Auto-generated method stub
		if (this.event.equals("submit")) {
			String submit = client.sendAndWaitMessage("submit:" + userInput.getText());
		}
		
	}
	
}
