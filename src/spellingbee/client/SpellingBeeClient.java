package spellingbee.client;

import spellingbee.network.Client;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class SpellingBeeClient extends Application {
	private Client client = new Client();
	
	public void start(Stage stage) {
		Group root = new Group();
		TabPane tab = new TabPane();
		GameTab game = new GameTab(client);
		VBox overall = new VBox();
		
		tab.getTabs().add(game);
		tab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		overall.getChildren().add(tab);
		root.getChildren().add(overall);
		
		Scene scene = new Scene(root, 650, 300);
		scene.setFill(Color.BLACK);
		
		stage.setScene(scene);
		stage.setTitle("Spelling Bee");
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
