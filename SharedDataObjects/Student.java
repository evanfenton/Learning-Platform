package SharedDataObjects;
/**
 * Extension of user used to represent a professor.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 */
public class Student extends User{
	/**
	 * type of user
	 */
	private String type;
	/**
	 * Serial ID for the class.
	 */
	private static final long serialVersionUID = 23351L;
	/**
	 * constructor for the Student
	 * @param id of the student
	 * @param firstname of the student 
	 * @param lastname of the student
	 * @param email of the student
	 * @param students system password
	 */
	public Student(int id, String firstname, String lastname, String email, String password) {
		super(id, firstname, lastname, email, password);
		setType("S");
	}
	/**
	 * getters and setters for the student
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
