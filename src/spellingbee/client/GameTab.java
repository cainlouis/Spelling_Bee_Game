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
		char[] letters = new char[7];
		String response = this.client.sendAndWaitMessage("getAll"); 
		for(int i = 0; i < response.length(); i++) {
			letters[i] = response.charAt(i);
		}
		//String centerLet = client.sendAndWaitMessage("getCenter"); 
		/*
		//Buttons for the letters
		 Button let1;
		 Button let2;
		 Button let3;
		 Button centerBtn;
		 Button let5;
		 Button let6;
		 Button let7;
		 Button[] buttonsLet = {let1, let2, let3, let5, let6, let7};
		int pos = 0;
		
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals(centerLet)) {
				centerBtn = new Button(letters[i]);
			}
			else {
				buttonsLet[pos] = new Button(letters[i]);
				pos++;
			}
		}*/
		
		//Create the buttons
		Button let1 = new Button("u");
		Button let2 = new Button("t");
		Button let3 = new Button("o");
		Button centerBtn = new Button("n");
		Button let5 = new Button("l");
		Button let6 = new Button("i");
		Button let7 = new Button("s");
		//Create the horizontal box and add the buttons to it
		HBox letBtn = new HBox();
		letBtn.getChildren().addAll(let1, let2, let3, centerBtn, let5, let6, let7);
		
		centerBtn.setStyle("-fx-text-fill: red");
		
		//Creating the textfield for the user input and styling the border
		TextField userInput = new TextField();
		userInput.setStyle("-fx-border-color: black");
		
		//creating the button that serves a function and added it to the a HBox
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button delt = new Button("Delete");
		//HBox for the function buttons and add them to it
		HBox functBtn = new HBox();
		functBtn.getChildren().addAll(submit, clear, delt);
		
		//TextFiel for the answer of the server if the word is valid
		TextField resp = new TextField("meh");
		TextField points = new TextField("1");
		//HBox containing the textfield
		HBox text = new HBox();
		text.getChildren().addAll(resp, points);
		
		//Adding all the element to a Vbox and set the content
		VBox overall = new VBox();
		overall.getChildren().addAll(letBtn, userInput, functBtn, text);
		this.setContent(overall);
	}
	
}
