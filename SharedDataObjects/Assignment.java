package SharedDataObjects;

import java.io.Serializable;
/**
 * Contains all the fields and methods for a Assignment object.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 *
 */
public class Assignment implements Serializable {

	/**
	 * Serial ID for the class.
	 */
	private static final long serialVersionUID = 636521L;
	/**
	 * id of the assignment
	 */
	private int id;
	/*
	 * id of the course the assignement is for
	 */
	private int course_id;
	/**
	 * title of the assignment
	 */
	private String title;
	/**
	 * path to the assignment on the computer.
	 */
	private String path;
	/**
	 * Whether or not the assignment is visible to students or not.
	 */
	private boolean active;
	/**
	 * due date of the assignment.
	 */
	private String due_date;
	/**
	 * Constructor for the class. Automatically sets active to false.
	 * @param id of the course
	 * @param course_id
	 * @param title of the assignment
	 * @param path of the assignment on the system
	 * @param due_date of the assignment
	 */
	public Assignment(int id, int course_id, String title, String path, String due_date)
	{
		this.id = id;
		this.course_id = course_id;
		this.title = title;
		this.path = path;
		this.due_date = due_date;
		active = false;
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

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
}
