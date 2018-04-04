package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import SharedDataObjects.*;


/**
 * Worker issued to each client connected to the server.
 * is able to read server messages from client and acts 
 * accordingly.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3rd, 2018
 *
 */
public class Worker implements Runnable {
	/**
	 * input stream of ServerMessage Objects
	 */
	private ObjectInputStream in;
	/**
	 * output stream of ServerMessage Objects
	 */
	private ObjectOutputStream out;
	/**
	 * the server file manager
	 */
	private FileHelper filemanager;
	/**
	 * the servers database
	 */
	private DatabaseHelper database;
	/**
	 * the servers email service
	 */
	private EmailHelper emailservice;
	/**
	 * Creates a worker by receiving the socket and accessing their IO streams as well
	 * as the servers database, file manager, and email service.
	 * @param socket for client server communication
	 * @param database of the server
	 * @param file manager of the server
	 * @param email service of the server
	 */
	public Worker(Socket socket, DatabaseHelper database, FileHelper file, EmailHelper email)
	{
		try
		{
			in =  new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			this.database = database;
			filemanager = file;
			emailservice = email;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * When running the worker will wait for a message from the client.
	 * It will then determine the object and message that the client sent
	 * and will act accordingly. 
	 */
	@Override
	public void run() {
		try 
		{
			ServerMessage<?> message = (ServerMessage<?>) in.readObject();
			//Example code to launch a DatabaseHelper function.
			if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("Add"))
			{
				database.addCourse((Course) message.getObject());
			}
			/**
			 * code for loging onto the system.
			 */
			if(message.getObject().getClass().toString().contains("LoginInfo") && message.getMessage().equals("Login"))
			{
				LoginInfo info = (LoginInfo) message.getObject();
				User user = database.LoginUser(info);
				System.out.println(user.getLogininfo().getPassword()+ " != " + info.getPassword());
				if(user.getLogininfo().getPassword().equals(info.getPassword()))
				{
					ServerMessage<User> returnmessage = new ServerMessage<User>(user, "");
					out.writeObject(returnmessage);
				}
				else
				{
					ServerMessage <User> returnmessage = new ServerMessage<User>(null,"");
					out.writeObject(returnmessage);
				}
			}
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
