package spellingbee.client;

import javafx.event.*;
import javafx.scene.control.*;
import spellingbee.network.Client;

public class GameTabSetting  implements EventHandler<ActionEvent> {
	private TextField userInput;
	private String letter;
	private String event;
	private Client client;
	private TextField resp;
	private TextField points;
	
	public GameTabSetting(TextField userInput, String letter, String event) {
		this.userInput = userInput;
		this.letter = letter;
		this.event = event;
	}
	
	public GameTabSetting(TextField userInput, String event) {
		this.userInput = userInput;
		this.event = event;
	}
	
	public GameTabSetting(TextField userInput, String event, TextField resp, TextField points, Client client) {
		this.userInput = userInput;
		this.event = event;
		this.resp = resp;
		this.points = points;
		this.client = client;
	}
	
	@Override
	public void handle(ActionEvent event) {
		if (this.event.equals("addText")) {
			this.userInput.setText(this.userInput.getText() + letter);
		}
		if (this.event.equals("clear")) {
			this.userInput.setText("");
		}
		if (this.event.equals("delete")) {
			String inputText = this.userInput.getText();
			this.userInput.setText(inputText.substring(0, inputText.length() - 1));
		}
		if (this.event.equals("submit")) {
			String submit = client.sendAndWaitMessage("submit:" + this.userInput.getText());
			String[] response = submit.split(":");
			this.resp.setText(response[0]);
			this.points.setText(response[1]);
			this.userInput.setText("");
		}
		
	}

}
