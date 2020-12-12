package spellingbee.network;

import spellingbee.client.SimpleSpellingBeeGame;
import spellingbee.server.*;

/**
 * This class will run on the server side and is used to connect the server code to the backend business code.
 * This class is where the "protocol" will be defined.
 */
public class ServerController {
	// This is the interface you will be creating!
	// Since we are storing the object as an interface, you can use any type of ISpellingBeeGame object.
	// This means you can work with either the SpellingBeeGame OR SimpleSpellingBeeGame objects and can
	// seamlessly change between the two.
	private ISpellingBeeGame spellingBee = new SpellingBeeGame();
	
	/**
	 * Action is the method where the protocol translation takes place.
	 * This method is called every single time that the client sends a request to the server.
	 * It takes as input a String which is the request coming from the client. 
	 * It then does some actions on the server (using the ISpellingBeeGame object)
	 * and returns a String representing the message to send to the client
	 * @param inputLine The String from the client
	 * @return The String to return to the client
	 */
	public String action(String inputLine) {
		/* Your code goes here!!!!
		 * Note: If you want to preserve information between method calls, then you MUST
		 * store it into private fields.
		 * You should use the spellingBee object to make various calls and here is where your communication
		 * code/protocol should go.
		 * For example, based on the samples in the assignment:*/
		 if (inputLine.equals("getCenter")) {
		 // client has requested getCenter. Call the getCenter method which returns a String of 7 letters
			 return "" + spellingBee.getCenterLetter();
		 }
		 else if (inputLine.equals("getAll")) {
			 return spellingBee.getAllLetters();
		 }
		 else if (inputLine.contains("submit")) {
			 String[] fromGame = inputLine.split(":");
			 return spellingBee.getMessage(fromGame[1]) + ":" + spellingBee.getScore();
		 } else if (inputLine.contains("getBrackets")) {
			 String bracketString = "";
			 int[] brackets = spellingBee.getBrackets();
			 for (int i = 0; i < brackets.length; i++) {
				 bracketString += brackets[i] + ",";
			 }
			 return bracketString;
		 } else if (inputLine.contains("getScore")) {
			 return Integer.toString(spellingBee.getScore());
		 }
		 //Change the inputLine
		 /*else if (inputLine.equals()) {
			 String[] highScore = inputLine.split(":");
			 return ""+ highScore[1] + ":" + spellingBee.getScore();
		 } */
		return null;
	}
}
