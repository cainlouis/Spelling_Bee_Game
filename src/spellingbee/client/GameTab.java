package spellingbee.client;

import spellingbee.network.*;
import spellingbee.server.SpellingBeeGame;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Extends the Tab abstract class and set the GameTab
 * @author Nael Louis
 *
 */
public class GameTab extends Tab {
	private Client client;
	private GameTabSetting event;
	
	/**
	 * Parameterized constructor creates the GameTab
	 * @param client
	 */
	public GameTab(Client client) {
		super("Game"); 
		this.client = client;
		
		//Creating the object to access the getter methods
		//SpellingBeeGame game = new SpellingBeeGame();
		
		//Get letters from the server
		String[] letters = new String[7];
		String response = this.client.sendAndWaitMessage("getAll"); 
		for(int i = 0; i < response.length(); i++) {
			letters[i] = Character.toString(response.charAt(i));
		}
		String centerLet = client.sendAndWaitMessage("getCenter"); 
		
		//Creating the textfield for the user input and styling the border
		TextField userInput = new TextField();
		userInput.setStyle("-fx-border-color: black");
		userInput.setPrefWidth(250);
				
		//Buttons for the letters
		Button[] buttonsLet = new Button[6];
		Button centerBtn = new Button();
		int pos = 0;
		
		//Goes through the array of letter and assign each to a button
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals(centerLet)) {
				centerBtn = new Button(letters[i]);
				//set the background color
				centerBtn.setStyle("-fx-background-color: lavenderblush");
				//create the event that is passed to GameTabSetting
				event = new GameTabSetting(userInput, letters[i], "addText");
				centerBtn.setOnAction(event);
			}
			else {
				buttonsLet[pos] = new Button(letters[i]);
				//set the background color
				buttonsLet[pos].setStyle("-fx-background-color: aliceblue");
				//create the event that is passed to GameTabSetting
				event = new GameTabSetting(userInput, letters[i], "addText");
				buttonsLet[pos].setOnAction(event);
				pos++;
			}
		}
		
		//Create the horizontal box and add the buttons to it
		HBox letBtn = new HBox();
		letBtn.getChildren().addAll(buttonsLet[0], buttonsLet[1], buttonsLet[2], centerBtn, buttonsLet[3], buttonsLet[4], buttonsLet[5]);
		letBtn.setAlignment(Pos.CENTER);
		
		//creating the button that serves a function and added it to the a HBox
		Button submit = new Button("Submit");
		submit.setStyle("-fx-background-color: aliceblue");
		Button clear = new Button("Clear");
		clear.setStyle("-fx-background-color: aliceblue");
		Button delt = new Button("Delete");
		delt.setStyle("-fx-background-color: aliceblue");
		//HBox for the function buttons and add them to it
		HBox functBtn = new HBox();
		functBtn.getChildren().addAll(submit, clear, delt);
		functBtn.setAlignment(Pos.CENTER);
				
		//TextFiel for the answer of the server if the word is valid
		//TextField resp = new TextField("" + game.getMessage(userInput.getText()));
		TextField resp = new TextField("not a word");
		resp.setPrefWidth(125);
		
		//TextField points = new TextField("" + game.getScore());
		TextField points = new TextField("1");
		points.setPrefWidth(125);
		
		//Setting up the event handler on the function buttons
		event = new GameTabSetting(userInput, "submit", resp, points, this.client);
		submit.setOnAction(event);
		event = new GameTabSetting(userInput, "clear");
		clear.setOnAction(event);
		event = new GameTabSetting(userInput, "delete");
		delt.setOnAction(event);
				
		//points.setStyle("-fx-background-color: aliceblue");
		//HBox containing the textfield
		HBox text = new HBox();
		text.getChildren().addAll(resp, points);
		
		//Adding all the element to a Vbox and set the content
		VBox overall = new VBox();
		overall.getChildren().addAll(letBtn, userInput, functBtn, text);
		this.setContent(overall);
	}
	
}
