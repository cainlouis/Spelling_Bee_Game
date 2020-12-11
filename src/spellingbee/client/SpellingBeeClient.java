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
 * @author Jose Carlos Betancourt, Sergio Arturo Segrega, Nael Louis
 *
 */
public class SpellingBeeClient extends Application {
	private Client client = new Client();
	
	/**
	 * This method set the stage.
	 */
	public void start(Stage stage) {
		//Create the object that we need
		Group root = new Group();
		TabPane tab = new TabPane();
		GameTab game = new GameTab(client);
		ScoreTab score = new ScoreTab(client);
		//HighScoreTab highScore = new HighScoreTab(client);
		
		//adding them to the tab object
		tab.getTabs().add(game);
		tab.getTabs().add(score);
		//tab.getTabs().add(highScore);
		
		//Setting the tab object before adding it to the root
		tab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		root.getChildren().add(tab);
		
		//Creating the scene
		Scene scene = new Scene(root, 250, 300);
		scene.setFill(Color.LIGHTSKYBLUE);
		
		//Set the scene to the stage
		stage.setScene(scene);
		stage.setTitle("Spelling Bee");
		
		stage.show();
	}
	
	/**
	 * launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
