package SharedDataObjects;

import java.io.Serializable;

/**
 * Class that stores the login information for users.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since
 */
public class LoginInfo implements Serializable{
	/**
	 * Serial ID for the class
	 */
	private static final long serialVersionUID = 985987L;
	/**
	 * username of the logininfo
	 */
	private String username;
	/**
	 * password of the logininfo
	 */
	private String password;
	/**
	 * Constructor of the login info
	 * @param user
	 * @param password
	 */
	public LoginInfo(String user, String password)
	{
		username = user;
		this.password = password;
	}
	/**
	 * Getters and setters
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
