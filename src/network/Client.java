package spellingbee.network;

import java.io.*;
import java.net.Socket;

/**
 * This class is used to create a connection with a server. The class is then used to interact with the server.
 * @author Daniel Pomerantz
 *
 */
public class Client {
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	
	// The IP address to connect to the server.
	// -If you want to your current machine (recommended!), use localhost
	// -If you want to connect to another computer within the same home network, use your router's page to locate the
	// IP address of the specific computer. (You can also find this from /ipconfig) This will be an ip address similar to 192.168.1.105
	// -If you want to connect to another computer over the internet, set the IP address to be the IP address of this other computer.
	// The person running the *host* server, will need to set up "port forwarding" on their home network, to forward port 4448 to their local machine
	private final String SERVER_IP = "localhost";
	
	public Client() {
		try {
		socket = new Socket(SERVER_IP, Server.PORT_NUMBER);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * This method takes as input a String, sends the request to the server and returns the response.
	 * @param message The message to send to the server.
	 * @return The response that the server gives back.
	 */
	public String sendAndWaitMessage(String message) {
		System.out.println("CLIENT: Sending message " + message + " to server");
		try {
			out.println(message);
			String result = in.readLine();
			System.out.println("CLIENT: Received message " + result + " from server.");
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
