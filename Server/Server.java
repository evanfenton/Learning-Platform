package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class for the server of the learning platform.
 * Creates a worker for each client that will manipulate the
 * database, file manager, and email service.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3rd, 2018
 *
 */
public class Server {
	/**
	 * Socket for the server
	 */
	private ServerSocket serverSocket;
	/**
	 * thread pool.
	 */
	private ExecutorService pool;
	/**
	 * file manager that is passed to the worker.
	 */
	private FileHelper filemanager;
	/**
	 * database that is passed to the worker.
	 */
	private DatabaseHelper database;
	/**
	 * email service that is passed to the worker.
	 */
	private EmailHelper emailservice;
	/**
	 * connects the server to the port and initializes the thread pool.
	 * Creates all its fields.
	 * @param portNumber
	 */
	public Server(int portNumber)
	{
		try
		{
			serverSocket = new ServerSocket(portNumber);
			pool = Executors.newCachedThreadPool();
			filemanager = new FileHelper();
			database = new DatabaseHelper();
			emailservice = new EmailHelper();
			System.out.println("Server Started...");
		}
		catch(IOException e)
		{
			System.out.println("Create new socket error");
			System.out.println(e.getMessage());
		}
	}
	/**
	 * waits for clients to connect to the server
	 * then issues them a worker.
	 */
	public void run()
	{
		try 
		{
			while(true)
			{
				pool.execute(new Worker(serverSocket.accept(), 
						database, filemanager, emailservice));
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			pool.shutdown();
		}
	}
	
	/**
	 * server startup
	 */
	public static void main(String[] args) {
		Server server = new Server(9091);
		server.run();

	}

}
