package spellingbee.client;

import javafx.scene.control.Tab;
import spellingbee.network.Client;

public class ScoreTab extends Tab {
	private Client client;
	
	ScoreTab(Client client) {
		super("Score");
		this.client = client;
	}
}
