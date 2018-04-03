package SharedDataObjects;

import java.io.Serializable;
/**
 * Contains all the fields and methods for a Grade object.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 *
 */
public class Grade implements Serializable {

	/**
	 * Serial ID for the class
	 */
	private static final long serialVersionUID = 8743278324L;
	/**
	 * id of the student being graded
	 */
	private int student_id;
	/**
	 * the grade itself
	 */
	private int grade;
	/**
	 * name of the student being graded
	 */
	private String student_name;
	/**
	 * name of the assignment being graded
	 */
	private String assign_name;
	/**
	 * Constructor for the class
	 * @param id of the student
	 * @param grade on the assignment
	 * @param name of the student
	 * @param assign name
	 */
	public Grade(int id, int grade, String name, String assign)
	{
		student_id = id;
		this.grade = grade;
		student_name = name;
		assign_name = assign;
	}
	/**
	 * getters and setters for the class
	 */
	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getAssign_name() {
		return assign_name;
	}

	public void setAssign_name(String assign_name) {
		this.assign_name = assign_name;
	}

}
