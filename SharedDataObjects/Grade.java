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
	 * id of the grade.
	 */
	private int id;
	/**
	 * id of the student being graded
	 */
	private int student_id;
	/**
	 * the grade itself
	 */
	private int grade;

	/**
	 * name of the assignment being graded
	 */
	private int  assign_id;
	/**
	 * course id of the assignment
	 */
	private int course_id;
	/**
	 * Constructor for the class
	 * @param id of the student
	 * @param grade on the assignment
	 * @param name of the student
	 * @param assign name
	 */
	public Grade(int id, int grade, int sid, int assign, int course_id)
	{
		this.setId(id);
		this.grade = grade;
		student_id = sid;
		assign_id = assign;
		this.setCourse_id(course_id);
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
	public int getAssign_id() {
		return assign_id;
	}
	public void setAssign_id(int assign_id) {
		this.assign_id = assign_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	

}
