package spellingbee.client;

import spellingbee.network.Client;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;

/**
 * This class create the stage for the GameTab, the ScoreTab, and the HighScore tab. Create a client object which is passed to the tabs. 
 * @author Nael Louis
 *
 */
public class SpellingBeeClient extends Application {
	private Client client = new Client();
	
	public void start(Stage stage) {
		Group root = new Group();
		TabPane tab = new TabPane();
		GameTab game = new GameTab(client);
		//ScoreTab score = new ScoreTab(client);
		//HighScore highScore = new HighScore(Client);
		VBox overall = new VBox();
		
		tab.getTabs().add(game);
		//tab.getTabs().add(score);
		//tab.getTabs.add(highScore);
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
