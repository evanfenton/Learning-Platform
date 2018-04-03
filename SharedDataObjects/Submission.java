package SharedDataObjects;

import java.io.Serializable;

/**
 * Contains all the fields and methods for a Submission object.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 *
 */
public class Submission implements Serializable {

	/**
	 * Serial ID for the object
	 */
	private static final long serialVersionUID = 6424999L;
	/*
	 * id of the Submission
	 */
	private int id;
	/**
	 * assignment id of the submission
	 */
	private int assign_id;
	/**
	 * student id of the submission
	 */
	private int student_id;
	/**
	 * path for the submission
	 */
	private String path;
	/**
	 * grade of the submission
	 * automatically set to 0 until marked.
	 */
	private int grade;
	/**
	 * comment on the submission.
	 * null until marked.
	 */
	private String comment;
	/**
	 * title of the submission
	 */
	private String title;
	/**
	 * timestamp of when the submission was handed in.
	 */
	private String timestamp;
	/**
	 * Constructor for the submission
	 * @param id of the submission
	 * @param assign_id
	 * @param student_id
	 * @param path for the submission
	 * @param title of the submission
	 * @param timestamp of submit time.
	 */
	public Submission(int id, int assign_id, int student_id, String path, String title, String timestamp)
	{
		this.id = id;
		this.assign_id = assign_id;
		this.student_id = student_id;
		this.path = path;
		this.title = title;
		this.timestamp = timestamp;
		grade = 0;
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

	public int getAssign_id() {
		return assign_id;
	}

	public void setAssign_id(int assign_id) {
		this.assign_id = assign_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
