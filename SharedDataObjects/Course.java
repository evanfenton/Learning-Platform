package SharedDataObjects;

import java.io.Serializable;
/**
 * Contains all the fields and methods for a course object.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 *
 */
public class Course implements Serializable{

	/**
	 * Serial ID for the class
	 */
	private static final long serialVersionUID = 1235412L;
	/**
	 * ID of the course
	 */
	private int id;
	/**
	 * ID of the prof who is teaching the course
	 */
	private int prof_id;
	/**
	 * name of the course
	 */
	private String name;
	/**
	 * boolean to tell wether the course is active or not.
	 */
	private boolean active;
	/**
	 * constructor for the course
	 * automatically sets active to false and must be activated manually
	 * by the prof.
	 * @param id of the course
	 * @param prof_id of the prof
	 * @param name of the course
	 */
	public Course(int id, int prof_id, String name)
	{
		this.id = id;
		this.prof_id = prof_id;
		this.name = name;
		active = false;
	}
	
	public Course(int id, int prof_id, String name, boolean active) {
		this.id = id;
		this.prof_id = prof_id;
		this.name = name;
		this.active = active;
	}

	public String toString()
	{
		if(active)
		{
			return id + " " + name;  
		}
		else
		{
			return id + " " + name + " InActive";  
		}
	}
	
	/**
	 * getters and setters for the class.
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProf_id() {
		return prof_id;
	}

	public void setProf_id(int prof_id) {
		this.prof_id = prof_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
