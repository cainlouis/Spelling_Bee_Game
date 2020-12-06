package spellingbee.network;
import java.net.*;

import java.io.*;

/**
 * A class created to handle the Server code. This code uses the ServerController code.
 * @author Daniel Pomerantz
 *
 */
public class Server {
	
	/**
	 * The port number that the server will run on. In the (unlikely) event that this port is already in use, you should change this to a different number.
	 * Note that the port *will* be in use if you don't terminate the server program (by hitting the stop button in Eclipse!)
	 */
	public static final int PORT_NUMBER = 4448;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Starting the server");
		try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
		
		// Continuous loop to accept new connections.
		// Whenever a connection is made, spawn a new thread to wait for the results.
		while (true) {
			System.out.println("SERVER: Waiting for connections!");
			Socket client1Socket = serverSocket.accept();
			System.out.println("SERVER: Accepted connection.");

			
			Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
						BufferedReader in1 = new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));
						PrintWriter out1 = new PrintWriter(client1Socket.getOutputStream(), true);
						
						// Here is the server controller code!!!
						ServerController controller = new ServerController();
						// 	This thread should wait and get information
						
						while (true) {
							String inputLine = in1.readLine();
							System.out.println("SERVER: Received " + inputLine + " from a client.");
							// Here is where your code comes into play. This code where the server code will translate things.
							String result = controller.action(inputLine);
							System.out.println("SERVER: Sending " + result + " to a client");
							out1.println(result);
						}
					}
					catch (IOException e) {
						System.out.println("Error with connections. Did a client close the connection? (If so, then this error is expected)");
						e.printStackTrace();
					}
				}
			
			});
			thread.start();
		}
	}
	}
}
