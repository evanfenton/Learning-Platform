package FrontEnd;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import SharedDataObjects.ServerMessage;
/**
 * Client class. All communication to the server goes through this.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3rd, 2018
 *
 */
public class Client {
	/**
	 * Socket for the client.
	 */
	private Socket socket;
	/**
	 * output stream to the server.
	 */
	private ObjectOutputStream out;
	/**
	 * input stream from the server.
	 */
	private ObjectInputStream in;
	/**
	 * Creates a client that connects to the specified port number.
	 * @param serverName
	 * @param portNumber
	 */
	public Client(String serverName, int portNumber)
	{
		try
		{
			socket = new Socket(serverName, portNumber);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Sends a serverMessage to the server and receives the response.
	 * @param message to send
	 * @return server response.
	 */
	public ServerMessage<?> communicate(ServerMessage<?> message)
	{
		try 
		{
			out.writeObject(message);
			if((message = (ServerMessage<?>) in.readObject()) != null)
			{
				return message;
			}
			return null;
		}
		catch(IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
