package SharedDataObjects;
/**
* Extension of user used to represent a professor.
* @author Gibson Dziwinski
* @version 1.0
* @since April 3, 2018
*
*/
public class Professor extends User {
	/**
	 * type of user
	 */
	private String type;
	/**
	 * serial ID for the class
	 */
	private static final long serialVersionUID = 23351L;
	/**
	 * Constructor for the professor.
	 * @param id of the professor
	 * @param firstname of the professor
	 * @param lastname of the professor
	 * @param email of the professor
	 * @param password of the professor
	 */
	public Professor(int id, String firstname, String lastname, String email, String password) {
		super(id, firstname, lastname, email, password, "P");
	}
	/**
	 * getters and setters for professor
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
