package spellingbee.client;

import spellingbee.network.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GameTab extends Tab {
	private Client client;
	
	public GameTab(Client client) {
		super("Game"); 
		this.client = client;
		
		//Get letters from the server
		//String[] letters = new String[7];
		//String response = client.sendAndWaitMessage("getAllLetters");
		//int pos = 0;
		//for(char let : response) {
			//letters[pos] = let;
			//pos++;
		//}
		//char centerLet = client.sendAndWaitMessage("getCenterLetter");
		
		//Buttons for the letters
		/*Button[] lettersBtn = new Button[7];
		int pos = 0;
		
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals(centerLet)) {
				lettersBtn[4] = new Button(letters[i]);
			}
			else {
				if (pos == 4) {
					pos++;
				}
				lettersBtn[pos] = new Button(letters[i]);
				pos++;
			}
		}*/
		
		
		Button let1 = new Button("u");
		Button let2 = new Button("t");
		Button let3 = new Button("o");
		Button let4 = new Button("n");
		Button let5 = new Button("l");
		Button let6 = new Button("i");
		Button let7 = new Button("s");
		HBox letBtn = new HBox();
		letBtn.getChildren().addAll(let1, let2, let3, let4, let5, let6, let7);
		
		let4.setStyle("-fx-text-fill: red");
		
		//Creating the textfield for the user input and styling the border
		TextField userInput = new TextField();
		userInput.setStyle("-fx-border-color: black");
		
		//creating the button that serves a function and added it to the a HBox
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button delt = new Button("Delete");
		HBox functBtn = new HBox();
		functBtn.getChildren().addAll(submit, clear, delt);
		
		TextField resp = new TextField("meh");
		TextField points = new TextField("1");
		HBox text = new HBox();
		text.getChildren().addAll(resp, points);
		
		VBox overall = new VBox();
		overall.getChildren().addAll(letBtn, userInput, functBtn, text);
		this.setContent(overall);
	}
	
}
