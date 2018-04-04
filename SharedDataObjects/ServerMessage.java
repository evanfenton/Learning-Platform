package SharedDataObjects;

import java.io.Serializable;
/**
 * Important class that is used by the client to send data to the server.
 * uUing this class makes it very easy for the server to read lots of 
 * different objects without needing multiple inputstreams.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 * 
 * @param <T> The type of object you are looking to send to the server.
 */
public class ServerMessage <T> implements Serializable{

	/**
	 * Serial Version for the Class
	 */
	private static final long serialVersionUID = 1231431L;
	/**
	 * The object you want to send to the server
	 */
	private T object;
	/**
	 * the message the server reads in order to know the 
	 * type of object it is being sent as well as what to do with it.
	 */
	private String message;
	/**
	 * Constructor for the message to server.
	 * @param an object you wish to send.
	 * @param msg you wish to send.
	 */
	public ServerMessage(T obj, String msg)
	{
		setObject(obj);
		setMessage(msg);
	}
	
	/**
	 * getters and setters for the class
	 */
	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
