package SharedDataObjects;

import java.io.Serializable;

/**
 * Contains all the fields and methods for a StudentEnrollment object.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 *
 */
public class StudentEnrollment implements Serializable {

	/**
	 * Serial ID for the class
	 */
	private static final long serialVersionUID = 321L;
	/**
	 * id for the enrollment
	 */
	private int id;
	/**
	 * id of the student
	 */
	private int student_id;
	/**
	 * id of the course the student wishes to enroll in.
	 */
	private int course_id;
	/**
	 * Whether the student is enrolling or not.
	 */
	private boolean enrolling;
	/**
	 * constructor for the class.
	 * @param id of the enrollment
	 * @param sid student id
	 * @param cid course id
	 * @param enroll
	 */
	public StudentEnrollment(int id, int sid, int cid, boolean enroll)
	{
		this.id = id;
		student_id = sid;
		course_id = cid;
		enrolling = enroll;
	}
	/**
	 * getters and setters for the class
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public boolean isEnrolling() {
		return enrolling;
	}

	public void setEnrolling(boolean enrolling) {
		this.enrolling = enrolling;
	}
	
}
