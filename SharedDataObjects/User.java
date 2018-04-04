package SharedDataObjects;

import java.io.Serializable;
/**
 * Class for the user who is using the program.
 * Professor and Student inherit this class.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 */
public class User implements Serializable{
	
	/**
	 * Unique serial ID for the object.
	 */
	private static final long serialVersionUID = 23351L;
	/**
	 * Id of the user
	 */
	private int id;
	/**
	 * firstname of the user
	 */
	private String firstname;
	/**
	 * lastname of the user
	 */
	private String lastname;
	/**
	 * login info for the user.
	 */
	private LoginInfo logininfo;
	/**
	 * email address of the user
	 */
	private String email;

	/**
	 * Constructor for the User class.
	 * @param id of the user
	 * @param firstname of the user
	 * @param lastname of the user
	 * @param email of the user
	 * @param password of the user
	 */
	public User(int id, String firstname, String lastname, String email, String password )
	{
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		setEmail(email);
		setLogininfo(new LoginInfo(email, password));
	}
	
	
	/**
	 * Getters and setters for the data fields.
	 */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
	this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LoginInfo getLogininfo() {
		return logininfo;
	}


	public void setLogininfo(LoginInfo logininfo) {
		this.logininfo = logininfo;
	}
	
}
